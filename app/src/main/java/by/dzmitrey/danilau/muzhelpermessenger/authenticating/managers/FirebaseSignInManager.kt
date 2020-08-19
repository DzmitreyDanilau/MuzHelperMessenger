package by.dzmitrey.danilau.muzhelpermessenger.authenticating.managers

import android.content.IntentSender
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import timber.log.Timber

@Suppress("JAVA_CLASS_ON_COMPANION")
class FirebaseSignInManager(private val context: FragmentActivity) {

    companion object {
        private val TAG = FirebaseSignInManager.javaClass.name
        private const val DEFAULT_SERVER_SIDE_CLIENT_ID = "id"
        const val RESULT_CODE_ONE_TAP_AUTH = 1234
        const val RESULT_CODE_GOOGLE_SIGN_IN = 2468
    }

    private lateinit var oneTapClient: SignInClient
    private lateinit var signInRequest: BeginSignInRequest
    private var firebaseAuth: FirebaseAuth = Firebase.auth
    var userDeclineOneTap = false

    fun startSignInt() {
        oneTapClient = Identity.getSignInClient(context)
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(DEFAULT_SERVER_SIDE_CLIENT_ID)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()
    }

    fun isUserSignIn() {
        val user = firebaseAuth.currentUser
        updateUI(user)
    }

    fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            //some login for next step
        } else {
            if (!userDeclineOneTap) {
                oneTapClient.beginSignIn(signInRequest).addOnSuccessListener {
                    // This listener will be triggered if the
                    // user does have saved credentials
                    try {
                        context.startIntentSenderForResult(
                            it.pendingIntent.intentSender, RESULT_CODE_ONE_TAP_AUTH, null, 0, 0, 0, null
                        )
                    } catch (exception: IntentSender.SendIntentException) {
                        Timber.e(TAG, "Couldn't start One Tap UI: ${exception.localizedMessage}")
                    }
                }.addOnFailureListener { error ->
                    // No saved credentials found. Launch the One Tap sign-up flow, or
                    // do nothing and continue presenting the signed-out UI.
                    Timber.e(TAG, "Couldn't start One Tap UI: ${error.localizedMessage}")
                }
            }
        }
    }

    fun firebaseAuthWithGoogle(anchorView: View, idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Sign in success, update UI with the signed-in user's information
                Timber.d(TAG, "signInWithCredential:success")
                val user = firebaseAuth.currentUser
                updateUI(user)
            } else {
                // If sign in fails, display a message to the user.
                Timber.w(task.exception, TAG, "signInWithCredential:failure")
                Snackbar.make(anchorView, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                updateUI(null)
            }
        }
    }
}
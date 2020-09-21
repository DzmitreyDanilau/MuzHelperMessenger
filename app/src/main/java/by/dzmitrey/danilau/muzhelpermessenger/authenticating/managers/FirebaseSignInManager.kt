package by.dzmitrey.danilau.muzhelpermessenger.authenticating.managers

import android.content.Intent
import android.content.IntentSender
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import timber.log.Timber
import javax.inject.Inject

@Suppress("JAVA_CLASS_ON_COMPANION")
class FirebaseSignInManager @Inject constructor() {

    companion object {
        private val TAG = FirebaseSignInManager.javaClass.name
        const val RC_ONE_TAP_AUTH = 1234
        const val RC_GOOGLE_SIGN_IN = 2468
    }

    lateinit var oneTapClient: SignInClient
    lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var signInRequest: BeginSignInRequest
    private var firebaseAuth: FirebaseAuth = Firebase.auth
    var userDeclineOneTap = false

    fun startSignIn(context: FragmentActivity): BeginSignInRequest {
        oneTapClient = Identity.getSignInClient(context)
        signInRequest = BeginSignInRequest.builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setServerClientId("id")
                    .setSupported(true)
                    .setFilterByAuthorizedAccounts(false)
                    .build()
            )
            .build()

        return signInRequest
    }

    fun isUserSignIn(context: FragmentActivity) {
        val user = firebaseAuth.currentUser
        updateUI(user, context)
    }

    fun configureGoogleSignInClient(context: FragmentActivity) {
        val googleSingInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(context, googleSingInOptions)
    }

    private fun updateUI(user: FirebaseUser?, context: FragmentActivity) {
        if (user != null) {

        } else {
            if (!userDeclineOneTap) {
                oneTapClient.beginSignIn(signInRequest).addOnSuccessListener {
                    // This listener will be triggered if the
                    // user does have saved credentials
                    try {
                        context.startIntentSenderForResult(
                            it.pendingIntent.intentSender,
                            RC_ONE_TAP_AUTH,
                            null,
                            0,
                            0, 0,
                            null
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

    fun handleGoogleSignInResult(data: Intent?, context: FragmentActivity) {
        val task = GoogleSignIn.getSignedInAccountFromIntent(data)
        try {
            // Google Sign In was successful, authenticate with Firebase
            val account = task.getResult(ApiException::class.java)
            Timber.d(TAG, "firebaseAuthWithGoogle:%s", account)
            authWithGoogle(account, context)
        } catch (e: ApiException) {
            // Google Sign In failed, update UI appropriately
            Timber.d(TAG, "Google sign in failed", e.message)
            // ...
        }
    }

    fun handleFirebaseOneTap(data: Intent?) {
        try {
            val credential = oneTapClient.getSignInCredentialFromIntent(data)
            // This credential contains a googleIdToken which
            // we can use to authenticate with FirebaseAuth
//            credential.googleIdToken?.let {
//                        firebaseAuthManager.firebaseAuthWithGoogle()
//            }
        } catch (e: ApiException) {
            when (e.statusCode) {
                CommonStatusCodes.CANCELED -> {
                    // The user closed the dialog
                    userDeclineOneTap = true
                }
                CommonStatusCodes.NETWORK_ERROR -> {
                    // No Internet connection ?
                }
                else -> {
                    // Some other error
                }
            }
        }
    }

    private fun authWithGoogle(account: GoogleSignInAccount?, context: FragmentActivity) {
        val credential = GoogleAuthProvider.getCredential(account?.idToken, null)
        firebaseAuth.signInWithCredential(credential)
            .addOnCompleteListener(context) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Timber.d(TAG, "signInWithCredential:success")
                    updateUI(firebaseAuth.currentUser, context)
                } else {
                    // If sign in fails, display a message to the user.
                    Timber.d(task.exception, TAG, "signInWithCredential:failure")
                    // ...
//                    Snackbar.make(view, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                    updateUI(null, context)
                }
            }
    }
}
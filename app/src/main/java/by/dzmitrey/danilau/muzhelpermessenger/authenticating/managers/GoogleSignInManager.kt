package by.dzmitrey.danilau.muzhelpermessenger.authenticating.managers

import android.content.Context
import android.content.Intent
import by.dzmitrey.danilau.muzhelpermessenger.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import javax.inject.Inject

@Suppress("JAVA_CLASS_ON_COMPANION")
class GoogleSignInManager @Inject constructor(private val context: Context) {

    companion object {
        private val TAG = GoogleSignInManager.javaClass.name
        const val RC_GOOGLE_SIGN_IN = 2468
    }

    fun getGoogleCredentials(data: Intent?): AuthCredential {
        val account = getGoogleSignInAccount(data)
        return GoogleAuthProvider.getCredential(account?.idToken, null)
    }

    fun configureSignInClient(): GoogleSignInClient {
        val options = configureSignInOptions()
        return GoogleSignIn.getClient(context, options)
    }

    private fun getGoogleSignInAccount(data: Intent?): GoogleSignInAccount? {
        return GoogleSignIn.getSignedInAccountFromIntent(data).result
    }

    private fun configureSignInOptions(): GoogleSignInOptions {
        return GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(context.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
    }
}
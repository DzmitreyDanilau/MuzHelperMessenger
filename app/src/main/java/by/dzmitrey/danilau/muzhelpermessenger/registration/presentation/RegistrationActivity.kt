package by.dzmitrey.danilau.muzhelpermessenger.registration.presentation

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.registration.presentation.signIn.FirebaseSignInManager
import by.dzmitrey.danilau.muzhelpermessenger.registration.presentation.signIn.FirebaseSignInManager.Companion.RESULT_CODE_GOOGLE_SIGN_IN
import by.dzmitrey.danilau.muzhelpermessenger.registration.presentation.signIn.FirebaseSignInManager.Companion.RESULT_CODE_ONE_TAP_AUTH
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.CommonStatusCodes
import kotlinx.android.synthetic.main.content_chat.*

class RegistrationActivity : AppCompatActivity() {

    lateinit var firebaseAuthManager: FirebaseSignInManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        firebaseAuthManager = FirebaseSignInManager(context = this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RESULT_CODE_GOOGLE_SIGN_IN -> {/*handle google sign in*/
            }
            RESULT_CODE_ONE_TAP_AUTH -> {
                try {
                    firebaseAuthManager.oneTapClient.getSignInCredentialFromIntent(data).apply {
                        googleIdToken?.let { token -> firebaseAuthManager.firebaseAuthWithGoogle(fragment_container, token) }
                    }
                } catch (exception: ApiException) {
                    when (exception.statusCode) {
                        CommonStatusCodes.CANCELED -> {
                            // The user closed the dialog
                            firebaseAuthManager.userDeclineOneTap = true
                        }
                        CommonStatusCodes.NETWORK_ERROR -> {
                            // No Internet connection
                        }
                        else -> {
                            // Some other error
                        }
                    }
                }
            }
        }
    }
}

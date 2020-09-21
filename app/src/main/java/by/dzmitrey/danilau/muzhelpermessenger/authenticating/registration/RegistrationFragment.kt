package by.dzmitrey.danilau.muzhelpermessenger.authenticating.registration

import android.content.Intent
import android.os.Bundle
import android.view.View
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.AuthActivity
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.managers.FirebaseSignInManager
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.managers.FirebaseSignInManager.Companion.RC_GOOGLE_SIGN_IN
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.managers.FirebaseSignInManager.Companion.RC_ONE_TAP_AUTH
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseFragment
import by.dzmitrey.danilau.muzhelpermessenger.databinding.FragmentRegistrationBinding
import kotlinx.android.synthetic.main.fragment_sign_in.*
import javax.inject.Inject

class RegistrationFragment : BaseFragment<RegistrationViewModel, FragmentRegistrationBinding>() {

    @Inject
    lateinit var firebaseAuthManager: FirebaseSignInManager

    override val viewModel: RegistrationViewModel by lazyViewModel()

    override fun getLayoutId() = R.layout.fragment_registration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        firebaseAuthManager.startSignIn(requireActivity())
        firebaseAuthManager.isUserSignIn(requireActivity())
        initClickListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
            RC_GOOGLE_SIGN_IN -> firebaseAuthManager.handleGoogleSignInResult(data, requireActivity())
            // Result returned from launching the Intent from startIntentSenderForResult(...)
            RC_ONE_TAP_AUTH -> firebaseAuthManager.handleFirebaseOneTap(data)
        }
    }

    private fun initClickListeners() {
        google_sign_in_button.setOnClickListener {
            firebaseAuthManager.configureGoogleSignInClient(requireActivity())
            signInWithGoogle()
        }
    }

    private fun signInWithGoogle() {
        startActivityForResult(
            firebaseAuthManager.googleSignInClient.signInIntent,
            RC_GOOGLE_SIGN_IN
        )
    }

    private fun performDI() {
        (activity as AuthActivity).registrationComponent.inject(this)
    }
}
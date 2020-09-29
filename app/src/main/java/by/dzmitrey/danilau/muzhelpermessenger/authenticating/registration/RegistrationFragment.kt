package by.dzmitrey.danilau.muzhelpermessenger.authenticating.registration

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.AuthActivity
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.CredentialsEntity
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.FirebaseAuthResult
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.managers.GoogleSignInManager.Companion.RC_GOOGLE_SIGN_IN
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseFragment
import by.dzmitrey.danilau.muzhelpermessenger.databinding.FragmentRegistrationBinding
import by.dzmitrey.danilau.muzhelpermessenger.utils.Navigator
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import kotlinx.android.synthetic.main.fragment_registration.*
import javax.inject.Inject

class RegistrationFragment : BaseFragment<RegistrationViewModel, FragmentRegistrationBinding>() {

    @Inject
    lateinit var navigator: Navigator

    override val viewModel: RegistrationViewModel by lazyViewModel()

    override fun getLayoutId() = R.layout.fragment_registration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initClickListeners()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            RC_GOOGLE_SIGN_IN -> googleAuthForFirebase(data)
        }
    }

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentRegistrationBinding.inflate(inflater, container, false)

    private fun initClickListeners() {
        registerWithEmailBtn.setOnClickListener {
            registerUserWithEmail()
        }

        signInWithGoogleBtn.setOnClickListener {
            prepareSignInOptions()
        }
    }

    private fun registerUserWithEmail() {
        val email = email.text.toString()
        val password = password.text.toString()
        if (email.isNotEmpty() && password.isNotEmpty()) {
            viewModel.registerWithEmailAndPassword(CredentialsEntity(email, password))
        }
    }

    private fun observeViewModel() {
        viewModel.getGoogleSignInOptions().observe(viewLifecycleOwner) {
            signInWithGoogle(it)
        }

        viewModel.getFirebaseAuthResult().observe(viewLifecycleOwner) {
            when (it) {
                is FirebaseAuthResult.Success -> navigator.navigateHome(requireActivity())
                is FirebaseAuthResult.Failed -> Toast.makeText(requireActivity(), "Result: Failed", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun prepareSignInOptions() {
        viewModel.getGoogleSignInClient()
    }

    private fun signInWithGoogle(signInClient: GoogleSignInClient) {
        viewModel.getGoogleSignInOptions()
        signInClient.signInIntent.also {
            startActivityForResult(it, RC_GOOGLE_SIGN_IN)
        }
    }

    private fun googleAuthForFirebase(data: Intent?) {
        viewModel.googleAuthForFirebase(data)
    }

    private fun performDI() {
        (activity as AuthActivity).registrationComponent.inject(this)
    }
}
package by.dzmitrey.danilau.muzhelpermessenger.authenticating.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.AuthActivity
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.CredentialsEntity
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.FirebaseAuthResult
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseFragment
import by.dzmitrey.danilau.muzhelpermessenger.databinding.FragmentLogInBinding
import by.dzmitrey.danilau.muzhelpermessenger.extensions.EMPTY
import by.dzmitrey.danilau.muzhelpermessenger.utils.Navigator
import by.dzmitrey.danilau.muzhelpermessenger.utils.getText
import javax.inject.Inject

class LoginFragment : BaseFragment<LoginViewModel, FragmentLogInBinding>() {

    @Inject
    lateinit var navigator: Navigator

    override val viewModel: LoginViewModel by lazyViewModel()

    override fun getLayoutId() = R.layout.fragment_log_in

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?, attachToParent: Boolean): FragmentLogInBinding {
        return FragmentLogInBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        initClickListeners()
    }

    private fun initClickListeners() {
        binding?.logInBtn?.setOnClickListener {
            logIn()
        }
    }

    private fun observeViewModel() {
        viewModel.getLoginResult().observe(viewLifecycleOwner, {
            when (it) {
                is FirebaseAuthResult.Success -> {
                    navigator.navigateHome(requireActivity())
                }
                is FirebaseAuthResult.Failed -> (activity as? AuthActivity)?.showMessage(it.error!!)
            }
        })
    }

    override fun performDI() {
        (activity as AuthActivity).registrationComponent.inject(this)
    }

    private fun logIn() {
        val email = binding?.email?.getText() ?: String.EMPTY
        val password = binding?.password?.getText() ?: String.EMPTY
        viewModel.loginWithEmailAndPassword(CredentialsEntity(email, password))
    }
}
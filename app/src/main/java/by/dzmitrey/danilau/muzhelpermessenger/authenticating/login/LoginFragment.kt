package by.dzmitrey.danilau.muzhelpermessenger.authenticating.login

import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseFragment
import by.dzmitrey.danilau.muzhelpermessenger.databinding.FragmentSignInBinding

class LoginFragment : BaseFragment<LoginViewModel, FragmentSignInBinding>() {

    override val viewModel: LoginViewModel by lazyViewModel()

    override fun getLayoutId() = R.layout.fragment_sign_in
}
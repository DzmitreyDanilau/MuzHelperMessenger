package by.dzmitrey.danilau.muzhelpermessenger.authenticating.login

import android.view.LayoutInflater
import android.view.ViewGroup
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseFragment
import by.dzmitrey.danilau.muzhelpermessenger.databinding.FragmentLogInBinding

class LoginFragment : BaseFragment<LoginViewModel, FragmentLogInBinding>() {

    override val viewModel: LoginViewModel by lazyViewModel()

    override fun getLayoutId() = R.layout.fragment_log_in

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentLogInBinding.inflate(inflater, container, false)
}
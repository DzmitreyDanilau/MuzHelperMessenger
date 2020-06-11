package by.dzmitrey.danilau.muzhelpermessenger.account.registration.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.account.registration.domain.RegisterEntity
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseFragment
import by.dzmitrey.danilau.muzhelpermessenger.home.presentation.HomeActivity
import kotlinx.android.synthetic.main.fragment_registration.*

class RegistrationFragment : BaseFragment<RegistrationViewModel>() {

    override val fragmentResId = R.layout.fragment_registration

    override fun getViewModelClass() = RegistrationViewModel::class

    override fun onAttach(context: Context) {
        super.onAttach(context)
        performDI()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }

    private fun confirmRegistration() {
        viewModel.register(createRegistrationEntity())
    }


    private fun observeViewModel() {
        viewModel.getIsRegistrationFinished().observe(this, Observer {
            when (it) {
                is RegistrationViewModel.UIState.Data -> {
                    hideProgress()
                    navigateToHomeActivity()
                }
                is RegistrationViewModel.UIState.Error -> {
                    hideProgress()
                    showMessage(it.error!!)
                }
                is RegistrationViewModel.UIState.Progress -> {
                    showProgress()
                }
            }
            navigateToHomeActivity()
        })
    }

    private fun initClickListeners() {
        confirmRegistrationBtn.setOnClickListener {
            confirmRegistration()
        }
    }

    private fun performDI() {
        (requireActivity() as RegistrationActivity).registrationComponent.inject(this)
    }

    private fun navigateToHomeActivity() {
        (requireActivity() as RegistrationActivity).navigator.navigateToHomeActivity(
            Intent(
                requireContext(),
                HomeActivity::class.java
            )
        )
    }

    private fun createRegistrationEntity(): RegisterEntity {
        val email = textInputEmail.editText?.text.toString()
        val userName = textInputUsername.editText?.text.toString()
        val password = textInputPassword.editText?.text.toString()
        return RegisterEntity(userName, email, password)
    }
}
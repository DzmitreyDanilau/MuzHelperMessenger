package by.dzmitrey.danilau.muzhelpermessenger.account.registration.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import by.dzmitrey.danilau.muzhelpermessenger.R
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

    private fun initClickListeners() {
        confirmRegistrationBtn.setOnClickListener {
            navigateToHomeActivity()
        }
    }

    private fun performDI() {
        (requireActivity() as RegistrationActivity).registrationComponent.inject(this)
    }

    private fun navigateToHomeActivity() {
        (requireActivity() as RegistrationActivity).navigator.navigateToHomeActivity(Intent(requireContext(), HomeActivity::class.java))
    }
}

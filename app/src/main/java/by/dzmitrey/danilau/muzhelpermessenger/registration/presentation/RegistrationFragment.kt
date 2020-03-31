package by.dzmitrey.danilau.muzhelpermessenger.registration.presentation

import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseFragment
import kotlin.reflect.KClass

class RegistrationFragment : BaseFragment<RegistrationViewModel>() {

    companion object {
        @JvmStatic
        fun newInstance() = RegistrationFragment()
    }

    override val fragmentResId = R.layout.fragment_registration

    override fun getViewModelClass(): KClass<RegistrationViewModel> {
        TODO("Not yet implemented")
    }
}

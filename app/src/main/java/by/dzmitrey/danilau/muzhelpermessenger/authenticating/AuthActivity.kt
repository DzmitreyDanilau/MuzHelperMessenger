package by.dzmitrey.danilau.muzhelpermessenger.authenticating

import android.os.Bundle
import by.dzmitrey.danilau.muzhelpermessenger.ChatModuleApp
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseActivity
import by.dzmitrey.danilau.muzhelpermessenger.di.components.RegistrationComponent
import by.dzmitrey.danilau.muzhelpermessenger.utils.Navigator
import javax.inject.Inject

class AuthActivity : BaseActivity() {

    @Inject
    lateinit var navigator: Navigator

    lateinit var registrationComponent: RegistrationComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

    }

    override val layoutResId = R.layout.activity_auth

    private fun performDI() {
        registrationComponent = (application as ChatModuleApp).appComponent.registrationComponent().create()
        registrationComponent.inject(this)
    }

    private fun navigateToHomeActivity() {
        navigator.navigateHome(this)
    }
}
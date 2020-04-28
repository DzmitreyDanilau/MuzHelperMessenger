package by.dzmitrey.danilau.muzhelpermessenger.account.registration.presentation

import android.content.Intent
import android.os.Bundle
import by.dzmitrey.danilau.muzhelpermessenger.ChatModuleApp
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseActivity
import by.dzmitrey.danilau.muzhelpermessenger.di.components.RegistrationComponent
import by.dzmitrey.danilau.muzhelpermessenger.utils.Navigator
import javax.inject.Inject

class RegistrationActivity : BaseActivity<RegistrationViewModel>() {

    @Inject
    lateinit var navigator: Navigator

    lateinit var registrationComponent: RegistrationComponent

    override val layoutResId: Int
        get() = R.layout.activity_registration

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(R.id.registrationFragmentContainer, RegistrationFragment()).commit()
    }

    private fun performDI() {
        registrationComponent = (application as ChatModuleApp).appComponent.registrationComponent().create()
        registrationComponent.inject(this)
    }

    private fun navigateToHomeActivity(intent: Intent) {
        navigator.navigateToHomeActivity(intent)
    }
}

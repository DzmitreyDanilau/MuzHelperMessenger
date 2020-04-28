package by.dzmitrey.danilau.muzhelpermessenger.registration.presentation

import android.os.Bundle
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseActivity
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseViewModel

class RegistrationActivity : BaseActivity<BaseViewModel>() {

    override val layoutResId: Int
        get() = R.layout.activity_registration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportFragmentManager.beginTransaction().replace(R.id.registrationFragmentContainer, RegistrationFragment()).commit()
    }
}

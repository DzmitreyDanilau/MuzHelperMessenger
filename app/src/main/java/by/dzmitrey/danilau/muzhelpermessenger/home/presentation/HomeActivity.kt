package by.dzmitrey.danilau.muzhelpermessenger.home.presentation

import android.os.Bundle
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseActivity
import by.dzmitrey.danilau.muzhelpermessenger.account.registration.presentation.RegistrationViewModel

class HomeActivity : BaseActivity<RegistrationViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }

    override val layoutResId: Int
        get() = R.layout.activity_home

}

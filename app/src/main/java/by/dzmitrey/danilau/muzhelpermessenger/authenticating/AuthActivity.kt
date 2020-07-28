package by.dzmitrey.danilau.muzhelpermessenger.authenticating

import android.os.Bundle
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseActivity

class AuthActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

    }

    override val layoutResId = R.layout.activity_auth

    private fun performDi() {

    }
}
package by.dzmitrey.danilau.muzhelpermessenger.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import by.dzmitrey.danilau.muzhelpermessenger.ChatModuleApp
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.login.LoginFragment
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.registration.RegistrationFragment
import by.dzmitrey.danilau.muzhelpermessenger.di.components.HomeComponent
import by.dzmitrey.danilau.muzhelpermessenger.extensions.replaceFragment
import by.dzmitrey.danilau.muzhelpermessenger.utils.IntentUtil

class HomeActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun getIntent(context: Context): Intent {
            return IntentUtil.getIntent<HomeActivity, LoginFragment>(context)
        }
    }

    lateinit var homeComponent: HomeComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        initFragment()
    }

    private fun initFragment() {
        replaceFragment(R.id.fragmentContainer, HomeFragment())
    }

    private fun performDI() {
        homeComponent = (application as ChatModuleApp).appComponent.homeComponent().create()
        homeComponent.inject(this)
    }
}
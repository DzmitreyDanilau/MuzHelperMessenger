package by.dzmitrey.danilau.muzhelpermessenger.authenticating

import android.content.Context
import android.content.Intent
import android.os.Bundle
import by.dzmitrey.danilau.muzhelpermessenger.ChatModuleApp
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.login.LoginFragment
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseActivity
import by.dzmitrey.danilau.muzhelpermessenger.databinding.ActivityAuthBinding
import by.dzmitrey.danilau.muzhelpermessenger.di.components.RegistrationComponent
import by.dzmitrey.danilau.muzhelpermessenger.extensions.replaceFragment
import by.dzmitrey.danilau.muzhelpermessenger.utils.IntentUtil
import by.dzmitrey.danilau.muzhelpermessenger.utils.Navigator
import javax.inject.Inject

class AuthActivity : BaseActivity() {

    companion object {
        @JvmStatic
        fun getIntent(context: Context): Intent {
            return IntentUtil.getIntent<AuthActivity, LoginFragment>(context)
        }
    }

    @Inject
    lateinit var navigator: Navigator

    lateinit var registrationComponent: RegistrationComponent

    override val viewBinding by viewBinding(ActivityAuthBinding::inflate)

    override val layoutResId = R.layout.activity_auth

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)
        initFragment()
    }

    override fun performDI() {
        registrationComponent = (application as ChatModuleApp).appComponent.registrationComponent().create()
        registrationComponent.inject(this)
    }

    private fun initFragment() {
        replaceFragment(R.id.fragmentContainer, LoginFragment())
    }
}
package by.dzmitrey.danilau.muzhelpermessenger.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import by.dzmitrey.danilau.muzhelpermessenger.ChatModuleApp
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseActivity
import by.dzmitrey.danilau.muzhelpermessenger.databinding.ActivityHomeBinding
import by.dzmitrey.danilau.muzhelpermessenger.di.components.HomeComponent
import by.dzmitrey.danilau.muzhelpermessenger.extensions.replaceFragment
import by.dzmitrey.danilau.muzhelpermessenger.utils.IntentUtil

class HomeActivity : BaseActivity() {

    companion object {
        @JvmStatic
        fun getIntent(context: Context): Intent {
            return IntentUtil.getIntent<HomeActivity, HomeFragment>(context)
        }
    }

    override val viewBinding by viewBinding(ActivityHomeBinding::inflate)

    lateinit var homeComponent: HomeComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarTitle(getString(R.string.home_tool_bar_name))
        initFragment()
    }

    private fun initFragment() {
        replaceFragment(R.id.fragmentContainer, HomeFragment())
    }

    override fun performDI() {
        homeComponent = (application as ChatModuleApp).appComponent.homeComponent().create()
        homeComponent.inject(this)
    }

    override val layoutResId = R.layout.activity_home
}
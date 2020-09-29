package by.dzmitrey.danilau.muzhelpermessenger.splash

import android.os.Bundle
import android.os.Handler
import androidx.annotation.LayoutRes
import by.dzmitrey.danilau.muzhelpermessenger.ChatModuleApp
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.managers.FirebaseAuthManager
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseActivity
import by.dzmitrey.danilau.muzhelpermessenger.di.components.ApplicationComponent
import by.dzmitrey.danilau.muzhelpermessenger.utils.Navigator
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    companion object {
        private val SPLASH_SLEEP = TimeUnit.SECONDS.toMillis(2)
    }

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var firebaseAuthManager: FirebaseAuthManager

    @LayoutRes
    override val layoutResId = R.layout.activity_splash

    lateinit var splashActivityComponent: ApplicationComponent

    private val handler = Handler()
    private val handleAuthStatusRunnable = {
        handleAuthStatus()
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handler.postDelayed(handleAuthStatusRunnable, SPLASH_SLEEP)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        handler.removeCallbacks(handleAuthStatusRunnable)
    }

    private fun handleAuthStatus() {
        firebaseAuthManager.getCurrentFirebaseUser()?.let {
            navigator.navigateHome(this)
        } ?: navigator.navigateToRegistration(this)
    }

    override fun performDI() {
        splashActivityComponent = (application as ChatModuleApp).appComponent
        splashActivityComponent.inject(this)
    }
}
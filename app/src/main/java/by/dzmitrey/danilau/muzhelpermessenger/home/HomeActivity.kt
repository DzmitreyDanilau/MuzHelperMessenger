package by.dzmitrey.danilau.muzhelpermessenger.home

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.login.LoginFragment
import by.dzmitrey.danilau.muzhelpermessenger.utils.IntentUtil

class HomeActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun getIntent(context: Context): Intent {
            return IntentUtil.getIntent<HomeActivity, LoginFragment>(context)
        }
    }
}
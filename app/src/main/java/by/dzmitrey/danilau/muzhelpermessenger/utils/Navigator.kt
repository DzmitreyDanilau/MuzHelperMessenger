package by.dzmitrey.danilau.muzhelpermessenger.utils

import android.content.Context
import android.content.Intent
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.AuthActivity
import by.dzmitrey.danilau.muzhelpermessenger.home.HomeActivity
import javax.inject.Inject

class Navigator @Inject constructor() {

    fun navigateHome(context: Context) {
        startActivity(context) { HomeActivity.getIntent(it) }
    }

    fun navigateToLogin(context: Context){
        startActivity(context){AuthActivity.getIntent(it)}
    }

    private fun startActivity(context: Context?, intent: (Context) -> Intent) {
        context?.let { it.startActivity(intent(it)) }
    }
}
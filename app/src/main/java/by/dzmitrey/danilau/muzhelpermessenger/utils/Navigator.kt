package by.dzmitrey.danilau.muzhelpermessenger.utils

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import by.dzmitrey.danilau.muzhelpermessenger.utils.extensions.closeAndStartAnother

class Navigator(private val activity: AppCompatActivity) {

    fun navigateToHomeActivity(intent: Intent) {
        activity.closeAndStartAnother(intent)
    }

}
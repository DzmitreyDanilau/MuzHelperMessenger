package by.dzmitrey.danilau.muzhelpermessenger

import android.app.Application
import timber.log.Timber

class ChatModuleApp : Application(){

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
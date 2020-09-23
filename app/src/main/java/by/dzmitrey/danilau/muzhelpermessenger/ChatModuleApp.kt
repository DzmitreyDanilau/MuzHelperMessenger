package by.dzmitrey.danilau.muzhelpermessenger

import android.app.Application
import by.dzmitrey.danilau.muzhelpermessenger.di.components.ApplicationComponent
import by.dzmitrey.danilau.muzhelpermessenger.di.components.DaggerApplicationComponent
import by.dzmitrey.danilau.muzhelpermessenger.di.modules.ApplicationModule
import timber.log.Timber

class ChatModuleApp : Application() {

    val appComponent: ApplicationComponent by lazy {
        initApplicationComponent()
    }

    override fun onCreate() {
        super.onCreate()
        plantDebugTree()
    }

    private fun plantDebugTree() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initApplicationComponent(): ApplicationComponent {
        return DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }
}
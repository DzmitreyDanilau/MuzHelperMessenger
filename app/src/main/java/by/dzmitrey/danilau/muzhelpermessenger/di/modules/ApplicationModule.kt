package by.dzmitrey.danilau.muzhelpermessenger.di.modules

import android.content.Context
import by.dzmitrey.danilau.muzhelpermessenger.ChatModuleApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: ChatModuleApp) {

    @Provides
    @Singleton
    fun provideApplication() : ChatModuleApp = application

    @Provides
    @Singleton
    fun provideApplicationContext(application: ChatModuleApp): Context = application
}
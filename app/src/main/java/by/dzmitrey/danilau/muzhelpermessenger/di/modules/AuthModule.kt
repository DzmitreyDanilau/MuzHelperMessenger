package by.dzmitrey.danilau.muzhelpermessenger.di.modules

import by.dzmitrey.danilau.muzhelpermessenger.authenticating.managers.FirebaseSignInManager
import dagger.Module
import dagger.Provides

@Module
class AuthModule {

    @Provides
    fun provideFirebaseAuthManager(): FirebaseSignInManager = FirebaseSignInManager()
}
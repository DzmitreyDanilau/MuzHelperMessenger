package by.dzmitrey.danilau.muzhelpermessenger.di.modules

import android.content.Context
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.managers.FirebaseAuthManager
import by.dzmitrey.danilau.muzhelpermessenger.authenticating.managers.GoogleSignInManager
import dagger.Module
import dagger.Provides

@Module
class AuthModule {

    @Provides
    fun provideFirebaseAuthManager(context: Context): FirebaseAuthManager = FirebaseAuthManager(context)

    @Provides
    fun provideGoogleSignInManager(context: Context): GoogleSignInManager = GoogleSignInManager(context)
}
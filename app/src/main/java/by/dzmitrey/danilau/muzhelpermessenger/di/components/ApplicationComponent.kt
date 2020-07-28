package by.dzmitrey.danilau.muzhelpermessenger.di.components

import by.dzmitrey.danilau.muzhelpermessenger.di.modules.AppSubComponentsModule
import by.dzmitrey.danilau.muzhelpermessenger.di.modules.ApplicationModule
import by.dzmitrey.danilau.muzhelpermessenger.di.modules.ViewModelFactoryModule
import by.dzmitrey.danilau.muzhelpermessenger.splash.SplashActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AppSubComponentsModule::class,
        ViewModelFactoryModule::class,
        AppSubComponentsModule::class,
    ]
)
interface ApplicationComponent {

    fun inject(activity: SplashActivity)

    fun registrationComponent(): RegistrationComponent.Factory
    fun homeComponent(): HomeComponent.Factory
}
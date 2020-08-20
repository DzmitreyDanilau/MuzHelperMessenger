package by.dzmitrey.danilau.muzhelpermessenger.di.components

import android.content.Context
import by.dzmitrey.danilau.muzhelpermessenger.di.modules.AppSubComponentsModule
import by.dzmitrey.danilau.muzhelpermessenger.di.modules.ApplicationModule
import by.dzmitrey.danilau.muzhelpermessenger.di.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApplicationModule::class,
        AppSubComponentsModule::class,
        ViewModelFactoryModule::class,
        AppSubComponentsModule::class
    ]
)
interface ApplicationComponent {

    fun registrationComponent(): RegistrationComponent.Factory
    fun homeComponent(): HomeComponent.Factory
}
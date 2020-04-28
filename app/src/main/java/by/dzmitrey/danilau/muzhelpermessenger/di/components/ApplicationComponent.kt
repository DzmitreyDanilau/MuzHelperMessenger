package by.dzmitrey.danilau.muzhelpermessenger.di.components

import android.content.Context
import by.dzmitrey.danilau.muzhelpermessenger.di.modules.AppSubComponentsModule
import by.dzmitrey.danilau.muzhelpermessenger.di.modules.ApplicationModule
import by.dzmitrey.danilau.muzhelpermessenger.di.modules.ViewModelFactoryModule
import by.dzmitrey.danilau.muzhelpermessenger.registration.presentation.RegistrationActivity
import by.dzmitrey.danilau.muzhelpermessenger.registration.presentation.RegistrationFragment
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

    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    fun registrationComponent(): RegistrationComponent.Factory
}

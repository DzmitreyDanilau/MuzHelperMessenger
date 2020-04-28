package by.dzmitrey.danilau.muzhelpermessenger.di.components

import by.dzmitrey.danilau.muzhelpermessenger.di.scopes.ActivityScope
import by.dzmitrey.danilau.muzhelpermessenger.registration.presentation.RegistrationActivity
import by.dzmitrey.danilau.muzhelpermessenger.registration.presentation.RegistrationFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface RegistrationComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }

    fun inject(activity: RegistrationActivity)
    fun inject(fragment: RegistrationFragment)

}
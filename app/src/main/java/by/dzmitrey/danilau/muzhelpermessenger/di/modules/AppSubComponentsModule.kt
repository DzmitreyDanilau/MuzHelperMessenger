package by.dzmitrey.danilau.muzhelpermessenger.di.modules

import by.dzmitrey.danilau.muzhelpermessenger.di.components.HomeComponent
import by.dzmitrey.danilau.muzhelpermessenger.di.components.RegistrationComponent
import dagger.Module

@Module(
    subcomponents = [
        RegistrationComponent::class,
        HomeComponent::class
    ]
)
interface AppSubComponentsModule
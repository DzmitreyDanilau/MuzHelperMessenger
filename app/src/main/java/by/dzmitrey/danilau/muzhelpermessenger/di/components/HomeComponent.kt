package by.dzmitrey.danilau.muzhelpermessenger.di.components

import by.dzmitrey.danilau.muzhelpermessenger.di.scopes.ActivityScope
import by.dzmitrey.danilau.muzhelpermessenger.home.HomeActivity
import by.dzmitrey.danilau.muzhelpermessenger.home.HomeFragment
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface HomeComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeComponent
    }

    fun inject(activity: HomeActivity)
    fun inject(fragment: HomeFragment)
}
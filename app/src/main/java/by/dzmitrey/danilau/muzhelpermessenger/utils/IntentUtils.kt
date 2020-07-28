package by.dzmitrey.danilau.muzhelpermessenger.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment

object IntentUtil {

    inline fun <reified AClass : Activity, reified FClass : Fragment> getIntent(
        context: Context,
        args: Bundle = Bundle.EMPTY
    ): Intent {
        return getIntent(context, AClass::class.java, FClass::class.java, args)
    }

    fun getIntent(
        context: Context,
        activityClass: Class<*>,
        fragmentClass: Class<*>,
        args: Bundle = Bundle.EMPTY,
    ): Intent {
        return Intent(context, activityClass)
    }
}
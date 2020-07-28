package by.dzmitrey.danilau.muzhelpermessenger.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

fun FragmentActivity.addBackStackFragment(id: Int, fragment: Fragment) =
    supportFragmentManager.beginTransaction().addToBackStack(null).add(id, fragment).commit()

fun FragmentActivity.replaceAddBackStackFragment(id: Int, fragment: Fragment) {
    val backStateName = fragment.javaClass.simpleName
    val fragmentManager = supportFragmentManager
    val isPopped = fragmentManager.popBackStackImmediate(backStateName, 0)
    if (!isPopped && fragmentManager.findFragmentByTag(backStateName) == null) {
        fragmentManager.beginTransaction().apply {
            replace(id, fragment, backStateName)
            addToBackStack(backStateName)
        }.commit()
    }
}

fun FragmentActivity.replaceFragment(id: Int, fragment: Fragment) =
    supportFragmentManager.beginTransaction().replace(id, fragment).commit()

fun AppCompatActivity.clearBackStack() {
    for (i in 0 until supportFragmentManager.backStackEntryCount) {
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }
}
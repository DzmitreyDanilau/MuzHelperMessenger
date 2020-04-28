package by.dzmitrey.danilau.muzhelpermessenger.base.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import by.dzmitrey.danilau.muzhelpermessenger.R
import timber.log.Timber
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseActivity<V : BaseViewModel> : AppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory

    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        Timber.d("onCreate %s", this.toString())
        initViews()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }
    }

    fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun closeAndStartAnother(intent: Intent) {
        finish()
        startActivity(intent)
    }

    private fun initViews() {
        toolbar = findViewById(R.id.toolbar)
    }

    private fun setToolbarTitle(title: String?) {
        title?.let {
            toolbar?.title = title
        }
    }

    @get:LayoutRes
    abstract val layoutResId: Int

}
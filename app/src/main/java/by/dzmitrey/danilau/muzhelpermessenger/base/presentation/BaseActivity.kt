package by.dzmitrey.danilau.muzhelpermessenger.base.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import by.dzmitrey.danilau.muzhelpermessenger.R
import timber.log.Timber

abstract class BaseActivity : AppCompatActivity() {

    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        performDI()
        super.onCreate(savedInstanceState)
        setContentView(layoutResId)
        Timber.d("onCreate %s", this.toString())
        initViews()
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) finish() else super.onBackPressed()
    }

    fun setToolbarTitle(title: String?) {
        title?.let {
            toolbar?.title = title
        }
    }

    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
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
        setSupportActionBar(toolbar)
    }

    @get:LayoutRes
    abstract val layoutResId: Int

    protected abstract fun performDI()
}
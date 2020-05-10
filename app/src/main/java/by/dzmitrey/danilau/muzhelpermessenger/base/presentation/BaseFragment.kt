package by.dzmitrey.danilau.muzhelpermessenger.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.utils.extensions.base
import javax.inject.Inject
import kotlin.reflect.KClass

abstract class BaseFragment<V : BaseViewModel> : Fragment() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelProvider.Factory
    protected lateinit var viewModel: V

    open val showToolbar = true
    open val toolBarTitle = R.string.app_name

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(fragmentResId, container, false)
    }

    fun showMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    fun isToolBarShow() {
        activity?.base<V> {
            if (showToolbar) supportActionBar?.show() else supportActionBar?.hide()
            supportActionBar?.title = getString(toolBarTitle)
        }
    }

    fun showProgress() {

    }

    fun hideProgress() {

    }

    @get:LayoutRes
    abstract val fragmentResId: Int

    protected abstract fun getViewModelClass(): KClass<V>
}
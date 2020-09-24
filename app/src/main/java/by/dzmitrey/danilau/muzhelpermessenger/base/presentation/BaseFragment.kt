package by.dzmitrey.danilau.muzhelpermessenger.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import by.dzmitrey.danilau.muzhelpermessenger.extensions.EMPTY
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel, B : ViewBinding> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected var binding: B? = null

    abstract val viewModel: VM

    open val showToolbar = true

    open val toolBarTitle = String.EMPTY

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = this.setBinding(inflater, container)
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    @MainThread
    inline fun <reified VM : ViewModel> lazyViewModel() =
        createViewModelLazy(VM::class, { this.viewModelStore }, { viewModelFactory })

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun setBinding(inflater: LayoutInflater, container: ViewGroup?): B
}
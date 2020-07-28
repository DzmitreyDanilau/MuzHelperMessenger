package by.dzmitrey.danilau.muzhelpermessenger.base.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import by.dzmitrey.danilau.muzhelpermessenger.extensions.EMPTY
import javax.inject.Inject

abstract class BaseFragment<VM : BaseViewModel, B : ViewDataBinding> : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    protected lateinit var binding: B

    abstract val viewModel: VM

    open val showToolbar = true

    open val toolBarTitle = String.EMPTY

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false)
        return binding.root
    }

    @MainThread
    inline fun <reified VM : ViewModel> lazyViewModel() =
        createViewModelLazy(VM::class, { this.viewModelStore }, { viewModelFactory })

    @LayoutRes
    abstract fun getLayoutId(): Int
}
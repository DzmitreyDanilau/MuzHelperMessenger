package by.dzmitrey.danilau.muzhelpermessenger.home

import android.view.LayoutInflater
import android.view.ViewGroup
import by.dzmitrey.danilau.muzhelpermessenger.R
import by.dzmitrey.danilau.muzhelpermessenger.base.presentation.BaseFragment
import by.dzmitrey.danilau.muzhelpermessenger.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val viewModel by lazyViewModel<HomeViewModel>()

    override fun getLayoutId() = R.layout.fragment_home

    override fun setBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)

}
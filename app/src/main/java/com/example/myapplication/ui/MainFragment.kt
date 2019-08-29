package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.example.myapplication.BR.callback
import com.example.myapplication.BR.data
import com.example.myapplication.viewmodel.MainFragmentViewModel
import com.example.myapplication.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentMainBinding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater,
                R.layout.fragment_main, container, false)
        val mainFragmentViewModel = MainFragmentViewModel();
        fragmentMainBinding.setVariable(callback, mainFragmentViewModel.buttonClicked)
        fragmentMainBinding.setVariable(data, mainFragmentViewModel)
        return fragmentMainBinding.root
    }
}
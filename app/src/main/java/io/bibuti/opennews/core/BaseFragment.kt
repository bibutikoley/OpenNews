package io.bibuti.opennews.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * try to initialize all the fragments from this class.
 */
abstract class BaseFragment<T : ViewDataBinding> : Fragment() {

    /**
     *  Can initialize/declare anything here which is to used across all the child fragments
     */
    abstract fun setFragmentView(): Int

    lateinit var binding: T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, setFragmentView(), container, false)
        return binding.root
    }

}
package io.bibuti.opennews.ui

import dagger.hilt.android.AndroidEntryPoint
import io.bibuti.opennews.R
import io.bibuti.opennews.core.BaseFragment
import io.bibuti.opennews.databinding.FragmentSplashBinding

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(
    layoutResId = R.layout.fragment_splash
)
package io.bibuti.opennews.ui.onboarding

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import io.bibuti.opennews.R
import io.bibuti.opennews.core.BaseFragment
import io.bibuti.opennews.databinding.FragmentSplashBinding
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding>(
    layoutResId = R.layout.fragment_splash
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenCreated {
            delay(2000)
            findNavController().navigate(R.id.action_splashFragment_to_allNewsFragment)
        }

    }

}
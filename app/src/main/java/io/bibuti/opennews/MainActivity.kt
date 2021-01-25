package io.bibuti.opennews

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import io.bibuti.opennews.core.BaseActivity
import io.bibuti.opennews.core.ConnectivityProvider
import io.bibuti.opennews.databinding.ActivityMainBinding
import timber.log.Timber

/**
 * This activity will serve as the the Activity over which the other UI components will
 * render it-self (This follows the Navigation Architecture)
 */

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        ConnectivityProvider.networkLiveData(this).observe(this) {
            Timber.d("Network Status -> $it")
        }

    }
}
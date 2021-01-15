package io.bibuti.opennews

import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.bibuti.opennews.core.BaseActivity
import io.bibuti.opennews.core.ConnectivityProvider
import io.bibuti.opennews.core.State
import io.bibuti.opennews.ui.NewsListingViewModel
import timber.log.Timber
import java.time.LocalDateTime

/**
 * This activity will serve as the the Activity over which the other UI components will
 * render it-self (This follows the Navigation Architecture)
 */

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val newsListingViewModel: NewsListingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newsListingViewModel.newsLiveData.observe(this) {
            when (it) {
                is State.Loading -> Timber.d("UI Data Loading -> $it")
                is State.Success -> Timber.d("UI Data Success -> $it")
                is State.ResponseError -> Timber.d("UI Data Response Error -> $it")
                is State.ExceptionError -> Timber.d("UI Data Exception -> $it")
            }
        }

        ConnectivityProvider.networkLiveData(this).observe(this) {
            newsListingViewModel.fetchNews()
        }

        newsListingViewModel.fetchNewsFromDB().observe(this) {
            Timber.d("No. of items from DB -> ${it.size}")
        }

    }
}
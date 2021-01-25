package io.bibuti.opennews.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import io.bibuti.opennews.R
import io.bibuti.opennews.core.BaseFragment
import io.bibuti.opennews.core.State
import io.bibuti.opennews.core.observe
import io.bibuti.opennews.data.apiresponses.NewsResponse
import io.bibuti.opennews.databinding.FragmentNewsListingBinding
import timber.log.Timber

@AndroidEntryPoint
class NewsListingFragment : BaseFragment<FragmentNewsListingBinding>() {

    private val newsListingViewModel: NewsListingViewModel by viewModels()

    override fun setFragmentView() = R.layout.fragment_news_listing

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(newsListingViewModel.newsLiveData, newsApiObserver)

        observe(newsListingViewModel.fetchNewsFromDB()) {
            Timber.d("No. of items from DB -> ${it.size}")
        }

        val material = MaterialAlertDialogBuilder(requireContext())
            .setTitle("Hello")
            .setMessage("Check the dialog")
            .setCancelable(false)
        material.show()

    }

    private val newsApiObserver = Observer<State<NewsResponse>> {
        when (it) {
            is State.Loading -> {
                Timber.d("UI Data Loading -> $it")
                binding.newsPB.show()
            }
            is State.Success -> {
                Timber.d("UI Data Success -> $it")
                binding.newsPB.hide()
            }
            is State.ResponseError -> {
                Timber.d("UI Data Response Error -> $it")
                binding.newsPB.hide()
            }
            is State.ExceptionError -> {
                Timber.d("UI Data Exception -> $it")
                binding.newsPB.hide()
            }
        }
    }

}
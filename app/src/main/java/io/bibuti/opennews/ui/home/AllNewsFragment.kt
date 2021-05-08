package io.bibuti.opennews.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import io.bibuti.opennews.R
import io.bibuti.opennews.core.BaseFragment
import io.bibuti.opennews.core.SingleEventPagedListAdapter
import io.bibuti.opennews.core.observe
import io.bibuti.opennews.data.db.SingleNewsItem
import io.bibuti.opennews.databinding.FragmentAllNewsBinding
import io.bibuti.opennews.databinding.ItemAllNewsBinding
import io.bibuti.opennews.ui.NewsViewModel
import io.bibuti.opennews.utils.newsDiffUtil

class AllNewsFragment : BaseFragment<FragmentAllNewsBinding>(
    R.layout.fragment_all_news
) {

    private val newsViewModel: NewsViewModel by activityViewModels()

    private val newsAdapter = SingleEventPagedListAdapter<SingleNewsItem, ItemAllNewsBinding>(
        diffUtil = newsDiffUtil,
        layoutResId = R.layout.item_all_news
    )

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.allNewsRV.adapter = newsAdapter

        observe(newsViewModel.newsPagedLiveData, newsObserver)

    }

    private val newsObserver: Observer<PagingData<SingleNewsItem>> = Observer {
        newsAdapter.submitData(lifecycle, it)
    }
}
package io.bibuti.opennews.ui

import dagger.hilt.android.AndroidEntryPoint
import io.bibuti.opennews.R
import io.bibuti.opennews.core.BaseFragment
import io.bibuti.opennews.databinding.FragmentNewsListingBinding

@AndroidEntryPoint
class NewsListingFragment : BaseFragment<FragmentNewsListingBinding>() {

    override fun setFragmentView() = R.layout.fragment_news_listing

}
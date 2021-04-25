package io.bibuti.opennews.utils

import androidx.recyclerview.widget.DiffUtil
import io.bibuti.opennews.data.db.SingleNewsItem

val newsDiffUtil = object : DiffUtil.ItemCallback<SingleNewsItem>() {
    override fun areItemsTheSame(oldItem: SingleNewsItem, newItem: SingleNewsItem): Boolean {
        return oldItem.newsUrl == newItem.newsUrl
    }

    override fun areContentsTheSame(oldItem: SingleNewsItem, newItem: SingleNewsItem): Boolean {
        return oldItem == newItem
    }

}
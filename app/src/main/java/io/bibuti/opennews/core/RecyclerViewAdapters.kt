package io.bibuti.opennews.core

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView


interface ItemClickListener {
    fun onItemClicked(dataType: Any, position: Int, view: View)
}

open class ItemViewHolder<B : ViewDataBinding>(
    val binding: B,
    private val listener: ItemClickListener? = null
) : RecyclerView.ViewHolder(binding.root) {

    //Uncomment this after creating XML files
    fun bindHolder(item: Any) {
        binding.setVariable(BR.data, item)
//        binding.setVariable(BR.position, bindingAdapterPosition)
//        listener?.let {
//            binding.setVariable(BR.callback, it)
//        }
        binding.executePendingBindings()
    }

}

class MultiEventListAdapter<T : Any, B : ViewDataBinding>(
    diffUtil: DiffUtil.ItemCallback<T>,
    @LayoutRes private val layoutResId: Int,
    private val itemClickListener: ItemClickListener? = null,
) : ListAdapter<T, ItemViewHolder<B>>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<B> {
        return ItemViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutResId,
                parent,
                false
            ),
            listener = itemClickListener
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder<B>, position: Int) {
        holder.bindHolder(getItem(position))
    }

}

class MultiEventPagedListAdapter<T : Any, B : ViewDataBinding>(
    diffUtil: DiffUtil.ItemCallback<T>,
    @LayoutRes private val layoutResId: Int,
    private val itemClickListener: ItemClickListener? = null,
) : PagingDataAdapter<T, ItemViewHolder<B>>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder<B> {
        return ItemViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutResId,
                parent,
                false
            ),
            listener = itemClickListener
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder<B>, position: Int) {
        getItem(position)?.apply {
            holder.bindHolder(this)
        }
    }

}

///////////////////////////////////////////////////////////////////////

class SingleEventPagedListAdapter<T : Any, B : ViewDataBinding>(
    diffUtil: DiffUtil.ItemCallback<T>,
    @LayoutRes private val layoutResId: Int,
    private val itemClickListener: (T) -> Unit = {},
) : PagingDataAdapter<T, ItemViewHolder<B>>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder<B> {
        return ItemViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutResId,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder<B>, position: Int) {
        getItem(position)?.apply {
            holder.bindHolder(this)
            holder.apply {
                binding.root.setOnClickListener {
                    getItem(bindingAdapterPosition)?.let {
                        itemClickListener.invoke(it)
                    }
                }
            }
        }
    }

}

////////////////////////////////////////////////////////////////////////////////

class SingleEventListAdapter<T : Any, B : ViewDataBinding>(
    diffUtil: DiffUtil.ItemCallback<T>,
    @LayoutRes private val layoutResId: Int,
    private val itemClickListener: (T) -> Unit = {},
) : ListAdapter<T, ItemViewHolder<B>>(diffUtil) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder<B> {
        return ItemViewHolder(
            binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutResId,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder<B>, position: Int) {
        getItem(position)?.apply {
            holder.bindHolder(this)
            holder.apply {
                binding.root.setOnClickListener {
                    getItem(bindingAdapterPosition)?.let {
                        itemClickListener.invoke(it)
                    }
                }
            }
        }
    }

}
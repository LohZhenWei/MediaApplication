package com.example.media.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.media.app.R
import com.example.media.app.databinding.ItemPageLoaderBinding
import com.example.media.app.extension.hide
import com.example.media.app.extension.show

class PagingLoadStateAdapter(
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder =
        LoadStateViewHolder.from(parent, R.layout.item_page_loader)

}

class LoadStateViewHolder(private val binding: ItemPageLoaderBinding) :
    RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun from(parent: ViewGroup, layout: Int): LoadStateViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding =
                DataBindingUtil.inflate<ItemPageLoaderBinding>(inflater, layout, parent, false)
            return LoadStateViewHolder(binding)
        }
    }


    fun bind(loadState: LoadState) {
        when (loadState) {
            LoadState.Loading -> {
                binding.pbLoader.show()
            }
            else -> {
                binding.pbLoader.hide()
            }
        }
    }
}

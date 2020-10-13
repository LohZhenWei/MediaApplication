package com.example.media.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.media.app.R
import com.example.media.app.databinding.ItemImageBinding
import com.example.media.app.network.response.ImageInfo
import com.example.media.app.util.ImageUtil

class ImageGalleryAdapter :
    PagingDataAdapter<ImageInfo, RecyclerView.ViewHolder>(DiffCallBack.callback) {

    var onImageClick: (ImageInfo) -> Unit = {}

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageViewHolder) {
            getItem(position)?.let {
                holder.bind(it, onImageClick)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ImageViewHolder.from(parent, R.layout.item_image)
}

class ImageViewHolder(private val binding: ItemImageBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup, layout: Int): ImageViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding =
                DataBindingUtil.inflate<ItemImageBinding>(inflater, layout, parent, false)
            return ImageViewHolder(binding)
        }
    }


    fun bind(data: ImageInfo, mOnImageClick: (ImageInfo) -> Unit = {}) {
        binding.root.setOnClickListener { mOnImageClick.invoke(data) }
        ImageUtil().load(binding.root.context, data.previewURL, binding.ivImage)
    }
}

object DiffCallBack {

    val callback = object : DiffUtil.ItemCallback<ImageInfo>() {
        override fun areItemsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ImageInfo, newItem: ImageInfo): Boolean {
            return oldItem == newItem
        }

    }
}

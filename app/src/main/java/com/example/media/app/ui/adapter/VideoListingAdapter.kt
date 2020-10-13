package com.example.media.app.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.media.app.R
import com.example.media.app.databinding.ItemVideoBinding
import com.example.media.app.model.VideoInfo
import com.example.media.app.util.ImageUtil
import kotlin.properties.Delegates


class VideoListingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), DiffAdapter {

    internal var data: List<VideoInfo> by Delegates.observable(emptyList()) { property, oldValue, newValue ->
        notify(oldValue, newValue) { o, n -> o.id == n.id }
    }

    override fun getItemCount(): Int = data.size

    var mOnVideoClick: (VideoInfo) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        VideoListingViewHolder.from(parent, R.layout.item_video)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is VideoListingViewHolder) {
            holder.bind(data[position], mOnVideoClick)
        }
    }
}

class VideoListingViewHolder(private val binding: ItemVideoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup, layout: Int): VideoListingViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding =
                DataBindingUtil.inflate<ItemVideoBinding>(inflater, layout, parent, false)
            return VideoListingViewHolder(binding)
        }
    }


    fun bind(data: VideoInfo, mOnVideoClick: (VideoInfo) -> Unit = {}) {
        binding.root.setOnClickListener { mOnVideoClick.invoke(data) }
        binding.tvLabelDescription.text = data.displayName
        ImageUtil().load(binding.root.context, data.path ?: "", binding.ivThumbnail)
    }

}


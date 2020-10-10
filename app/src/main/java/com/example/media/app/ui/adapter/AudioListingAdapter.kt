package com.example.media.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.media.app.R
import com.example.media.app.databinding.ItemVideoBinding
import com.example.media.app.model.AudioInfo
import com.example.media.app.model.VideoInfo
import kotlin.properties.Delegates


class AudioListingAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(), DiffAdapter {

    internal var data: List<AudioInfo> by Delegates.observable(emptyList()) { property, oldValue, newValue ->
        autoNotify(oldValue, newValue) { o, n -> o.id == n.id }
    }

    override fun getItemCount(): Int = data.size

    var mOnAudioClick: (AudioInfo) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        AudioListingViewHolder.from(parent, R.layout.item_video)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is AudioListingViewHolder) {
            holder.bind(data[position], mOnAudioClick)
        }
    }
}

class AudioListingViewHolder(private val binding: ItemVideoBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun from(parent: ViewGroup, layout: Int): AudioListingViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding =
                DataBindingUtil.inflate<ItemVideoBinding>(inflater, layout, parent, false)
            return AudioListingViewHolder(binding)
        }
    }


    fun bind(data: AudioInfo, mOnAudioClick: (AudioInfo) -> Unit = {}) {
        binding.root.setOnClickListener { mOnAudioClick.invoke(data) }
        binding.tvLabelTitle.text = data.displayName
    }
}


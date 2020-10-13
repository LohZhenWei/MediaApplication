package com.example.media.app.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.media.app.R
import com.example.media.app.extension.observe
import com.example.media.app.ui.activity.ImageDetailActivity
import com.example.media.app.ui.adapter.ImageGalleryAdapter
import com.example.media.app.ui.adapter.PagingLoadStateAdapter
import com.example.media.app.ui.viewmodel.ImageGalleryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_image_gallery.*

@AndroidEntryPoint
class ImageGalleryFragment : BaseFragment<ImageGalleryViewModel>() {

    override val viewModel: ImageGalleryViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_image_gallery

    private val imageGalleryAdapter = ImageGalleryAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
        initObserver()
    }

    private fun initView() {
        val gridLayout =
            StaggeredGridLayoutManager(this.getSpanCount(150), StaggeredGridLayoutManager.VERTICAL)
        rv_listing.apply {
            itemAnimator = null
            layoutManager = gridLayout
            adapter = imageGalleryAdapter.withLoadStateFooter(
                footer = PagingLoadStateAdapter()
            )
        }
    }

    private fun initListener() {
        imageGalleryAdapter.onImageClick = {
            startActivity(ImageDetailActivity.newIntent(context, it))
        }
    }

    private fun initObserver() {
        observe(viewModel.onGetImagesSuccess) {
            imageGalleryAdapter.submitData(lifecycle, it)
        }
    }

    private fun getSpanCount(columnWidth: Int): Int {
        val displayMetrics = context?.resources?.displayMetrics
        displayMetrics?.let {
            return ((it.widthPixels / it.density) / columnWidth).toInt()
        }
        return 2
    }

    override fun popBackStack() {
        activity?.finish()
    }

}
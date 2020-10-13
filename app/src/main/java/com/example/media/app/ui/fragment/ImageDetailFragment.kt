package com.example.media.app.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.media.app.R
import com.example.media.app.network.response.ImageInfo
import com.example.media.app.service.DownloadWorker
import com.example.media.app.service.IMAGE_ID
import com.example.media.app.service.IMAGE_URL
import com.example.media.app.ui.activity.IMAGE_DATA
import com.example.media.app.ui.viewmodel.ImageDetailViewModel
import com.example.media.app.util.ImageUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_image_detail.*

@AndroidEntryPoint
class ImageDetailFragment : BaseFragment<ImageDetailViewModel>() {

    override val viewModel: ImageDetailViewModel by viewModels()

    override fun getLayoutResId(): Int = R.layout.fragment_image_detail

    private val data by lazy {
        activity?.intent?.getParcelableExtra(IMAGE_DATA) ?: ImageInfo()
    }

    private val workManager by lazy { WorkManager.getInstance(requireContext()) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListener()
    }

    private fun initView() {
        ImageUtil().load(context, data.largeImageURL, iv_image)
        tv_image_detail.text = getSampleImageDescription()
    }

    private fun initListener() {
        btn_download.setOnClickListener {
            val request = OneTimeWorkRequest.Builder(DownloadWorker::class.java)
                .setInputData(
                    workDataOf(
                        IMAGE_URL to data.largeImageURL,
                        IMAGE_ID to data.id.toString()
                    )
                )
                .build()
            workManager.enqueue(request)
        }
    }

    private fun getSampleImageDescription(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("User : ${data.user} \n")
        stringBuilder.append("View : ${data.views} \n")
        stringBuilder.append("Like : ${data.likes} \n")
        stringBuilder.append("Download : ${data.downloads}")
        return stringBuilder.toString()
    }

    override fun popBackStack() {
        activity?.finish()
    }

}
package com.example.media.app.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.media.app.extension.observe
import com.example.media.app.ui.viewmodel.BaseViewModel
import timber.log.Timber

abstract class BaseFragment<ViewModelType : BaseViewModel> : Fragment() {

    abstract val viewModel: ViewModelType

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResId(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            observe(showProgressEvent) {
                //show progress dialog
            }
            observe(hideProgressEvent) {
                //hide progress dialog
            }
            observe(showErrorEvent) {
                // show error dialog
                Timber.d("Error : $it")
            }
        }
    }

    @LayoutRes
    abstract fun getLayoutResId(): Int

    open fun popBackStack() {
        findNavController().popBackStack()
    }
}
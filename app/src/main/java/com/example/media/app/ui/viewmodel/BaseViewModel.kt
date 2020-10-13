package com.example.media.app.ui.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel @ViewModelInject constructor() : ViewModel() {

    protected val disposeBag = CompositeDisposable()

    private val _showProgressEvent: MutableLiveData<Unit> = MutableLiveData()
    val showProgressEvent: LiveData<Unit> get() = _showProgressEvent

    private val _hideProgressEvent: MutableLiveData<Unit> = MutableLiveData()
    val hideProgressEvent: LiveData<Unit> get() = _hideProgressEvent

    private val _showErrorEvent: MutableLiveData<Throwable> = MutableLiveData()
    val showErrorEvent: LiveData<Throwable>
        get() = _showErrorEvent

    fun showProgressDialog() {
        _showProgressEvent.postValue(Unit)
    }

    fun hideProgressDialog() {
        _hideProgressEvent.postValue(Unit)
    }

    fun sendErrorMessage(throwable: Throwable) {
        _showErrorEvent.postValue(throwable)
    }

    override fun onCleared() {
        disposeBag.clear()
        super.onCleared()
    }
}

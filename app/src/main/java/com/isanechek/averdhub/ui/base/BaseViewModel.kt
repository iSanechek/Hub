package com.isanechek.averdhub.ui.base

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val _toastState = MutableLiveData<Int>()
    val toastState: LiveData<Int> get() = _toastState

    private val _errorDialog = MutableLiveData<Pair<String, String>>()
    val errorDialogState: LiveData<Pair<String, String>> get() = _errorDialog

    private val _progressState = MutableLiveData<Boolean>()
    val progressState: LiveData<Boolean> get() = _progressState

    open fun showToast(@StringRes textId: Int) {
        _toastState.postValue(textId)
    }

    open fun showErrorDialog(message: String) {
        _errorDialog.postValue(Pair("show", message))
    }

    open fun hideErrorDialog() {
        _errorDialog.postValue(Pair("hide", ""))
    }

    open fun showProgress() {
        _progressState.postValue(true)
    }

    open fun hideProgress() {
        _progressState.postValue(false)
    }
}
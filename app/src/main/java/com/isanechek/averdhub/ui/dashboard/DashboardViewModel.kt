package com.isanechek.averdhub.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.isanechek.averdhub.data.models.InstallApp
import com.isanechek.averdhub.data.models.OperationResult
import com.isanechek.averdhub.data.models.SocialAction
import com.isanechek.averdhub.data.repositories.AppsRepository
import com.isanechek.averdhub.ext._text
import com.isanechek.averdhub.ui.base.BaseViewModel
import kotlinx.coroutines.launch

private const val TAG = "DashboardViewModel"

class DashboardViewModel(private val repository: AppsRepository) : BaseViewModel() {

    private val _socialData = MutableLiveData<List<SocialAction>>()
    val socialData: LiveData<List<SocialAction>> get() = _socialData

    private val _appProgressState = MutableLiveData<Boolean>()
    val appsProgressState: LiveData<Boolean> get() = _appProgressState

    private val _appsData = MutableLiveData<List<InstallApp>>()
    val appsData: LiveData<List<InstallApp>> get() = _appsData


    fun socialSectionCallback(iem: SocialAction) {

    }

    fun appsSectionCallback(item: InstallApp) {

    }


    private fun loadApps() {
        _appProgressState.value = true

        viewModelScope.launch {
            when (val result = repository.loadInstallingApps()) {
                is OperationResult.Success -> {
                    _appProgressState.value = false
                    _appsData.value = result.data
                }
                is OperationResult.Error -> {
                    _appProgressState.value = false
                    when (result.error) {
                        is OperationResult.ErrorState.Unknown -> showToast(_text.apps_load_error_toast_msg)
                        is OperationResult.ErrorState.Empty -> showToast(_text.apps_load_error_toast_msg)
                        else -> showToast(_text.apps_load_error_toast_msg)
                    }
                }
                else -> {
                    _appProgressState.value = false
                    showToast(_text.apps_load_error_toast_msg)
                }
            }
        }
    }

    private fun testSocialData() {
        val data = listOf(
            SocialAction(title = "Hello", network = "Vkontakte"),
            SocialAction(title = "Hello", network = "Vkontakte"),
            SocialAction(title = "Hello", network = "Vkontakte"),
            SocialAction(title = "Hello", network = "Vkontakte"),
            SocialAction(title = "Hello", network = "Vkontakte")
        )
        _socialData.value = data

    }

    init {
        loadApps()
        testSocialData()
    }
}

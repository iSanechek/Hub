package com.isanechek.averdhub.ui

import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import com.isanechek.averdhub.data.models.InstallApp
import com.isanechek.averdhub.data.models.OperationResult
import com.isanechek.averdhub.data.models.SocialAction
import com.isanechek.averdhub.data.repositories.AppsRepository
import com.isanechek.averdhub.ext._text
import com.isanechek.averdhub.ui.base.BaseViewModel
import kotlinx.coroutines.launch


private const val TAG = "DashboardViewModel"

class AppViewModel(private val repository: AppsRepository) : BaseViewModel() {

    private val _socialData = MutableLiveData<List<SocialAction>>()
    val socialData: LiveData<List<SocialAction>> = _socialData

    private val _appProgressState = MutableLiveData<Boolean>()
    val appsProgressState: LiveData<Boolean> = _appProgressState

    private val _appsData = MutableLiveData<List<InstallApp>>()
    val appsData: LiveData<List<InstallApp>> = _appsData
    val shortAppsData: LiveData<List<InstallApp>> =
        Transformations.switchMap(_appsData) { data -> MutableLiveData(data.take(3)) }

    private val _darkStatusBar = MutableLiveData<Boolean>(false)
    val darkStatusBar: LiveData<Boolean> = _darkStatusBar
    var currentTheme by +state { lightThemeColors }
    val changeTheme = {
        currentTheme = if (currentTheme == lightThemeColors) {
            _darkStatusBar.value = true
            darkThemeColors
        } else {
            _darkStatusBar.value = false
            lightThemeColors
        }
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

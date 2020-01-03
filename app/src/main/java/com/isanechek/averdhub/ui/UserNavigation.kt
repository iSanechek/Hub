package com.isanechek.averdhub.ui

import android.util.Log
import androidx.compose.Composable
import com.github.zsoltk.compose.router.Router
import com.isanechek.averdhub.data.models.InstallApp
import com.isanechek.averdhub.data.models.SocialAction
import com.isanechek.averdhub.ui.appsdetail.AppsDetailScreen
import com.isanechek.averdhub.ui.dashboard.DashboardScreen
import com.isanechek.averdhub.ui.dashboard.DashboardViewModel

interface UserNavigation {

    sealed class Routing {
        object Dashboard : Routing()
        data class AppsDetail(val data: InstallApp) : Routing()
    }

    companion object {
        private const val TAG = "UserNavigation"
        @Composable
        fun Content(dashboardVm: DashboardViewModel, defaultRouting: Routing) {
            Router(contextId = "Hub", defaultRouting = defaultRouting) { backStack ->
                when(val routing = backStack.last()) {
                    is Routing.Dashboard -> DashboardScreen.Content(
                        vm = dashboardVm,
                        goToScreen = { data ->
                            Log.e(TAG, "Content: data $data")
                            when (data) {
                                is InstallApp -> {
                                    Log.e(TAG, "Content: Go to App Detail")
                                    backStack.push(Routing.AppsDetail(
                                        data = data
                                    ))
                                }
                                is SocialAction -> {
                                    Log.e(TAG, "Content: Go to Social Detail")
                                }
                                else -> Log.e(TAG, "Content: Go to Hz")
                            }
                        }
                    )
                    is Routing.AppsDetail -> AppsDetailScreen.Content(
                        item = routing.data,
                        goBack = { backStack.pop() }
                    )
                }
            }
        }
    }
}
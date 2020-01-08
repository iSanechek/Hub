package com.isanechek.averdhub.ui

import android.util.Log
import androidx.compose.Composable
import com.github.zsoltk.compose.router.Router
import com.isanechek.averdhub.data.models.GoToScreen
import com.isanechek.averdhub.data.models.InstallApp
import com.isanechek.averdhub.ui.appsdetail.AppsDetailScreen
import com.isanechek.averdhub.ui.appslist.AppsListScreen
import com.isanechek.averdhub.ui.dashboard.DashboardScreen

interface UserNavigation {

    sealed class Routing {
        data class Dashboard(val appVm: AppViewModel) : Routing()
        data class AppsList(val appVm: AppViewModel): Routing()
        data class AppsDetail(val data: InstallApp) : Routing()
    }

    companion object {
        private const val TAG = "UserNavigation"
        @Composable
        fun Content(defaultRouting: Routing) {
            Router(contextId = "Hub", defaultRouting = defaultRouting) { backStack ->
                when(val routing = backStack.last()) {
                    is Routing.Dashboard -> DashboardScreen.Content(
                        vm = routing.appVm,
                        goToScreen = { screen ->
                            Log.e(TAG, "Go to: $screen")
                            when (screen) {
                                is GoToScreen.AllAppsScreen -> { backStack.push(Routing.AppsList(routing.appVm)) }
                                is GoToScreen.AllSocialScreen -> {}
                                is GoToScreen.DetailSocial -> {}
                                is GoToScreen.DetailApp -> { backStack.push(Routing.AppsDetail(screen.data)) }
                            }
                        }
                    )
                    is Routing.AppsList -> AppsListScreen.Content(
                        appViewModel = routing.appVm,
                        openDetail = { backStack.push(Routing.AppsDetail(it)) }
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
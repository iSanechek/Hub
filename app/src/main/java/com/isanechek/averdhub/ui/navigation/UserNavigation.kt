package com.isanechek.averdhub.ui.navigation

import android.util.Log
import androidx.compose.Composable
import com.github.zsoltk.compose.router.Router
import com.isanechek.averdhub.data.models.GoToScreen
import com.isanechek.averdhub.data.models.InstallApp
import com.isanechek.averdhub.data.models.SocialAction
import com.isanechek.averdhub.ui.viewmodel.AppViewModel
import com.isanechek.averdhub.ui.apps.detail.AppsDetailScreen
import com.isanechek.averdhub.ui.apps.list.AppsListScreen
import com.isanechek.averdhub.ui.dashboard.DashboardScreen
import com.isanechek.averdhub.ui.social.list.SocialListScreen

interface UserNavigation {

    sealed class Routing {
        data class Dashboard(val appViewModel: AppViewModel) : Routing()
        data class AllSocial(val appViewModel: AppViewModel) : Routing()
        data class SocialDetail(val data: SocialAction) : Routing()
        data class AppsList(val appViewModel: AppViewModel) : Routing()
        data class AppsDetail(val data: InstallApp) : Routing()
    }

    companion object {
        private const val TAG = "UserNavigation"
        @Composable
        fun Content(defaultRouting: Routing) {
            Router(contextId = "Hub", defaultRouting = defaultRouting) { backStack ->
                when(val routing = backStack.last()) {
                    is Routing.Dashboard -> DashboardScreen.Content(
                        vm = routing.appViewModel,
                        goToScreen = { goTo ->
                            when (goTo) {
                                is GoToScreen.AllAppsScreen -> { backStack.push(
                                    Routing.AppsList(
                                        routing.appViewModel
                                    )
                                ) }
                                is GoToScreen.AllSocialScreen -> { backStack.push(
                                    Routing.AllSocial(
                                        routing.appViewModel
                                    )
                                ) }
                                is GoToScreen.DetailSocial -> {}
                                is GoToScreen.DetailApp -> { backStack.push(
                                    Routing.AppsDetail(
                                        goTo.data
                                    )
                                ) }
                                else -> Log.e(TAG, "Dashboard go to hz! :(")
                            }
                        }
                    )
                    is Routing.AppsList -> AppsListScreen.Content(
                        appViewModel = routing.appViewModel,
                        goToScreen = { goTo ->
                            when(goTo) {
                                is GoToScreen.GoBack -> backStack.pop()
                                is GoToScreen.DetailApp -> { backStack.push(
                                    Routing.AppsDetail(
                                        goTo.data
                                    )
                                ) }
                                else -> Log.e(TAG, "Apps List go to hz! :(")
                            }
                        }
                    )
                    is Routing.AppsDetail -> AppsDetailScreen.Content(
                        item = routing.data,
                        goBack = { backStack.pop() }
                    )
                    is Routing.AllSocial -> SocialListScreen.Content(
                        appViewModel = routing.appViewModel,
                        goToScreen = { goTo ->
                            when(goTo) {
                                is GoToScreen.DetailSocial -> {
                                    Routing.SocialDetail(
                                        goTo.data
                                    )
                                }
                                is GoToScreen.GoBack -> { backStack.pop() }
                                else -> Log.e(TAG, "Social list go to hz! :(")
                            }
                        }
                    )
                }
            }
        }
    }
}
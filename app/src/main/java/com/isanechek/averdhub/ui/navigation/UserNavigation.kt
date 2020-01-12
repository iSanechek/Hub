package com.isanechek.averdhub.ui.navigation

import android.util.Log
import androidx.compose.Composable
import androidx.ui.animation.Crossfade
import com.github.zsoltk.compose.router.Router
import com.isanechek.averdhub.data.models.GoToScreen
import com.isanechek.averdhub.data.models.InstallApp
import com.isanechek.averdhub.data.models.SocialAction
import com.isanechek.averdhub.ui.viewmodel.AppViewModel
import com.isanechek.averdhub.ui.apps.detail.AppsDetailScreen
import com.isanechek.averdhub.ui.apps.list.AppsListScreen
import com.isanechek.averdhub.ui.dashboard.DashboardScreen
import com.isanechek.averdhub.ui.home.HomeScreen
import com.isanechek.averdhub.ui.imageviewer.ImageViewerScreen
import com.isanechek.averdhub.ui.social.detail.SocialDetailScreen
import com.isanechek.averdhub.ui.social.list.SocialListScreen

interface UserNavigation {

    sealed class Routing {
        data class Dashboard(val appViewModel: AppViewModel) : Routing()
        data class AllSocial(val appViewModel: AppViewModel) : Routing()
        data class SocialDetail(val data: SocialAction) : Routing()
        data class AppsList(val appViewModel: AppViewModel) : Routing()
        data class AppsDetail(val data: InstallApp) : Routing()
        data class ImageViewer(val url: String) : Routing()
    }

    companion object {
        private const val TAG = "UserNavigation"
        @Composable
        fun Content(defaultRouting: Routing) {
            Router(contextId = "Hub", defaultRouting = defaultRouting) { backStack ->
                when (val routing = backStack.last()) {
                    is Routing.Dashboard -> HomeScreen.Content(
                        appViewModel = routing.appViewModel,
                        goToScreen = { goTo ->
                            when (goTo) {
                                is GoToScreen.AllAppsScreen -> {
                                    backStack.push(
                                        Routing.AppsList(
                                            routing.appViewModel
                                        )
                                    )
                                }
                                is GoToScreen.AllSocialScreen -> {
                                    backStack.push(
                                        Routing.AllSocial(
                                            routing.appViewModel
                                        )
                                    )
                                }
                                is GoToScreen.DetailSocial -> {
                                }
                                is GoToScreen.DetailApp -> {
                                    backStack.push(
                                        Routing.AppsDetail(
                                            goTo.data
                                        )
                                    )
                                }
                                else -> Log.e(TAG, "Dashboard go to hz! :(")
                            }
                        }
                    )
                    is Routing.AppsList -> AppsListScreen.Content(
                        appViewModel = routing.appViewModel,
                        goToScreen = { goTo ->
                            when (goTo) {
                                is GoToScreen.GoBack -> backStack.pop()
                                is GoToScreen.DetailApp -> {
                                    backStack.push(
                                        Routing.AppsDetail(
                                            goTo.data
                                        )
                                    )
                                }
                                else -> Log.e(TAG, "Apps List go to hz! :(")
                            }
                        }
                    )
                    is Routing.AppsDetail -> AppsDetailScreen.Content(
                        item = routing.data,
                        goToScreen = { goTo ->
                            when (goTo) {
                                is GoToScreen.GoBack -> {
                                    backStack.pop()
                                }
                                is GoToScreen.ImageViewer -> {
                                    backStack.push(Routing.ImageViewer(goTo.url))
                                }
                                else -> Log.e(TAG, "Apps Detail go to hz! :(")
                            }
                        }
                    )
                    is Routing.AllSocial -> SocialListScreen.Content(
                        appViewModel = routing.appViewModel,
                        goToScreen = { goTo ->
                            when (goTo) {
                                is GoToScreen.DetailSocial -> {
                                    backStack.push(
                                        Routing.SocialDetail(
                                            goTo.data
                                        )
                                    )
                                }
                                is GoToScreen.GoBack -> {
                                    backStack.pop()
                                }
                                else -> Log.e(TAG, "Social list go to hz! :(")
                            }
                        }
                    )
                    is Routing.SocialDetail -> SocialDetailScreen.Content(
                        data = routing.data,
                        goToScreen = { goTo ->
                            when (goTo) {
                                is GoToScreen.ImageViewer -> {
                                    backStack.push(Routing.ImageViewer(goTo.url))
                                }
                                is GoToScreen.GoBack -> {
                                    backStack.pop()
                                }
                                else -> Log.e(TAG, "Social detail go to hz! :(")
                            }
                        }
                    )
                    is Routing.ImageViewer -> ImageViewerScreen.Content(
                        goBack = { backStack.pop() }
                    )
                }
            }
        }
    }
}
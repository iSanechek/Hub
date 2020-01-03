package com.isanechek.averdhub.ui.dashboard

import android.util.Log
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.TopAppBar
import androidx.ui.res.stringResource
import com.isanechek.averdhub.ext._text
import com.isanechek.averdhub.ext.observe

interface DashboardScreen {

    companion object {
        private const val TAG = "DashboardScreen"

        @Composable
        fun Content(vm: DashboardViewModel, goToScreen: (Any) -> Unit) {


            FlexColumn {
                inflexible {
                    TopAppBar(title = { Text(text = "Hub") })
                }

                flexible(flex = 1f) {
                    VerticalScroller {
                        Column {
                            SocialActionSection(vm, goToScreen)
                            AppsSection(vm, goToScreen)
                        }
                    }
                }
            }
        }

        @Composable
        private fun SocialActionSection(vm: DashboardViewModel, goToScreen: (Any) -> Unit) {

            SectionTitle(title = +stringResource(_text.ds_social_title))

            HorizontalScroller {
                Row(modifier = Spacing(all = 8.dp)) {
                    val data = +observe(vm.socialData)
                    Log.e(TAG, "SocialActionSection: size ${data?.size}")
                    data?.forEach { item ->
                        DashboardSocialCard(
                            item = item,
                            callback = { data -> goToScreen.invoke(data) })
                    }
                }
            }
        }

        @Composable
        private fun AppsSection(vm: DashboardViewModel, goToScreen: (Any) -> Unit) {
            SectionTitle(title = +stringResource(_text.ds_apps_title))

            HorizontalScroller {
                Row(modifier = Spacing(all = 8.dp)) {
                    val data = +observe(vm.appsData)
                    data?.forEach {
                        DashboardAppsCard(item = it, callback = { item ->
                            Log.e(TAG, "AppsSection: Click ${item.name}")
                            goToScreen.invoke(item)
                        })
                    }
                }
            }
        }

        @Composable
        private fun SectionTitle(title: String) {
            Padding(top = 16.dp, left = 16.dp, right = 16.dp) {
                Text(text = title)
            }
        }
    }
}
package com.isanechek.averdhub.ui.appslist

import android.util.Log
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.TopAppBar
import com.isanechek.averdhub.data.models.InstallApp
import com.isanechek.averdhub.ext.observe
import com.isanechek.averdhub.ui.AppViewModel
import com.isanechek.averdhub.ui.dashboard.DashboardAppsCard

interface AppsListScreen {

    companion object {
        private const val TAG = "AppsListScreen"

        @Composable
        fun Content(appViewModel: AppViewModel, openDetail: (InstallApp) -> Unit) {

            Log.e(TAG, "Content: CREATE")

            FlexColumn {
                inflexible { TopAppBar(title = { Text(text = "Apps list") }) }
                flexible(flex = 0f) {

                    showContent(appViewModel, openDetail)

                }
            }
        }


        @Composable
        private fun showContent(appViewModel: AppViewModel, openDetail: (InstallApp) -> Unit) {
            val data = +observe(appViewModel.appsData)
            Log.e(TAG, "Content: SIZE ${data?.size}")
            VerticalScroller {
                Column {
                    if (!data.isNullOrEmpty()) {
                        data.forEach { item ->
                            Padding(left = 8.dp, right = 8.dp, top = 3.dp, bottom = 3.dp) {
                                DashboardAppsCard(
                                    item = item,
                                    callback = { openDetail.invoke(it) },
                                    isVertical = true
                                )
                            }
                        }
                    }
                }

            }
        }
    }
}
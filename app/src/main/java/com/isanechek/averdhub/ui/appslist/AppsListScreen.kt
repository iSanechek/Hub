package com.isanechek.averdhub.ui.appslist

import android.util.Log
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.FlexColumn
import androidx.ui.layout.Padding
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.res.stringResource
import com.isanechek.averdhub.data.models.GoToScreen
import com.isanechek.averdhub.ext._drawable
import com.isanechek.averdhub.ext._text
import com.isanechek.averdhub.ext.observe
import com.isanechek.averdhub.ui.AppViewModel
import com.isanechek.averdhub.ui.components.VectorImageButton
import com.isanechek.averdhub.ui.dashboard.DashboardAppsCard

interface AppsListScreen {

    companion object {
        private const val TAG = "AppsListScreen"

        @Composable
        fun Content(appViewModel: AppViewModel, goTo: (GoToScreen) -> Unit) {
            FlexColumn {
                inflexible {
                    TopAppBar(
                        title = { Text(text = +stringResource(_text.als_toolbar_title)) },
                        navigationIcon = {
                            VectorImageButton(
                                id = _drawable.ic_baseline_arrow_back_24,
                                onClick = { goTo.invoke(GoToScreen.GoBack) },
                                tint = (+MaterialTheme.colors()).onBackground
                            )
                        }
                    )
                }
                flexible(flex = 0f) { showContent(appViewModel, goTo) }
            }
        }


        @Composable
        private fun showContent(appViewModel: AppViewModel, openDetail: (GoToScreen) -> Unit) {
            val data = +observe(appViewModel.appsData)
            Log.e(TAG, "Content: SIZE ${data?.size}")
            VerticalScroller {
                Column {
                    if (!data.isNullOrEmpty()) {
                        data.forEach { item ->
                            Padding(left = 8.dp, right = 8.dp, top = 5.dp, bottom = 3.dp) {
                                DashboardAppsCard(
                                    item = item,
                                    callback = { openDetail.invoke(GoToScreen.DetailApp(it)) },
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
package com.isanechek.averdhub.ui.home.apps

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.Padding
import com.isanechek.averdhub.data.models.GoToScreen
import com.isanechek.averdhub.ext.observe
import com.isanechek.averdhub.ui.dashboard.DashboardAppsCard
import com.isanechek.averdhub.ui.viewmodel.AppViewModel

@Composable
fun HomeAppsScreen(appViewModel: AppViewModel, goToScreen: (GoToScreen) -> Unit) {
    val data = +observe(appViewModel.appsData)
    VerticalScroller {
        Column {
            if (!data.isNullOrEmpty()) {
                data.forEach { item ->
                    Padding(left = 8.dp, right = 8.dp, top = 5.dp, bottom = 3.dp) {
                        DashboardAppsCard(
                            item = item,
                            callback = { goToScreen.invoke(GoToScreen.DetailApp(it)) },
                            isVertical = true
                        )
                    }
                }
            }
        }
    }
}
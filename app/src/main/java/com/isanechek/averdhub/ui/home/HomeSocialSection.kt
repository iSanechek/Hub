package com.isanechek.averdhub.ui.home

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.HeightSpacer
import androidx.ui.layout.Padding
import androidx.ui.layout.Row
import com.isanechek.averdhub.data.models.GoToScreen
import com.isanechek.averdhub.ext.observe
import com.isanechek.averdhub.ui.style.themeTypography
import com.isanechek.averdhub.ui.viewmodel.AppViewModel

@Composable
fun HomeSocialSection(appViewModel: AppViewModel, goToScreen: (GoToScreen) -> Unit) {
    val data = +observe(appViewModel.shortSocialData)
    Padding(left = 16.dp, right = 16.dp) {
        if (!data.isNullOrEmpty()) {
            Column {
                HeightSpacer(height = 8.dp)
                Padding(left = 8.dp) {
                    Text(text = "Social", style = themeTypography.h4)
                }
                HeightSpacer(height = 8.dp)
                HorizontalScroller {
                    Row {
                        data.forEach { item ->
                            HomeSocialCard(
                                data = item,
                                onClick = { goToScreen.invoke(GoToScreen.DetailSocial(item)) })
                        }
                    }
                }
            }
        }
    }
}
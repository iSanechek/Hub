package com.isanechek.averdhub.ui.home

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.HeightSpacer
import androidx.ui.layout.Padding
import androidx.ui.material.surface.Card
import com.isanechek.averdhub.data.models.GoToScreen
import com.isanechek.averdhub.ui.style.themeTypography
import com.isanechek.averdhub.ui.viewmodel.AppViewModel

@Composable
fun HomeActionSection(appViewModel: AppViewModel, goToScreen: (GoToScreen) -> Unit) {
    Padding(left = 16.dp, right = 16.dp) {
        Column {
            HeightSpacer(height = 8.dp)
            Padding(left = 8.dp) {
                Text(text = "Actions", style = themeTypography.h4)
            }
            HeightSpacer(height = 8.dp)
            Card(
                elevation = 0.dp,
                shape = RoundedCornerShape(16.dp),
                color = Color(0xFFF5F5F5),
                border = Border(Color(0xFFEEEEEEE), 1.dp)
            ) {
                Container(height = 200.dp, expanded = true) {



                }
            }
        }
    }
}
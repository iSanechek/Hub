package com.isanechek.averdhub.ui.apps.detail

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Container
import androidx.ui.layout.Padding
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import com.isanechek.averdhub.data.models.GoToScreen
import com.isanechek.averdhub.ext.PostImage

@Composable
fun ScreenshotCard(url: String, goToScreen: (GoToScreen) -> Unit) {
    Padding(left = 2.dp, right = 2.dp) {
        Card(
            elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            border = Border(color = (+MaterialTheme.colors()).onBackground, width = 1.dp)
        ) {
            Ripple(bounded = true) {
                Clickable(onClick = { goToScreen.invoke(GoToScreen.ImageViewer("")) }) {
                    Container(height = 100.dp, width = 80.dp, expanded = true) {
                        PostImage(imageUrl = "")
                    }
                }
            }
        }
    }
}
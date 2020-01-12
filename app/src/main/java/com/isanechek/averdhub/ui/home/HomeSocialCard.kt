package com.isanechek.averdhub.ui.home

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.Padding
import androidx.ui.layout.Spacing
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import com.isanechek.averdhub.data.models.SocialAction
import com.isanechek.averdhub.ext.PostImage

@Composable
fun HomeSocialCard(data: SocialAction, onClick: () -> Unit) {
    Padding(left = 2.dp, right = 2.dp, bottom = 2.dp) {
        Card(shape = RoundedCornerShape(8.dp)) {
            Ripple(bounded = true) {
                Clickable(onClick = onClick) {
                    Column {
                        Container(height = 100.dp, width = 140.dp, expanded = true) {
                            PostImage(imageUrl = "")
                        }
                        Column(modifier = Spacing(16.dp)) {
                            Text(text = data.title)
                            Text(text = data.network)
                        }
                    }
                }
            }
        }
    }
}
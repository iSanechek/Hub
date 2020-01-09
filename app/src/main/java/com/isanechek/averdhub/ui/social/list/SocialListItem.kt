package com.isanechek.averdhub.ui.social.list

import androidx.compose.Composable
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Column
import androidx.ui.layout.Container
import androidx.ui.layout.Padding
import androidx.ui.layout.Stack
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import com.isanechek.averdhub.data.models.SocialAction
import com.isanechek.averdhub.ext.PostImage
import com.isanechek.averdhub.ui.themeTypography

@Composable
fun SocialListItem(item: SocialAction, goTo: () -> Unit) {
    Card(elevation = 4.dp, shape = RoundedCornerShape(8.dp)) {
        Ripple(bounded = true) {
            Clickable(onClick = { goTo.invoke() }) {
                Column {
                    Container(height = 150.dp, expanded = true) {
                        PostImage(imageUrl = "")
                    }
                    Stack {
                        expanded { Container(expanded = true) {} }
                        aligned(alignment = Alignment.CenterLeft) {
                            Padding(padding = 8.dp) {
                                Column {
                                    Text(text = item.title, style = themeTypography.subtitle1)
                                    Text(text = item.network, style = themeTypography.subtitle2)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
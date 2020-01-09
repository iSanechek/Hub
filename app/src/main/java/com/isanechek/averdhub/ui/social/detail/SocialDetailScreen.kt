package com.isanechek.averdhub.ui.social.detail

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.surface.Card
import com.isanechek.averdhub.data.models.GoToScreen
import com.isanechek.averdhub.data.models.SocialAction
import com.isanechek.averdhub.ext.PostImage
import com.isanechek.averdhub.ext._drawable
import com.isanechek.averdhub.ui.components.VectorImageButton
import com.isanechek.averdhub.ui.style.themeTypography

interface SocialDetailScreen {

    companion object {
        @Composable
        fun Content(data: SocialAction, goToScreen: (GoToScreen) -> Unit) {
            FlexColumn {
                inflexible {
                    TopAppBar(
                        title = { Text(text = "Social detail") },
                        navigationIcon = {
                            VectorImageButton(
                                id = _drawable.ic_baseline_arrow_back_24,
                                onClick = { goToScreen.invoke(GoToScreen.GoBack) },
                                tint = (+MaterialTheme.colors()).onBackground
                            )
                        })
                }
                flexible(flex = 0f) { ShowContent(data = data, goTo = goToScreen) }
            }
        }

        @Composable
        private fun ShowContent(data: SocialAction, goTo: (GoToScreen) -> Unit) {
            VerticalScroller {
                Column {
                    Clickable(onClick = { goTo.invoke(GoToScreen.ImageViewer("")) }) {
                        Container(height = 150.dp, expanded = true) {
                            PostImage(imageUrl = "")
                        }
                    }

                    Card(elevation = 2.dp, shape = RoundedCornerShape(0.dp)) {
                        Stack {
                            expanded { Container(expanded = true) {} }
                            aligned(alignment = Alignment.CenterLeft) {
                                Padding(padding = 8.dp) {
                                    Column {
                                        Text(text = data.title, style = themeTypography.subtitle1)
                                        Text(text = data.network, style = themeTypography.subtitle2)
                                    }
                                }
                            }
                        }
                    }
                    Padding(padding = 8.dp) {
                        Card(elevation = 2.dp, shape = RoundedCornerShape(8.dp)) {
                            Padding(padding = 8.dp) {
                                Text(text = "Здесь должно быть описание приложение, но его пока нет. Так что здесь будет пока эта хрень. И хрени будет много!", style = themeTypography.body1)
                            }
                        }
                    }
                }
            }
        }
    }
}
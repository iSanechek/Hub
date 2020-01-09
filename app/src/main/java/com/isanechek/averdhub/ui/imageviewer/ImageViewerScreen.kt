package com.isanechek.averdhub.ui.imageviewer

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.DrawShadow
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.shape.DrawShape
import androidx.ui.foundation.shape.corner.CircleShape
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.layout.Container
import androidx.ui.layout.HeightSpacer
import androidx.ui.layout.Padding
import androidx.ui.layout.Stack
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview
import com.isanechek.averdhub.ext.PostImage
import com.isanechek.averdhub.ext._drawable
import com.isanechek.averdhub.ui.components.VectorImageButton

interface ImageViewerScreen {


    companion object {
        @Composable
        fun Content(goBack: () -> Unit) {
            Stack {
                expanded {
                    Container(expanded = true) {
                        PostImage(imageUrl = "")
                    }
                }

                aligned(alignment = Alignment.TopLeft) {
                    HeightSpacer(height = 8.dp)
                    Padding(top = 16.dp, left = 16.dp) {
                        Container(width = 45.dp, height = 45.dp, expanded = true) {
                            Stack(children = {
                                aligned(alignment = Alignment.Center) {
                                    DrawShape(
                                        shape = CircleShape,
                                        color = (+MaterialTheme.colors()).surface
                                    )
                                }
                                aligned(alignment = Alignment.Center) {
                                    VectorImageButton(
                                        id = _drawable.ic_baseline_arrow_back_24,
                                        onClick = { goBack.invoke() },
                                        tint = (+MaterialTheme.colors()).onBackground
                                    )
                                }
                            })
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun ViewerPreview() {
    ImageViewerScreen.Content(
        goBack = {}
    )
}
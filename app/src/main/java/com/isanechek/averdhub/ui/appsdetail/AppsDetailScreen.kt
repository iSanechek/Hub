package com.isanechek.averdhub.ui.appsdetail

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Dp
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.surface.Card
import androidx.ui.tooling.preview.Preview
import com.isanechek.averdhub.data.models.InstallApp
import com.isanechek.averdhub.ext.PostImage
import com.isanechek.averdhub.ext._drawable
import com.isanechek.averdhub.ui.components.VectorImageButton

interface AppsDetailScreen {

    companion object {
        @Composable
        fun Content(item: InstallApp, goBack: () -> Unit) {
            FlexColumn {
                inflexible { DetailAppsAppBar(title = item.name, goBack = goBack) }
                flexible(1f) {
                    VerticalScroller {
                        Column {
                            CoverSection(item = item)
                            AppInfoSection(item = item)
                            HeightSpacer(height = 16.dp)
                            ContentTextSection(item = item)
                        }
                    }
                }
            }
        }

        @Composable
        private fun DetailAppsAppBar(
            title: String,
            goBack: () -> Unit
        ) {
            TopAppBar(
                title = { Text(text = title) },
                navigationIcon = {
                    VectorImageButton(
                        id = _drawable.ic_baseline_arrow_back_24,
                        onClick = goBack,
                        tint = (+MaterialTheme.colors()).onBackground
                    )
                }
            )
        }

        @Composable
        private fun CoverSection(item: InstallApp) {

            Container(height = 180.dp, expanded = true) {
                PostImage(imageUrl = item.caverUrl)
            }
        }

        @Composable
        private fun AppInfoSection(item: InstallApp) {
            Card {
                Container(height = 80.dp, expanded = true) {
                    Row {
                        Column {
                            Text(text = item.name)
                            Text(text = item.version)
                        }

                    }
                }
            }
        }

        @Composable
        private fun ContentTextSection(item: InstallApp) {

            Padding(left = 16.dp, right = 16.dp) {
                Container(expanded = true) {
                    Column {

                    }
                }
            }

        }



    }
}

@Preview
@Composable
fun ADSPreview() {
    AppsDetailScreen.Content(item = InstallApp.testItem()) {}
}
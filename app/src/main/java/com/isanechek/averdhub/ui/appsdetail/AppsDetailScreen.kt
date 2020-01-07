package com.isanechek.averdhub.ui.appsdetail

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.Button
import androidx.ui.material.ButtonStyle
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import androidx.ui.res.vectorResource
import androidx.ui.tooling.preview.Preview
import com.isanechek.averdhub.data.models.InstallApp
import com.isanechek.averdhub.ext.*
import com.isanechek.averdhub.ui.components.SimpleVector
import com.isanechek.averdhub.ui.components.SmallInstallButton
import com.isanechek.averdhub.ui.components.VectorImageButton
import com.isanechek.averdhub.ui.themeTypography

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
            Card(elevation = 2.dp, color = (+MaterialTheme.colors()).background) {

                Stack {
                    aligned(alignment = Alignment.CenterLeft) {
                        Padding(left = 16.dp) {
                            Column {
                                Text(
                                    text = "version: ${item.version}",
                                    style = themeTypography.subtitle2
                                )
                            }
                        }
                    }


                    expanded {
                        // по другому хз как раскарячить стек.
                        Container(height = 80.dp, expanded = true) {


                        }
                    }

                    aligned(alignment = Alignment.CenterRight) {
                        Padding(right = 16.dp) {
                            setupStatusAction(status = item.status)
                        }
                    }

                }

            }
        }

        @Composable
        private fun ContentTextSection(item: InstallApp) {
            Padding(left = 8.dp, right = 8.dp, bottom = 8.dp) {
                Card(shape = RoundedCornerShape(8.dp), elevation = 2.dp) {
                    Padding(padding = 16.dp) {
                        Text(text = item.description, style = themeTypography.body1)
                    }
                }
            }
        }

        @Composable
        private fun setupStatusAction(status: String) {
            when (status) {
                InstallApp.STATUS_NOT_INSTALL -> SmallInstallButton(text = "install", onClick = {}, backgroundColor = red700)
                InstallApp.STATUS_INSTALL -> SmallInstallButton(text = "rating", onClick = {}, backgroundColor = green700)
                InstallApp.STATUS_NEED_UPDATE -> SmallInstallButton(text = "update", onClick = {}, backgroundColor = yellow700)
            }
        }
    }
}

@Preview
@Composable
fun ADSPreview() {
    AppsDetailScreen.Content(item = InstallApp.testItem()) {}
}
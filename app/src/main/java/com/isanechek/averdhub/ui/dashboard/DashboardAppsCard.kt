package com.isanechek.averdhub.ui.dashboard

import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.*
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.shape.border.Border
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Card
import androidx.ui.res.stringResource
import com.isanechek.averdhub.data.models.InstallApp
import com.isanechek.averdhub.ext.*
import com.isanechek.averdhub.ui.components.CircularImage
import com.isanechek.averdhub.ui.components.SimpleVector
import com.isanechek.averdhub.ui.themeTypography

private const val TAG = "DashboardAppsCard"

@Composable
fun DashboardAppsCard(item: InstallApp, callback: (InstallApp) -> Unit, isVertical: Boolean) {
    Padding(right = 2.dp, left = 2.dp) {
        Card(shape = RoundedCornerShape(8.dp)) {
            Ripple(bounded = true) {
                Clickable(onClick = { callback.invoke(item) }) {
                    Stack {
                        expanded {
                            Column {
                                Dp.Infinity
                                val widthSize = if (isVertical) null else 250.dp
                                Container(height = 120.dp, width = widthSize, expanded = true) {
                                    Clip(
                                        shape = RoundedCornerShape(
                                            topLeft = 8.dp,
                                            topRight = 8.dp,
                                            bottomLeft = 0.dp,
                                            bottomRight = 0.dp
                                        )
                                    ) {
                                        PostImage(imageUrl = item.caverUrl)
                                    }
                                }
                                Column(modifier = Spacing(16.dp)) {
                                    Text(text = item.name, style = themeTypography.h6)
                                    drawAppStatus(status = item.status)
                                }
                            }
                        }
                        aligned(alignment = Alignment.BottomRight) {
                            Padding(
                                padding = EdgeInsets(
                                    left = 0.dp,
                                    right = 16.dp,
                                    top = 0.dp,
                                    bottom = 55.dp
                                )
                            ) {
                                Card(
                                    border = Border(
                                        color = (+MaterialTheme.colors()).background,
                                        width = 1.dp
                                    ),
                                    shape = RoundedCornerShape(50.dp),
                                    elevation = 0.dp
                                ) {
                                    CircularImage(
                                        data = _drawable.test_img,
                                        size = 50.dp
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun drawAppStatus(status: String) {
    when (status) {
        InstallApp.STATUS_INSTALL -> drawStatus(
            iconId = _drawable.app_install_done,
            statusText = +stringResource(_text.app_install_status_done),
            color = green700
        )
        InstallApp.STATUS_NEED_UPDATE -> drawStatus(
            iconId = _drawable.app_install_alert,
            statusText = +stringResource(_text.app_install_status_update),
            color = yellow700
        )
        InstallApp.STATUS_NOT_INSTALL -> drawStatus(
            iconId = _drawable.app_install_alert,
            statusText = +stringResource(_text.app_install_status_not),
            color = red700
        )
    }
}


@Composable
private fun drawStatus(@DrawableRes iconId: Int, statusText: String, color: Color) {
    Row {
        Padding(padding = 2.dp) {
            SimpleVector(id = iconId, tint = color)
        }

        WidthSpacer(width = 2.dp)

        Padding(padding = 4.dp) {
            Text(
                text = statusText,
                style = themeTypography.body2
            )
        }
    }
}
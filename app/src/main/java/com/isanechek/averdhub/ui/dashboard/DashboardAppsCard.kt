package com.isanechek.averdhub.ui.dashboard

import android.util.Log
import androidx.compose.Composable
import androidx.ui.core.Clip
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
import androidx.ui.tooling.preview.Preview
import com.isanechek.averdhub.data.models.InstallApp
import com.isanechek.averdhub.ext.PostImage

private const val TAG = "DashboardAppsCard"

@Composable
fun DashboardAppsCard(item: InstallApp, callback: (InstallApp) -> Unit) {
    Padding(right = 2.dp, left = 2.dp) {
        Card(shape = RoundedCornerShape(8.dp)) {
            Ripple(bounded = true) {
                Clickable(onClick = {
                    Log.e(TAG, "DashboardAppsCard: Click")
                    callback.invoke(item)
                }) {
                    Column {

                        Container(height = 100.dp, width = 250.dp, expanded = false) {
                            Clip(shape = RoundedCornerShape(8.dp)) {
                                PostImage(imageUrl = item.caverUrl)
                            }
                        }
                        Column(modifier = Spacing(16.dp)) {
                            Text(text = item.name)
                            Text(text = item.status)
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun DACPreview() {
    DashboardAppsCard(InstallApp.testItem()) {}
}
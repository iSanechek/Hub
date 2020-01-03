package com.isanechek.averdhub.ui.dashboard

import android.util.Log
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
import androidx.ui.tooling.preview.Preview
import com.isanechek.averdhub.data.models.SocialAction

private const val TAG = "DashboardSocialCard"

@Composable
fun DashboardSocialCard(item: SocialAction, callback: (SocialAction) -> Unit) {

    Padding(left = 2.dp, right = 2.dp) {
        Card(shape = RoundedCornerShape(8.dp)) {
            Ripple(bounded = true) {
                Clickable(onClick = {
                    Log.e(TAG, "DashboardSocialCard: Click")
                    callback.invoke(item)
                }) {

                    Column {
                        Container(height = 100.dp, width = 140.dp, expanded = true) {

                        }
                        Column(modifier = Spacing(16.dp)) {
                            Text(text = item.title)
                            Text(text = item.network)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun testDSCard() {
    DashboardSocialCard(item = SocialAction.testData()) {
        Log.e(TAG, "testDSCard: click ${it.title}")
    }
}
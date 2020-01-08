package com.isanechek.averdhub.ui.dashboard

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Alignment
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.res.stringResource
import com.isanechek.averdhub.data.models.GoToScreen
import com.isanechek.averdhub.ext._drawable
import com.isanechek.averdhub.ext._text
import com.isanechek.averdhub.ext.observe
import com.isanechek.averdhub.ui.AppViewModel
import com.isanechek.averdhub.ui.components.CustomTopAppBar
import com.isanechek.averdhub.ui.components.VectorImageButton
import com.isanechek.averdhub.ui.themeTypography

interface DashboardScreen {

    companion object {
        private const val TAG = "DashboardScreen"

        @Composable
        fun Content(vm: AppViewModel, goToScreen: (GoToScreen) -> Unit) {
            FlexColumn {
                inflexible {
                    CustomTopAppBar(
                        title = { Text(text = "Hub") },
                        actionData = listOf(_drawable.ic_baseline_brightness_2_24)
                    ) { item ->
                        VectorImageButton(
                            id = item,
                            onClick = vm.changeTheme,
                            tint = (+MaterialTheme.colors()).onBackground
                        )
                    }
                }

                flexible(flex = 1f) {
                    VerticalScroller {
                        Column {
                            HeightSpacer(height = 16.dp)
                            SocialActionSection(vm, goToScreen)
                            HeightSpacer(height = 20.dp)
                            AppsSection(vm, goToScreen)
                        }
                    }
                }
            }
        }

        @Composable
        private fun SocialActionSection(vm: AppViewModel, goToScreen: (GoToScreen) -> Unit) {
            SectionTitle(
                title = +stringResource(_text.ds_social_title),
                click = goToScreen,
                isApps = false
            )

            HorizontalScroller {
                Row(modifier = Spacing(all = 8.dp)) {
                    val data = +observe(vm.socialData)
                    data?.forEach { item ->
                        DashboardSocialCard(
                            item = item,
                            callback = { data -> goToScreen.invoke(GoToScreen.DetailSocial(data)) })
                    }
                }
            }
        }

        @Composable
        private fun AppsSection(vm: AppViewModel, goToScreen: (GoToScreen) -> Unit) {

            SectionTitle(
                title = +stringResource(_text.ds_apps_title),
                click = goToScreen,
                isApps = true
            )
            HorizontalScroller {
                Row(modifier = Spacing(all = 8.dp)) {
                    val data = +observe(vm.shortAppsData)
                    data?.forEach {
                        DashboardAppsCard(
                            item = it,
                            callback = { item -> goToScreen.invoke(GoToScreen.DetailApp(item)) },
                            isVertical = false
                        )
                    }
                }
            }
        }

        @Composable
        private fun SectionTitle(title: String, click: (GoToScreen) -> Unit, isApps: Boolean) {
            Padding(left = 16.dp, right = 16.dp) {
                Stack {
                    expanded {
                        Container(expanded = true) {}
                    }

                    aligned(alignment = Alignment.CenterLeft) {
                        Text(text = title, style = themeTypography.h5)
                    }

                    aligned(alignment = Alignment.CenterRight) {
                        Ripple(bounded = false) {
                            Clickable(onClick = { click.invoke(if (isApps) GoToScreen.AllAppsScreen else GoToScreen.AllSocialScreen) }) {
                                Text(text = +stringResource(_text.ds_all_title))
                            }
                        }
                    }
                }
            }
            HeightSpacer(height = 6.dp)
        }
    }
}
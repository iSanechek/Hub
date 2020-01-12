package com.isanechek.averdhub.ui.home

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.Composable
import androidx.compose.state
import androidx.compose.unaryPlus
import androidx.ui.animation.Crossfade
import androidx.ui.core.*
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.*
import androidx.ui.material.MaterialTheme
import androidx.ui.material.ripple.Ripple
import androidx.ui.material.surface.Surface
import androidx.ui.text.TextStyle
import androidx.ui.text.font.FontWeight
import com.isanechek.averdhub.data.models.GoToScreen
import com.isanechek.averdhub.ext._drawable
import com.isanechek.averdhub.ui.components.CustomTopAppBar
import com.isanechek.averdhub.ui.components.SimpleVector
import com.isanechek.averdhub.ui.components.VectorImageButton
import com.isanechek.averdhub.ui.home.apps.HomeAppsScreen
import com.isanechek.averdhub.ui.style.appFontFamily
import com.isanechek.averdhub.ui.viewmodel.AppViewModel

interface HomeScreen {

    companion object {

        private const val TAG = "HomeScreen"
        private const val HOME_TAB = "hom.tab"
        private const val INFO_TAB = "info.tab"
        private const val APPS_TAB = "apps.tab"
        private val stateScreen = +state { HOME_TAB }

        @Composable
        fun Content(appViewModel: AppViewModel, goToScreen: (GoToScreen) -> Unit) {
            FlexColumn(crossAxisSize = LayoutSize.Expand, block = {
                inflexible {
                    val title = when(stateScreen.value) {
                        HOME_TAB -> "Hub"
                        INFO_TAB -> "Info"
                        APPS_TAB -> "Apps"
                        else -> ""
                    }
                    CustomTopAppBar(
                        title = { Text(text = title) },
                        actionData = listOf(_drawable.ic_baseline_brightness_2_24)
                    ) { item ->
                        VectorImageButton(
                            id = item,
                            onClick = appViewModel.changeTheme,
                            tint = (+MaterialTheme.colors()).onBackground
                        )
                    }
                }

                flexible(flex = 1f) {
                    Column(modifier = ExpandedHeight, arrangement = Arrangement.SpaceBetween) {
                        Crossfade(current = stateScreen) {
                            when(stateScreen.value) {
                                HOME_TAB -> VerticalScroller {
                                    Column {
                                        HomeActionSection(
                                            appViewModel = appViewModel,
                                            goToScreen = goToScreen
                                        )
                                        HomeSocialSection(
                                            appViewModel = appViewModel,
                                            goToScreen = goToScreen
                                        )
                                    }
                                }
                                INFO_TAB -> {}
                                APPS_TAB -> HomeAppsScreen(
                                    appViewModel = appViewModel,
                                    goToScreen = goToScreen
                                )
                            }
                        }
                    }
                }

                inflexible {
                    BottomBarSection(onClick = { screen ->
                        stateScreen?.value = screen
                        Log.e(TAG, "Content: SCREEN $screen")
                    })
                }
            })
        }

        @Composable
        private fun BottomBarSection(onClick: (String) -> Unit) {
            val stateScreen = +state { HOME_TAB }
            Surface(elevation = 2.dp) {
                Container(height = 56.dp, expanded = true) {
                    Stack(children = {
                        expanded { Container(expanded = true) {} }
                        aligned(alignment = Alignment.CenterLeft) {
                            Padding(left = 54.dp) {
                                BottomBarButton(
                                    iconId = _drawable.bottom_nav_info_icon,
                                    title = "info",
                                    click = {
                                        INFO_TAB.apply {
                                            stateScreen.value = this
                                            onClick.invoke(this)
                                        }
                                    },
                                    leftPadding = 2.dp,
                                    isSelect = stateScreen.value == INFO_TAB
                                )
                            }
                        }
                        aligned(alignment = Alignment.Center) {
                            BottomBarButton(
                                iconId = _drawable.bottom_nav_home_icon,
                                title = "home",
                                click = {
                                    HOME_TAB.apply {
                                        stateScreen.value = this
                                        onClick.invoke(this)
                                    }
                                },
                                leftPadding = 8.dp,
                                isSelect = stateScreen.value == HOME_TAB
                            )
                        }
                        aligned(alignment = Alignment.CenterRight) {
                            Padding(right = 54.dp) {
                                BottomBarButton(
                                    iconId = _drawable.bottom_nav_apps_icon,
                                    title = "apps",
                                    click = {
                                        APPS_TAB.apply {
                                            stateScreen.value = this
                                            onClick.invoke(this)
                                        }
                                    },
                                    leftPadding = 4.dp,
                                    isSelect = stateScreen.value == APPS_TAB
                                )
                            }
                        }
                    })
                }
            }
        }

        @Composable
        private fun BottomBarButton(
            @DrawableRes iconId: Int, title: String,
            click: () -> Unit,
            leftPadding: Dp,
            isSelect: Boolean
        ) {
            Ripple(bounded = false) {
                Clickable(onClick = click) {
                    Column {
                        Padding(left = leftPadding) {
                            SimpleVector(
                                iconId,
                                if (isSelect) (+MaterialTheme.colors()).secondary else (+MaterialTheme.colors()).onBackground
                            )
                        }
                        Text(
                            text = title,
                            style = TextStyle(
                                color = if (isSelect) (+MaterialTheme.colors()).secondary else (+MaterialTheme.colors()).onBackground,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.W500,
                                fontFamily = appFontFamily
                            )
                        )
                    }
                }
            }
        }
    }
}
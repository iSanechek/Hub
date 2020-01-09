package com.isanechek.averdhub.ui.social.list

import androidx.compose.Composable
import androidx.compose.unaryPlus
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.foundation.VerticalScroller
import androidx.ui.layout.Column
import androidx.ui.layout.FlexColumn
import androidx.ui.layout.Padding
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import androidx.ui.res.stringResource
import com.isanechek.averdhub.data.models.GoToScreen
import com.isanechek.averdhub.ext._drawable
import com.isanechek.averdhub.ext._text
import com.isanechek.averdhub.ext.observe
import com.isanechek.averdhub.ui.AppViewModel
import com.isanechek.averdhub.ui.components.VectorImageButton

interface SocialListScreen {

    companion object {

        @Composable
        fun Content(appViewModel: AppViewModel, goToScreen: (GoToScreen) -> Unit) {
            FlexColumn {
                inflexible {
                    TopAppBar(
                        title = { Text(text = +stringResource(_text.asa_toolbar_title)) },
                        navigationIcon = {
                            VectorImageButton(
                                id = _drawable.ic_baseline_arrow_back_24,
                                onClick = { goToScreen.invoke(GoToScreen.GoBack) },
                                tint = (+MaterialTheme.colors()).onBackground)
                        }
                    )
                }
                flexible(flex = 0f) {
                    ShowContent(
                        appViewModel,
                        goToScreen
                    )
                }
            }
        }

        @Composable
        private fun ShowContent(appViewModel: AppViewModel, goToScreen: (GoToScreen) -> Unit) {
            val data = +observe(appViewModel.socialData)
            if (!data.isNullOrEmpty()) {
                VerticalScroller {
                    Column {
                        data.forEach { item ->
                            Padding(left = 8.dp, right = 8.dp, top = 5.dp, bottom = 3.dp) {
                                SocialListItem(
                                    item = item,
                                    goTo = { })
                            }
                        }
                    }
                }
            }
        }
    }
}
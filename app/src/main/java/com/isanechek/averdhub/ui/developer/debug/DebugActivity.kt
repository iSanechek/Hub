package com.isanechek.averdhub.ui.developer.debug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.ui.core.Text
import androidx.ui.core.setContent
import androidx.ui.layout.Container
import androidx.ui.layout.FlexColumn
import androidx.ui.material.MaterialTheme
import androidx.ui.material.TopAppBar
import com.isanechek.averdhub.data.network.InstagramParser
import com.isanechek.averdhub.ui.home.HomeScreen
import com.isanechek.averdhub.ui.viewmodel.AppViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.scope.currentScope
import org.koin.androidx.viewmodel.ext.android.viewModel

class DebugActivity : AppCompatActivity() {

    private val appViewModel: AppViewModel by viewModel()
    private val instagram: InstagramParser by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {

                HomeScreen.Content(
                    appViewModel = appViewModel,
                    goToScreen = {}
                )
//                FlexColumn {
//                    inflexible { TopAppBar(title = { Text(text = "Debug") }) }
//                    flexible(flex = 1f) {
//                        Container(expanded = true) {
//                            Text(text = "Info")
//                        }
//                    }
//                }
            }
        }

//        GlobalScope.launch {
//            instagram.parseFeed("isanechek_")
//        }
    }
}
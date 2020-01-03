package com.isanechek.averdhub

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import com.github.zsoltk.compose.backpress.BackPressHandler
import com.github.zsoltk.compose.savedinstancestate.TimeCapsule
import com.isanechek.averdhub.data.models.InstallApp
import com.isanechek.averdhub.ui.Root
import com.isanechek.averdhub.ui.UserNavigation
import com.isanechek.averdhub.ui.dashboard.DashboardViewModel
//import com.isanechek.averdhub.ui.dashboard.DashboardViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val dashboardViewModel by viewModel<DashboardViewModel>()
    private val backPressHandler = BackPressHandler()
    private val timeCapsule = TimeCapsule()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                backPressHandler.Provider {
                    timeCapsule.Provider(savedInstanceState = savedInstanceState) {
                        Root.Content(
                            defaultRouting = Root.Routing.User(dashboardViewModel)
                        )
                    }
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        timeCapsule.onSaveInstanceState(outState)
    }
}
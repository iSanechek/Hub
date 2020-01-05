package com.isanechek.averdhub

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.unaryPlus
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.ui.core.setContent
import androidx.ui.material.MaterialTheme
import androidx.ui.material.surface.Surface
import com.github.zsoltk.compose.backpress.BackPressHandler
import com.github.zsoltk.compose.savedinstancestate.TimeCapsule
import com.isanechek.averdhub.ui.Root
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
            MaterialTheme(dashboardViewModel.currentTheme) {
                Surface(color = (+MaterialTheme.colors()).background) {
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

        dashboardViewModel.darkStatusBar.observe(this, Observer(::changeColorStatusBar))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        timeCapsule.onSaveInstanceState(outState)
    }

    private fun changeColorStatusBar(light: Boolean) {
        val decor = window.decorView
        when {
            !light -> {

//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                var flags = decor.systemUiVisibility
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                decor.systemUiVisibility = flags
                window.statusBarColor = Color.WHITE
                window.navigationBarColor = Color.WHITE
            }
            else -> {
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//                window.statusBarColor = ContextCompat.getColor(this, R.color.hubDarkPrimaryDark)
                decor.systemUiVisibility = 0
                window.statusBarColor = Color.BLACK
                window.navigationBarColor = Color.BLACK
            }
        }
    }
}
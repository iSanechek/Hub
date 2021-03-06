package com.isanechek.averdhub.ui.navigation

import android.util.Log
import androidx.compose.Composable
import com.github.zsoltk.compose.router.Router
import com.isanechek.averdhub.ui.viewmodel.AppViewModel
import com.isanechek.averdhub.ui.developer.Developer

interface Root {
    sealed class Routing {
        data class User(val appViewModel: AppViewModel) : Routing()
        data class God(val appViewModel: AppViewModel) : Routing()
    }

    companion object {
        private const val TAG = "RootNavigation"

        @Composable
        fun Content(defaultRouting: Routing) {
            Router(contextId = "Root", defaultRouting = defaultRouting) { backStack ->
                when (val currentRouting = backStack.last()) {
                    is Routing.User -> {
                        Log.e(TAG, "Content: Go to User")
                        UserNavigation.Content(
                            defaultRouting = UserNavigation.Routing.Dashboard(currentRouting.appViewModel)
                        )
                    }
                    is Routing.God -> {
                        Log.e(TAG, "Content: Go to Developer")
                        Developer.Content(
                            defaultRouting = Developer.Routing.Hello
                        )
                    }
                }
            }
        }
    }
}
package com.isanechek.averdhub.data.models

sealed class GoToScreen {
    object AllSocialScreen : GoToScreen()
    object AllAppsScreen : GoToScreen()
    data class DetailApp(val data: InstallApp) : GoToScreen()
    data class DetailSocial(val data: SocialAction) : GoToScreen()
    data class ImageViewer(val url: String) : GoToScreen()
    object GoBack : GoToScreen()
}
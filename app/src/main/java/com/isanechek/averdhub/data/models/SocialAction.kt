package com.isanechek.averdhub.data.models

data class SocialAction(val title: String, val network: String) {

    companion object {
        fun testData() = SocialAction(title = "Hello World", network = "Instagram")
    }
}
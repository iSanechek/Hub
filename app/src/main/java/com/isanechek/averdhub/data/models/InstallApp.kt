package com.isanechek.averdhub.data.models

data class InstallApp(
    val packageName: String,
    val name: String,
    val status: String,
    val caverUrl: String,
    val iconUrl: String,
    val version: String,
    val description: String
) {

    companion object {
        const val STATUS_NEED_UPDATE = "need.update"
        const val STATUS_NOT_INSTALL = "not.install"
        const val STATUS_INSTALL = "install"

        fun testItem() = InstallApp(
            packageName = "my.wallpapers.averdsoft",
            name = "WallpaperX",
            status = "Not install",
            iconUrl = "",
            caverUrl = "",
            version = "2.01.1(6544)",
            description = "Здесь должно быть описание приложение, но его пока нет. Так что здесь будет пока эта хрень. И хрени будет много!"
        )
    }
}
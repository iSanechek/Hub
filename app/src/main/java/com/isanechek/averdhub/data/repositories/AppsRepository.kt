package com.isanechek.averdhub.data.repositories

import android.content.Context
import android.util.Log
import com.isanechek.averdhub.data.models.AverdApp
import com.isanechek.averdhub.data.models.InstallApp
import com.isanechek.averdhub.data.models.OperationResult
import com.isanechek.averdhub.utils.AppsUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface AppsRepository {
    suspend fun loadInstallingApps(): OperationResult<List<InstallApp>>
}

private const val TAG = "AppsRepository"

class AppsRepositoryImpl(
    private val context: Context,
    private val appsUtils: AppsUtils
) : AppsRepository {

    override suspend fun loadInstallingApps(): OperationResult<List<InstallApp>> =
        withContext(Dispatchers.IO) {
            when (val result = appsUtils.getInstallingApps(context)) {
                is OperationResult.Success -> {
                    val packages = result.data
                    when {
                        packages.isNotEmpty() -> {
                            val temp = mutableListOf<InstallApp>()
                            packages.forEach { info ->
                                var status = InstallApp.STATUS_NOT_INSTALL
                                val averdApp = testData.find { it.packageName == info.packageName }
                                if (averdApp != null) {
                                    Log.e(
                                        TAG,
                                        "transformation: Install Version ${info.versionName}"
                                    )
                                    Log.e(TAG, "transformation: Averd Version ${averdApp.version}")
                                    status = when {
                                        !info.versionName.contains(averdApp.version.toRegex()) -> {
                                            InstallApp.STATUS_NEED_UPDATE
                                        }
                                        else -> {
                                            InstallApp.STATUS_INSTALL
                                        }
                                    }

                                    temp.add(
                                        InstallApp(
                                            name = averdApp.name,
                                            packageName = info.packageName,
                                            status = status,
                                            caverUrl = "",
                                            iconUrl = "",
                                            version = "",
                                            description = ""
                                        )
                                    )
                                }
                            }

                            if (temp.isNotEmpty()) {
                                temp.forEach { item ->
                                    testData.filter { app -> app.packageName != item.packageName }
                                        .forEach { i ->
                                            temp.add(
                                                InstallApp(
                                                    name = i.name,
                                                    packageName = i.packageName,
                                                    status = InstallApp.STATUS_NOT_INSTALL,
                                                    iconUrl = "",
                                                    caverUrl = "",
                                                    version = "",
                                                    description = ""
                                                )
                                            )
                                        }
                                }
                            }

                            OperationResult.Success(temp)
                        }
                        else -> OperationResult.Error(OperationResult.ErrorState.Unknown)
                    }
                }
                is OperationResult.Error -> OperationResult.Error(result.error)
                else -> OperationResult.Error(OperationResult.ErrorState.Unknown)
            }
        }

    private val testData = listOf(
        AverdApp(
            name = "WallpapersX",
            packageName = "my.wallpapers.averdsoft",
            coverUrl = "",
            iconUrl = "",
            version = "2.0.14"
        ),
        AverdApp(
            name = "БалтТур",
            packageName = "com.averdsoft.balttur",
            coverUrl = "",
            iconUrl = "",
            version = "1.0.1"
        ),
        AverdApp(
            name = "СваркаСпБ",
            packageName = "com.svarochnyeraboty.tosno",
            coverUrl = "",
            iconUrl = "",
            version = "1.2.2"
        )
    )

}
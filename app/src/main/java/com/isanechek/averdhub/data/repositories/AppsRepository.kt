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
                                testData.find { it.packageName == info.packageName }?.let { app ->
                                    val status = when {
                                        !info.versionName.contains(app.version) -> InstallApp.STATUS_NEED_UPDATE
                                        else -> InstallApp.STATUS_INSTALL
                                    }
                                    temp.add(app.toInstallApp(status))
                                }
                            }

                            when {
                                temp.isNotEmpty() -> testData.filter { item -> item.packageName !in temp.map { it.packageName }.toSet() }
                                    .forEach { app -> temp.add(app.toInstallApp(InstallApp.STATUS_NOT_INSTALL))}
                                else -> testData.forEach { item -> temp.add(item.toInstallApp(InstallApp.STATUS_NOT_INSTALL)) }
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

    private fun AverdApp.toInstallApp(status: String): InstallApp = InstallApp(
        packageName = this.packageName,
        status = status,
        name = this.name,
        iconUrl = this.iconUrl,
        caverUrl = this.coverUrl,
        version = this.version,
        description = this.description
    )

    private val testData = listOf(
        AverdApp(
            name = "WallpapersX",
            packageName = "my.wallpapers.averdsoft",
            coverUrl = "",
            iconUrl = "",
            version = "2.0.14",
            description = "Здесь должно быть описание приложение, но его пока нет. Так что здесь будет пока эта хрень. И хрени будет много!"
        ),
        AverdApp(
            name = "БалтТур",
            packageName = "com.averdsoft.balttur",
            coverUrl = "",
            iconUrl = "",
            version = "1.0.1 (689)",
            description = "Здесь должно быть описание приложение, но его пока нет. Так что здесь будет пока эта хрень. И хрени будет много!"
        ),
        AverdApp(
            name = "СваркаСпБ",
            packageName = "com.svarochnyeraboty.tosno",
            coverUrl = "",
            iconUrl = "",
            version = "1.2.2",
            description = "Здесь должно быть описание приложение, но его пока нет. Так что здесь будет пока эта хрень. И хрени будет много!"
        )
    )

}
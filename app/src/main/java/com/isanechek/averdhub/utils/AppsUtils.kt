package com.isanechek.averdhub.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.isanechek.averdhub.data.models.OperationResult
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface AppsUtils {
    suspend fun getInstallingApps(context: Context): OperationResult<List<PackageInfo>>
}

class AppsUtilsImpl : AppsUtils {

    override suspend fun getInstallingApps(context: Context): OperationResult<List<PackageInfo>> = suspendCoroutine { c ->
        try {
            val pm = context.packageManager
            val packages = pm.getInstalledPackages(PackageManager.GET_META_DATA)
            when {
                packages.isNullOrEmpty() -> c.resume(OperationResult.Error(OperationResult.ErrorState.Empty))
                else -> c.resume(OperationResult.Success(packages))
            }
        } catch (ex: Exception) {
            c.resume(OperationResult.Error(OperationResult.ErrorState.Empty))
        }
    }
}
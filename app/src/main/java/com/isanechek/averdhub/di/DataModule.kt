package com.isanechek.averdhub.di

import com.isanechek.averdhub.data.repositories.AppsRepository
import com.isanechek.averdhub.data.repositories.AppsRepositoryImpl
import com.isanechek.averdhub.utils.AppsUtils
import com.isanechek.averdhub.utils.AppsUtilsImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataModule = module {

    // Apps Repository
    single<AppsRepository> {
        AppsRepositoryImpl(
            androidContext(),
            get()
        )
    }

    // Apps Utils
    factory<AppsUtils> {
        AppsUtilsImpl()
    }
}
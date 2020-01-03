package com.isanechek.averdhub

import android.app.Application
import com.isanechek.averdhub.di.appModule
import com.isanechek.averdhub.di.dataModule
//import com.isanechek.averdhub.di.appModule
//import com.isanechek.averdhub.di.dataModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(appModule, dataModule))
        }
    }
}
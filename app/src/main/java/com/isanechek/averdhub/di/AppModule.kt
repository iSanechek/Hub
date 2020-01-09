package com.isanechek.averdhub.di

import com.isanechek.averdhub.ui.viewmodel.AppViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel {
        AppViewModel(get())
    }
}
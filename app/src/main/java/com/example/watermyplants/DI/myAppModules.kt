package com.example.watermyplants.DI

import com.example.watermyplants.Repository.CameraRepository
import com.example.watermyplants.ViewModel.CameraViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myAppModules = module {
    viewModel {
        CameraViewModel(get(), get())
    }

    single {
        CameraRepository()
    }

    single {
        androidApplication()
    }


}
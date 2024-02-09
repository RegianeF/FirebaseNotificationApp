package com.example.firebasenotificationapp.di

import com.example.firebasenotificationapp.ui.chat.ChatViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

fun appModuleConfig() = module {

}

fun viewModelModules() = module {

    viewModelOf(::ChatViewModel)
}
package com.ateam.herbacrop.di

import com.ateam.herbacrop.favorite.FavoriteViewModel
import com.ateam.herbacrop.ui.viewmodel.AboutViewModel
import com.ateam.herbacrop.ui.viewmodel.HomeViewModel
import com.ateam.herbacrop.ui.viewmodel.LibraryViewModel
import com.ateam.herbacrop.ui.viewmodel.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { FavoriteViewModel(get()) }
    viewModel { AboutViewModel(get()) }
    viewModel { HomeViewModel(get()) }
    viewModel { SearchViewModel(get()) }
    viewModel { LibraryViewModel(get()) }
}
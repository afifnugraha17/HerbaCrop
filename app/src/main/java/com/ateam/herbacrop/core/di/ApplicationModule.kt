package com.ateam.herbacrop.core.di

import com.ateam.herbacrop.core.data.Repository
import com.ateam.herbacrop.core.data.source.local.LocalSource
import com.ateam.herbacrop.core.domain.repository.IRepository
import com.ateam.herbacrop.core.domain.usecase.PlantInteractor
import com.ateam.herbacrop.core.domain.usecase.PlantUseCase
import org.koin.dsl.module

val applicationModule = module {
    single<IRepository> {
        Repository(get())
    }

    single {
        LocalSource(get())
    }

    single<PlantUseCase> {
        PlantInteractor(get())
    }
}
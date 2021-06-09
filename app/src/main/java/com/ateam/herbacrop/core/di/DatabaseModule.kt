package com.ateam.herbacrop.core.di

import androidx.room.Room
import com.ateam.herbacrop.core.data.source.local.database.HerbacropDatabase
import com.ateam.herbacrop.core.utils.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), HerbacropDatabase::class.java, Constants.dbName)
            .fallbackToDestructiveMigration()
            .build()
    }
    single {
        get<HerbacropDatabase>().plantDao()
    }
}
package com.ateam.herbacrop

import android.app.Application
import com.ateam.herbacrop.core.di.applicationModule
import com.ateam.herbacrop.core.di.databaseModule
import com.ateam.herbacrop.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@App)
            modules(
                applicationModule,
                databaseModule,
                viewModelModule
            )
        }
    }
}
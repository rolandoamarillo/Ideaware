package com.rolando.amarillo.ideaware

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class IdeawareApp : Application() {

    override fun onCreate() {
        super.onCreate()

        //Starting Koin
        startKoin {
            androidLogger()
            androidContext(this@IdeawareApp)
            modules(appModule)
        }
    }

}
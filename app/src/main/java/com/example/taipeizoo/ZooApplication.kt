package com.example.taipeizoo

import android.app.Application
import android.content.Context
import com.example.taipeizoo.koin.appModule
import com.facebook.drawee.backends.pipeline.Fresco
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ZooApplication : Application() {

    override fun attachBaseContext(base: Context) {

        startKoin {
            androidContext(base)
            modules(appModule)
        }

        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()

        Fresco.initialize(this)
    }
}
package com.contraomnese.courses.app

import android.app.Application
import com.contraomnese.courses.di.appModule
import com.contraomnese.courses.di.dataModule
import com.contraomnese.courses.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                appModule,
                dataModule,
                domainModule
            )
        }
//        if (BuildConfig.DEBUG) {
//            StrictMode.setThreadPolicy(
//                StrictMode.ThreadPolicy.Builder()
//                    .detectDiskReads()
//                    .detectDiskWrites()
//                    .detectNetwork()
//                    .penaltyLog()
//                    .build()
//            )
//        }
    }
}
package io.github.cleitonmonteiro.androidarchitecturebasics.ui

import android.app.Application
import io.github.cleitonmonteiro.androidarchitecturebasics.BuildConfig
import io.github.cleitonmonteiro.androidarchitecturebasics.core.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(){
            androidLogger()
            androidContext(this@MainApplication)
            modules(appModule)
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

}

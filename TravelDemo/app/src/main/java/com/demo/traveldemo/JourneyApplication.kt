package com.demo.traveldemo

import android.app.Application
import android.content.Context

class JourneyApplication : Application() {

    companion object {
        lateinit var instance: JourneyApplication
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    // 提供全局的Context
    fun getAppContext(): Context {
        return applicationContext
    }
}

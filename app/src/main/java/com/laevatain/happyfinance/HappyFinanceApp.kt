package com.laevatain.happyfinance

import android.app.Application
import com.laevatain.happyfinance.di.AppContainer

class HappyFinanceApp : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppContainer(this)
    }
}
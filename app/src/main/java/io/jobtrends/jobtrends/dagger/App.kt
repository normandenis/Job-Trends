package io.jobtrends.jobtrends.dagger

import android.app.Application

class App : Application() {

    companion object {

        lateinit var component: AppComponent

        lateinit var app: App

    }

    init {
        component = DaggerAppComponent.create()
        app = this
    }
}
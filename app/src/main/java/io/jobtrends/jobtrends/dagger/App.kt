package io.jobtrends.jobtrends.dagger

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import io.github.inflationx.calligraphy3.CalligraphyConfig
import io.github.inflationx.calligraphy3.CalligraphyInterceptor
import io.github.inflationx.viewpump.ViewPump
import io.jobtrends.jobtrends.R


class App : Application() {

    companion object {

        lateinit var component: AppComponent

        lateinit var app: App

    }

    init {
        component = DaggerAppComponent.create()
        app = this
        Logger.addLogAdapter(AndroidLogAdapter())
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(
                    CalligraphyInterceptor(
                        CalligraphyConfig.Builder()
                            .setDefaultFontPath("fonts/Lato-Light.ttf")
                            .setFontAttrId(R.attr.fontPath)
                            .build()
                    )
                )
                .build()
        )
    }
}
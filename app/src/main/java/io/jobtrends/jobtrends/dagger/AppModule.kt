package io.jobtrends.jobtrends.dagger

import android.content.Context
import android.content.res.Resources
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.jobtrends.jobtrends.managers.BoardingManager
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.managers.RawManager
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideBoardingManager(): BoardingManager = BoardingManager()

    @Provides
    @Singleton
    fun provideRawManager(): RawManager = RawManager()

    @Provides
    @Singleton
    fun provideJsonManager(): JsonManager = JsonManager()

    @Provides
    @Singleton
    fun provideContext(): Context = App.app.applicationContext

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideResources(): Resources = App.app.resources
}
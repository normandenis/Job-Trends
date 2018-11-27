package io.jobtrends.jobtrends.dagger

import android.content.Context
import android.content.res.Resources
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.jobtrends.jobtrends.managers.*
import io.jobtrends.jobtrends.models.JobModel
import io.jobtrends.jobtrends.wrappers.Wrapper
import javax.inject.Singleton

@Module
class AppModule {

    private val wrapper = Wrapper()

    @Provides
    @Singleton
    fun provideWrapper(): Wrapper = wrapper

    @Provides
    fun provideJobModel(): JobModel = wrapper.getInstance()

    @Provides
    fun provideContext(): Context = wrapper.getInstance()

    @Provides
    @Singleton
    fun provideTrainingManager(): TrainingManager = TrainingManager()

    @Provides
    @Singleton
    fun provideJobManager(): JobManager = JobManager()

    @Provides
    @Singleton
    fun provideBoardingManager(): BoardingManager = BoardingManager()

    @Provides
    @Singleton
    fun provideHomeManager(): HomeManager = HomeManager()

    @Provides
    @Singleton
    fun provideRawManager(): RawManager = RawManager()

    @Provides
    fun provideJsonManager(): JsonManager = JsonManager()

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideResources(): Resources = App.app.resources
}
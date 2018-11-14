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

    private val jobModelWrapper: Wrapper<JobModel> = Wrapper()

    // TODO: Context Wrapper
    private val contextWrapper: Wrapper<Context> = Wrapper()

    @Provides
    @Singleton
    fun provideSingletonWrapperJobModel(): Wrapper<JobModel> = jobModelWrapper

    @Provides
    fun provideJobModel(): JobModel = jobModelWrapper.obj ?: JobModel()

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
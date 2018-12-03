package io.jobtrends.jobtrends.dagger

import android.content.res.Resources
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import io.jobtrends.jobtrends.managers.ApiManager
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.managers.RawManager
import io.jobtrends.jobtrends.models.JobModel
import io.jobtrends.jobtrends.viewmodels.*
import io.jobtrends.jobtrends.wrappers.Wrapper
import javax.inject.Singleton

@Module
class AppModule {

    // region App Tools

    private val wrapper = Wrapper()

    @Provides
    @Singleton
    fun provideGson(): Gson = Gson()

    @Provides
    @Singleton
    fun provideWrapper(): Wrapper = wrapper

    @Provides
    @Singleton
    fun provideResources(): Resources = App.app.resources

    // endregion

    // region Managers

    @Provides
    @Singleton
    fun provideRawManager(): RawManager = RawManager()

    @Provides
    @Singleton
    fun provideJsonManager(): JsonManager = JsonManager()

    @Provides
    @Singleton
    fun provideApiManager(): ApiManager = ApiManager()

    // endregion

    // region Models

    @Provides
    fun provideJobModel(): JobModel = wrapper.getInstance()

    // endregion

    // region ViewModels

    @Provides
    @Singleton
    fun provideBoardingViewModel(): BoardingViewModel = BoardingViewModel()

    @Provides
    @Singleton
    fun provideExperienceViewModel(): ExperienceViewModel = ExperienceViewModel()

    @Provides
    @Singleton
    fun provideHomeViewModel(): HomeViewModel = HomeViewModel()

    @Provides
    @Singleton
    fun provideJobViewModel(): JobViewModel = JobViewModel()

    @Provides
    @Singleton
    fun providePassionViewModel(): PassionViewModel = PassionViewModel()

    @Provides
    @Singleton
    fun provideTrainingViewModel(): TrainingViewModel = TrainingViewModel()

    @Provides
    @Singleton
    fun provideUserViewModel(): UserViewModel = UserViewModel()

    // endregion
}
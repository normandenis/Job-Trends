package io.jobtrends.jobtrends.dagger

import dagger.Component
import io.jobtrends.jobtrends.activities.*
import io.jobtrends.jobtrends.fragments.*
import io.jobtrends.jobtrends.managers.*
import io.jobtrends.jobtrends.models.UserModel
import io.jobtrends.jobtrends.viewmodels.*
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    // region Activities

    fun inject(boardingActivity: BoardingActivity)
    fun inject(curriculumActivity: CurriculumActivity)
    fun inject(homeActivity: HomeActivity)
    fun inject(jobActivity: JobActivity)
    fun inject(splashActivity: SplashActivity)

    // endregion

    // region Fragments

    fun inject(boardingFragment: BoardingFragment)
    fun inject(experienceEmptyFragment: ExperienceEmptyFragment)
    fun inject(experienceFragment: ExperienceFragment)
    fun inject(passionEmptyFragment: PassionEmptyFragment)
    fun inject(passionFragment: PassionFragment)
    fun inject(trainingEmptyFragment: TrainingEmptyFragment)
    fun inject(trainingFragment: TrainingFragment)
    fun inject(userFragment: UserFragment)

    // endregion

    // region Managers

    fun inject(boardingViewModel: BoardingViewModel)
    fun inject(experienceViewModel: ExperienceViewModel)
    fun inject(homeViewModel: HomeViewModel)
    fun inject(jobViewModel: JobViewModel)
    fun inject(jsonManager: JsonManager)
    fun inject(passionManager: PassionViewModel)
    fun inject(rawManager: RawManager)
    fun inject(trainingManager: TrainingViewModel)

    // endregion
}
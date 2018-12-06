package io.jobtrends.jobtrends.dagger

import dagger.Component
import io.jobtrends.jobtrends.activities.*
import io.jobtrends.jobtrends.fragments.*
import io.jobtrends.jobtrends.managers.*
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
    fun inject(resultActivity: ResultActivity)
    fun inject(splashActivity: SplashActivity)

    // endregion

    // region Fragments

    fun inject(boardingFragment: BoardingFragment)
    fun inject(experienceEmptyFragment: ExperienceEmptyFragment)
    fun inject(experienceFragment: ExperienceFragment)
    fun inject(skillEmptyFragment: SkillEmptyFragment)
    fun inject(skillFragment: SkillFragment)
    fun inject(trainingEmptyFragment: TrainingEmptyFragment)
    fun inject(trainingFragment: TrainingFragment)
    fun inject(userFragment: UserFragment)

    // endregion

    // region Managers

    fun inject(jsonManager: JsonManager)
    fun inject(rawManager: RawManager)

    // endregion

    // region ViewModels

    fun inject(boardingViewModel: BoardingViewModel)
    fun inject(experienceViewModel: ExperienceViewModel)
    fun inject(homeViewModel: HomeViewModel)
    fun inject(jobViewModel: JobViewModel)
    fun inject(resultViewModel: ResultViewModel)
    fun inject(skillManager: SkillViewModel)
    fun inject(trainingManager: TrainingViewModel)
    fun inject(userViewModel: UserViewModel)

    // endregion
}
package io.jobtrends.jobtrends.dagger

import dagger.Component
import io.jobtrends.jobtrends.activities.*
import io.jobtrends.jobtrends.adapters.PagerAdapter
import io.jobtrends.jobtrends.fragments.BoardingFragment
import io.jobtrends.jobtrends.fragments.TrainingEmptyFragment
import io.jobtrends.jobtrends.fragments.TrainingFragment
import io.jobtrends.jobtrends.managers.*
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    // Activities

    fun inject(boardingActivity: BoardingActivity)

    fun inject(boardingActivity: HomeActivity)

    fun inject(boardingActivity: SplashActivity)

    fun inject(jobActivity: JobActivity)

    fun inject(trainingActivity: TrainingActivity)

    // Fragments

    fun inject(boardingFragment: BoardingFragment)

    fun inject(trainingFragment: TrainingFragment)

    fun inject(trainingEmptyFragment: TrainingEmptyFragment)

    // Adapters

    fun inject(pagerAdapter: PagerAdapter)

    // Managers

    fun inject(jsonManager: JsonManager)

    fun inject(rawManager: RawManager)

    fun inject(boardingManager: BoardingManager)

    fun inject(homeManager: HomeManager)

    fun inject(jobManager: JobManager)

    fun inject(trainingManager: TrainingManager)
}
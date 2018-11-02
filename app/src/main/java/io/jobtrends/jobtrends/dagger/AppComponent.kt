package io.jobtrends.jobtrends.dagger

import dagger.Component
import io.jobtrends.jobtrends.activities.BoardingActivity
import io.jobtrends.jobtrends.activities.HomeActivity
import io.jobtrends.jobtrends.activities.JobActivity
import io.jobtrends.jobtrends.activities.SplashActivity
import io.jobtrends.jobtrends.adapters.BoardingAdapter
import io.jobtrends.jobtrends.adapters.HomeAdapter
import io.jobtrends.jobtrends.fragments.BoardingFragment
import io.jobtrends.jobtrends.managers.BoardingManager
import io.jobtrends.jobtrends.managers.HomeManager
import io.jobtrends.jobtrends.managers.JsonManager
import io.jobtrends.jobtrends.managers.RawManager
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {

    // Activities

    fun inject(boardingActivity: BoardingActivity)

    fun inject(boardingActivity: HomeActivity)

    fun inject(boardingActivity: SplashActivity)

    fun inject(jobActivity: JobActivity)

    // Fragments

    fun inject(boardingFragment: BoardingFragment)

    // Adapters

    fun inject(boardingAdapter: BoardingAdapter)

    fun inject(homeAdapter: HomeAdapter)

    // Managers

    fun inject(jsonManager: JsonManager)

    fun inject(rawManager: RawManager)

    fun inject(boardingManager: BoardingManager)

    fun inject(homeManager: HomeManager)
}
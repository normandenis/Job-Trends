package io.jobtrends.jobtrends.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.dagger.App

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val BOARDING = "BOARDING"
        private const val DELAY_MILLIS: Long = 1000
    }

    init {
        App.component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            navTo()
        }, DELAY_MILLIS)
    }

    private fun navTo() {
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val isBoarding = sharedPreferences.getBoolean(BOARDING, true)
        val intent = if (isBoarding) {
            Intent(this, BoardingActivity::class.java)
        } else {
            Intent(this, HomeActivity::class.java)
        }
        startActivity(intent)
        finish()
    }
}

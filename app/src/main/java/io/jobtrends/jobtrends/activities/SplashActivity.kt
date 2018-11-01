package io.jobtrends.jobtrends.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import io.jobtrends.jobtrends.R

class SplashActivity : AppCompatActivity() {

    companion object {
        private const val DELAY_MILLIS: Long = 1000
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, DELAY_MILLIS)
    }
}

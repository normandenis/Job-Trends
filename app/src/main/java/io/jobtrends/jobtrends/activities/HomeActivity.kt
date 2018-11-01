package io.jobtrends.jobtrends.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.jobtrends.jobtrends.R

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setLogo(R.drawable.logo_android)
    }
}

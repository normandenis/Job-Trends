package io.jobtrends.jobtrends.activities

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.databinding.ActivityBoardingBinding

class BoardingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityBoardingBinding = DataBindingUtil.setContentView(this, R.layout.activity_boarding)
    }
}

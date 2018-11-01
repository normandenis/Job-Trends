package io.jobtrends.jobtrends.activities

import android.content.Context
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.AppCompatActivity
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.dagger.App
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    companion object {
        private const val BOARDING = "BOARDING"
    }

    @Inject
    lateinit var context: Context

    init {
        App.component.inject(this)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()
        editor.putBoolean(BOARDING, false)
        editor.apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.logo_android)
    }

    override fun onBackPressed() {}
}

package io.jobtrends.jobtrends.activities

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ObservableField
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.app.ActionBar.LayoutParams
import android.support.v7.app.ActionBar.LayoutParams.MATCH_PARENT
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.adapters.RecyclerAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.ActionbarHomeBinding
import io.jobtrends.jobtrends.managers.HomeManager
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject


class HomeActivity : AppCompatActivity() {

    companion object {
        private const val BOARDING = "BOARDING"
    }

    @Inject
    lateinit var homeManager: HomeManager

    @Inject
    lateinit var context: Context

    val jobSought: ObservableField<String>

    init {
        App.component.inject(this)
        jobSought = ObservableField("")
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val editor = sharedPreferences.edit()
        editor.putBoolean(BOARDING, false)
        editor.apply()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val inflater = LayoutInflater.from(this)
        val binding: ActionbarHomeBinding = DataBindingUtil.inflate(inflater, R.layout.actionbar_home, null, false)
        binding.homeActivity = this

        val layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setCustomView(binding.root, layoutParams)
        supportActionBar?.setDisplayShowCustomEnabled(true)
        picker_0.adapter = RecyclerAdapter(homeManager, R.layout.fragment_home)
        picker_0.scrollToPosition(1)
    }

    override fun onBackPressed() {}
}

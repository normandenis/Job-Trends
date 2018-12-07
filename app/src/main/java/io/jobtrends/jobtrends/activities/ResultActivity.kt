package io.jobtrends.jobtrends.activities

import android.content.Intent
import android.databinding.DataBindingUtil.setContentView
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import io.jobtrends.jobtrends.R.layout.activity_result
import io.jobtrends.jobtrends.R.layout.surface_result
import io.jobtrends.jobtrends.activities.ResultActivity.ResultState.HOME_STATE
import io.jobtrends.jobtrends.adapters.RecyclerAdapter
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.databinding.ActivityResultBinding
import io.jobtrends.jobtrends.viewmodels.ListKey
import io.jobtrends.jobtrends.viewmodels.ResultViewModel
import io.jobtrends.jobtrends.viewmodels.ResultViewModel.ResultListKey.*
import kotlinx.android.synthetic.main.activity_result.*
import javax.inject.Inject

class ResultActivity : AppCompatActivity(), ActivityManager {

    enum class ResultState : ActivityState {
        HOME_STATE
    }

    override var activityState: ActivityState = HOME_STATE

    @Inject
    lateinit var resultViewModel: ResultViewModel

    init {
        App.component.inject(this)
    }

    override fun build() {
        when (activityState) {
            HOME_STATE -> {
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        build()
    }

    override fun setState(activityState: ActivityState) {
        this.activityState = activityState
    }

    override fun getState(): ActivityState = activityState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityResultBinding = setContentView(this, activity_result)
        buildRecyclers()
    }

    private fun buildAdapters(key: ListKey): RecyclerAdapter {
        return RecyclerAdapter(resultViewModel, surface_result, key)
    }

    private fun buildRecyclers() {
        recycler_0.adapter = buildAdapters(NEAR_JOBS_LIST_KEY)
        recycler_1.adapter = buildAdapters(POSSIBLE_JOBS_LIST_KEY)
        recycler_2.adapter = buildAdapters(FORMATIONS_LIST_KEY)
    }
}

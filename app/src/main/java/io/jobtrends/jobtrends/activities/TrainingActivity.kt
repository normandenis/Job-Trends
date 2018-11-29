package io.jobtrends.jobtrends.activities

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import io.github.inflationx.viewpump.ViewPumpContextWrapper
import io.jobtrends.jobtrends.R
import io.jobtrends.jobtrends.dagger.App
import io.jobtrends.jobtrends.fragments.TrainingEmptyFragment
import io.jobtrends.jobtrends.fragments.TrainingFragment
import io.jobtrends.jobtrends.managers.TrainingManager
import kotlinx.android.synthetic.main.activity_training.*
import javax.inject.Inject

class TrainingActivity : AppCompatActivity(), ActivityListener {

    enum class TrainingState : State {
        BACK_STATE,
        TRAINING_STATE,
        EXPERIENCE_STATE,
        PASSION_STATE
    }

    @Inject
    lateinit var trainingManager: TrainingManager

    private lateinit var fragment: Fragment

    override var state: State = TrainingState.TRAINING_STATE

    init {
        App.component.inject(this)
        trainingManager.registerActivityListener(this)
    }


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        onNavNext()
    }

    override fun onNavBack() {
        state = when (state) {
            TrainingState.TRAINING_STATE -> TrainingState.BACK_STATE
            TrainingState.EXPERIENCE_STATE -> TrainingState.TRAINING_STATE
            TrainingState.PASSION_STATE -> TrainingState.EXPERIENCE_STATE
            else -> TODO()
        }
        if (state == TrainingState.BACK_STATE) {
            finish()
        }
    }

    override fun onSetState(state: State) {
        this.state = state
    }

    override fun onGetState(): State = state

    override fun onNavNext() {
        fragment = when (state) {
            TrainingState.TRAINING_STATE -> {
                when (trainingManager.getCount()) {
                    0 -> TrainingEmptyFragment()
                    else -> TrainingFragment()
                }
            }
            TrainingState.EXPERIENCE_STATE -> TODO()
            TrainingState.PASSION_STATE -> TODO()
            else -> TODO()
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(frame_0.id, fragment)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        finish()
        return true
    }
}

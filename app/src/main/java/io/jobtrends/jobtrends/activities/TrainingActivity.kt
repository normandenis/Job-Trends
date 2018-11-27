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
import io.jobtrends.jobtrends.wrappers.Wrapper
import kotlinx.android.synthetic.main.activity_training.*
import javax.inject.Inject

class TrainingActivity : AppCompatActivity() {

    @Inject
    lateinit var trainingManager: TrainingManager

    @Inject
    lateinit var wrapper: Wrapper

    private lateinit var fragment: Fragment

    init {
        App.component.inject(this)
        wrapper.register(this as Context, true)
    }


    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_training)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        start()
    }

    private fun start() {
        fragment = when (trainingManager.getCount()) {
            0 -> TrainingEmptyFragment()
            else -> TrainingFragment()
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(frame_0.id, fragment)
        transaction.commit()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        this.finish()
        return true
    }
}

package com.lumut.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.lumut.test.injector.ActivityComponent
import com.lumut.test.injector.DaggerActivityComponent
import com.lumut.test.injector.module.ActivityModule
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity() {

    protected abstract fun getLayoutResource(): Int
    protected lateinit var context: Context
    private var activityComponent: ActivityComponent? = null
    private var observable: Disposable? = null
    private var alertLoading: AlertDialog? = null
    private var isPause: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        context = this
    }

    fun getActivityComponent(): ActivityComponent? {
        if (activityComponent == null) {
            activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .applicationComponent(BaseApplication.get(this).getApplicationComponent())
                .build()
        }
        return activityComponent
    }

    override fun onPause() {
        isPause = true
        this.observable?.dispose()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        this.observable?.dispose()
    }

    fun showActivity(intent: Intent) {
        startActivity(intent)
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left)
    }

    fun showActivity(packageContext: Context, cls: Class<*>) {
        val intent = getIntent(packageContext, cls)
        showActivity(intent)
    }

    fun showActivity(intent: Intent, request_code: Int) {
        startActivityForResult(intent, request_code)
        overridePendingTransition(R.anim.pull_in_right, R.anim.push_out_left)
    }

    private fun getIntent(packageContext: Context, cls: Class<*>): Intent {
        return Intent(packageContext, cls)
    }

    fun showActivityClear(packageContext: Context, cls: Class<*>) {
        val intent = getIntent(packageContext, cls)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        showActivity(intent)
    }

    fun showActivityFinish(packageContext: Context, cls: Class<*>) {
        showActivity(packageContext, cls)
        finish()
    }

    fun showActivityFinish(intent: Intent) {
        showActivity(intent)
        finish()
    }

    fun dismissLoading(){
        alertLoading?.dismiss()
    }
}

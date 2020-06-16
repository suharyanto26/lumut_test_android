package com.lumut.test

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.lumut.test.BaseActivity
import java.util.*

abstract class BaseFragment : Fragment() {

    protected abstract fun getLayoutResource(): Int

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(getLayoutResource(), container, false)
        return view
    }


    protected fun getBaseActivity(): BaseActivity? {
        return (activity as BaseActivity)
    }

    protected fun showActivity(intent: Intent) {
        getBaseActivity()?.showActivity(intent)
    }

    fun showActivity(intent: Intent, request_code: Int) {
        getBaseActivity()?.showActivity(intent, request_code)
    }

    protected fun showActivity(packageContext: Context, cls: Class<*>) {
        getBaseActivity()?.showActivity(packageContext, cls)
    }

    protected fun showActivityFinish(packageContext: Context, cls: Class<*>) {
        getBaseActivity()?.showActivityFinish(packageContext, cls)
    }

    protected fun showActivityClear(packageContext: Context, cls: Class<*>) {
        getBaseActivity()?.showActivityClear(packageContext, cls)
    }

    fun showToastMessage(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
    }

    fun getLocaleLanguage(): String {
        return Locale.getDefault().language
    }
}
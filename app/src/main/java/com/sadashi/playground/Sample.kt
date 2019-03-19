package com.sadashi.playground

import android.app.Activity
import android.content.Context

class Sample(private val module: Module) {
    fun getMockModuleString(): String = module.getString()
    fun getMockModuleInt(): Int = module.getInt()

    fun mockContext(context: Context): String {
        return context.getString(R.string.app_name)
    }
    fun mockActivity(activity: Activity): String {
        return activity.callingPackage ?: ""
    }
}
package com.sadashi.playground

import android.app.Activity
import android.content.Context
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.flatMapIterable
import io.reactivex.schedulers.Schedulers

class Sample(private val module: Module) {
    fun getMockModuleString(): String = module.getString()
    fun getMockModuleInt(): Int = module.getInt()

    fun mockContext(context: Context): String {
        return context.getString(R.string.app_name)
    }
    fun mockActivity(activity: Activity): String {
        return activity.callingPackage ?: ""
    }

    fun testSingle(): Observable<String> {
        return module.getSingle()
            .subscribeOn(Schedulers.io())
            .toObservable()
            .flatMapIterable()
            .filter { (it != 2) }
            .observeOn(Schedulers.computation())
            .map { it.toString() }
            .observeOn(AndroidSchedulers.mainThread())

    }

}
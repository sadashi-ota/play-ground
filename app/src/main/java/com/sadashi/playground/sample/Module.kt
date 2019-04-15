package com.sadashi.playground.sample

import io.reactivex.Single

class Module {
    fun getString() = "Module#getString()"
    fun getInt() = 1
    fun getSingle(): Single<List<Int>> {
        return Single.create {
            it.onSuccess(listOf(1, 2, 3))
        }
    }
}
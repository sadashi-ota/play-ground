package com.sadashi.playground.ui.api

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sadashi.playground.R
import com.sadashi.playground.infra.api.UserApiClient
import com.sadashi.playground.infra.api.UserApiClientFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_retrofit.*

class RetrofitActivity : AppCompatActivity() {

    private val apiClient: UserApiClient = UserApiClientFactory.create()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)

        button.setOnClickListener {
            apiClient.getUserDetail("sadashi-ota")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    println(it)
                }, {
                    println(it)
                })
        }
    }
}

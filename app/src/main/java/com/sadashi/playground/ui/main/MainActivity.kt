package com.sadashi.playground.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sadashi.playground.R
import com.sadashi.playground.ui.api.RetrofitActivity
import com.sadashi.playground.ui.blur.BlurryActivity
import com.sadashi.playground.ui.picasso.PicassoActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private val clickItem: (SampleScreenItem) -> Unit = { item ->
        startActivity(Intent(this, item.transitionClass.java))
    }

    private val adapter = SampleScreenListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = adapter
        adapter.clickListener = clickItem
    }

    override fun onStart() {
        super.onStart()
        adapter.collection = listOf(
            SampleScreenItem(RetrofitActivity::class, "api sample"),
            SampleScreenItem(BlurryActivity::class, "blur sample"),
            SampleScreenItem(PicassoActivity::class, "api sample")
        )
    }
}

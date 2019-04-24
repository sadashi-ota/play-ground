package com.sadashi.playground.ui.picasso

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sadashi.playground.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picasso.*

class PicassoActivity : AppCompatActivity() {

    companion object {
        private const val imageUrl = "https://avatars0.githubusercontent.com/u/29793443?s=460&v=4"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picasso)

        Picasso.with(this).load(imageUrl).into(imageView)
    }
}

package com.sadashi.playground.ui.blur

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sadashi.playground.R
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import jp.wasabeef.blurry.Blurry
import kotlinx.android.synthetic.main.activity_blurry.*
import java.lang.ref.WeakReference
import java.util.concurrent.Executors


class BlurryActivity : AppCompatActivity() {

    companion object {
        private const val imageUrl = "https://avatars0.githubusercontent.com/u/29793443?s=460&v=4"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blurry)

        imageView.tag = object : Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
            }

            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                Blurry.with(imageView.context)
                    .async()
                    .from(bitmap)
                    .into(imageView)
            }
        }
        Picasso.get().load(imageUrl).into(imageView.tag as Target)
    }
}

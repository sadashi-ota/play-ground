package com.sadashi.playground.ui.main

import android.app.Activity
import kotlin.reflect.KClass

data class SampleScreenItem(
    val transitionClass: KClass<out Activity>,
    val name: String
)
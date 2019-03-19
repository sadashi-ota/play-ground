package com.sadashi.playground

class Sample(private val module: Module) {
    fun getMockModuleString(): String = module.getString()
    fun getMockModuleInt(): Int = module.getInt()
}
package com.semye.android.module.item14_animation

import android.animation.TypeEvaluator

/**
 * 自定义TypeEvaluator
 */
class StringEvaluator : TypeEvaluator<String> {
    override fun evaluate(fraction: Float, startValue: String?, endValue: String?): String {
        println("fraction====>$fraction")
        println("startValue====>$startValue")
        println("endValue====>$endValue")
        return if (fraction < 1) {
            startValue!!
        } else {
            endValue!!
        }
    }
}
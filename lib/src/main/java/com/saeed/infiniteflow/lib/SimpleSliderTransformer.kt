package com.saeed.infiniteflow.lib

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max

class SimpleSliderTransformer(
    private val minScale: Float = 0.8f,
    private val unSelectedItemRotation: Float = 0f,
    private val unSelectedItemAlpha: Float = 1f
) : ViewPager2.PageTransformer {

    init {
        require(minScale in 0f..1f) { "minScale value should be between 1.0 to 0.0" }
        require(unSelectedItemAlpha in 0f..1f) { "unSelectedItemAlpha value should be between 1.0 to 0.0" }
    }

    override fun transformPage(page: View, position: Float) {

        page.apply {
            elevation = -position

            val delta = max(1f - abs(position * (1 - minScale)), minScale)

            if (unSelectedItemRotation != 0f) {
                val rotation =
                    (1 - delta) * if (position > 0) unSelectedItemRotation else -unSelectedItemRotation
                rotationY = rotation
            }

            when {
                position <= -1f -> { // [-Infinity, -1]
                    // This page is way off-screen to the left.
                    alpha = this@SimpleSliderTransformer.unSelectedItemAlpha
                    scaleX = minScale
                    scaleY = minScale
                }
                position >= -1f && position <= 1f -> { // (0, 1]
                    // page move from right to center.
                    if (this@SimpleSliderTransformer.unSelectedItemAlpha != 1f) {
                        alpha =
                            this@SimpleSliderTransformer.unSelectedItemAlpha + ((1 - abs(position)) *
                                    (1 - this@SimpleSliderTransformer.unSelectedItemAlpha))
                    }

                    scaleX = max(1f - abs(position * (1 - minScale)), minScale)
                    scaleY = max(1f - abs(position * (1 - minScale)), minScale)
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = this@SimpleSliderTransformer.unSelectedItemAlpha
                    scaleX = minScale
                    scaleY = minScale
                }
            }
        }
    }

}

package com.saeed.infiniteflow.lib

import android.view.View
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.max

class StackSliderTransformer(
    private val orientation: Int,
    private val behindScale: Float,
    private val behindAlpha: Float = 0f
) : ViewPager2.PageTransformer {

    init {
        require(behindScale >= 0f) { "Behind Scale value must be positive" }
        require(behindAlpha in 1f..0f) { "unSelectedItemAlpha value should be between 1.0 to 0.0" }
    }

    override fun transformPage(page: View, position: Float) {
        page.apply {
            val pageWidth = width
            elevation = -position
            when {
                position <= -1f -> { // [-Infinity, -1]
                    // This page is way off-screen to the left.
                    alpha = behindAlpha
                }
                position <= 0f -> { // (-1, 0]
                    // page move from left to center.

                    alpha = 1f
                    translationX = 0f
                    scaleX = 1f
                    scaleY = 1f
                }
                position <= 1f -> { // (0, 1]
                    // page move from right to center.

                    alpha = max(1f - position, behindAlpha)

                    when (orientation) {
                        ViewPager2.ORIENTATION_VERTICAL -> {
                            translationY = pageWidth * -position
                        }
                        ViewPager2.ORIENTATION_HORIZONTAL -> {
                            translationX = pageWidth * -position
                        }
                    }

//                    val scaleFactor = (behindScale + (1 - behindScale) * (1 - abs(position)))
                    val scaleFactor = BezierFormula.fourPoints(
                        1f, 1.1f, behindScale, behindScale, position
                    )
                    scaleX = scaleFactor
                    scaleY = scaleFactor
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = behindAlpha
                }
            }

        }

    }

}

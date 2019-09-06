package com.saeed.infiniteflow.example

import android.graphics.Bitmap
import android.graphics.Canvas
import android.view.View
import androidx.core.graphics.translationMatrix
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs
import kotlin.math.max

class ScaleAlphaPageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(page: View, position: Float) {

        page.apply {
            val pageWidth = width
            elevation = -position

            when {
                position <= -1f -> { // [-Infinity, -1]
                    // This page is way off-screen to the left.
                    alpha = 0.2f
                    scaleX = 0.75f
                    scaleY = 0.75f
                }
                position >= -1f  && position <= 1f -> { // (0, 1]
                    // page move from right to center.
                    alpha = 0.2f + ((1 - abs(position)) * 0.8f)

                    scaleX = max(1f - abs(position * 0.25f), 0.75f)
                    scaleY = max(1f - abs(position * 0.25f), 0.75f)
                }
                else -> { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    alpha = 0.2f
                    scaleX = 0.75f
                    scaleY = 0.75f
                }
            }
        }
    }

}
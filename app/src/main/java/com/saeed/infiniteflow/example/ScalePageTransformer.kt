package com.saeed.infiniteflow.example

import android.content.res.Resources
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.saeed.infiniteflow.lib.BezierFormula
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min


fun Int.toDp(): Int = (this / Resources.getSystem().displayMetrics.density).toInt()

fun Int.toPx(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

class ScalePageTransformer : ViewPager2.PageTransformer {

    override fun transformPage(page: View, position: Float) {
        /*if (position == 0f) {
            *//*page.spring(DynamicAnimation.SCALE_X, 1f)
            page.spring(DynamicAnimation.SCALE_Y, 1f)

            page.spring(DynamicAnimation.ROTATION_Y, 0f)
            page.spring(DynamicAnimation.TRANSLATION_X, 0f)*//*
            page.rotationY = 0f
            page.translationX = 0f
            page.scaleX = 1f
            page.scaleY = 1f

        }*/

//        val delta = max(1f - abs(position / 2), 0.5f)
        val pos = abs(position)
        val delta = if (pos <= 1f) {
            max(
                min(BezierFormula.fourPoints(1f, 0.95f, 0.55f, 0.5f, pos), 2f)
                , 0.5f
            )
        } else {
            max(
                min(BezierFormula.twoPoints(1f, 0.5f, pos), 1.1f)
                , 0.5f
            )
        }

        val realScale = delta
        page.scaleX = realScale
        page.scaleY = realScale

        val rotation = max(1 - pos, 1f) * if (position > 0) 120f else -120f
//        page.rotationY = rotation

        if (position in 0f..1f) {
            page.translationX =
                BezierFormula.fourPoints(0f, (-120).toPx().toFloat(), (20).toPx().toFloat(), 0f, position)
        } else {
            page.translationX = position
        }

        page.elevation = -abs(position)

    }

}
package com.saeed.finiteflow.example.overlapslider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.viewpager2.widget.ViewPager2
import com.saeed.finiteflow.example.R
import com.saeed.finiteflow.example.RecyclerPagerAdapter
import com.saeed.finiteflow.example.spring
import com.saeed.finiteflow.lib.toPx
import kotlinx.android.synthetic.main.activity_vertical_overlap_example.*

class VerticalOverlapExampleActivity : AppCompatActivity() {

    private var animationStartNeeded = true

    private val recyclerPagerAdapter: RecyclerPagerAdapter by lazy {
        RecyclerPagerAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical_overlap_example)

        view_pager.adapter = recyclerPagerAdapter
        view_pager.offscreenPageLimit = 4

        title_text_view.text = "Title 0"

        pager_container.setOverlapSlider(
            unSelectedItemRotation = 0f,
            unSelectedItemAlpha = 0f,
            minScale = 0.2f,
            itemGap = 0f
        )

        var targetPosition = 0f
        var targetAlpha = 1f
        view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageScrollStateChanged(state: Int) {

                when (state) {
                    ViewPager2.SCROLL_STATE_IDLE -> {
                        animationStartNeeded = true
                        targetPosition = 0f
                        targetAlpha = 1f
                    }
                    else -> {
                        if (animationStartNeeded) {
                            animationStartNeeded = false
                            targetPosition = -20.toPx().toFloat()
                            targetAlpha = 0f
                        }
                    }
                }

                title_text_view.spring(DynamicAnimation.TRANSLATION_X, targetPosition)
                title_text_view.spring(DynamicAnimation.ALPHA, targetAlpha)
                super.onPageScrollStateChanged(state)
            }

            override fun onPageSelected(position: Int) {
                title_text_view.text = "Title $position"
                super.onPageSelected(position)
            }
        })
    }

}

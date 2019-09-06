package com.saeed.infiniteflow.example.ecommerce

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.viewpager2.widget.ViewPager2
import com.saeed.infiniteflow.example.R
import com.saeed.infiniteflow.example.simplecrousel.ImageSliderPagerAdapter
import com.saeed.infiniteflow.example.spring
import com.saeed.infiniteflow.lib.toPx
import kotlinx.android.synthetic.main.activity_vertical_ecommerce_example.*

class VerticalEcommorceItemExampleActivity : AppCompatActivity() {

    private var animationStartNeeded = true

    private val recyclerPagerAdapter: EcommercePagerAdapter by lazy {
        EcommercePagerAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical_ecommerce_example)

        view_pager.adapter = recyclerPagerAdapter
        view_pager.offscreenPageLimit = 4

        title_text_view.text = "Watch 0"

        pager_container.setSimpleSlider(
            unSelectedItemRotation = 0f,
            unSelectedItemAlpha = 0.2f,
            minScale = 0.5f
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
                            targetPosition = 20.toPx().toFloat()
                            targetAlpha = 0f
                        }
                    }
                }

                title_text_view.spring(DynamicAnimation.TRANSLATION_Y, targetPosition)
                title_text_view.spring(DynamicAnimation.ALPHA, targetAlpha)
                super.onPageScrollStateChanged(state)
            }

            override fun onPageSelected(position: Int) {
                title_text_view.text = "Watch $position"
                super.onPageSelected(position)
            }
        })

        var isTransitionToEnd = false
        button.setOnClickListener {
            if (!isTransitionToEnd) {
                isTransitionToEnd = true
                motion_layout.transitionToEnd()
            } else {
                isTransitionToEnd = false
                motion_layout.transitionToStart()
            }
        }
    }

}

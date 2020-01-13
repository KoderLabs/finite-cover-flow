package com.saeed.finiteflow.example.simplecrousel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.saeed.finiteflow.example.R
import kotlinx.android.synthetic.main.activity_vertical_ecommerce_example.pager_container
import kotlinx.android.synthetic.main.activity_vertical_ecommerce_example.view_pager
import kotlinx.android.synthetic.main.activity_vertical_image_viewer_example.*

class VerticalImageViewerExampleActivity : AppCompatActivity() {

    private lateinit var recyclerPagerAdapter: ImageSliderPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vertical_image_viewer_example)

        recyclerPagerAdapter = ImageSliderPagerAdapter(onClick = {
            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    this@VerticalImageViewerExampleActivity,
                    it
                )
            )
        })

        view_pager.adapter = recyclerPagerAdapter
        view_pager.offscreenPageLimit = 4

        pager_container.setSimpleSlider(
            unSelectedItemRotation = 0f,
            unSelectedItemAlpha = 0.2f,
            minScale = 0.5f
        )

        view_pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val resId = when {
                    position % 4 == 0 -> {
                        R.drawable.watch_4
                    }
                    position % 3 == 0 -> {

                        R.drawable.watch_3
                    }
                    position % 2 == 0 -> {
                        R.drawable.watch_2
                    }
                    position % 1 == 0 -> {
                        R.drawable.watch_1
                    }
                    else -> {
                        R.drawable.watch_4
                    }
                }
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        this@VerticalImageViewerExampleActivity,
                        resId
                    )
                )
            }
        })

    }
}

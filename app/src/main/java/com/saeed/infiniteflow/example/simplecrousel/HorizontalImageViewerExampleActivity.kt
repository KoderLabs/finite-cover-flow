package com.saeed.infiniteflow.example.simplecrousel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.saeed.infiniteflow.example.R
import kotlinx.android.synthetic.main.activity_horizontal_image_viewer_example.*

class HorizontalImageViewerExampleActivity : AppCompatActivity() {

    private lateinit var recyclerPagerAdapter: ImageSliderPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_image_viewer_example)

        recyclerPagerAdapter = ImageSliderPagerAdapter(onClick = {
            imageView.setImageDrawable(
                ContextCompat.getDrawable(
                    this@HorizontalImageViewerExampleActivity,
                    it
                )
            )
        })

        view_pager.adapter = recyclerPagerAdapter
        view_pager.offscreenPageLimit = 4

        image_left.setOnClickListener {
            if (view_pager.currentItem > 0) {
                view_pager.currentItem--
            }
        }

        image_right.setOnClickListener {
            if (view_pager.currentItem < recyclerPagerAdapter.itemCount) {
                view_pager.currentItem++
            }
        }

        pager_container.setSimpleSlider(
            unSelectedItemRotation = 0f,
            unSelectedItemAlpha = 0.5f,
            minScale = 0.8f
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
                        this@HorizontalImageViewerExampleActivity,
                        resId
                    )
                )
            }
        })

    }

}

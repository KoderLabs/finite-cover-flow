package com.saeed.infiniteflow.example.fullscreenslider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.saeed.infiniteflow.example.R
import com.saeed.infiniteflow.example.RecyclerPagerAdapter
import kotlinx.android.synthetic.main.activity_full_screen.*

class FullScreenActivity : AppCompatActivity() {

    private val recyclerPagerAdapter: RecyclerPagerAdapter by lazy {
        RecyclerPagerAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_screen)

        pager_container.getViewPager().adapter = recyclerPagerAdapter

        pager_container.setStackSlider(
            behindScale = 0.7f,
            behindAlpha = 0f
        )
    }

}

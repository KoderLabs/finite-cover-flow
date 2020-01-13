package com.saeed.finiteflow.example.floatingheader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.saeed.finiteflow.example.R
import kotlinx.android.synthetic.main.activity_floating_header.*

class FloatingHeaderActivity : AppCompatActivity() {

    private val recyclerPagerAdapter: FloatingPagerAdapter by lazy {
        FloatingPagerAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_floating_header)

        view_pager.adapter = recyclerPagerAdapter
        view_pager.offscreenPageLimit = 4

//        pager_container.setHeaderFloatSlider()

    }
}

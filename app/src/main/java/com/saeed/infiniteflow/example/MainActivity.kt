package com.saeed.infiniteflow.example

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import com.saeed.infiniteflow.example.ecommerce.HorizontalEcommorceItemExampleActivity
import com.saeed.infiniteflow.example.ecommerce.VerticalEcommorceItemExampleActivity
import com.saeed.infiniteflow.example.fullscreenslider.FullScreenActivity
import com.saeed.infiniteflow.example.infinitepagerexample.InfiniteHorizontalCrouselExampleActivity
import com.saeed.infiniteflow.example.overlapslider.HorizontalOverlapExampleActivity
import com.saeed.infiniteflow.example.overlapslider.VerticalOverlapExampleActivity
import com.saeed.infiniteflow.example.simplecrousel.HorizontalImageViewerExampleActivity
import com.saeed.infiniteflow.example.simplecrousel.VerticalImageViewerExampleActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fs.setOnClickListener {
            val intent = Intent(this@MainActivity, FullScreenActivity::class.java)
            startActivity(intent)
        }

        infs.setOnClickListener {
            val intent = Intent(this@MainActivity, InfiniteHorizontalCrouselExampleActivity::class.java)
            startActivity(intent)
        }

        eh.setOnClickListener {
            val intent =
                Intent(this@MainActivity, HorizontalEcommorceItemExampleActivity::class.java)
            startActivity(intent)
        }

        ev.setOnClickListener {
            val intent = Intent(this@MainActivity, VerticalEcommorceItemExampleActivity::class.java)
            startActivity(intent)
        }

        oh.setOnClickListener {
            val intent = Intent(this@MainActivity, HorizontalOverlapExampleActivity::class.java)
            startActivity(intent)
        }

        ov.setOnClickListener {
            val intent = Intent(this@MainActivity, VerticalOverlapExampleActivity::class.java)
            startActivity(intent)
        }

        ih.setOnClickListener {
            val intent = Intent(this@MainActivity, HorizontalImageViewerExampleActivity::class.java)
            startActivity(intent)
        }

        iv.setOnClickListener {
            val intent = Intent(this@MainActivity, VerticalImageViewerExampleActivity::class.java)
            startActivity(intent)
        }

    }
}

fun View.spring(property: DynamicAnimation.ViewProperty, value: Float) {
    val anim = SpringAnimation(this, property)
    val springForce = SpringForce()
    springForce.dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
    springForce.stiffness = SpringForce.STIFFNESS_LOW
    anim.spring = springForce
    anim.animateToFinalPosition(value)
}

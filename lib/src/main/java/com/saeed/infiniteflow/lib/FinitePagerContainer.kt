package com.saeed.infiniteflow.lib

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import kotlin.math.abs

/**
 * @author Muhammad Saeed
 *
 * Finite Pager Container create viewpager pager transform animations
 */
class FinitePagerContainer : FrameLayout {

    private lateinit var mPager: ViewPager2

    constructor(ctx: Context) : super(ctx) {
        initialize()
    }

    constructor(ctx: Context, attrs: AttributeSet) : super(ctx, attrs) {
        initialize()
    }

    constructor(ctx: Context, attrs: AttributeSet, defStyle: Int) : super(ctx, attrs, defStyle) {
        initialize()
    }

    private fun initialize() {
        clipChildren = false
    }

    override fun onFinishInflate() {
        try {
            mPager = getChildAt(0) as ViewPager2
            mPager.clipChildren = false
        } catch (e: Exception) {
            throw IllegalStateException("The root child of PagerContainer must be a ViewPager")
        }
        super.onFinishInflate()
    }

    fun getViewPager() = mPager

    /**
     * Create a StackSliderTransformer which is PageTransformer
     *
     * @param behindAlpha set unselected item opacity between 1.0f to 0.0f
     * @param behindScale set behindScale value for behind from selected item
     *
     * @see SimpleSliderTransformer
     * @see OverlapSliderTransformer
     * @see StackSliderTransformer
     */
    fun setStackSlider(
        behindScale: Float = 0.75f,
        behindAlpha: Float = 0f
    ) {
        val transformer = StackSliderTransformer(
            orientation = mPager.orientation,
            behindScale = behindScale,
            behindAlpha = behindAlpha
        )
        setPagerTransformer(transformer)
    }

    /**
     * Create a OverlapSliderTransformer which is PageTransformer
     *
     * @param unSelectedItemRotation set unselected item rotation in degree
     * @param unSelectedItemAlpha set unselected item opacity between 1.0f to 0.0f
     * @param minScale set min scale value for unselected item and it should between 1.0f to 0.0f
     * @param itemGap set space between items
     *
     * @see SimpleSliderTransformer
     * @see OverlapSliderTransformer
     * @see StackSliderTransformer
     */
    fun setOverlapSlider(
        unSelectedItemRotation: Float = 120f,
        unSelectedItemAlpha: Float = 0f,
        minScale: Float = 0.25f,
        itemGap: Float = 0f
    ) {
        val transformer = OverlapSliderTransformer(
            orientation = mPager.orientation,
            unSelectedItemRotation = unSelectedItemRotation,
            unSelectedItemAlpha = unSelectedItemAlpha,
            minScale = minScale,
            itemGap = itemGap
        )
        setPagerTransformer(transformer)
    }

    /**
     * Create a SimpleSliderTransformer which is PageTransformer
     *
     * @param unSelectedItemRotation set unselected item rotation in degree
     * @param unSelectedItemAlpha set unselected item opacity between 1.0f to 0.0f
     * @param minScale set min scale value for unselected item and it should between 1.0f to 0.0f
     *
     * @see SimpleSliderTransformer
     * @see OverlapSliderTransformer
     * @see StackSliderTransformer
     */
    fun setSimpleSlider(
        unSelectedItemRotation: Float = 120f,
        unSelectedItemAlpha: Float = 0.5f,
        minScale: Float = 0.8f
    ) {
        val transformer = SimpleSliderTransformer(
            unSelectedItemRotation = unSelectedItemRotation,
            unSelectedItemAlpha = unSelectedItemAlpha,
            minScale = minScale
        )
        setPagerTransformer(transformer)
    }

    /**
     * Sets a {@link ViewPager2.PageTransformer} that will be called for each attached page whenever the
     * scroll position is changed. This allows the application to apply custom property
     * transformations to each page, overriding the default sliding behavior.
     * <p>
     * Note: setting a {@link PageTransformer} disables data-set change animations to prevent
     * conflicts between the two animation systems. Setting a {@code null} transformer will restore
     * data-set change animations.
     *
     * @param transformer ViewPager2.PageTransformer that will modify each page's animation properties
     *
     * @see MarginPageTransformer
     * @see CompositePageTransformer
     */
    fun setPagerTransformer(transformer: ViewPager2.PageTransformer) {
        mPager.setPageTransformer(transformer)
    }

    private var lastXvalue = 0f
    private var lastYvalue = 0f

    private var fakeXvalue = 0f
    private var fakeYvalue = 0f

    override fun onTouchEvent(event: MotionEvent): Boolean {
        //We capture any touches not already handled by the ViewPager
        // to implement scrolling from a touch outside the pager bounds.
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastXvalue = event.x
                lastYvalue = event.y

                fakeXvalue = event.x
                fakeYvalue = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                val value = getFakeDragDelta(event)
                if (abs(value) > 20f) {
                    if (!mPager.isFakeDragging) {
                        mPager.beginFakeDrag()
                    }
                    val dragValue = getLastDragDelta(event)
                    mPager.fakeDragBy(dragValue)
                    fakeXvalue = event.x
                    fakeYvalue = event.y
                } else {
                    if (mPager.isFakeDragging) {
                        mPager.fakeDragBy(value)
                        fakeXvalue = event.x
                        fakeYvalue = event.y
                    }
                }
                lastXvalue = event.x
                lastYvalue = event.y
            }
            MotionEvent.ACTION_UP -> {
                if (mPager.isFakeDragging) {
                    mPager.endFakeDrag()
                }
            }
        }

        return true
    }

    private fun getFakeDragDelta(event: MotionEvent): Float {
        val x = event.x - fakeXvalue
        val y = event.y - fakeYvalue
        return if (mPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            x
        } else {
            y
        }
    }

    private fun getLastDragDelta(event: MotionEvent): Float {
        val x = event.x - lastXvalue
        val y = event.y - lastYvalue
        return if (mPager.orientation == ViewPager2.ORIENTATION_HORIZONTAL) {
            x
        } else {
            y
        }
    }

}
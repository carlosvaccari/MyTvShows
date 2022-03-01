package com.cvaccari.core_views.stickyrecyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import br.com.ps.customviews.recyclerviewstickheader.StickyHeaderItemDecorator
import com.cvaccari.core_views.R

class RecyclerViewStickyHeader @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : RecyclerView(context, attrs, defStyleAttr) {

    init {
        configAnim()
    }

    private fun configAnim() {
        val controller =
            AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_wave)
        layoutAnimation = controller
    }

    fun setStickAdapter(adapter: StickyAdapter<*, *>?) {
        adapter?.apply {
            val decorator = StickyHeaderItemDecorator(adapter)
            decorator.attachToRecyclerView(this@RecyclerViewStickyHeader)
            setAdapter(adapter)
        }

        startAnim()
    }

    fun startAnim() {
        scheduleLayoutAnimation()
    }
}

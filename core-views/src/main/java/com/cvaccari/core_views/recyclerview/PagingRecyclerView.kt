package com.cvaccari.core_views.recyclerview

import android.content.Context
import android.util.AttributeSet
import android.view.animation.AnimationUtils
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cvaccari.core_views.R

class PagingRecyclerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0

) : RecyclerView(context, attrs, defStyleAttr) {

    var isLoading = false

    private var loadMoreListener: LoadMoreListener? = null

    override fun setAdapter(adapter: Adapter<*>?) {
        super.setAdapter(adapter)
        startAnim()
        addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            isLoading = false
        }
    }

    fun startAnim() {
        if (layoutManager?.childCount == 0) {
            val controller =
                AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_wave)
            layoutAnimation = controller
            scheduleLayoutAnimation()
        }
    }

    override fun onScrolled(dx: Int, dy: Int) {
        val layoutManager = layoutManager as LinearLayoutManager
        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val pastVisibleItems = layoutManager.findFirstVisibleItemPosition()
        if (!isLoading) {
            if (visibleItemCount + pastVisibleItems >= (totalItemCount - 50)) {
                loadMoreListener?.loadMore()
                isLoading = true
            }
        }
    }

    fun setOnLoadMoreListener(listener: LoadMoreListener) {
        loadMoreListener = listener
    }

    interface LoadMoreListener {
        fun loadMore()
    }
}

@BindingAdapter("bindListener")
fun PagingRecyclerView.bindListener(listener: PagingRecyclerView.LoadMoreListener) {
    setOnLoadMoreListener(listener)
}
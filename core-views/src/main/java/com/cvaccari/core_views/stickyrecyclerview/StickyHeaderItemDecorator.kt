package br.com.ps.customviews.recyclerviewstickheader

import android.graphics.Canvas
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.cvaccari.core_views.stickyrecyclerview.StickyAdapter

class StickyHeaderItemDecorator(private val adapter: StickyAdapter<*, *>) : RecyclerView.ItemDecoration() {

    private var currentStickyPosition = RecyclerView.NO_POSITION

    private lateinit var currentStickyHolder: RecyclerView.ViewHolder

    private var lastViewOverlappedByHeader: View? = null

    fun attachToRecyclerView(recyclerToAttach: RecyclerView?) {
        recyclerToAttach?.let {
            destroyCallbacks(it)
            currentStickyHolder = adapter.onCreateHeaderViewHolder(it)
            fixLayoutSize(it)
            setupCallbacks(it)
        }
    }

    private fun setupCallbacks(recyclerView: RecyclerView) {
        recyclerView.addItemDecoration(this)
    }

    private fun destroyCallbacks(recyclerView: RecyclerView) {
        recyclerView.removeItemDecoration(this)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val layoutManager = parent.layoutManager ?: return

        val topChildPosition = (layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

        if (topChildPosition == RecyclerView.NO_POSITION) {
            return
        }

        var viewOverlappedByHeader = getChildInContact(parent, currentStickyHolder.itemView.bottom)

        if (viewOverlappedByHeader == null) {
            viewOverlappedByHeader = if (lastViewOverlappedByHeader != null) {
                lastViewOverlappedByHeader
            } else {
                parent.getChildAt(topChildPosition)
            }
        }

        lastViewOverlappedByHeader = viewOverlappedByHeader

        viewOverlappedByHeader?.apply {
            val overlappedByHeaderPosition = parent.getChildAdapterPosition(this)

            val overlappedHeaderPosition: Int

            val preOverlappedPosition: Int

            if (overlappedByHeaderPosition > 0) {
                preOverlappedPosition =
                    adapter.getHeaderPositionForItem(overlappedByHeaderPosition - 1)
                overlappedHeaderPosition =
                    adapter.getHeaderPositionForItem(overlappedByHeaderPosition)
            } else {
                preOverlappedPosition = adapter.getHeaderPositionForItem(topChildPosition)
                overlappedHeaderPosition = preOverlappedPosition
            }

            if (preOverlappedPosition == RecyclerView.NO_POSITION) {
                return
            }

            if (preOverlappedPosition != overlappedHeaderPosition && shouldMoveHeader(this)) {
                updateStickyHeader(topChildPosition)
                moveHeader(c, this)
            } else {
                updateStickyHeader(topChildPosition)
                drawHeader(c)
            }
        }
    }

    private fun shouldMoveHeader(viewOverlappedByHeader: View): Boolean {
        val dy = viewOverlappedByHeader.top - viewOverlappedByHeader.height
        return 0 in dy..viewOverlappedByHeader.top
    }

    private fun updateStickyHeader(topChildPosition: Int) {
        val headerPositionForItem = adapter.getHeaderPositionForItem(topChildPosition)
        if (headerPositionForItem != RecyclerView.NO_POSITION) {
            adapter.onBindHeaderViewHolder(
                currentStickyHolder,
                headerPositionForItem,
                adapter.getHeaderItems()
            )

            if (headerPositionForItem != currentStickyPosition) {
                currentStickyPosition = headerPositionForItem
            }
        }
    }

    private fun drawHeader(c: Canvas) {
        c.save()
        c.translate(0f, 0f)
        currentStickyHolder.itemView.draw(c)
        c.restore()
    }

    private fun moveHeader(c: Canvas, nextHeader: View) {
        c.save()
        c.translate(0f, (nextHeader.top - nextHeader.height).toFloat())
        currentStickyHolder.itemView.draw(c)
        c.restore()
    }

    private fun getChildInContact(parent: RecyclerView, contactPoint: Int): View? {
        var childInContact: View? = null
        (0 until parent.childCount).forEach {
            val child = parent.getChildAt(it)
            if (child.bottom > contactPoint && child.top <= contactPoint) {
                childInContact = child
                return@forEach
            }
        }
        return childInContact
    }

    private fun fixLayoutSize(recyclerView: RecyclerView) {
        recyclerView.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                recyclerView.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                val widthSpec =
                    View.MeasureSpec.makeMeasureSpec(
                        recyclerView.width,
                        View.MeasureSpec.EXACTLY
                    )

                val heightSpec = View.MeasureSpec.makeMeasureSpec(
                    recyclerView.height,
                    View.MeasureSpec.UNSPECIFIED
                )

                val childWidthSpec = ViewGroup.getChildMeasureSpec(
                    widthSpec,
                    recyclerView.paddingLeft + recyclerView.paddingRight,
                    currentStickyHolder.itemView.layoutParams.width
                )
                val childHeightSpec = ViewGroup.getChildMeasureSpec(
                    heightSpec,
                    recyclerView.paddingTop + recyclerView.paddingBottom,
                    currentStickyHolder.itemView.layoutParams.height
                )

                currentStickyHolder.apply {
                    itemView.measure(childWidthSpec, childHeightSpec)
                    itemView.layout(
                        0, 0,
                        currentStickyHolder.itemView.measuredWidth,
                        currentStickyHolder.itemView.measuredHeight
                    )
                }
            }
        })
    }
}

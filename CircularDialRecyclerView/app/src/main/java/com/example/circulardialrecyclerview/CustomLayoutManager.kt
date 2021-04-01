package com.example.circulardialrecyclerview

import android.content.res.Resources
import android.view.View
import android.view.ViewGroup
import androidx.core.math.MathUtils
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.acos
import kotlin.math.floor
import kotlin.math.sin

class CustomLayoutManager(private val screenWidth: Int, resources: Resources) : RecyclerView.LayoutManager() {

    private var horizontalScrollOffset = 0
    private val viewWidth = resources.getDimensionPixelSize(R.dimen.view_width)
    private val recyclerViewHeight = resources.getDimensionPixelSize(R.dimen.recycler_viewer_height).toDouble()

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun canScrollHorizontally(): Boolean {
        return true
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: RecyclerView.Recycler, state: RecyclerView.State): Int {
        horizontalScrollOffset += dx
        fill(recycler, state)
        return dx
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State) {
        fill(recycler, state)
    }


    private fun fill(recycler:RecyclerView.Recycler, state: RecyclerView.State){
        detachAndScrapAttachedViews(recycler)

        val firstVisiblePosition = floor(horizontalScrollOffset.toDouble()/viewWidth.toDouble()).toInt()
        val lastVisiblePosition = (horizontalScrollOffset+screenWidth)/viewWidth

        for(index in firstVisiblePosition until lastVisiblePosition){
            var recyclerIndex = index % itemCount
            if(recyclerIndex<0){
                recyclerIndex+=itemCount
            }
            val view = recycler.getViewForPosition(recyclerIndex)
            addView(view)

            layoutChildView(index, viewWidth, view)
        }

        val scrapListCopy = recycler.scrapList.toList()
        scrapListCopy.forEach {
            recycler.recycleView(it.itemView)
        }
    }

    private fun layoutChildView(i: Int, viewWidthWithSpacing: Int, view: View){
        val left = i * viewWidthWithSpacing - horizontalScrollOffset
        val right = left + viewWidth
        val top = getTopOffsetForView(left + viewWidth/2)
        val bottom = top + viewWidth

        measureChild(view, viewWidth, viewWidth)

        layoutDecorated(view, left, top, right, bottom)
    }

    private fun getTopOffsetForView(viewCentreX: Int): Int {
        val s: Double = screenWidth.toDouble() / 2
        val h: Double = recyclerViewHeight - viewWidth.toDouble()
        val radius: Double = ( h*h + s*s ) / (h*2)

        val cosAlpha = (s - viewCentreX) / radius
        val alpha = acos(MathUtils.clamp(cosAlpha, -1.0, 1.0))

        val yComponent = radius - (radius * sin(alpha))
        return yComponent.toInt()
    }

}
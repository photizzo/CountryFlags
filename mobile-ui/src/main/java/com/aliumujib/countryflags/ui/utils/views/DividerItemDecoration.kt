package com.aliumujib.countryflags.ui.utils.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.RecyclerView.State
import com.aliumujib.countryflags.ui.adapters.allcountries.AllCountriesAdapter


class DividerItemDecoration(context: Context, color: Int, heightDp: Float) :
    ItemDecoration() {

    private val mPaint: Paint = Paint()
    private val mHeightDp: Int

    constructor(context: Context) : this(context, Color.argb((255 * 0.2).toInt(), 0, 0, 0), 1f) {}

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: State) {
        val position = parent.getChildAdapterPosition(view)
        val viewType = parent.adapter!!.getItemViewType(position)
        if (viewType == AllCountriesAdapter.CONTENT_VIEW) {
            outRect.set(0, 0, 0, mHeightDp)
        } else {
            outRect.setEmpty()
        }
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: State) {
        for (i in 0 until parent.childCount) {
            val view: View = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            val viewType = parent.adapter!!.getItemViewType(position)
            if (viewType == AllCountriesAdapter.CONTENT_VIEW) {
                c.drawRect(
                    view.left.toFloat(),
                    view.bottom.toFloat(),
                    view.right.toFloat(),
                    (view.bottom + mHeightDp).toFloat(),
                    mPaint
                )
            }
        }
    }

    init {
        mPaint.style = Paint.Style.FILL
        mPaint.color = color
        mHeightDp = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            heightDp,
            context.resources.displayMetrics
        ).toInt()
    }
}
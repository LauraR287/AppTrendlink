package com.lrincon.trendlink

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class DrawingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paths = mutableListOf<Pair<Path, Paint>>()
    private var currentPath = Path()
    private var currentPaint = Paint().apply {
        color = 0xFF000000.toInt()
        strokeWidth = 8f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }

    init {
        paths.add(currentPath to currentPaint)
    }

    fun setColor(color: Int) {
        currentPaint = Paint(currentPaint).apply {
            this.color = color
        }
        currentPath = Path()
        paths.add(currentPath to currentPaint)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for ((path, paint) in paths) {
            canvas.drawPath(path, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val x = event.x
        val y = event.y
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                currentPath.moveTo(x, y)
            }
            MotionEvent.ACTION_MOVE -> {
                currentPath.lineTo(x, y)
            }
        }
        invalidate()
        return true
    }

    fun getBitmap(): Bitmap {
        val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(bitmap)
        canvas.drawColor(0xFFFFFFFF.toInt())
        draw(canvas)
        return bitmap
    }

    fun clear() {
        paths.clear()
        currentPath.reset()
        invalidate()
    }

}


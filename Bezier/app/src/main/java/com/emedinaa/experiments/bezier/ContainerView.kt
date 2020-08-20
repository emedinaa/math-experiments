package com.emedinaa.experiments.bezier

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

/**
 * @author : Eduardo Medina
 */
class ContainerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val paint: Paint = Paint()
    private val bezier: Bezier = Bezier()
    private var path: Path = Path()

    var start: BPoint = BPoint(0f, 0f)
    var c1: BPoint = BPoint(0f, 0f)
    var c2: BPoint = BPoint(0f, 0f)
    var end: BPoint = BPoint(0f, 0f)

    private fun setupGraphics() {
        paint.apply {
            color = Color.MAGENTA
            strokeWidth = 10f
            style = Paint.Style.STROKE
            isAntiAlias = true
        }
    }

    init {
        setupGraphics()
    }

    private fun drawBezier(canvas: Canvas?) {
        path.reset()
        bezier.draw(path, start, c1, c2, end)
        canvas?.drawPath(path, paint)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        drawBezier(canvas)
    }
}
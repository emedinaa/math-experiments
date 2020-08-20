package com.emedinaa.experiments.bezier

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @author : Eduardo Medina
 */
class MainActivity : AppCompatActivity(), View.OnTouchListener {

    private val startPoint = BPoint(0f, 0f)
    private val c1Point = BPoint(0f, 0f)
    private val c2Point = BPoint(0f, 0f)
    private val endPoint = BPoint(0f, 0f)
    private var valueAnimator: ValueAnimator?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
    }

    private fun setupUi() {
        imageViewDot1.setOnTouchListener(this)
        imageViewDot2.setOnTouchListener(this)
        imageViewDot3.setOnTouchListener(this)
        imageViewDot4.setOnTouchListener(this)

        button.setOnClickListener {
            runAnimation()
        }
    }

    private fun runAnimation(){
        stopAnimation()
        val bezier = Bezier()
        imageView.x = startPoint.x
        imageView.y = startPoint.y

        imageView.visibility = View.VISIBLE
        valueAnimator = ValueAnimator.ofFloat(0f, 100f).apply {
            duration = 2000
            addUpdateListener {
                val p = bezier.calculatePoints(it.animatedValue as Float/100,startPoint,c1Point,c2Point,endPoint)
                imageView.x = p.x - imageView.width/2.0f
                imageView.y = p.y - imageView.height/2.0f
            }
            start()
        }
    }

    private fun stopAnimation(){
        valueAnimator?.let {
            it.pause()
        }
        imageView.visibility = View.GONE
    }

    private fun render() {
        viewContainer?.apply {
            start = startPoint.apply {
                x = imageViewDot1.fitX()
                y = imageViewDot1.fitY()
            }
            c1 = c1Point.apply {
                x = imageViewDot2.fitX()
                y = imageViewDot2.fitY()
            }
            c2 = c2Point.apply {
                x = imageViewDot3.fitX()
                y = imageViewDot3.fitY()
            }
            end = endPoint.apply {
               x = imageViewDot4.fitX()
               y = imageViewDot4.fitY()
            }

            log("start $start c1:  $c1 c2: $c2 end $end")
            viewContainer.invalidate()
        }
    }

    override fun onTouch(view: View, e: MotionEvent): Boolean {
        var pX = 0f
        var pY = 0f

        when (e.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                stopAnimation()
                pX = view.x.minus(e.rawX)
                pY = view.y.minus(e.rawY)
            }

            MotionEvent.ACTION_MOVE -> {
                view.x = e.rawX - view.width / 2.0f
                view.y = e.rawY - view.height
                render()
            }

            else -> return false
        }
        return true
    }
}
package com.emedinaa.experiments.rotation

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import com.emedinaa.experiments.rotation.databinding.ActivityMainBinding

/**
 * @author Eduardo Medina
 */
private const val INCREASE = 10
private const val TAG = "CONSOLE"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var angle = 0f
    private var posX: Float = 0f
    private var posY: Float = 0f
    private var prevX: Float = 0f
    private var prevY: Float = 0f

    private fun run() {
        binding.root.performClick()

        binding.root.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN ->   true

                MotionEvent.ACTION_MOVE -> {
                    posX = motionEvent.rawX
                    posY = motionEvent.rawY

                    if ((posX >= prevX)) {
                        if (posY <= binding.view.y) {
                            angle += INCREASE
                            Log.v(TAG, "RD")
                        }

                    } else {
                        if (posY >= binding.view.y) {
                            angle += INCREASE
                            Log.v(TAG, "LU")
                        }
                    }
                    binding.imageView.rotation = angle

                    Log.v(TAG, "y $posY prevY $prevY view.y ${binding.view.y}")
                    prevX = posX
                    prevY = posY
                    false
                }
                MotionEvent.ACTION_UP -> {
                    view.performClick()
                    posX = 0f
                    prevX = 0f
                    posY = 0f
                    prevY = 0f
                    false
                }
                else -> false
            }

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        run()
    }
}
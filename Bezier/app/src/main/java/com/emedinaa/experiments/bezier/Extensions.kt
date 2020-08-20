package com.emedinaa.experiments.bezier

import android.content.Context
import android.util.Log
import android.view.View

/**
 * @author : Eduardo Medina
 */

fun View.fitX() = this.x + this.width / 2.0f
fun View.fitY() = this.y + this.height / 2.0f

fun Context.log(message: String) = Log.v("CONSOLE", message)
package com.emedinaa.experiments.bezier

import android.graphics.Path
import kotlin.math.pow

/**
 * @author : Eduardo Medina
 */
class Bezier {

    // 𝑓𝑥(𝑡):=(1−𝑡)3𝑝1𝑥+3𝑡(1−𝑡)2𝑝2𝑥+3𝑡2(1−𝑡)𝑝3𝑥+𝑡3𝑝4𝑥
    fun calculatePoints(t: Float, start: BPoint, c1: BPoint, c2: BPoint, end: BPoint): BPoint {
        val x =
            (1.0 - t).pow(3.0) * start.x + 3 * t * (1.0 - t).pow(2.0) * c1.x + 3 * t * t * (1.0 - t) * c2.x + t * t * t * end.x
        val y =
            (1.0 - t).pow(3.0) * start.y + 3 * t * (1.0 - t).pow(2.0) * c1.y + 3 * t * t * (1.0 - t) * c2.y + t * t * t * end.y
        return BPoint(x.toFloat(), y.toFloat())
    }

    fun draw(mPath: Path, pi: BPoint, c1: BPoint, c2: BPoint, pf: BPoint) {
        mPath.moveTo(pi.x, pi.y)
        mPath.cubicTo(c1.x, c1.y, c2.x, c2.y, pf.x, pf.y)
    }
}
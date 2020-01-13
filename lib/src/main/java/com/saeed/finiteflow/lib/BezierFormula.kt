package com.saeed.finiteflow.lib

class BezierFormula {

    companion object {
        fun twoPoints(x1: Float, x2: Float, t: Float): Float {
            val a = 1 - t
            val b = t
            return (a * x1) + (b * x2)
        }

        fun threePoints(x1: Float, x2: Float, x3: Float, t: Float): Float {
            val a = 1 - t
            val b = t
            return (a.sqr() * x1) + (2 * a * b * x2) + (b.sqr() * x3)
        }

        fun fourPoints(x1: Float, x2: Float, x3: Float, x4: Float, t: Float): Float {
            val a = 1 - t
            val b = t
            return (a.cube() * x1) + (3 * a.sqr() * b * x2) + (3 * a * b.sqr() * x3) + (b.cube() * x4)
        }
    }

}

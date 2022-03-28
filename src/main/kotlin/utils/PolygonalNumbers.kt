package utils

import kotlin.math.sqrt

object PolygonalNumbers {
    fun nthTriangular(n: Int): Long {
        return n.toLong() * (n + 1) / 2
    }

    fun isTriangular(t_n: Int): Boolean {
        val n = (1 + sqrt(8 * t_n + 1.0)) / 2.0
        return n % 1.0 == 0.0
    }
}
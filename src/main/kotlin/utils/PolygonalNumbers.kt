package utils

import kotlin.math.sqrt

object PolygonalNumbers {
    fun nthTriangular(n: Int): Long {
        return n.toLong() * (n + 1) / 2
    }

    fun isTriangular(t_n: Int): Boolean {
        return isTriangular(t_n.toLong())
    }

    fun isTriangular(t_n: Long): Boolean {
        val n = (1 + sqrt(8 * t_n + 1.0)) / 2.0
        return n % 1.0 == 0.0
    }

    fun nthPentagonal(n: Int): Long {
        return n.toLong() * (3 * n - 1) / 2
    }

    fun isPentagonal(p_n: Int): Boolean {
        return isPentagonal(p_n.toLong())
    }

    fun isPentagonal(p_n: Long): Boolean {
        val n = (1 + sqrt(24 * p_n + 1.0)) / 6.0
        return n % 1.0 == 0.0
    }

    fun nthHexagonal(n: Int): Long {
        return n.toLong() * (2 * n - 1)
    }

    fun isHexagonal(h_n: Long): Boolean {
        val n = (1 + sqrt(8 * h_n + 1.0)) / 4.0
        return n % 1.0 == 0.0
    }
}
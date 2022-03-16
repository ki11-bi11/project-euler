package utils

object PolygonalNumbers {
    fun nthTriangular(n: Int): Long {
        return n.toLong() * (n + 1) / 2
    }
}
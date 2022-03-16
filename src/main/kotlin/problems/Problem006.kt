package problems

import utils.PolygonalNumbers

fun main() {
    Problem006().run()
}

/**
 * The sum of the squares of the first ten natural numbers is 385,
 * The square of the sum of the first ten natural numbers is 3025,
 * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 2640.
 *
 * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
 */
class Problem006: Problem() {
    override suspend fun solve(): Long {
        val n = 100
        val sumOfSquares = (1..n).sumOf { i -> i * i }
        val nthTriangular = PolygonalNumbers.nthTriangular(n)
        val squareOfSums = nthTriangular * nthTriangular
        return squareOfSums - sumOfSquares
    }
}

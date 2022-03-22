package problems

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

fun main() {
    Problem080().run()
}

/**
 * It is well known that if the square root of a natural number is not an integer, then it is irrational.
 * The decimal expansion of such square roots is infinite without any repeating pattern at all.
 * The square root of two is 1.41421356237309504880...,
 * and the digital sum of the first one hundred decimal digits is 475.
 * For the first one hundred natural numbers,
 * find the total of the digital sums of the first one hundred decimal digits for all the irrational square roots.
 */
class Problem080: Problem() {
    override suspend fun solve(): Long {
        var sumDigitalSum = 0L

        val squares = setOf(1, 4, 9, 16, 25, 36, 49, 64, 81, 100)

        val mc = MathContext(105, RoundingMode.FLOOR)
        for (n in 1 .. 100) {
            if (n in squares) continue
            val sqrt = BigDecimal(n).sqrt(mc)
            val str = sqrt.toString().replace(".","").take(100)
            println("$n: $str [${str.length}]")
            val digitalSum = str.sumOf { c -> c.digitToInt() }
            sumDigitalSum += digitalSum
        }

        return sumDigitalSum
    }
}



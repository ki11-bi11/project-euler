package problems

import utils.Divisors

fun main() {
    Problem021().run()
}

/**
 * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
 * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284.
 * The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
 * Evaluate the sum of all the amicable numbers under 10000.
 */
class Problem021: Problem() {
    override suspend fun solve(): Long {
        val n = 10000
        val dValues = LongArray(n) { if(it == 0) 0 else d(it) }

        var amicableSum = 0L
        for (a in 1 until 10000) {
            val b = dValues[a].toInt()
            if(a != b && b < n && dValues[b].toInt() == a) {
                amicableSum += a
            }
        }

        return amicableSum
    }

    fun d(n: Int): Long {
        return Divisors.getProperDivisors(n.toLong()).sum()
    }
}
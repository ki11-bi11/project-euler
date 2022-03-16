package problems

import utils.Primes
import kotlin.math.pow

fun main() {
    Problem005().run()
}

/**
 * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
 */
class Problem005: Problem() {
    override suspend fun solve(): Long {
        val divisors = 20 downTo 1

        // Smallest number is simply the product of all the largest prime factors of the divisors
        return divisors.flatMap { d -> Primes.primePowers(d) }
            .groupBy { (p, _) -> p }.values
            .map { lsPrimePow -> lsPrimePow.maxByOrNull { (_, pow) -> pow }!! }
            .fold(1L) { prod, (p, pow) -> prod * p.toDouble().pow(pow).toLong() }
    }
}

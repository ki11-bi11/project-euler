package problems

import utils.Primes

fun main() {
    Problem007().run()
}

/**
 * The prime factors of 13195 are 5, 7, 13 and 29.
 * What is the largest prime factor of the number 600851475143?
 */
class Problem007: Problem() {
    override suspend fun solve(): Long {
        val n = 10_001
        val primes = Primes.getFirstNPrimes(n.toLong())
        return primes.sorted()[n - 1]
    }
}
package problems

import utils.Primes

fun main() {
    Problem007().run()
}

/**
 * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
 * What is the 10 001st prime number?
 */
class Problem007: Problem() {
    override suspend fun solve(): Long {
        val n = 10_001
        val primes = Primes.getFirstNPrimes(n.toLong())
        return primes.sorted()[n - 1]
    }
}
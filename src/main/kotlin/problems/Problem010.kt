package problems

import utils.Primes

fun main() {
    Problem010().run()
}

/**
 * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 */
class Problem010: Problem() {
    override suspend fun solve(): Long {
        val primes = Primes.getPrimesUpTo(2_000_000L)
        return primes.sumOf { p -> p }
    }
}
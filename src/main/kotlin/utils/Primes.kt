package utils

import kotlin.math.ln
import kotlin.math.sqrt

object Primes {
    private var primes: Set<Long> = emptySet()
    private var maxGenerated: Long = 0

    fun getPrimesUpTo(n: Long): Set<Long> {
        if (n > maxGenerated) {
            primes = sieve(n)
            maxGenerated = n
        }

        return primes
    }

    fun getFirstNPrimes(n: Long): Set<Long> {
        if (primes.size < n) {
            val p_n = (n * ln(n.toDouble()) + n * ln(ln(n.toDouble()))).toLong()
            primes = sieve(p_n)
        }

        return primes
    }

    private fun sieve(n: Long): Set<Long> {
        val composites = mutableSetOf<Long>()
        val primes = mutableSetOf<Long>()

        var p = 2L
        val x = sqrt(n.toDouble()).toLong()
        while (p <= sqrt(n.toDouble()).toLong()) {
            if (p !in composites) {
                primes.add(p)
                for (i in 2 * p .. n step p) {
                    composites.add(i)
                }
            }
            p++
        }

        while (p <= n) {
            if (p !in composites) {
                primes.add(p)
            }
            p++
        }

        return primes
    }

    fun isPrime(n: Long) = n in getPrimesUpTo(n).toSet()

    fun primeFactors(n: Long): Set<Long> {
        return getPrimesUpTo(n)
            .filter { p -> n % p == 0L }
            .toSet()
    }

    fun primePowers(n: Int): Set<PrimePower> {
        return primePowers(n.toLong())
    }

    fun primePowers(n: Long): Set<PrimePower> {
        return getPrimesUpTo(n)
            .filter { p -> n % p == 0L }
            .map { p ->
                var rem = n
                var power = 0
                while (rem % p == 0L) {
                    rem /= p
                    power++
                }
                PrimePower(p, power)
            }
            .toSet()
    }

    data class PrimePower (
        val prime: Long,
        val power: Int,
    )
}
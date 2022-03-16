package utils

import kotlin.math.sqrt

object Primes {
    private var primes: Sequence<Long> = emptySequence()
    private var maxGenerated: Long = 0

    fun getPrimesUpTo(n: Long): Sequence<Long> {
        if (n > maxGenerated) {
            primes = sieve(n)
            maxGenerated = n
        }

        return primes
    }

    private fun sieve(n: Long): Sequence<Long> {
        val composites = mutableSetOf<Long>()

        var p = 2L
        return sequence {
            while (p < sqrt(n.toDouble()).toLong()) {
                if (p !in composites) {
                    yield(p)
                    for (i in 2 * p until n step p) {
                        composites.add(i)
                    }
                }
                p++
            }
        }
    }

    fun isPrime(n: Long) = n in getPrimesUpTo(n).toSet()

    fun primeFactors(n: Long): Set<Long> {
        return getPrimesUpTo(n)
            .filter { p -> n % p == 0L }
            .toSet()
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
        val multiple: Int,
    )
}
package utils

import kotlin.math.pow

object Divisors {
    fun getNumberOfDivisors(n: Long): Long {
        val primePowers = Primes.primePowers(n)
        return primePowers.fold(1) { divisors, (_, pow) -> divisors * (pow + 1) }
    }

    fun getDivisors(n: Long): Set<Long> {
        val primePowers = Primes.primePowers(n)
        return getDivisors(setOf(1), ArrayDeque(primePowers.toList()))
    }

    private fun getDivisors(divisors: Set<Long>, remainingPrimePowers: ArrayDeque<Primes.PrimePower>): Set<Long> {
        if (remainingPrimePowers.isEmpty()) return divisors

        val nextPrimePower = remainingPrimePowers.removeFirst()
        val newDivisors = mutableSetOf<Long>()
        for (exp in 0 .. nextPrimePower.power) {
            newDivisors.addAll(divisors.map { d -> d * nextPrimePower.prime.toDouble().pow(exp).toLong() })
        }

        return getDivisors(newDivisors, remainingPrimePowers)
    }

    fun getProperDivisors(n: Long): Set<Long> {
        val divisors = getDivisors(n).toMutableSet()
        divisors.remove(n)
        return divisors
    }
}
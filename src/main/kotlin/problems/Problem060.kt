package problems

import utils.Primes

fun main() {
    Problem060().run()
}

/**
 * The primes 3, 7, 109, and 673, are quite remarkable.
 * By taking any two primes and concatenating them in any order the result will always be prime.
 * For example, taking 7 and 109, both 7109 and 1097 are prime.
 * The sum of these four primes, 792, represents the lowest sum for a set of four primes with this property.
 * Find the lowest sum for a set of five primes for which any two primes concatenate to produce another prime.
 */
class Problem060: Problem() {
    override suspend fun solve(): Long {
        val maxPrime = 9500L
        // sieve for primes up to concatenation of target number
        Primes.getPrimesUpTo(concatenate(maxPrime, maxPrime))
        val primes = Primes.getPrimesUpTo(maxPrime).toList()
        var primeSets = mutableListOf<Set<Long>>()
        for (n1 in 0 until primes.size - 1) {
            for (n2 in n1 until primes.size) {
                val p1 = primes[n1]
                val p2 = primes[n2]
                if (Primes.isPrime(concatenate(p1, p2)) && Primes.isPrime(concatenate(p2, p1))) {
                    primeSets.add(setOf(p1, p2))
                }
            }
        }

        for (i in 2 until 5) {
            primeSets = findPrimeSets(primes, primeSets).toMutableList()
        }

        return primeSets.firstOrNull()?.sum() ?: 0L
    }

    private fun concatenate(p1: Long, p2: Long): Long {
        return (p1.toString() + p2.toString()).toLong()
    }

    private fun findPrimeSets(primes: List<Long>, primeSets: List<Set<Long>>): List<Set<Long>> {
        val shorterPrimeSets = primeSets.toMutableList()
        val longerPrimeSets = mutableListOf<Set<Long>>()
        for (p in primes) {
            val pSets = shorterPrimeSets.filter { s -> p in s }
            for(s1 in pSets) {
                val sOther = s1 - p
                pSets.asSequence()
                    .map { s2 -> (s2 - p) union sOther }
                    .filter { s3 -> s3 in shorterPrimeSets }
                    .forEach { s3 -> longerPrimeSets.add(s1 union s3) }
            }
            shorterPrimeSets.removeAll(pSets)
        }
        return longerPrimeSets
    }
}
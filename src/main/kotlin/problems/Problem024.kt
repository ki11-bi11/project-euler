package problems

import utils.Combinatorics
import utils.Divisors
import utils.Primes

fun main() {
    Problem024().run()
}

/**
 * A permutation is an ordered arrangement of objects.
 * For example, 3124 is one possible permutation of the digits 1, 2, 3 and 4.
 * If all of the permutations are listed numerically or alphabetically, we call it lexicographic order.
 * The lexicographic permutations of 0, 1 and 2 are:
 *      012   021   102   120   201   210
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8 and 9?
 */
class Problem024: Problem() {
    override suspend fun solve(): Long {
        println(Combinatorics.nthLexicographical(1_000_000L, *"0123456789".toCharArray()))
        return -1L
    }
}
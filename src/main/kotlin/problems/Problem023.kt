package problems

import utils.Divisors
import utils.Primes

fun main() {
    Problem023().run()
}

/**
 * A perfect number is a number for which the sum of its proper divisors is exactly equal to the number.
 * For example, the sum of the proper divisors of 28 would be 1 + 2 + 4 + 7 + 14 = 28, which means that 28 is a perfect number.
 * A number n is called deficient if the sum of its proper divisors is less than n,
 * and it is called abundant if this sum exceeds n.
 * As 12 is the smallest abundant number, 1 + 2 + 3 + 4 + 6 = 16,
 * the smallest number that can be written as the sum of two abundant numbers is 24.
 * By mathematical analysis, it can be shown that all integers greater than 28123 can be written as the sum of two abundant numbers.
 * However, this upper limit cannot be reduced any further by analysis even though it is known that
 * the greatest number that cannot be expressed as the sum of two abundant numbers is less than this limit.
 * Find the sum of all the positive integers which cannot be written as the sum of two abundant numbers.
 */
class Problem023: Problem() {
    override suspend fun solve(): Long {
        val UB = 28123L
        // Sieve for primes up to the limit so that we can get the divisors of numbers less than it
        Primes.getPrimesUpTo(UB)

        val abundantNumbers = mutableSetOf<Long>()
        for (n in 12 .. UB) {
            if (Divisors.getProperDivisors(n).sum() > n) {
                abundantNumbers.add(n)
            }
        }

        // 24 is the first number that can, so first 23 cannot. 23*24/2 = 276
        var sumCannotBeWrittenAsSumTwoAbundant = 276L
        for (n in 24 .. UB) {
            val abundantLessThan = abundantNumbers.filter { a -> a < n }
            if (abundantLessThan.any { a1 -> (n - a1) in abundantNumbers }) {
                continue
            }
            sumCannotBeWrittenAsSumTwoAbundant += n
        }

        return sumCannotBeWrittenAsSumTwoAbundant
    }
}
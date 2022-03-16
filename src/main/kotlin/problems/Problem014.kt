package problems

fun main() {
    Problem014().run()
}

/**
 * The following iterative sequence is defined for the set of positive integers:
 *
 * n → n/2 (n is even)
 * n → 3n + 1 (n is odd)
 *
 * Using the rule above and starting with 13, we generate the following sequence:
 *
 * 13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
 *
 * It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
 * Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.
 * Which starting number, under one million, produces the longest chain?
 * NOTE: Once the chain starts the terms are allowed to go above one million.
 */
class Problem014: Problem() {
    private val knownCollatzLengths = mutableMapOf(13L to 10L)

    override suspend fun solve(): Long {
        var bestStart = 13L
        var maxLength = 10L
        for(n in 1L until 1_000_000L) {
            val length = collatzLength(n)
            if (length > maxLength) {
                bestStart = n
                maxLength = length
            }
        }
        return bestStart
    }

    fun collatzLength(start: Long): Long {
        if(start in knownCollatzLengths) return knownCollatzLengths.getValue(start)

        val seq = collatz(start).iterator()
        var length = 0L
        while (seq.hasNext()) {
            val n = seq.next()
            if (n in knownCollatzLengths) {
                length += knownCollatzLengths.getValue(n)
                break
            } else {
                length++
            }
        }
        knownCollatzLengths[start] = length
        return length
    }

    fun collatz(start: Long): Sequence<Long> {
        return generateSequence(start) { n ->
            when {
                n == 1L -> null
                n % 2 == 0L -> n / 2
                else -> 3 * n + 1
            }
        }
    }
}
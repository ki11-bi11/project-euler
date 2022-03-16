package problems

fun main() {
    Problem002().run()
}

/**
 * Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be:
 * 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
 * By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.
 */
class Problem002: Problem() {
    private val fib = mutableListOf(1L, 1L, 2L, 3L, 5L, 8L, 13L, 21L, 34L, 55L, 89L)

    override suspend fun solve(): Long {
        var n = 1
        var last = fib(n)
        var sum = 0L
        while (last < 4_000_000L) {
            if(last % 2L == 0L) sum += last
            n++
            last = fib(n)
        }
        return sum
    }

    fun fib(n: Int): Long {
        if(n < fib.size) return fib[n]

        val fib_n = fib(n - 1) + fib(n - 2)
        fib.add(n, fib_n)
        return fib_n
    }
}
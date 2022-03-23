package problems

fun main() {
    Problem145().run()
}

/**
 * Some positive integers n have the property that the sum (n + reverse(n)) consists entirely of odd (decimal) digits.
 * For instance, 36 + 63 = 99 and 409 + 904 = 1313. We will call such numbers reversible; so 36, 63, 409, and 904 are reversible.
 * Leading zeroes are not allowed in either n or reverse(n).
 * There are 120 reversible numbers below one-thousand.
 * How many reversible numbers are there below one-billion (109)?
 */
class Problem145: Problem() {
    override suspend fun solve(): Long {
        var count = 0L
        for (n in 1 until 1_000_000_000L) {
            if (n % 10 == 0L) continue
            if (isReversible(n)) count++
        }
        return count
    }

    fun isReversible(n: Long): Boolean {
        return allOddDigits(n + reverse(n))
    }

    fun reverse(n: Long) = n.toString().reversed().toInt()

    fun allOddDigits(n: Long): Boolean {
        var num = n
        while (num > 0) {
            if (num % 2 == 0L) return false
            num /= 10
        }
        return true
    }
}



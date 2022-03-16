package problems

fun main() {
    Problem009().run()
}

/**
 * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
 * a^2 + b^2 = c^2
 * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
 * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
 * Find the product abc.
 */
class Problem009: Problem() {
    override suspend fun solve(): Long {
        val n = 1000
        for (a in 1 .. n / 3) {
            for (b in a + 1 .. (n - a) / 2) {
                val c = n - (a + b)
                if ((a * a + b * b) == c * c) {
                    return (a * b * c).toLong()
                }
            }
        }
        return -1
    }
}
package utils

object Strings {
    fun isPalindromic(s: String) = s == s.reversed()

    fun wordScore(s: String): Int {
        var sum = 0
        for (c in s) {
            sum += (c.code - 'A'.code + 1)
        }
        return sum
    }
}
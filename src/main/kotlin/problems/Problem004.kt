package problems

import utils.Strings

fun main() {
    Problem004().run()
}

/**
 * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */
class Problem004: Problem() {
    override suspend fun solve(): Long {
        var maxPalindrome = 0L
        for(a in 999 downTo 100) {
            for(b in 999 downTo 100) {
                val product = a * b
                if (Strings.isPalindromic(product.toString()) && product > maxPalindrome) {
                    maxPalindrome = product.toLong()
                }
            }
        }
        return maxPalindrome
    }
}
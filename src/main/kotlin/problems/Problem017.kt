package problems

fun main() {
    Problem017().run()
}

/**
 * If the numbers 1 to 5 are written out in words: one, two, three, four, five, then there are 3 + 3 + 5 + 4 + 4 = 19 letters used in total.
 * If all the numbers from 1 to 1000 (one thousand) inclusive were written out in words, how many letters would be used?
 * NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two) contains 23 letters and 115 (one hundred and fifteen) contains 20 letters.
 * The use of "and" when writing out numbers is in compliance with British usage.
 */
class Problem017: Problem() {
    private val onesNames = arrayOf("", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    private val tensNames = arrayOf("", null, "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety")
    private val teensNames = arrayOf("ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen")
    private val hundred = "hundred"
    private val hundredsSeparator = "and"
    private val thousand = "thousand"

    override suspend fun solve(): Long {
        var digitCount = 0L
        for (n in 1 .. 1000) {
            digitCount += getWrittenRepresentation(n).length
        }
        return digitCount
    }

    fun getWrittenRepresentation(n: Int): String {
        return when {
            n >= 1000 -> onesNames[n / 1000] + thousand + getWrittenRepresentation(n % 1000)
            n >= 100 -> onesNames[n / 100] + hundred + (if (n % 100 > 0) hundredsSeparator else "") + getWrittenRepresentation(n % 100)
            n >= 20 -> tensNames[n / 10]!! + getWrittenRepresentation(n % 10)
            n >= 10 -> teensNames[n - 10]
            else -> onesNames[n]
        }
    }
}
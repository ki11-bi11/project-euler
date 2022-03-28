package problems

import utils.PolygonalNumbers
import utils.Strings
import java.io.File
import kotlin.math.sqrt

fun main() {
    Problem042().run()
}

/**
 * The nth term of the sequence of triangle numbers is given by, tn = ½n(n+1); so the first ten triangle numbers are:
 * 1, 3, 6, 10, 15, 21, 28, 36, 45, 55, ...
 * By converting each letter in a word to a number corresponding to its alphabetical position
 * and adding these values we form a word value.
 * For example, the word value for SKY is 19 + 11 + 25 = 55 = t_10.
 * If the word value is a triangle number then we shall call the word a triangle word.
 * Using words.txt, a text file containing nearly two-thousand common English words, how many are triangle words?
 */
class Problem042: Problem() {
    override suspend fun solve(): Long {
        val f = File("src/main/resources/p042_words.txt").readText()
        val triangleCount = f.split(",")
            .asSequence()
            .map { w -> w.replace("\"", "") }
            .count { word -> PolygonalNumbers.isTriangular(Strings.wordScore(word)) }
        return triangleCount.toLong()
    }
}
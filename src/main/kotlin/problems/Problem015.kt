package problems

import utils.BigFraction
import utils.Combinatorics

fun main() {
    Problem015().run()
}

/**
 * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down, there are exactly 6 routes to the bottom right corner.
 * How many such routes are there through a 20×20 grid?
 */
class Problem015: Problem() {
    override suspend fun solve(): Long {
        // For an r x c grid, we need to pick r "D"owns and c "R"ights
        // Thus this is equivalent to the number of permutaions w/ repetition
        // (D + R)! / D!R!
        val nDown = 20
        val nRight = 20
        val numeratorFactors = LongArray(nDown + nRight) { n -> nDown + nRight - n.toLong() }
        val downRepeatFactors = LongArray(nDown) { d -> nDown - d.toLong() }
        val rightRepeatFactors = LongArray(nDown) { d -> nDown - d.toLong() }
        return BigFraction(numeratorFactors, downRepeatFactors + rightRepeatFactors).getValue()
    }
}
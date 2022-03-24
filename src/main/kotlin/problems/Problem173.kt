package problems

fun main() {
    Problem173().run()
}

/**
 * We shall define a square lamina to be a square outline with a square "hole"
 * so that the shape possesses vertical and horizontal symmetry.
 * For example, using exactly thirty-two square tiles we can form two different square laminae
 * With one-hundred tiles, and not necessarily using all of the tiles at one time,
 * it is possible to form forty-one different square laminae.
 * Using up to one million tiles how many different square laminae can be formed?
 */
class Problem173: Problem() {
    override suspend fun solve(): Long {
        // Let a lamina be defined by (b, s)
        // where b is the length of the big square, s is the length of the smaller square
        // Lamina (b,s) uses b^2 - s^2 = (b - s)(b + s) squares
        val maxSquares = 1E6.toLong()
        var laminae = 0L
        for (b in 3 .. maxSquares / 4 + 1) {
            for (s in b - 2 downTo 1 step 2) {
                val squares = (b + s) * (b - s)
                if (squares > maxSquares) break
                println("($b, $s) = $squares")
                laminae++
            }
        }
        return laminae
    }
}

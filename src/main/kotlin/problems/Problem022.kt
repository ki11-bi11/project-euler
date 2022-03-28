package problems

import java.io.File

fun main() {
    Problem022().run()
}

/**
 * Using names.txt, a text file containing over five-thousand first names, begin by sorting it into alphabetical order.
 * Then working out the alphabetical value for each name, multiply this value by its alphabetical position in the list to obtain a name score.
 * For example, when the list is sorted into alphabetical order,
 * COLIN, which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list.
 * So, COLIN would obtain a score of 938 × 53 = 49714.
 * What is the total of all the name scores in the file?
 */
class Problem022: Problem() {
    override suspend fun solve(): Long {
        val f = File("src/main/resources/p022_names.txt").readText()
        val names = f.split(",").map { n -> n.replace("\"", "") }.sorted()
        return names.foldIndexed(0L) { i, sum, name ->
            sum + (i + 1) * nameScore(name)
        }
    }

    fun nameScore(name: String): Int {
        var sum = 0
        for (c in name) {
            sum += (c.code - 'A'.code + 1)
        }
        return sum
    }
}
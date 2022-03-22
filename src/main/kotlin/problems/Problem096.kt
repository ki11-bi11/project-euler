package problems

fun main() {
    Problem096().run()
}

/**
 * Su Doku (Japanese meaning number place) is the name given to a popular puzzle concept.
 * Its origin is unclear, but credit must be attributed to Leonhard Euler who invented a similar,
 * and much more difficult, puzzle idea called Latin Squares.
 * The objective of Su Doku puzzles, however, is to replace the blanks (or zeros) in a 9 by 9 grid in such that
 * each row, column, and 3 by 3 box contains each of the digits 1 to 9.
 * Below is an example of a typical starting puzzle grid and its solution grid.
 * A well constructed Su Doku puzzle has a unique solution and can be solved by logic,
 * although it may be necessary to employ "guess and test" methods in order to eliminate options
 * (there is much contested opinion over this). The complexity of the search determines the difficulty of the puzzle;
 * the example above is considered easy because it can be solved by straight forward direct deduction.
 * The file, sudoku.txt contains fifty different Su Doku puzzles ranging in difficulty,
 * but all with unique solutions (the first puzzle in the file is the example above).
 * By solving all fifty puzzles find the sum of the 3-digit numbers found in the top left corner of each solution grid;
 * for example, 483 is the 3-digit number found in the top left corner of the solution grid above.
 */
class Problem096: Problem() {
    override suspend fun solve(): Long {
        val file = ClassLoader.getSystemResourceAsStream("p096_sudoku.txt")!!.reader()
        val lines = file.readLines()
        val games = lines.chunked(Sudoku.N + 1)

        var sumFirst3 = 0L
        for (game in games) {
            println("Solving ${game.first()}")
            val grid = game.takeLast(Sudoku.N)
                .map { l -> l.map { c -> c.digitToInt() }.toIntArray() }
                .toTypedArray()

            val sudoku = Sudoku(grid)
            sudoku.solve()
            val first3 = sudoku.grid[0].take(3).joinToString("").toInt()
            sumFirst3 += first3
        }

        return sumFirst3
    }
}

class Sudoku(
    val grid: Array<IntArray>
) {

    companion object {
        const val N = 9
    }

    fun solve(): Boolean {
        return solve(grid, 0, 0)
    }

    private fun solve(grid: Array<IntArray>, r: Int, c: Int): Boolean {
        if(r == N) {
            println(printGrid(grid))
            return true
        }

        var nextCol = c + 1
        var nextRow = r
        if (nextCol == N) {
            nextRow++
            nextCol = 0
        }

        // Already solved
        if (grid[r][c] > 0) return solve(grid, nextRow, nextCol)

        for (num in 1 .. N) {
            if (isSafe(grid, r, c, num)) {
                grid[r][c] = num

                if (solve(grid, nextRow, nextCol)) {
                    return true
                }
            }
            grid[r][c] = 0
        }
        return false
    }

    private fun isSafe(grid: Array<IntArray>, r: Int, c: Int, num: Int): Boolean {
        // Check row
        if (grid[r].contains(num)) return false

        // Check col
        for (row in 0 until N) {
            if (grid[row][c] == num) return false
        }

        // Check box
        val boxRow = r - r % 3
        val boxCol = c - c % 3
        for (i in 0 until 3) {
            for (j in 0 until 3) {
                if (grid[i + boxRow][j + boxCol] == num) return false
            }
        }

        return true;
    }

    fun printGrid(grid: Array<IntArray>): String {
        val sb = StringBuilder()
        for (r in 0 until N) {
            sb.appendLine(grid[r].joinToString(" "))
        }
        return sb.toString()
    }
}



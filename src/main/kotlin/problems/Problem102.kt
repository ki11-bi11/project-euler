package problems

import java.io.File

fun main() {
    Problem102().run()
}

/**
 * Three distinct points are plotted at random on a Cartesian plane, for which -1000 ≤ x, y ≤ 1000, such that a triangle is formed.
 * Consider the following two triangles:
 * A(-340,495), B(-153,-910), C(835,-947)
 * X(-175,41), Y(-421,-714), Z(574,-645)
 * It can be verified that triangle ABC contains the origin, whereas triangle XYZ does not.
 * Using p102_triangles.txt a 27K text file containing the co-ordinates of one thousand "random" triangles,
 * find the number of triangles for which the interior contains the origin.
 */
class Problem102: Problem() {
    override suspend fun solve(): Long {
        var count = 0L
        val file = ClassLoader.getSystemResourceAsStream("p102_triangles.txt")!!.reader()
        for (line in file.readLines()) {
            val triangle = line.split(",").chunked(2).map { ls -> Point(ls[0].toInt(), ls[1].toInt()) }
            if (triangleContainsOrigin(triangle[0], triangle[1], triangle[2])) {
                println(triangle)
                count++
            }
        }
        return count
    }

    fun triangleContainsOrigin(a: Point, b: Point, c: Point): Boolean {
        val origin = Point(0, 0)

        val ab = pointSign(origin, a, b)
        val bc = pointSign(origin, b, c)
        val ca = pointSign(origin, c, a)

        return ((ab < 0) == (bc < 0)) && ((bc < 0) == (ca < 0))
    }

    fun pointSign(c: Point, a: Point, b: Point): Int {
        // Point slope: y - y_1 = m * (x - x_1)
        // Let a = (x_1, y_1) be on the line, calc slope w.r.t b
        // m = ((b.y - a.y)/(b.x - a.x))
        // Side of line that c is on is the sign of:
        // => (y - a.y) * (b.x - a.x) ?= (b.y - a.y) * (x - a.x)
        return (c.y - a.y) * (b.x - a.x) - (b.y - a.y) * (c.x - a.x)
    }
}

data class Point(
    val x: Int,
    val y: Int
)
package utils

object Combinatorics {

    private val knownFactorials = mutableMapOf(0L to 1L, 1L to 1L)

    fun factorial(n: Long): Long {
        require(n >= 0) { "n must be non-negative" }
        if(n in knownFactorials) return knownFactorials.getValue(n)
        val result = n * factorial(n - 1)
        knownFactorials[n] = result
        return result
    }

    fun combinations(n: Long, r: Long): Long {
        require(r <= n) { "r must be <= n"}
        return factorial(n) / (factorial(r) * factorial(n - r))
    }

    fun permutationsWithRepetition(n: Long, vararg rs: Long): Long {
        require(rs.all { r -> r <= n }) { "all rs must be <= n"}
        return factorial(n) / rs.fold(1L) { prod, r -> prod * factorial(r) }
    }

    fun nthLexicographical(n: Long, vararg objects: Char): String {
        val sortedObjects = objects.sorted().toMutableList()
        var permutation = ""
        var nthLexicographical = 1L
        for (i in objects.indices.reversed()) {
            val permutationsOfLowerPlaces = factorial(i.toLong())
            val rem = n - nthLexicographical
            val nthBiggest = (rem / permutationsOfLowerPlaces).toInt()
            val nthBiggestObject = sortedObjects[nthBiggest]
            permutation += nthBiggestObject
            sortedObjects.remove(nthBiggestObject)
            nthLexicographical += (nthBiggest * permutationsOfLowerPlaces)
        }
        return permutation
    }
}
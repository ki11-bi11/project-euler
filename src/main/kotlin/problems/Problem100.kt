package problems

import kotlin.math.*

fun main() {
    Problem100().run()
}

/**
 * If a box contains twenty-one coloured discs, composed of fifteen blue discs and six red discs,
 * and two discs were taken at random, it can be seen that the probability of taking two blue discs,
 * P(BB) = (15/21)×(14/20) = 1/2.
 * The next such arrangement, for which there is exactly 50% chance of taking two blue discs at random,
 * is a box containing eighty-five blue discs and thirty-five red discs.
 * By finding the first arrangement to contain over 1012 = 1,000,000,000,000 discs in total,
 * determine the number of blue discs that the box would contain.
 */
class Problem100: Problem() {
    override suspend fun solve(): Long {
        // Let T = B + R
        // Then P(BB) = B(B-1) / T(T-1)
        // If we set P(BB) = 1/2 we can solve for T in terms of B,
        // T = 1/2 + sqrt(2B(B-1)+1/4)
        // Arrangement works if B is a Diophantine Pair
        val B_seq = nextDiophantinePairs().iterator()
        while(B_seq.hasNext()) {
            val B = B_seq.next()
            val T = 1/2.0 + sqrt(2.0*B*(B-1)+1/4.0)
            if (T > 1E12) {
                println("P(BB) = $B/$T * ${B-1}/${T-1} = 1/2")
                return B
            }
        }
        return -1
    }

    // A011900
    fun nextDiophantinePairs(): Sequence<Long> {
        return sequence {
            var a_nMinus2 = 1L
            var a_nMinus1 = 3L

            yield(a_nMinus1)

            while (true) {
                val a_n = 6*a_nMinus1 - a_nMinus2 - 2
                yield(a_n)
                a_nMinus2 = a_nMinus1
                a_nMinus1 = a_n
            }
        }
    }
}

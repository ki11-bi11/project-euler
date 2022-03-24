package problems

fun main() {
    Problem151().run()
}

/**
 * A printing shop runs 16 batches (jobs) every week and each batch requires a sheet of special colour-proofing paper of size A5.
 * Every Monday morning, the supervisor opens a new envelope, containing a large sheet of the special paper with size A1.
 * The supervisor proceeds to cut it in half, thus getting two sheets of size A2.
 * Then one of the sheets is cut in half to get two sheets of size A3 and so on until an A5-size sheet is obtained,
 * which is needed for the first batch of the week.
 * All the unused sheets are placed back in the envelope.
 * At the beginning of each subsequent batch, the supervisor takes from the envelope one sheet of paper at random.
 * If it is of size A5, then it is used.
 * If it is larger, then the 'cut-in-half' procedure is repeated until an A5-size sheet is obtained,
 * and any remaining sheets are always placed back in the envelope.
 * Excluding the first and last batch of the week,
 * find the expected number of times (during each week) that the supervisor finds a single sheet of paper in the envelope.
 * Give your answer rounded to six decimal places using the format x.xxxxxx .
 */
class Problem151: Problem() {
    override suspend fun solve(): Long {
        println(processBatch(2, PaperSizes.A1.cutToA5(), 0.0))
        return -1L
    }

    private fun processBatch(batch: Int, envelope: List<PaperSizes>, timesOneSheetInEnvelope: Double): Double {
        if (batch == 16) return timesOneSheetInEnvelope

        if (envelope.size == 1)  {
            return processBatch(batch + 1, envelope.first().cutToA5(), timesOneSheetInEnvelope + 1)
        }

        val sizesInEnvelope = envelope.groupBy { p -> p }.mapValues { (_, ls) -> ls.size }

        var average = 0.0
        for ((size, count) in sizesInEnvelope) {
            val newEnvelope = envelope.toMutableList().apply {
                remove(size)
                addAll(size.cutToA5())
            }
            average += count * processBatch(batch + 1, newEnvelope, timesOneSheetInEnvelope)
        }

        return average / envelope.size
    }
}

enum class PaperSizes {
    A1,
    A2,
    A3,
    A4,
    A5;

    fun cutToA5(): List<PaperSizes> {
        return when (this) {
            A1 -> listOf(A2, A3, A4, A5)
            A2 -> listOf(A3, A4, A5)
            A3 -> listOf(A4, A5)
            A4 -> listOf(A5)
            else -> emptyList()
        }
    }
}





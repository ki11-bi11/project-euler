package problems

import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.time.ExperimentalTime
import kotlin.time.measureTimedValue

abstract class Problem {
    companion object {
       const val TIME_LIMIT = 60_000L
    }

    @OptIn(ExperimentalTime::class)
    fun run() {
        runBlocking {
            try {
                val (result, elapsed) = withTimeout(TIME_LIMIT) {
                    measureTimedValue {
                        solve()
                    }
                }
                println("Answer: $result")
                println("Took $elapsed")
            } catch (ex: TimeoutCancellationException) {
                println("Problem did not solve within 1 minute time limit")
            }
        }
    }

    abstract suspend fun solve(): Long
}
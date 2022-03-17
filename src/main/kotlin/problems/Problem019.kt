package problems

import utils.CalendarDay
import utils.DayOfWeek
import utils.Month

fun main() {
    Problem019().run()
}

/**
 * You are given the following information, but you may prefer to do some research for yourself.
 *
 * - 1 Jan 1900 was a Monday
 * - Thirty days has September,
 *   April, June and November.
 *   All the rest have thirty-one,
 *   Saving February alone,
 *   Which has twenty-eight, rain or shine.
 *   And on leap years, twenty-nine.
 * - A leap year occurs on any year evenly divisible by 4, but not on a century unless it is divisible by 400.
 *
 * How many Sundays fell on the first of the month during the twentieth century (1 Jan 1901 to 31 Dec 2000)?
 */
class Problem019: Problem() {
    override suspend fun solve(): Long {
        val date = CalendarDay(Month.January, 1, 1900, DayOfWeek.Monday)
        var count = 0L
        while(date.year <= 2000) {
            date.advanceDate()
            if (date.year >= 1901 && date.day == 1 && date.dayOfWeek == DayOfWeek.Sunday) {
                count++
            }
        }
        return count
    }
}
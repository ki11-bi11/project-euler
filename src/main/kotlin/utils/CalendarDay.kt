package utils

class CalendarDay(
    var month: Month,
    var day: Int,
    var year: Int,
    var dayOfWeek: DayOfWeek
) {

    companion object {
        fun isLeapYear(year: Int) = if(year % 100 == 0) year % 400 == 0 else year % 4 == 0
    }

    fun advanceDate() {
        day += 1
        val daysInMonth = month.daysInMonth + if(month == Month.February && isLeapYear(year)) 1 else 0
        if(daysInMonth < day) {
            day -= daysInMonth
            month = Month.values()[(month.ordinal + 1) % 12]
            if (month == Month.January) year++
        }

        dayOfWeek = DayOfWeek.values()[(dayOfWeek.ordinal + 1) % 7]
    }
}

enum class Month(val daysInMonth: Int) {
    January(31),
    February(28),
    March(31),
    April(30),
    May(31),
    June(30),
    July(31),
    August(31),
    September(30),
    October(31),
    November(30),
    December(31)
}

enum class DayOfWeek {
    Sunday,
    Monday,
    Tuesday,
    Wednesday,
    Thursday,
    Friday,
    Saturday
}
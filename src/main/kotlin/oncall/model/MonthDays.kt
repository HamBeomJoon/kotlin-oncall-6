package oncall.model

object MonthDays {
    private val dayOfMonth = mapOf(
        1 to 31, 3 to 31, 5 to 31, 7 to 31, 8 to 31, 10 to 31, 12 to 31,
        4 to 30, 6 to 30, 9 to 30, 11 to 30, 2 to 28
    )

    fun getDaysInMonth(month: Int): Int? {
        return dayOfMonth[month]
    }
}
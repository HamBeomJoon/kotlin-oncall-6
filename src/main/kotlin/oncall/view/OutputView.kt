package oncall.view

import oncall.model.MonthDays
import java.util.Collections

object OutputView {
    fun printResult(month: Int, startDay: String, publicHoliday: List<Int>, result: List<String>) {
        val days = listOf("월", "화", "수", "목", "금", "토", "일")
        Collections.rotate(days, -days.indexOf(startDay))

        for (day in 1..MonthDays.getDaysInMonth(month)!!) {
            if (day in publicHoliday) println("${month}월 ${day}일(휴일) ${days[(day - 1) % 7]} ${result[day - 1]}")
            else println("${month}월 ${day}일 ${days[(day - 1) % 7]} ${result[day - 1]}")
        }
    }
}
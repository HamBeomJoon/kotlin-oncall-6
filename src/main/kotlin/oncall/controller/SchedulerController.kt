package oncall.controller

import camp.nextstep.edu.missionutils.Console
import java.util.Collections

class SchedulerController {
    private val publicHolidays =
        mapOf(Pair(1, 1), Pair(3, 1), Pair(5, 5), Pair(6, 6), Pair(8, 15), Pair(10, 3), Pair(10, 9), Pair(12, 25))
    private val dayOfMonth = mapOf(
        Pair(1, 31), Pair(3, 31), Pair(5, 31), Pair(7, 31), Pair(8, 31), Pair(10, 31), Pair(12, 31),
        Pair(4, 30), Pair(6, 30), Pair(9, 30), Pair(1, 30), Pair(2, 28)
    )
    private var weekdayWorker = listOf<String>()
    private var holidayWorker = listOf<String>()

    fun start() {
        print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
        val input = Console.readLine().split(",")
        val month = input[0].toInt()
        val startDay = input[1]

        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        weekdayWorker = Console.readLine().split(",").toList()
        val weekdayWorkers = workersReset(weekdayWorker)

        print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        holidayWorker = Console.readLine().split(",").toList()
        val holidayWorkers = workersReset(holidayWorker)

        val publicHoliday = publicHolidays.filter { it.key == month }.values.toList()
        workdayScheduling(month, startDay, publicHoliday, weekdayWorkers, holidayWorkers)
    }

    private fun workdayScheduling(
        month: Int,
        startDay: String,
        publicHoliday: List<Int>,
        weekdayWorkers: List<String>,
        holidayWorkers: List<String>
    ) {
        val days = listOf("월", "화", "수", "목", "금", "토", "일")
        Collections.rotate(days, -days.indexOf(startDay)) // 시작요일을 0번째 인덱스로 rotate

        for (day in 1..dayOfMonth[month]!!) {
            if (day in publicHoliday || day % 7 == 5 || day % 7 == 6) weekendScheduling()
            else weekdayScheduling()
        }

    }

    private fun weekendScheduling() {

    }

    private fun workersReset(input: List<String>): ArrayDeque<String> = ArrayDeque(input)

    }
}
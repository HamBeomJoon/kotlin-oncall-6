package oncall.controller

import camp.nextstep.edu.missionutils.Console

class SchedulerController {
    private val publicHolidays =
        mapOf(Pair(1, 1), Pair(3, 1), Pair(5, 5), Pair(6, 6), Pair(8, 15), Pair(10, 3), Pair(10, 9), Pair(12, 25))
    private val dayOfMonth = mapOf(
        Pair(1, 31), Pair(3, 31), Pair(5, 31), Pair(7, 31), Pair(8, 31), Pair(10, 31), Pair(12, 31),
        Pair(4, 30), Pair(6, 30), Pair(9, 30), Pair(1, 30), Pair(2, 28)
    )

    fun start() {
        print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
        val input = Console.readLine().split(",")
        val month = input[0].toInt()
        val startDay = input[1]

        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val weekdayWorkers = Console.readLine().split(",").toList()

        print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val holidayWorkers = Console.readLine().split(",").toList()

        val publicHoliday = publicHolidays.filter { it.key == month }.values.toList()

            if (day in publicHoliday || day % 7 == 5 || day % 7 == 6) weekendScheduling()
            else weekdayScheduling()
        }

    }

    private fun weekendScheduling() {

    }

    private fun weekdayScheduling() {

    }
}
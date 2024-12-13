package oncall.controller

import camp.nextstep.edu.missionutils.Console
import oncall.view.InputView
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
        val (month, startDay) = InputView.getMonthStartDay()

        val workersInput = InputView.getWorkers()
        val weekdayWorkers = workersReset(workersInput.first)
        val holidayWorkers = workersReset(workersInput.second)

        val publicHoliday = publicHolidays.filter { it.key == month }.values.toList()

        val result = workdayScheduling(month, startDay, publicHoliday, weekdayWorkers, holidayWorkers)

        printResult(month, startDay, publicHoliday, result)
    }

    private fun workdayScheduling(
        month: Int,
        startDay: String,
        publicHoliday: List<Int>,
        weekdayWorkerList: ArrayDeque<String>,
        holidayWorkerList: ArrayDeque<String>
    ): MutableList<String> {
        val days = listOf("월", "화", "수", "목", "금", "토", "일")
        Collections.rotate(days, -days.indexOf(startDay)) // 시작요일을 0번째 인덱스로 rotate

        val workers = mutableListOf<String>()
        var weekdayWorkers = weekdayWorkerList
        var holidayWorkers = holidayWorkerList

        var weekdayTemp: String? = null
        var holidayTemp: String? = null
        var previousWorker = ""
        for (day in 1..dayOfMonth[month]!!) {
            if (day in publicHoliday || days[(day - 1) % 7] == "토" || days[(day - 1) % 7] == "일") {
                val (copyWorkers, todayWorker, tempWorker) = weekendScheduling(
                    holidayWorkers,
                    previousWorker,
                    holidayTemp
                )
                holidayWorkers = copyWorkers
                previousWorker = todayWorker
                holidayTemp = tempWorker
            } else {
                val (copyWorkers, todayWorker, tempWorker) = weekdayScheduling(
                    weekdayWorkers,
                    previousWorker,
                    weekdayTemp
                )
                weekdayWorkers = copyWorkers
                previousWorker = todayWorker
                weekdayTemp = tempWorker
            }
            workers.add(previousWorker)
        }
        return workers
    }

    private fun weekendScheduling(
        holidayWorkers: ArrayDeque<String>,
        previousWorker: String,
        holidayTemp: String?
    ): Triple<ArrayDeque<String>, String, String?> {
        var copyWorkers = holidayWorkers
        var todayWorker: String
        var tempWorker: String? = holidayTemp

        if (holidayWorkers.isEmpty()) copyWorkers = workersReset(this.holidayWorker)

        if (tempWorker != null) {
            if (tempWorker == previousWorker) {
                todayWorker = copyWorkers.removeFirst()
            } else {
                todayWorker = tempWorker
                tempWorker = null
            }
        } else {
            todayWorker = copyWorkers.removeFirst()
            if (todayWorker == previousWorker) {
                tempWorker = todayWorker
                todayWorker = copyWorkers.removeFirst()
            }
        }

        return Triple(copyWorkers, todayWorker, tempWorker)
    }

    private fun weekdayScheduling(
        weekdayWorkers: ArrayDeque<String>,
        previousWorker: String,
        weekdayTemp: String?
    ): Triple<ArrayDeque<String>, String, String?> {
        var copyWorkers = weekdayWorkers
        var todayWorker: String
        var tempWorker: String? = weekdayTemp

        if (copyWorkers.isEmpty()) copyWorkers = workersReset(this.weekdayWorker)

        if (tempWorker != null) {
            if (tempWorker == previousWorker) {
                todayWorker = copyWorkers.removeFirst()
            } else {
                todayWorker = tempWorker
                tempWorker = null
            }
        } else {
            todayWorker = copyWorkers.removeFirst()
            if (copyWorkers.isEmpty()) workersReset(this.weekdayWorker)
            if (todayWorker == previousWorker) {
                tempWorker = todayWorker
                todayWorker = copyWorkers.removeFirst()
            }
        }

        return Triple(copyWorkers, todayWorker, tempWorker)
    }

    private fun workersReset(input: List<String>): ArrayDeque<String> = ArrayDeque(input)

    private fun printResult(month: Int, startDay: String, publicHoliday: List<Int>, result: List<String>) {
        val days = listOf("월", "화", "수", "목", "금", "토", "일")
        Collections.rotate(days, -days.indexOf(startDay))

        for (day in 1..dayOfMonth[month]!!) {
            if (day in publicHoliday) println("${month}월 ${day}일(휴일) ${days[(day - 1) % 7]} ${result[day - 1]}")
            else println("${month}월 ${day}일 ${days[(day - 1) % 7]} ${result[day - 1]}")
        }
    }
}
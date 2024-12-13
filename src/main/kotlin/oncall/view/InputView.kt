package oncall.view

import camp.nextstep.edu.missionutils.Console
import oncall.utils.Constants
import oncall.utils.Validator

object InputView {
    fun getMonthStartDay(): Pair<Int, String> {
        while (true) {
            print(Constants.INPUT_MONTH_START_DAY)
            val input = Console.readLine()
            try {
                Validator.validateInput(input)
                val (month, startDay) = input.split(",").map { it.trim() }
                Validator.validateMonth(month)
                Validator.validateStartDay(startDay)
                return Pair(month.toInt(), startDay)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }

    fun getWorkers(): Pair<List<String>, List<String>> {
        while (true) {
            try {
                print(Constants.INPUT_WEEKDAY_WORKERS)
                val weekdayInput = Console.readLine()
                val weekdayWorkers = weekdayInput.split(",")
                Validator.validateWorkers(weekdayWorkers)

                print(Constants.INPUT_HOLIDAY_WORKERS)
                val holidayInput = Console.readLine()
                val holidayWorkers = holidayInput.split(",")
                Validator.validateWorkers(holidayWorkers)

                return Pair(weekdayWorkers, holidayWorkers)
            } catch (e: IllegalArgumentException) {
                println(e.message)
            }
        }
    }
}
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

}
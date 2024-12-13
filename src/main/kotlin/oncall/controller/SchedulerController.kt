package oncall.controller

import camp.nextstep.edu.missionutils.Console

class SchedulerController {
    private val holidayList =
        mapOf(Pair(1, 1), Pair(3, 1), Pair(5, 5), Pair(6, 6), Pair(8, 15), Pair(10, 3), Pair(10, 9), Pair(12, 25))

    fun start() {
        print("비상 근무를 배정할 월과 시작 요일을 입력하세요> ")
        val (month, weekOfDay) = Console.readLine().split(",")

        print("평일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val weekdayList = Console.readLine().split(",").toList()

        print("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요> ")
        val holidayList = Console.readLine().split(",").toList()
    }


        }

    }
}
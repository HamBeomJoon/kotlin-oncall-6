package oncall.controller

import camp.nextstep.edu.missionutils.Console

class SchedulerController {

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
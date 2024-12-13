package oncall

import oncall.controller.SchedulerController

fun main() {
    val controller = SchedulerController()
    controller.start()
}

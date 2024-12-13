package oncall.utils

object Validator {
    fun validateInput(input: String) {
        require(input.split(",").size == 2) { Constants.ERROR_MESSAGE }
    }

    fun validateMonth(input: String) {
        require(input.isNotEmpty() && input.toIntOrNull() != null) { Constants.ERROR_MESSAGE }
        require(input.toInt() in 1..12) { Constants.ERROR_MESSAGE }
    }

    fun validateStartDay(input: String) {
        require(input.isNotEmpty()) { Constants.ERROR_MESSAGE }
        require(input in listOf("월", "화", "수", "목", "금", "토", "일")) { Constants.ERROR_MESSAGE }
    }

    fun validateWorkers(workers: List<String>) {
        require(workers.size in 5..35) {
            Constants.ERROR_MESSAGE
        }
        require(workers.distinct().size == workers.size) {
            Constants.ERROR_MESSAGE
        }
        require(workers.all { it.length in 1..5 }) { Constants.ERROR_MESSAGE }
    }
}
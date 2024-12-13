package oncall.utils

object Validator {
    fun validateInput(input: String) {
        require(input.split(",").size == 2) { Constants.ERROR_MESSAGE }
    }

    fun validateMonth(input: String) {
        require(input.isNotEmpty() && input.toIntOrNull() != null) { Constants.ERROR_MESSAGE }
        require(input.toInt() in 1..12) { Constants.ERROR_MESSAGE }
    }

}
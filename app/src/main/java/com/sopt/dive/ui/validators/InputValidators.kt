package com.sopt.dive.ui.validators

object InputValidators {
    private val ageRegex = Regex("^([1-9][0-9]?|1[01][0-9]|120)$")
    private val emailRegex = Regex(".+@.+")

    fun isValidId(id:String): Boolean = id.length in 6..10
    fun isValidPw(pw:String): Boolean = pw.length in 8..12
    fun isValidNickName(nickname:String): Boolean = nickname.isNotBlank()
    fun isValidAge(input: String) = ageRegex.matches(input)
    fun isValidEmail(input: String) = emailRegex.matches(input)
}

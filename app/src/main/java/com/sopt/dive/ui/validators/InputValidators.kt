package com.sopt.dive.ui.validators

object InputValidators {
    private val birthdayRegex = Regex("^(0[1-9]|1[0-2])(0[1-9]|[12][0-9]|3[01])$")

    fun isValidId(id:String): Boolean = id.length in 6..10
    fun isValidPw(pw:String): Boolean = pw.length in 8..12
    fun isValidNickName(nickname:String): Boolean = nickname.isNotBlank()
    fun isValidBirthday(birthday: String): Boolean =  birthdayRegex.matches(birthday)
}

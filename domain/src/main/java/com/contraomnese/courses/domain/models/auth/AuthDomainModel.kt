package com.contraomnese.courses.domain.models.auth

@JvmInline value class Email(val value: String) {
    fun isValidEmail() : Boolean {
        val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
        return value.matches(emailRegex) || value.isEmpty()
    }
}

@JvmInline value class Password(val value: String)

fun String.toEmail() = Email(this)
fun String.toPassword() = Password(this)
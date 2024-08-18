package com.sandbox.ddd.domain.user.valueobject

data class UserId private constructor(
    val value: String,
) {
    companion object {
        fun of(value: String) = UserId(value)
    }
}

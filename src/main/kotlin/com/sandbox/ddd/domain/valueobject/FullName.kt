package com.sandbox.ddd.domain.valueobject

data class FullName private constructor(
    val firstName: String,
    val lastName: String,
) {
    init {
        require(firstName.isNotEmpty()) {"名は1文字以上である必要があります。"}
        require(lastName.isNotEmpty()) {"姓は1文字以上である必要があります。"}
    }

    companion object {
        fun of(firstName: String, lastName: String) = FullName(firstName, lastName)
    }
}

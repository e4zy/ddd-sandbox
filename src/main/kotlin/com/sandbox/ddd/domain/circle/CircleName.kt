package com.sandbox.ddd.domain.circle

data class CircleName private constructor(val value: String) {
    init {
        require(value.length >= 3) { "サークル名は3文字以上です。" }
        require(value.length < 20) { "サークル名は20文字以下です。" }
    }

    companion object {
        fun of(value: String) = CircleName(value)
    }
}

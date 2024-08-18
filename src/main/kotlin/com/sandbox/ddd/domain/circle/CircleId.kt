package com.sandbox.ddd.domain.circle

data class CircleId private constructor(val value: String) {
    companion object {
        fun of(value: String) = CircleId(value)
    }
}

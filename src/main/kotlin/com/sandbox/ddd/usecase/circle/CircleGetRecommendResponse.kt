package com.sandbox.ddd.usecase.circle

import com.sandbox.ddd.domain.circle.Circle

data class CircleResponse(
    val id: String,
    val name: String,
) {
    companion object {
        fun from(circle: Circle) = CircleResponse(
            id = circle.id.value,
            name = circle.name.value,
        )
    }
}

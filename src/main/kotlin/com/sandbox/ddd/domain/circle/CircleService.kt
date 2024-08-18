package com.sandbox.ddd.domain.circle

import org.springframework.stereotype.Service

@Service
class CircleService(
    private val circleRepository: CircleRepository,
) {
    fun isDuplicated(circle: Circle) : Boolean {
        val duplicatedCircle = circleRepository.find(circle.name)
        return duplicatedCircle != null
    }
}

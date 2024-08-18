package com.sandbox.ddd.domain.circle

import org.springframework.stereotype.Repository

@Repository
interface CircleRepository {
    fun save(circle: Circle)

    fun find(id: CircleId) : Circle?

    fun find(name: CircleName) : Circle?
}

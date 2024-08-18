package com.sandbox.ddd.domain.circle

import com.sandbox.ddd.domain.circle.specification.Specification
import org.springframework.stereotype.Repository
import java.time.LocalDate

@Repository
interface CircleRepository {
    fun save(circle: Circle)

    fun findAll() : List<Circle>

    fun find(id: CircleId) : Circle?

    fun find(name: CircleName) : Circle?

    fun find(specification: Specification<Circle>) : List<Circle>

    fun findRecommended(date: LocalDate) : List<Circle>
}

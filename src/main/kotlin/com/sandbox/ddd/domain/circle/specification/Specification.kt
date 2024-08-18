package com.sandbox.ddd.domain.circle.specification

interface Specification<T> {
    fun isSatisfiedBy(value: T) : Boolean
}

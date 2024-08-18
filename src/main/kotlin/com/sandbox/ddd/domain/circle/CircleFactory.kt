package com.sandbox.ddd.domain.circle

import com.sandbox.ddd.domain.user.entity.User
import org.springframework.stereotype.Component

@Component
interface CircleFactory {
    fun create(name: CircleName, owner: User) : Circle
}

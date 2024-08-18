package com.sandbox.ddd.infrastructure.user

import com.sandbox.ddd.domain.user.entity.User
import com.sandbox.ddd.domain.user.repository.UserRepository
import com.sandbox.ddd.domain.user.valueobject.UserId

class UserRepositoryImpl : UserRepository {
    override fun save(user: User) {
        TODO("Not yet implemented")
    }

    override fun find(userId: UserId): User? {
        TODO("Not yet implemented")
    }

    override fun isDuplicated(userId: UserId): Boolean {
        TODO("Not yet implemented")
    }
}
package com.sandbox.ddd.infrastructure.repositoryImpl

import com.sandbox.ddd.domain.entity.User
import com.sandbox.ddd.domain.repository.UserRepository
import com.sandbox.ddd.domain.valueobject.UserId

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
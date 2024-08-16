package com.sandbox.ddd.infrastructure.repositoryImpl

import com.sandbox.ddd.domain.entity.User
import com.sandbox.ddd.domain.repository.UserRepository

class UserRepositoryImpl : UserRepository {
    override fun save(user: User) {
        TODO("Not yet implemented")
    }

    override fun find(name: String): User? {
        TODO("Not yet implemented")
    }
}
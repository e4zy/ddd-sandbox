package com.sandbox.ddd.domain.service

import com.sandbox.ddd.domain.entity.User
import org.springframework.stereotype.Service

@Service
class UserService {
    fun isDuplicated(user: User) : Boolean {
        TODO("DBアクセスしてユーザ名に重複がないかチェックする")
    }
}
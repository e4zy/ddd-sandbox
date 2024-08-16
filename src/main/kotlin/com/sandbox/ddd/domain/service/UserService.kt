package com.sandbox.ddd.domain.service

import com.sandbox.ddd.domain.entity.User
import org.springframework.stereotype.Service

@Service
class UserService {
    fun isDuplicated(user: User) : Boolean {
        /**
         * TODO ここで呼び出すのは repository だっけ？ mapper だっけ？
         */
        TODO("DBアクセスしてユーザ名に重複がないかチェックする")
    }
}
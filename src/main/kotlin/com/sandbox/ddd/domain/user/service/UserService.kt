package com.sandbox.ddd.domain.user.service

import com.sandbox.ddd.domain.user.entity.User
import com.sandbox.ddd.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    /**
     * 基本的にはドメインオブジェクトをそのまま受け取る
     * ・【メリット】具体的に必要なプロパティや処理を、呼び出し側が意識しなくて済む
     */
    fun isDuplicated(user: User) : Boolean {
        val duplicatedUser = userRepository.find(user.id)
        return duplicatedUser != null
    }
}

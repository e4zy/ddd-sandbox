package com.sandbox.ddd.usecase.user

import com.sandbox.ddd.domain.user.entity.User
import com.sandbox.ddd.domain.user.repository.UserRepository
import com.sandbox.ddd.domain.user.service.UserService

class UserRegisterUseCase(
    private val userRepository: UserRepository,
    private val userService: UserService,
) {
    /**
     * ユーザ情報を登録する
     *
     * @param name ユーザ名
     */
    fun register(name: String) {
        val user = User.of(name)

        // userをそのままサービスに渡すので、呼び出し側は中の処理を意識しなくて済む
        if (userService.isDuplicated(user)) {
            throw IllegalArgumentException("ユーザがすでに存在しています。")
        }

        userRepository.save(user)
    }
}
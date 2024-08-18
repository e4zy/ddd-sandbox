package com.sandbox.ddd.usecase.user

import com.sandbox.ddd.domain.user.repository.UserRepository
import com.sandbox.ddd.domain.user.valueobject.UserId

class UserGetUseCase(
    private val userRepository: UserRepository,

    /** クラス分割し凝集度が高まったことで、Service の記述が不要になった */
//    private val userService: UserService,
) {
    /**
     * ユーザ情報を取得する
     *
     * @param userId ユーザID
     * @return [UserResponse]
     */
    fun getUser(userId: UserId) : UserResponse? {
        // user が null の場合は早期リターン
        val user = userRepository.find(userId) ?: return null
        return UserResponse.from(user)
    }
}

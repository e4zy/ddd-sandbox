package com.sandbox.ddd.usecase.user

import com.sandbox.ddd.domain.user.entity.User
import com.sandbox.ddd.domain.user.repository.UserRepository
import com.sandbox.ddd.domain.user.service.UserService
import com.sandbox.ddd.domain.user.valueobject.UserId

/**
 * ユーザ操作を行うユースケース
 * 今の状態だと userService が register でしか利用されておらず、凝集度が低い
 *  -> [UserRegisterUseCase] と [UserGetUseCase] にクラスを分割するパターンもある
 *  -> 1クラス1ユースケース
 */
class UserUseCase(
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

    /**
     * ユーザ情報を取得する（NGパターン）
     * ・戻り値としてドメインオブジェクトをそのまま返却している
     * ・【メリット】コードが比較的シンプルになる
     * ・【デメリット】呼び出し側から意図せずドメイン情報を操作される可能性がある（User.changeName, 等）
     *
     * @param userId ユーザID
     * @return [User] ドメインオブジェクト
     */
    fun getUserNG(userId: UserId) : User? {
        // TODO 基本的にドメイン情報をユースケース層より外に公開すべきではない
        return userRepository.find(userId)
    }

    /**
     * ユーザ情報を取得する
     * ・戻り値としてレスポンス返却用の data class を別途定義する
     * ・【メリット】ドメイン情報を外部に公開せずに済む
     * ・【デメリット】上記に比べてコードが少し煩雑になる, 処理が増えるためパフォーマンスは劣る
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

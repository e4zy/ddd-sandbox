package com.sandbox.ddd.usecase.user

import com.sandbox.ddd.domain.user.entity.User

/**
 * レスポンスクラス
 * ・private constructor にしても copy メソッドがあるから意味ないよって警告が出てる
 * ・interface を経由することで回避できるらしい
 * 　https://qiita.com/eno314/items/3c3e6c46e3502890b199
 */
data class UserResponse private constructor(
    val id: String,
    val name: String,
) {
    companion object {
        /**
         * ドメインオブジェクト -> レスポンスクラスへの変換メソッド
         * 基本的にドメインオブジェクトをまるっと渡す
         * 【メリット】
         * ・返却するレスポンスが増減したとき、修正箇所をレスポンスクラスに閉じることができる
         */
        fun from(user: User) = UserResponse(
            id = user.id.value,
            name = user.name,
        )
    }
}

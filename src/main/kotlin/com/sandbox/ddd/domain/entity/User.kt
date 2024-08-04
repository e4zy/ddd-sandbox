package com.sandbox.ddd.domain.entity

import com.sandbox.ddd.domain.valueobject.UserId

data class User private constructor(
    /** エンティティを区別するための識別子 */
    val id: UserId,

    // TODO エンティティだけど値を可変にしてもいいのか、、？
    var name: String,
) {
    init {
        require(name.length >= 3) { "ユーザ名は3文字以上です。" }
    }

    companion object {
        fun of(id: UserId, name: String) = User(id = id, name = name)
    }

    fun changeName(name: String) {
        this.name = name
    }

    /**
     * ユーザ名の重複を確認するメソッド
     * → 他のユーザの名前を知っていることになってしまうので違和感がある
     * → ドメインサービスを利用する
     */
    fun isDuplicated() {
        TODO()
    }
}

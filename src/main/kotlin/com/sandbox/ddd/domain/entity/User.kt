package com.sandbox.ddd.domain.entity

import com.sandbox.ddd.domain.valueobject.UserId
import java.util.UUID

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
        /**
         * 新規作成用ファクトリーメソッド
         *
         * ・この例ではUUIDを利用しているが、シーケンスを利用したい場合もあり得る
         * 　そのような複雑な生成処理の場合は Factoryメソッドを利用する
         */
        fun of(name: String) =
            User(
                id = UserId.of(UUID.randomUUID().toString()),
                name = name
            )

        /**
         * データストアからの再構築用ファクトリーメソッド
         */
        fun of(id: UserId, name: String) =
            User(
                id = id,
                name = name
            )

        /**
         * UserFactory にもファクトリーメソッドが存在するが、Userクラスを見るだけでは気付けない
         * この場合同じパッケージに含めるなど、気付かせる工夫が必要
         */
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

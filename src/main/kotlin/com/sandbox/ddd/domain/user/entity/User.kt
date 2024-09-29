package com.sandbox.ddd.domain.user.entity

import com.sandbox.ddd.domain.user.valueobject.UserId
import com.sandbox.ddd.domain.user.valueobject.UserRank
import java.util.UUID

/**
 * ドメインモデル貧血症を改善したコード例
 * 新規作成時の状態やユーザ名の文字数制限など、ユーザに関する仕様が記述されるようになった
 */
data class User private constructor(
    /** エンティティを区別するための識別子 */
    val id: UserId,

    // TODO エンティティだけど値を可変にしてもいいのか、、？直接プロパティを変更できるからダメそう
    var name: String,

    val rank: UserRank,
) {
    init {
        require(name.length >= 3) { "ユーザ名は3文字以上です。" }
    }

    /**
     * プレミアムユーザかどうか（仕様パターン説明用）
     */
    fun isPremium() = this.rank == UserRank.PREMIUM

    companion object {
        /**
         * 新規作成用ファクトリーメソッド
         * 必ずファクトリーメソッドを経由することで、常に正しいインスタンスしか存在させない
         *
         * ・この例ではUUIDを利用しているが、シーケンスを利用したい場合もあり得る
         * 　そのような複雑な生成処理の場合は Factoryメソッドを利用する
         */
        fun of(name: String) =
            User(
                id = UserId.of(UUID.randomUUID().toString()),
                name = name,
                rank = UserRank.REGULAR,    // 新規作成時は必ず一般ランク
            )

        /**
         * データストアからの再構築用ファクトリーメソッド
         *
         * ・DBから取得した値をそのまま受け取り、バリデーションはしない
         * ・Javaであれば package-private にすることで他のパッケージから呼ばれないようにできるが、Kotlinにはない
         * 　-> メソッド名で区別するのがベター（fromRepository, reconstruct）
         *
         * ・TODO 再構築するだけでドメイン知識はないから、インフラ層の責務ではないか？ -> Kotlinならそれでもよさげ
         */
        fun fromRepository(id: UserId, name: String, rank: UserRank) =
            User(
                id = id,
                name = name,
                rank = rank,
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
     * ユーザ名の重複を確認する
     */
    @Deprecated(
        "他のユーザの名前をユーザオブジェクトが知っているのは違和感がある -> ドメインサービスを利用する",
        ReplaceWith("UserService.isDuplicated()")
    )
    fun isDuplicated() {
        TODO()
    }
}

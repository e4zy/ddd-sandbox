package com.sandbox.ddd.domain.circle

import com.sandbox.ddd.domain.user.entity.User
import com.sandbox.ddd.domain.user.valueobject.UserId
import java.time.LocalDate

data class Circle private constructor(
    val id: CircleId,
    val name: CircleName,

    /**
     * 以下の実装だと、CircleがUserのインスタンスを保持している
     * -> サークル集約からユーザー集約のインスタンスを操作できてしまう
     * -> 同じ集約ならインスタンス参照でOKだが、別集約なら避けるべき
     */
//    val owner: User,
//    val member: MutableList<User>,

    /**
     * サークル集約はユーザーの識別子のみを保持するようにすると、上記を避けられる
     * -> 別集約の場合は識別子参照
     */
    val ownerId: UserId,
    val memberIds: MutableList<UserId>,
) {
    /**
     * サークルの人数を返却する（オーナーを含めるため+1する）
     */
    fun memberCount() : Int = memberIds.size + 1

    /**
     * サークルの人数が上限の30かどうかを返却する
     * TODO 意図的にpublicにしている
     */
    fun isFull() : Boolean = this.memberCount() > 30

    /**
     * サークルが作成された日付（仕様パターン説明用）
     * 簡単のため固定値を返す
     */
    fun createdAt() : LocalDate = LocalDate.of(2024, 1, 1)

    /**
     * メンバーを追加する
     * ・メンバー追加処理は集約ルートであるCircleに実装する
     */
    fun join(userId: UserId) {
        if (this.isFull()) {
            throw IllegalArgumentException("サークルの人数が上限に達しています。")
        }
        this.memberIds.add(userId)
    }

    companion object {
        fun of(
            id: CircleId,
            name: CircleName,
            ownerId: UserId,
            memberIds: MutableList<UserId>,
        ) = Circle(
            id = id,
            name = name,
            ownerId = ownerId,
            memberIds = memberIds,
        )
    }
}

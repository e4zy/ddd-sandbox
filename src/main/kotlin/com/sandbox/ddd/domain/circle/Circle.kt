package com.sandbox.ddd.domain.circle

import com.sandbox.ddd.domain.user.entity.User
import com.sandbox.ddd.domain.user.valueobject.UserId

data class Circle private constructor(
    val id: CircleId,
    val name: CircleName,

    /**
     * 現状の実装だと、CircleがUserのインスタンスを保持している
     * -> サークル集約からユーザー集約のインスタンスを操作できてしまう
     */
    val owner: User,
    val member: MutableList<User>,

    /**
     * サークル集約はユーザーの識別子のみを保持するようにすると、上記を避けられる
     */
//    val ownerId: UserId,
//    val memberIds: List<UserId>,
    ) {
        /**
         * サークルの人数を返却する（オーナーを含めるため+1する）
         */
        private fun size() : Int = member.size + 1

        /**
         * サークルの人数が上限の30かどうかを返却する
         * TODO 意図的にpublicにしている
         */
        fun isFull() : Boolean = this.size() > 30

        /**
         * メンバーを追加する
         * ・メンバー追加処理は集約ルートであるCircleに実装する
         */
        fun join(user: User) {
            if (this.isFull()) {
                throw IllegalArgumentException("サークルの人数が上限に達しています。")
            }
            this.member.add(user)
        }

        companion object {
            fun of(
                id: CircleId,
                name: CircleName,
                owner: User,
                member: MutableList<User>,
            ) = Circle(
                id = id,
                name = name,
                owner = owner,
                member = member,
            )
        }
}

package com.sandbox.ddd.domain.circle

import com.sandbox.ddd.domain.user.entity.User

/**
 * ファーストクラスコレクション
 */
data class CircleMembers private constructor(
    val id: CircleId,
    val owner: User,
    val members: List<User>,
) {
    /**
     * サークルの人数を返却する（オーナーを含めるため+1する）
     */
    fun size() : Int = members.size + 1

    /**
     * プレミアムユーザ数を返却する
     */
    fun countPremiumMembers() : Int {
        return members.filter { it.isPremium() }.size
    }

    companion object {
        fun of(
            id: CircleId,
            owner: User,
            members: List<User>,
        ) = CircleMembers(
            id = id,
            owner = owner,
            members = members,
        )
    }
}

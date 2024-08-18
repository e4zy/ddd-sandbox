package com.sandbox.ddd.domain.circle.specification

import com.sandbox.ddd.domain.circle.Circle
import com.sandbox.ddd.domain.circle.CircleMembers
import com.sandbox.ddd.domain.user.repository.UserRepository
import org.springframework.stereotype.Component

/**
 * プレミアムユーザ数を基にサークル上限を判定する処理を仕様として切り出す
 * （やってることとしては、結局ただ切り出してドメイン層に置くだけ感）
 */
@Component
class CircleFullSpecification(
    private val userRepository: UserRepository,
) {
    /**
     * 仕様クラス = ドメインオブジェクトなので、内部でリポジトリを呼ぶことを避ける場合もある
     * -> その場合は後述のファーストクラスコレクションを利用するパターンを使う
     */
    fun isSatisfiedBy(circle: Circle) : Boolean {
        val users = userRepository.findByIds(circle.memberIds)
        val premiumUserCount = users.filter { it.isPremium() }.size
        val circleUpperLimit = if (premiumUserCount > 10) 30 else 50
        return circle.size() > circleUpperLimit
    }

    /**
     * ファーストクラスコレクションを利用することで、仕様クラスでリポジトリを呼ばないパターン
     */
    fun isSatisfiedByWithFirstClassCollection(circleMembers: CircleMembers) : Boolean {
        val premiumUserCount = circleMembers.countPremiumMembers()
        val circleUpperLimit = if (premiumUserCount > 10) 30 else 50
        return circleMembers.size() > circleUpperLimit
    }
}

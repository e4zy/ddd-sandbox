package com.sandbox.ddd.usecase.circle

import com.sandbox.ddd.domain.circle.*
import com.sandbox.ddd.domain.circle.specification.CircleFullSpecification
import com.sandbox.ddd.domain.user.repository.UserRepository
import com.sandbox.ddd.domain.user.valueobject.UserId

class CircleJoinUseCase(
    private val circleRepository: CircleRepository,
    private val userRepository: UserRepository,
    private val circleFullSpecification: CircleFullSpecification,
) {
    fun join(userId: UserId, circleId: CircleId) {
        val user = userRepository.find(userId) ?: throw IllegalArgumentException("ユーザが見つかりませんでした。")

        val circle = circleRepository.find(circleId) ?: throw IllegalArgumentException("サークルが見つかりませんでした。")

        // サークルのオーナー含めて30名か確認
        // TODO 【NG】サークル人数が30人までという制約がユースケースに漏れ出ている（かつオーナー除いて29名という暗黙の知識も含まれる）
        // ユースケースがCircleのプロパティのメソッドを呼び出している = デメテルの法則に反している
        if (circle.memberIds.size >= 29) {
            throw IllegalArgumentException("サークルの人数が上限に達しています。")
        }

        // サークルのオーナー含めて30名か確認
        // TODO 【△】ユースケースからは人数チェックメソッドを呼び出すだけでよくなった。が、人数上限があるという知識は漏れ出たまま
        if (circle.isFull()) {
            throw IllegalArgumentException("サークルの人数が上限に達しています。")
        }

        // TODO 【OK】ユースケースからはCircleにメンバー追加を依頼するだけでよくなった（上限チェックはCircle内で完結している）
        // TODO 新しいメンバーを増やす場合、memberを直接追加するのか、もしくはCircleを新しく作り直すのかどっちだろ
        circle.join(user.id)

        circleRepository.save(circle)
    }

    /**
     * 以下の仕様が追加された場合を考える
     * ・ユーザにはプレミアムユーザが存在する
     * ・プレミアムユーザが10名以上所属するサークルの場合、人数上限が50名になる
     */
    fun joinForSpecificationNG(userId: UserId, circleId: CircleId) {
        val circle = circleRepository.find(circleId) ?: throw IllegalArgumentException("サークルが見つかりませんでした。")

        // CircleはUserIdしか持たないため、プレミアムユーザ判定のため一度Userインスタンスを取得する
        val users = userRepository.findByIds(circle.memberIds)
        val premiumUserCount = users.filter { it.isPremium() }.size

        // プレミアムユーザ数を基にサークル上限を取得する
        val circleUpperLimit = if (premiumUserCount > 10) 30 else 50

        /**
         * 結局ドメイン知識がユースケースに漏れ出す形になってしまう
         * circle.isFull()のようにCircle側にメソッドを実装する形も取れるが、Userインスタンスを外から渡してあげないとチェックできない
         * ex. circle.isFull(users)
         *
         * -> これを解決するのか仕様パターン
         */
        if(circle.memberCount() > circleUpperLimit) {
            throw IllegalArgumentException("サークルの人数が上限に達しています。")
        }
    }

    /**
     * 以下の仕様が追加された場合を考える
     * ・ユーザにはプレミアムユーザが存在する
     * ・プレミアムユーザが10名以上所属するサークルの場合、人数上限が50名になる
     *
     * 仕様パターンを使って書き直したパターン
     */
    fun joinForSpecificationOK(userId: UserId, circleId: CircleId) {
        val circle = circleRepository.find(circleId) ?: throw IllegalArgumentException("サークルが見つかりませんでした。")

        if(circleFullSpecification.isSatisfiedBy(circle)) {
            throw IllegalArgumentException("サークルの人数が上限に達しています。")
        }

        /**
         * ファーストクラスコレクションを利用する場合、詰替え処理をユースケースで行う必要がある
         * ・これはこれでクラスと処理が増えるので面倒くさい感ある...
         */
        val owner = userRepository.find(circle.ownerId) ?: throw IllegalArgumentException("ユーザが見つかりませんでした。")
        val users = userRepository.findByIds(circle.memberIds)
        val circleMembers = CircleMembers.of(
            id = circle.id,
            owner = owner,
            members = users,
        )
        if(circleFullSpecification.isSatisfiedByWithFirstClassCollection(circleMembers)) {
            throw IllegalArgumentException("サークルの人数が上限に達しています。")
        }
    }
}
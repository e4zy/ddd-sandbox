package com.sandbox.ddd.usecase.circle

import com.sandbox.ddd.domain.circle.CircleId
import com.sandbox.ddd.domain.circle.CircleRepository
import com.sandbox.ddd.domain.user.repository.UserRepository
import com.sandbox.ddd.domain.user.valueobject.UserId

class CircleJoinUseCase(
    private val circleRepository: CircleRepository,
    private val userRepository: UserRepository,
) {
    fun join(userId: UserId, circleId: CircleId) {
        val user = userRepository.find(userId) ?: throw IllegalArgumentException("ユーザが見つかりませんでした。")

        val circle = circleRepository.find(circleId) ?: throw IllegalArgumentException("サークルが見つかりませんでした。")

        // サークルのオーナー含めて30名か確認
        // TODO 【NG】サークル人数が30人までという制約がユースケースに漏れ出ている（かつオーナー除いて29名という暗黙の知識も含まれる）
        // ユースケースがCircleのプロパティのメソッドを呼び出している = デメテルの法則に反している
        if (circle.member.size >= 29) {
            throw IllegalArgumentException("サークルの人数が上限に達しています。")
        }

        // サークルのオーナー含めて30名か確認
        // TODO 【△】ユースケースからは人数チェックメソッドを呼び出すだけでよくなった。が、人数上限があるという知識は漏れ出たまま
        if (circle.isFull()) {
            throw IllegalArgumentException("サークルの人数が上限に達しています。")
        }

        // TODO 【OK】ユースケースからはCircleにメンバー追加を依頼するだけでよくなった（上限チェックはCircle内で完結している）
        // TODO 新しいメンバーを増やす場合、memberを直接追加するのか、もしくはCircleを新しく作り直すのかどっちだろ
        circle.join(user)

        circleRepository.save(circle)
    }
}
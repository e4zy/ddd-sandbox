package com.sandbox.ddd.domain.circle.specification

import com.sandbox.ddd.domain.circle.Circle
import org.springframework.stereotype.Component
import java.time.LocalDate

/**
 * おすすめサークルを判定する処理を仕様として切り出す
 */
@Component
class CircleRecommendSpecification(
    private val executeDate: LocalDate = LocalDate.now(),
) : Specification<Circle> {
    override fun isSatisfiedBy(value: Circle) : Boolean {
        // 所属メンバー数が10名以上かどうか
        if (value.memberCount() < 10) return false

        // 直近1ヶ月以内に作成されたかどうか
        return value.createdAt() > executeDate.plusMonths(-1)
    }
}

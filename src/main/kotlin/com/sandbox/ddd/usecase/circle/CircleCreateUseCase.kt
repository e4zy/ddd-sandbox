package com.sandbox.ddd.usecase.circle

import com.sandbox.ddd.domain.circle.CircleFactory
import com.sandbox.ddd.domain.circle.CircleName
import com.sandbox.ddd.domain.circle.CircleRepository
import com.sandbox.ddd.domain.circle.CircleService
import com.sandbox.ddd.domain.user.repository.UserRepository
import com.sandbox.ddd.domain.user.valueobject.UserId

class CircleCreateUseCase(
    private val circleRepository: CircleRepository,
    private val circleService: CircleService,
    private val circleFactory: CircleFactory,
    private val userRepository: UserRepository,
) {
    fun create(ownerId: UserId, circleName: CircleName) {
        val owner = userRepository.find(ownerId) ?: throw IllegalArgumentException("ユーザが見つかりませんでした。")

        val circle = circleFactory.create(circleName, owner)

        if (circleService.isDuplicated(circle)) {
            throw IllegalArgumentException("サークルはすでに存在しています。")
        }

        circleRepository.save(circle)
    }
}
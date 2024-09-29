package com.sandbox.ddd.domain.user.entity

import com.sandbox.ddd.domain.user.valueobject.UserId
import com.sandbox.ddd.domain.user.valueobject.UserRank

/**
 * ドメインモデル貧血症（Anemic Domain Model）のコード例
 * プロパティの定義のみで、ドメインのルールや制約が何も書かれていない
 *
 * 【デメリット】
 * ・不整合なデータをいくらでも作れてしまう
 * ・仕様を把握するために複数のクラスやコードを追う必要がある
 */
data class AnemicUser(
    val id: UserId,
    val name: String,
    val rank: UserRank,
)

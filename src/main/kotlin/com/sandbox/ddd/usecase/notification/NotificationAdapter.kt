package com.sandbox.ddd.usecase.notification

/**
 * ドメインと無関係な外部APIを呼び出す場合、ユースケース層にインタフェースを定義する
 * 実装クラスはインフラ層に配置する
 * ⇔ 外部APIから取得する値がドメイン知識である場合は、リポジトリとして設計する
 */
interface NotificationAdapter {
    fun notification()
}

package com.sandbox.ddd.domain.factory

import com.sandbox.ddd.domain.entity.User

interface UserFactory {
    /**
     * シーケンスを利用してIDを採番したユーザ情報を作成する
     *
     * TODO この実装クラスってどのレイヤーに書くの？
     * TODO シーケンスの取得処理って Repository ？ドメインオブジェクトの永続化ではないから違う？
     */
    fun createBySequence(name: String) : User
}

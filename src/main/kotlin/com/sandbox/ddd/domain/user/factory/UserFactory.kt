package com.sandbox.ddd.domain.user.factory

import com.sandbox.ddd.domain.user.entity.User
import org.springframework.stereotype.Service

@Service
interface UserFactory {
    /**
     * シーケンスを利用してIDを採番したユーザ情報を作成する
     *
     * TODO ボトムアップ本ではインタフェースで定義してたけど、この実装クラスってどのレイヤーに書くの？
     * TODO シーケンスの取得処理って Repository ？ドメインオブジェクトの永続化ではないから違う？
     */
    fun createBySequence(name: String) : User
}


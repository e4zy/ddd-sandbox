package com.sandbox.ddd.domain.user.repository

import com.sandbox.ddd.domain.user.entity.User
import com.sandbox.ddd.domain.user.valueobject.UserId
import org.springframework.stereotype.Repository

/**
 * TODO なぜリポジトリのインタフェースをドメイン層に置くのか？
 *
 * ・ドメイン層に置くことで集約の範囲を明示するため
 */
@Repository
interface UserRepository {
    /**
     * ユーザ情報を保存する（データの永続化処理を提供）
     * ・引数には永続化するオブジェクト自体を指定する
     */
    fun save(user: User)

    /**
     * ユーザ情報を検索する（データの復元処理を提供）
     */
    fun find(userId: UserId): User?

    /**
     * 複数IDを基にユーザ情報を検索する
     */
    fun findByIds(userIds: List<UserId>): List<User>

    /**
     * 複数IDを基にユーザ情報を検索する（ソートあり）
     *
     * ・ソート順は引数で指定できるようにすることで、責務に矛盾なく実装できる
     */
    fun findByIds(userIds: List<UserId>, sortKey: String): List<User>

    /**
     * ユーザ名の重複をチェックする（NGパターン）
     * ・DBアクセスなので repository に実装したくなるが、repository の責務としてNG
     * ・あくまで repository はデータの永続化＆復元のみ
     *
     * → と思ったけど、結局 domain service から repository を呼び出すんじゃないの？
     */
    // fun isDuplicated(user: User) : Boolean

    /**
     * 重複をチェックするために必要なキーを引数に取っておく場合は、 repository に実装してもOKらしい
     * ・domain service から呼び出されることになる？
     */
    fun isDuplicated(userId: UserId) : Boolean

    /**
     * シーケンスを利用してユーザIDを採番する
     *
     * ・ファクトリーを用意するのとは別の選択肢としてとりうる
     * ・ただしリポジトリはあくまでデータの永続化と再構築が責務であるため、ボトムアップ本では推奨していない
     */
    fun nextIdentify() : UserId
}
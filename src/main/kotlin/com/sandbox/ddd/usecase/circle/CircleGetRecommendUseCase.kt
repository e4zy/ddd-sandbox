package com.sandbox.ddd.usecase.circle

import com.sandbox.ddd.domain.circle.specification.CircleRecommendSpecification
import com.sandbox.ddd.domain.circle.CircleRepository
import java.time.LocalDate

/**
 * おすすめサークル検索機能を考える
 * ・直近1ヶ月以内に作成されたサークルである
 * ・所属メンバー数が10名以上である
 */
class CircleGetRecommendUseCase(
    private val circleRepository: CircleRepository,
    private val circleRecommendSpecification: CircleRecommendSpecification,
) {
    /**
     * 普通に実装したパターン
     * -> おすすめサークルの条件がリポジトリに記載されることになる
     * -> 条件を仕様として切り出してみる
     */
    fun getRecommended() : List<CircleResponse> {
        val circles = circleRepository.findRecommended(LocalDate.now())
        return circles.map { CircleResponse.from(it) }
    }

    /**
     * 仕様を切り出し、ユースケースでフィルタリングするパターン
     * ・【メリット】リポジトリから仕様を切り離せる
     * ・【デメリット】データ取得後に判定する都合上、データ量によってはパフォーマンス懸念が発生する
     */
    fun getRecommendedWithSpecification() : List<CircleResponse> {
        // サークルを全件取得する
        val circles = circleRepository.findAll()

        // 仕様メソッドを呼び出しておすすめサークルをフィルタリングする
        val recommendedCircles = circles.filter { circleRecommendSpecification.isSatisfiedBy(it) }
        return recommendedCircles.map { CircleResponse.from(it) }
    }

    /**
     * 仕様を切り出し、リポジトリ側でフィルタリングするパターン
     * ・【メリット】仕様クラスのインタフェースを引数に取るので、仕様が増えたとしても同じ呼び出しかたで対応できる
     * ・【デメリット】データ取得後に判定する都合上、データ量によってはパフォーマンス懸念が発生する
     */
    fun getRecommendedWithISpecification() : List<CircleResponse> {
        // リポジトリに仕様を引き渡し、結果を受け取る
        val recommendedCircles = circleRepository.find(circleRecommendSpecification)

        // 仕様が増えても同じ呼び出し処理
        // val hogeCircles = circleRepository.find(circleHogeSpecification)

        return recommendedCircles.map { CircleResponse.from(it) }
    }
}
package com.sandbox.ddd.domain.valueobject

data class Money private constructor(
    val amount: Int,
    val currency: Currency,
) {
    companion object {
        fun of(amount: Int, currency: Currency) = Money(amount = amount, currency = currency)
    }

    // 独自のふるまいを定義できる（自身に関する制約をオブジェクト内で表現する）
    fun add(arg: Money) : Money {
        // TODO Validationの記述はここでいいんだろうか
        require(this.currency == arg.currency) { "通貨単位が異なります。" }

        return Money(
            amount = this.amount + arg.amount,
            currency = this.currency,
        )
    }
}



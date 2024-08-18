package com.sandbox.ddd.domain.valueobject

import com.sandbox.ddd.domain.money.Currency
import com.sandbox.ddd.domain.money.Money
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class MoneyTest {
    @Test
    fun `of - 正常系`() {
        val amount = 100
        val currency = Currency.JPY
        val actual = Money.of(amount = amount, currency = currency)

        assertSoftly {
            actual.amount shouldBe amount
            actual.currency shouldBe currency
        }
    }

    @Test
    fun `add - 正常系`() {
        val money1 = Money.of(amount = 100, currency = Currency.JPY)
        val money2 = Money.of(amount = 200, currency = Currency.JPY)
        val actual = money1.add(money2)

        assertSoftly {
            actual.amount shouldBe 300
            actual.currency shouldBe Currency.JPY
        }
    }
    @Test
    fun `add - 正常系 -  通貨単位が異なる場合はエラー`() {
        val money1 = Money.of(amount = 100, currency = Currency.JPY)
        val money2 = Money.of(amount = 200, currency = Currency.USD)

        val exception = shouldThrow<IllegalArgumentException> {
            money1.add(money2)
        }
        exception.message shouldBe "通貨単位が異なります。"
    }
}

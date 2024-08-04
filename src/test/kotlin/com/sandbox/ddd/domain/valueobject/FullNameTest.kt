package com.sandbox.ddd.domain.valueobject

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class FullNameTest {
    @Test
    fun `of - 正常系`() {
        val firstName = "Jon"
        val lastName = "Snow"
        val actual = FullName.of(firstName = firstName, lastName = lastName)

        assertSoftly {
            actual.firstName shouldBe firstName
            actual.lastName shouldBe lastName
        }
    }

    @Test
    fun `of - 正常系 - 名が空の場合はエラー`() {
        val firstName = ""
        val lastName = "Snow"

        val exception = shouldThrow<IllegalArgumentException> {
            FullName.of(firstName = firstName, lastName = lastName)
        }
        exception.message shouldBe "名は1文字以上である必要があります。"
    }

    @Test
    fun `of - 正常系 - 姓が空の場合はエラー`() {
        val firstName = "Jon"
        val lastName = ""

        val exception = shouldThrow<IllegalArgumentException> {
            FullName.of(firstName = firstName, lastName = lastName)
        }
        exception.message shouldBe "姓は1文字以上である必要があります。"
    }
}

package com.example.testmockk

import android.widget.Button
import com.google.common.truth.Truth.assertThat
import io.mockk.*
import io.mockk.junit4.MockKRule
import org.junit.Rule
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test;

@DisplayName("Testing using JUnit 5")
class CalculatorTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    val calculator = spyk<Calculator>()


    @Test
    fun test() {
        every { calculator.calculate(1, 2) } returns 3
        val calculate = calculator.calculate(1, 2)
        assertThat(calculate).isEqualTo(3)
    }

    @Test
    fun testConvert() {
        every { calculator.convertToWord(any()) } returns "XXX"
        val convertToWord = calculator.convertToWord(52)
        assertThat(convertToWord).isEqualTo("XXX")
    }

    @Test
    fun `add two number and return its words`() {
        every { calculator.calculate(any(), any()) } returns 2
        every { calculator.convertToWord(any()) } returns "two"

        val sumWordy = calculator.sumWordy(1, 2)

        verifyOrder {
            calculator.calculate(any(), any())
            calculator.convertToWord(any())
        }

        verify(exactly = 1){ calculator.calculate(any(), any())}
        assertThat(sumWordy).isEqualTo("two")
    }

    @Test
    fun `convert word to number`() {
        every { calculator.convertToNumber(any()) } answers { callOriginal() }
        val convertToNumber = calculator.convertToNumber("2")
        assertThat(convertToNumber).isEqualTo(2)
    }

}
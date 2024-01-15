package com.niloda.arrow.test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class FailOnErrorsTests {

    @Test
    fun `When raising errors in failOnErrors block, throw AssertionError`() {
        assertThrows<AssertionError> {
            failOnErrors {
                raiseFirstThenSecond()
            }
        }
    }

    @Test
    fun `When returning without raising any errors in failOnErrorsBlock, return value`() {
        failOnErrors {
            assertEquals(returnWithoutErrorsValue, returnWithoutErrors())
        }
    }
}
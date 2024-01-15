package com.niloda.arrow.test

import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class ExpectErrorsTests {

    @Test
    fun `When raising expected errors, succeed`() {
        expectErrors("first", "second") {
            raiseFirstThenSecond()
        }
    }

    @Test
    fun `When raising expected errors out of order, succeed`() {
        expectErrors("second", "first") {
            raiseFirstThenSecond()
        }
    }

    @Test
    fun `When no errors are raised, throw AssertionError`() {
        assertThrows<AssertionError> {
            expectErrors("first", "second") {
                returnWithoutErrors()
            }
        }
    }

    @Test
    fun `When different errors are raised, throw AssertionError`() {
        assertThrows<AssertionError> {
            expectErrors("OTHER", "ANOTHER") {
                raiseFirstThenSecond()
            }
        }
    }

}

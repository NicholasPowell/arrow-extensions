package com.niloda.arrow.test

import arrow.core.raise.ensure
import arrow.core.raise.recover
import arrow.core.raise.zipOrAccumulate
import com.niloda.arrow.test.failOnError
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.DefaultAsserter.fail

class FailOnErrorTests {

    @Test
    fun `When raising an error in failOnError block, fail`() {
        assertThrows<AssertionError> {
            failOnError {
                raise("error")
            }
        }
    }

    @Test
    fun `When returning without a raised error in failOnError block, succeed`() {
        failOnError{
            ensure(true) {"Error will not be raised"}
        }
    }

    @Test
    fun `When recovering from a raised error in failOnError block, succeed`() {
        failOnError<String, String> {
            raiseAndRecover()
        }
    }



    private fun raiseAndRecover() = recover(
        block = { raise{ "Raised Error" } },
        recover = { _ -> "Whew, recovered" }
    )

}
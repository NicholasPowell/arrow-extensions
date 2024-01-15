package com.niloda.arrow.test

import arrow.core.Nel
import arrow.core.raise.Raise
import arrow.core.raise.eagerEffect
import arrow.core.raise.getOrElse
import org.junit.jupiter.api.Assertions.assertEquals
import kotlin.test.fail

/**
 * Assert supplied Errors are raised from the supplied block
 */
fun <Error, A> expectErrors(
    vararg expected: Error,
    wrongErrorMessage: (Nel<Error>) -> String = {
        """expectedErrorsDoNotMatchRaised
             expected: ${expected.joinToString()}
             raised: $it
        """.trimIndent()
    },
    noErrorMessage: () -> String = {
        "No errors raised, expected ${expected.joinToString(",", "[", "]")}"
    },
    block: Raise<Nel<Error>>.() -> A
) {
    eagerEffect {
        block()
        fail(noErrorMessage())
    } getOrElse { nonEmptyErrors ->
        assertEquals(expected.toSet(), nonEmptyErrors.toList().toSet()) {
            fail(wrongErrorMessage(nonEmptyErrors))
        }
    }
}



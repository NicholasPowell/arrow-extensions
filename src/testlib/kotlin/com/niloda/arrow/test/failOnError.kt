package com.niloda.arrow.test

import arrow.core.Nel
import arrow.core.raise.Raise
import arrow.core.raise.eagerEffect
import arrow.core.raise.getOrElse
import kotlin.test.fail

/**
 * Provide a Raise context that fails on error with the supplied message
 */
fun <Error, A> failOnError(
    supplyFailMessage: (Error) -> String = { "Fail: $it" },
    block: Raise<Error>.() -> A
): A = eagerEffect { block() }.getOrElse { fail(supplyFailMessage(it)) }

/**
 * Provide a Raise context that fails on collected Nel errors with the supplied message
 */
fun <Error, A> failOnErrors(
    supplyFailMessage: (Nel<Error>) -> String = { "Fail: ${it.joinToString { it.toString() }}" },
    block: Raise<Nel<Error>>.() -> A
): A = eagerEffect { block() } getOrElse { fail(supplyFailMessage(it)) }

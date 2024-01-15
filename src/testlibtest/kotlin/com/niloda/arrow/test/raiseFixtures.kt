package com.niloda.arrow.test

import arrow.core.Nel
import arrow.core.raise.Raise
import arrow.core.raise.zipOrAccumulate

fun Raise<Nel<String>>.raiseFirstThenSecond() =
    zipOrAccumulate(
        { raise("first") },
        { raise("second") }
    ) { _,_ -> }

fun Raise<Nel<String>>.returnWithoutErrors() =
    zipOrAccumulate(
        { "No Error" },
        { "Still No Error" }
    ) { _,_ -> returnWithoutErrorsValue }

val returnWithoutErrorsValue = "RESULT"

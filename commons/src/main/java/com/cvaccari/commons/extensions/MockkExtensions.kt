package com.cvaccari.commons.extensions

import io.mockk.MockKVerificationScope
import io.mockk.coVerify
import io.mockk.verify

fun coVerifyOnce(verifyBlock: suspend MockKVerificationScope.() -> Unit) = coVerify(exactly = 1, verifyBlock = verifyBlock)

infix fun Any.shouldCall(verifyBlock: suspend MockKVerificationScope.() -> Unit) = coVerify(
    exactly = 1,
    verifyBlock = verifyBlock
)

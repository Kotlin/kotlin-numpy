/*
 * Copyright 2019 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.numkt.logic

import org.jetbrains.numkt.callFunc
import org.jetbrains.numkt.core.KtNDArray

/**
 *
 */
fun <T : Any> all(a: KtNDArray<T>): Boolean =
    callFunc(arrayOf("all"), args = arrayOf(a), kClass = Boolean::class)

/**
 *
 */
fun <T : Any> all(a: KtNDArray<T>, axis: Int): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("all"), args = arrayOf(a, axis))

/**
 *
 */
fun <T : Any> any(a: KtNDArray<T>): Boolean =
    callFunc(nameMethod = arrayOf("any"), args = arrayOf(a), kClass = Boolean::class)

/**
 *
 */
fun <T : Any> any(a: KtNDArray<T>, axis: Int): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("any"), args = arrayOf(a, axis))

/**
 *
 */
fun <T : Any> isFinite(x: KtNDArray<T>): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("isfinite"), args = arrayOf(x))

/**
 *
 */
fun <T : Any> isInf(x: KtNDArray<T>): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("isinf"), args = arrayOf(x))


/**
 *
 */
fun <T : Any> isNan(x: KtNDArray<T>): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("isnan"), args = arrayOf(x))

/**
 *
 */
fun <T : Any> isNegInf(x: KtNDArray<T>): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("isneginf"), args = arrayOf(x))

/**
 *
 */
fun <T : Any> isPosInf(x: KtNDArray<T>): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("isposinf"), args = arrayOf(x))

/**
 *
 */
fun <T : Any, E : Any> logicalAnd(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("logical_and"), args = arrayOf(x1, x2))

/**
 *
 */
fun <T : Any, E : Any> logicalOr(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("logical_or"), args = arrayOf(x1, x2))

/**
 *
 */
fun <T : Any> logicalNot(x: KtNDArray<T>): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("logical_not"), args = arrayOf(x))

/**
 *
 */
fun <T : Any, E : Any> logicalXor(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("logical_xor"), args = arrayOf(x1, x2))

/**
 *
 */
fun <T : Any, E : Any> allClose(
    a: KtNDArray<T>,
    b: KtNDArray<E>,
    rtol: Double = 1e-05,
    atol: Double = 1e-08,
    equalNan: Boolean = false
): Boolean =
    callFunc(nameMethod = arrayOf("allclose"), args = arrayOf(a, b, rtol, atol, equalNan), kClass = Boolean::class)

/**
 *
 */
fun <T : Any, E : Any> isClose(
    a: KtNDArray<T>,
    b: KtNDArray<E>,
    rtol: Double = 1e-05,
    atol: Double = 1e-08,
    equalNan: Boolean = false
): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("isclose"), args = arrayOf(a, b, rtol, atol, equalNan))

/**
 *
 */
fun <T : Any, E : Any> arrayEqual(a1: KtNDArray<T>, a2: KtNDArray<E>): Boolean =
    callFunc(nameMethod = arrayOf("array_equal"), args = arrayOf(a1, a2), kClass = Boolean::class)


/**
 *
 */
fun <T : Any, E : Any> arrayEquiv(a1: KtNDArray<T>, a2: KtNDArray<E>): Boolean =
    callFunc(nameMethod = arrayOf("array_equiv"), args = arrayOf(a1, a2), kClass = Boolean::class)

/**
 *
 */
fun <T : Any, E : Any> greater(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("greater"), args = arrayOf(x1, x2))

/**
 *
 */
fun <T : Any, E : Any> greaterEqual(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("greater_equal"), args = arrayOf(x1, x2))


/**
 *
 */
fun <T : Any, E : Any> less(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("less"), args = arrayOf(x1, x2))


/**
 *
 */
fun <T : Any, E : Any> lessEqual(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("less_equal"), args = arrayOf(x1, x2))

/**
 *
 */
fun <T : Any, E : Any> equal(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("eqaul"), args = arrayOf(x1, x2))

/**
 *
 */
fun <T : Any, E : Any> notEqual(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf("not_equal"), args = arrayOf(x1, x2))
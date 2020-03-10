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

package org.jetbrains.numkt.math

import org.jetbrains.numkt.append
import org.jetbrains.numkt.callFunc
import org.jetbrains.numkt.core.KtNDArray
import org.jetbrains.numkt.core.None
import org.jetbrains.numkt.emptyLike

/**
 * Return the product of array elements over a given axis.
 */
inline fun <reified T : Number> prod(a: KtNDArray<T>): T =
    callFunc(nameMethod = arrayOf("prod"), args = arrayOf(a, None.none, a.dtype), kClass = T::class)

fun <T : Number> prod(a: KtNDArray<T>, axis: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("prod"), args = arrayOf(a, axis, a.dtype))

/**
 * Sum of array elements over a given axis.
 */
inline fun <reified T : Number> sum(a: KtNDArray<T>): T =
    callFunc(nameMethod = arrayOf("sum"), args = arrayOf(a, None.none, a.dtype), kClass = T::class)

fun <T : Number> sum(a: KtNDArray<T>, axis: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("sum"), args = arrayOf(a, axis, a.dtype))

/**
 * Return the product of array elements over a given axis treating Not a Numbers (NaNs) as ones.
 */
inline fun <reified T : Number> nanprod(a: KtNDArray<T>): T =
    callFunc(nameMethod = arrayOf("nanprod"), args = arrayOf(a, a.dtype), kClass = T::class)

fun <T : Number> nanprod(a: KtNDArray<T>, axis: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("nanprod"), args = arrayOf(a, axis, a.dtype))

/**
 * 	Return the sum of array elements over a given axis treating Not a Numbers (NaNs) as zero.
 */
inline fun <reified T : Number> nansum(a: KtNDArray<T>): T =
    callFunc(nameMethod = arrayOf("nanprod"), args = arrayOf(a, a.dtype), kClass = T::class)

fun <T : Number> nansum(a: KtNDArray<T>, axis: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("nansum"), args = arrayOf(a, axis, a.dtype))

/**
 * Return the cumulative product of elements along a given axis.
 */
fun <T : Any> cumprod(a: KtNDArray<T>, axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("cumprod"), args = arrayOf(a, axis ?: None.none, a.dtype))

/**
 * Return the cumulative sum of the elements along a given axis.
 */
@JvmName("cumsumNumber")
fun <T : Number> cumsum(a: KtNDArray<T>, axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("cumsum"), args = arrayOf(a, axis ?: None.none, a.dtype))

@JvmName("cumsumBoolean")
fun cumsum(a: KtNDArray<Boolean>, axis: Int? = null): KtNDArray<Int> =
    callFunc(nameMethod = arrayOf("cumsum"), args = arrayOf(a, axis ?: None.none))

/**
 * Return the cumulative product of array elements over a given axis treating Not a Numbers (NaNs) as one.
 */
fun <T : Number> nancumprod(a: KtNDArray<T>, axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("nancumprod"), args = arrayOf(a, axis ?: None.none, a.dtype))

/**
 * Return the cumulative sum of array elements over a given axis treating Not a Numbers (NaNs) as zero.
 */
fun <T : Number> nancumsum(a: KtNDArray<T>, axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("nancumsum"), args = arrayOf(a, axis ?: None.none, a.dtype))

/**
 * Calculate the n-th discrete difference along the given axis.
 */
fun <T : Number> diff(a: KtNDArray<T>, n: Int = 1, axis: Int = -1): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("diff"), args = arrayOf(a, n, axis))

/**
 * The differences between consecutive elements of an array.
 */
fun <T : Number> ediff1d(
    ary: KtNDArray<T>,
    toEnd: KtNDArray<T>? = null,
    toStart: KtNDArray<T>? = null
): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("ediff1d"), args = arrayOf(ary, toEnd ?: None.none, toStart ?: None.none))


/**
 * Return the gradient of an one-dimensional array.
 */
fun <T : Any> gradient1D(f: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("gradient"), args = arrayOf(f))

/**
 * Return the gradient of an N-dimensional array.
 */
fun <T : Any> gradientND(f: KtNDArray<T>): List<KtNDArray<Double>> =
    callFunc(nameMethod = arrayOf("gradient"), args = arrayOf(f), kClass = List::class) as List<KtNDArray<Double>>

/**
 * Return the cross product of two (arrays of) vectors.
 */
fun <T : Number, E : Number> cross(
    a: KtNDArray<T>,
    b: KtNDArray<E>,
    axisa: Int = -1,
    axisb: Int = -1,
    axisc: Int = -1,
    axis: Int? = null
): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("cross"), args = arrayOf(a, b, axisa, axisb, axisc, axis ?: None.none))

/**
 * 	Integrate along the given axis using the composite trapezoidal rule.
 */
fun <T : Number> trapz(
    y: KtNDArray<T>,
    x: KtNDArray<out Number>? = null,
    dx: Double = 1.0
): Double =
    callFunc(nameMethod = arrayOf("trapz"), args = arrayOf(y, x ?: None.none, dx), kClass = Double::class)

fun <T : Number> trapz(
    y: KtNDArray<T>,
    x: KtNDArray<out Number>? = null,
    dx: Double = 1.0,
    axis: Int = -1
): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("trapz"), args = arrayOf(y, x ?: None.none, dx, axis))
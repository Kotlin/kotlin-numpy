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

import org.jetbrains.numkt.callFunc
import org.jetbrains.numkt.core.KtNDArray
import org.jetbrains.numkt.core.None

/**
 * Returns the discrete, linear convolution of two one-dimensional sequences.
 */
fun <T : Number, E : Number> convolve(a: KtNDArray<T>, v: KtNDArray<E>, mode: String = "full"): KtNDArray<E> =
    callFunc(nameMethod = arrayOf("convolve"), args = arrayOf(a, v, mode))

/**
 * Clip (limit) the values in an array.
 */
fun <T : Number> clip(a: KtNDArray<T>, aMin: T?, aMax: T?): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("clip"), args = arrayOf(a, aMin ?: None.none, aMax ?: None.none))

/**
 * Return the non-negative square-root of an array, element-wise.
 */
fun <T : Number> sqrt(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("sqrt"), args = arrayOf(x), dtype = Double::class)

/**
 * Return the cube-root of an array, element-wise.
 */
fun <T : Number> cbrt(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("cbrt"), args = arrayOf(x), dtype = Double::class)

/**
 * 	Return the element-wise square of the input.
 */
fun <T : Number> square(x: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("square"), args = arrayOf(x))

/**
 * 	Calculate the absolute value element-wise.
 */
fun <T : Number> absolute(x: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("absolute"), args = arrayOf(x))

/**
 * Compute the absolute values element-wise.
 */
fun <T : Number> fabs(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("fabs"), args = arrayOf(x), dtype = Double::class)

/**
 * Returns an element-wise indication of the sign of a number.
 */
fun <T : Number> sign(x: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("sign"), args = arrayOf(x))

/**
 * Compute the Heaviside step function.
 */
fun <T : Number, E : Number> heaviside(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("heaviside"), args = arrayOf(x1, x2), dtype = Double::class)

/**
 * Element-wise maximum of array elements.
 */
fun <T : Number> maximum(x1: KtNDArray<T>, x2: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("maximum"), args = arrayOf(x1, x2))

/**
 * Element-wise minimum of array elements.
 */
fun <T : Number> minimum(x1: KtNDArray<T>, x2: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("minimum"), args = arrayOf(x1, x2))

/**
 * Element-wise maximum of array elements.
 */
fun <T : Number, E : Number> fmax(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("fmax"), args = arrayOf(x1, x2), dtype = Double::class)

/**
 * Element-wise minimum of array elements.
 */
fun <T : Number, E : Number> fmin(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("fmin"), args = arrayOf(x1, x2), dtype = Double::class)

/**
 * 	Replace NaN with zero and infinity with large finite numbers (default behaviour).
 */
fun <T : Number> nanToNum(
    x: KtNDArray<T>,
    nan: Double = 0.0,
    posinf: Double? = null,
    neginf: Double? = null
): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("nan_to_num"), args = arrayOf(x, nan, posinf ?: None.none, neginf ?: None.none))

/**
 * One-dimensional linear interpolation.
 */
fun <T : Number, E : Number, R : Number> interp(
    x: KtNDArray<T>,
    xp: KtNDArray<E>,
    fp: KtNDArray<R>,
    left: Double? = null,
    right: Double? = null,
    period: Double? = null
): KtNDArray<Double> =
    callFunc(
        nameMethod = arrayOf("interp"),
        args = arrayOf(x, xp, fp, left ?: None.none, right ?: None.none, period ?: None.none)
    )
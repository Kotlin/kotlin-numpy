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
 *
 */
fun <T : Number, E : Number> convolve(a: KtNDArray<T>, v: KtNDArray<E>, mode: String = "full"): KtNDArray<E> =
    callFunc(nameMethod = arrayOf("convolve"), args = arrayOf(a, v, mode))

/**
 *
 */
fun <T : Number> clip(a: KtNDArray<T>, aMin: T?, aMax: T?): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("clip"), args = arrayOf(a, aMin ?: None.none, aMax ?: None.none))

/**
 *
 */
fun <T : Number> sqrt(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("sqrt"), args = arrayOf(x), dtype = Double::class)

/**
 *
 */
fun <T : Number> cbrt(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("cbrt"), args = arrayOf(x), dtype = Double::class)

/**
 *
 */
fun <T : Number> square(x: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("square"), args = arrayOf(x))

/**
 *
 */
fun <T : Number> absolute(x: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("absolute"), args = arrayOf(x))

/**
 *
 */
fun <T : Number> fabs(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("fabs"), args = arrayOf(x), dtype = Double::class)

/**
 *
 */
fun <T : Number> sign(x: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("sign"), args = arrayOf(x))

/**
 *
 */
fun <T : Number, E : Number> heaviside(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("heaviside"), args = arrayOf(x1, x2), dtype = Double::class)

/**
 *
 */
fun <T : Number> maximum(x1: KtNDArray<T>, x2: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("maximum"), args = arrayOf(x1, x2))

/**
 *
 */
fun <T : Number> minimum(x1: KtNDArray<T>, x2: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("minimum"), args = arrayOf(x1, x2))

/**
 *
 */
fun <T : Number, E : Number> fmax(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("fmax"), args = arrayOf(x1, x2), dtype = Double::class)

/**
 *
 */
fun <T : Number, E : Number> fmin(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("fmin"), args = arrayOf(x1, x2), dtype = Double::class)

/**
 *
 */
fun <T : Number> nanToNum(
    x: KtNDArray<T>,
    nan: Double = 0.0,
    posinf: Double? = null,
    neginf: Double? = null
): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("nan_to_num"), args = arrayOf(x, nan, posinf ?: None.none, neginf ?: None.none))

/**
 *
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
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

package org.jetbrains.numkt.statistics

import org.jetbrains.numkt.callFunc
import org.jetbrains.numkt.core.KtNDArray

/**
 *
 */
inline fun <reified T : Number> amin(a: KtNDArray<T>): T =
    callFunc(nameMethod = arrayOf("amin"), args = arrayOf(a), kClass = T::class)

/**
 *
 */
fun <T : Number> amin(a: KtNDArray<T>, axis: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("amin"), args = arrayOf(a, axis))

/**
 *
 */
inline fun <reified T : Number> amax(a: KtNDArray<T>): T =
    callFunc(nameMethod = arrayOf("amax"), args = arrayOf(a), kClass = T::class)

/**
 *
 */
fun <T : Number> amax(a: KtNDArray<T>, axis: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("amax"), args = arrayOf(a, axis))

/**
 *
 */
fun <T : Number> nanmin(a: KtNDArray<T>): Double =
    callFunc(nameMethod = arrayOf("nanmin"), args = arrayOf(a), kClass = Double::class)

/**
 *
 */
fun <T : Number> nanmin(a: KtNDArray<T>, axis: Int): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("nanmin"), args = arrayOf(a, axis))

/**
 *
 */
fun <T : Number> nanmax(a: KtNDArray<T>): Double =
    callFunc(nameMethod = arrayOf("nanmax"), args = arrayOf(a), kClass = Double::class)

/**
 *
 */
fun <T : Number> nanmax(a: KtNDArray<T>, axis: Int): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("nanmax"), args = arrayOf(a, axis))

/**
 *
 */
fun <T : Number> ptp(a: KtNDArray<T>, axis: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("ptp"), args = arrayOf(a, axis))

/**
 *
 */
fun <T : Number> percentile(a: KtNDArray<T>, q: Double): Double =
    callFunc(nameMethod = arrayOf("percentile"), args = arrayOf(a, q), kClass = Double::class)

/**
 *
 */
fun <T : Number> percentile(a: KtNDArray<T>, q: Double, axis: Int): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("percentile"), args = arrayOf(a, q, axis))


/**
 *
 */
fun <T : Number> nanPercentile(a: KtNDArray<T>, q: Double): Double =
    callFunc(nameMethod = arrayOf("nanpercentile"), args = arrayOf(a, q), kClass = Double::class)

/**
 *
 */
fun <T : Number> nanPercentile(a: KtNDArray<T>, q: Double, axis: Int): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("nanpercentile"), args = arrayOf(a, q, axis))

/**
 *
 */
fun <T : Number> quantile(a: KtNDArray<T>, q: Double): Double =
    callFunc(nameMethod = arrayOf("quantile"), args = arrayOf(a, q), kClass = Double::class)

/**
 *
 */
fun <T : Number> quantile(a: KtNDArray<T>, q: Double, axis: Int): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("nanpercentile"), args = arrayOf(a, q, axis))


/**
 *
 */
fun <T : Number> nanQuantile(a: KtNDArray<T>, q: Double): Double =
    callFunc(nameMethod = arrayOf("nanquantile"), args = arrayOf(a, q), kClass = Double::class)

/**
 *
 */
fun <T : Number> nanQuantile(a: KtNDArray<T>, q: Double, axis: Int): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("nanpercentile"), args = arrayOf(a, q, axis))
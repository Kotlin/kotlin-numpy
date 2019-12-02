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
fun <T : Number> median(a: KtNDArray<T>): Double =
    callFunc(nameMethod = arrayOf("median"), args = arrayOf(a), kClass = Double::class)

/**
 *
 */
fun <T : Number> median(a: KtNDArray<T>, axis: Int): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("median"), args = arrayOf(a, axis))

/**
 *
 */
fun <T : Number> average(a: KtNDArray<T>): Double =
    callFunc(nameMethod = arrayOf("average"), args = arrayOf(a), kClass = Double::class)

/**
 *
 */
fun <T : Number> average(a: KtNDArray<T>, axis: Int): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("average"), args = arrayOf(a, axis))

/**
 *
 */
fun <T : Number> mean(a: KtNDArray<T>): Double =
    callFunc(nameMethod = arrayOf("mean"), args = arrayOf(a), kClass = Double::class)

/**
 *
 */
fun <T : Number> mean(a: KtNDArray<T>, axis: Int): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("mean"), args = arrayOf(a, axis))

/**
 *
 */
fun <T : Number> std(a: KtNDArray<T>, ddof: Int = 0): Double =
    callFunc(nameMethod = arrayOf("std"), args = arrayOf(a, ddof), kClass = Double::class)

/**
 *
 */
fun <T : Number> std(a: KtNDArray<T>, axis: Int, ddof: Int = 0): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("std"), args = arrayOf(a, axis, ddof))

/**
 *
 */
fun <T : Number> `var`(a: KtNDArray<T>, ddof: Int = 0): Double =
    callFunc(nameMethod = arrayOf("var"), args = arrayOf(a, ddof), kClass = Double::class)

/**
 *
 */
fun <T : Number> `var`(a: KtNDArray<T>, axis: Int, ddof: Int = 0): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("var"), args = arrayOf(a, axis, ddof))

/**
 *
 */
fun <T : Number> nanMedian(a: KtNDArray<T>): Double =
    callFunc(nameMethod = arrayOf("nanmedian"), args = arrayOf(a), kClass = Double::class)

/**
 *
 */
fun <T : Number> nanMedian(a: KtNDArray<T>, axis: Int): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("nanmedian"), args = arrayOf(a, axis))

/**
 *
 */
fun <T : Number> nanMean(a: KtNDArray<T>): Double =
    callFunc(nameMethod = arrayOf("nanmean"), args = arrayOf(a), kClass = Double::class)

/**
 *
 */
fun <T : Number> nanMean(a: KtNDArray<T>, axis: Int): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("nanmean"), args = arrayOf(a, axis))

/**
 *
 */
fun <T : Number> nanStd(a: KtNDArray<T>, ddof: Int = 0): Double =
    callFunc(nameMethod = arrayOf("nanstd"), args = arrayOf(a, ddof), kClass = Double::class)

/**
 *
 */
fun <T : Number> nanStd(a: KtNDArray<T>, axis: Int, ddof: Int = 0): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("nanstd"), args = arrayOf(a, axis, ddof))

/**
 *
 */
fun <T : Number> nanVar(a: KtNDArray<T>, ddof: Int = 0): Double =
    callFunc(nameMethod = arrayOf("nanvar"), args = arrayOf(a, ddof), kClass = Double::class)

/**
 *
 */
fun <T : Number> nanVar(a: KtNDArray<T>, axis: Int, ddof: Int = 0): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("nanvar"), args = arrayOf(a, axis, ddof))
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


/**
 * Calculate the exponential of all elements in the input array.
 */
fun <T : Number> exp(x: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("exp"), args = arrayOf(x))

/**
 * Calculate exp(x) - 1 for all elements in the array.
 */
fun <T : Number> expm1(x: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("expm1"), args = arrayOf(x))

/**
 * 	Calculate 2**p for all p in the input array.
 */
fun <T : Number> exp2(x: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("exp2"), args = arrayOf(x))

/**
 * Natural logarithm, element-wise.
 */
fun <T : Number> log(x: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("log"), args = arrayOf(x))

/**
 * Return the base 10 logarithm of the input array, element-wise.
 */
fun <T : Number> log10(x: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("log10"), args = arrayOf(x))

/**
 * Base-2 logarithm of x.
 */
fun <T : Number> log2(x: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("log2"), args = arrayOf(x))

/**
 * Return the natural logarithm of one plus the input array, element-wise.
 */
fun <T : Number> log1p(x: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("log1p"), args = arrayOf(x))

/**
 * Logarithm of the sum of exponentiations of the inputs.
 */
fun <T : Number, E : Number> logaddexp(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("logaddexp"), args = arrayOf(x1, x2))

/**
 * Logarithm of the sum of exponentiations of the inputs in base-2.
 */
fun <T : Number, E : Number> logaddexp2(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("logaddexp2"), args = arrayOf(x1, x2))
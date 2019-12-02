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
 *
 */
fun <T : Number> exp(x: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("exp"), args = arrayOf(x))

/**
 *
 */
fun <T : Number> expm1(x: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("expm1"), args = arrayOf(x))

/**
 *
 */
fun <T : Number> exp2(x: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("exp2"), args = arrayOf(x))

/**
 *
 */
fun <T : Number> log(x: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("log"), args = arrayOf(x))

/**
 *
 */
fun <T : Number> log10(x: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("log10"), args = arrayOf(x))

/**
 *
 */
fun <T : Number> log2(x: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("log2"), args = arrayOf(x))

/**
 *
 */
fun <T : Number> log1p(x: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("log1p"), args = arrayOf(x))

/**
 *
 */
fun <T : Number, E : Number> logaddexp(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("logaddexp"), args = arrayOf(x1, x2))

/**
 *
 */
fun <T : Number, E : Number> logaddexp2(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("logaddexp2"), args = arrayOf(x1, x2))
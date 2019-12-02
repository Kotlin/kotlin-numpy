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
fun <T : Number> reciprocal(x: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("reciprocal"), args = arrayOf(x))

/**
 *
 */
fun <T : Number> positive(x: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("positive"), args = arrayOf(x))

/**
 *
 */
fun <T : Number> negative(x: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("negative"), args = arrayOf(x))


/**
 *
 */
fun <T : Number> power(x1: KtNDArray<T>, x2: Byte): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("power"), args = arrayOf(x1, x2))

fun <T : Number> power(x1: KtNDArray<T>, x2: Short): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("power"), args = arrayOf(x1, x2))

fun <T : Number> power(x1: KtNDArray<T>, x2: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("power"), args = arrayOf(x1, x2))

fun <T : Number> power(x1: KtNDArray<T>, x2: Long): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("power"), args = arrayOf(x1, x2))

fun <T : Number> power(x1: KtNDArray<T>, x2: Float): KtNDArray<Float> =
    callFunc(nameMethod = arrayOf("power"), args = arrayOf(x1, x2))

fun <T : Number> power(x1: KtNDArray<T>, x2: Double): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("power"), args = arrayOf(x1, x2))

/**
 *
 */
fun <T : Number, E : Number> floatPower(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("float_power"), args = arrayOf(x1, x2))

/**
 *
 */
fun <T : Number, E : Number> fmod(x1: KtNDArray<T>, x2: E): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("fmod"), args = arrayOf(x1, x2))
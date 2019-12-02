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
import kotlin.math.PI

/**
 *
 */
fun <T : Number> sin(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("sin"), args = arrayOf(x), dtype = Double::class)

/**
 *
 */
fun <T : Number> cos(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("cos"), args = arrayOf(x), dtype = Double::class)

/**
 *
 */
fun <T : Number> tan(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("tan"), args = arrayOf(x), dtype = Double::class)

/**
 *
 */
fun <T : Number> arcsin(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("arcsin"), args = arrayOf(x), dtype = Double::class)

/**
 *
 */
fun <T : Number> arccos(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("arccos"), args = arrayOf(x), dtype = Double::class)

/**
 *
 */
fun <T : Number> arctan(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("arctan"), args = arrayOf(x), dtype = Double::class)

/**
 *
 */
fun <T : Number, E : Number> hypot(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("hypot"), args = arrayOf(x1, x2), dtype = Double::class)

/**
 *
 */
fun <T : Number, E : Number> arctan2(x1: KtNDArray<T>, x2: KtNDArray<E>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("arctan2"), args = arrayOf(x1, x2), dtype = Double::class)

/**
 *
 */
fun <T : Number> degrees(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("degrees"), args = arrayOf(x), dtype = Double::class)

/**
 *
 */
fun <T : Number> radians(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("radians"), args = arrayOf(x), dtype = Double::class)

/**
 *
 */
fun <T : Number> unwrap(p: KtNDArray<T>, discont: Double = PI, axis: Int = -1): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("unwrap"), args = arrayOf(p, discont, axis))

/**
 *
 */
fun <T : Number> deg2rad(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("deg2rad"), args = arrayOf(x), dtype = Double::class)

/**
 *
 */
fun <T : Number> rad2deg(x: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("rad2deg"), args = arrayOf(x), dtype = Double::class)
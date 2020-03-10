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
 * Evenly round to the given number of decimals.
 */
fun <T : Number> around(a: KtNDArray<T>, decimals: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("around"), args = arrayOf(a, decimals))

/**
 * Round an array to the given number of decimals.
 */
fun <T : Number> rint(a: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("rint"), args = arrayOf(a))

/**
 * Round to nearest integer towards zero.
 */
fun <T : Number> fix(a: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("fix"), args = arrayOf(a))

/**
 * 	Return the floor of the input, element-wise.
 */
fun <T : Number> floor(a: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("floor"), args = arrayOf(a))

/**
 * 	Return the ceiling of the input, element-wise.
 */
fun <T : Number> ceil(a: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("ceil"), args = arrayOf(a))

/**
 * Return the truncated value of the input, element-wise.
 */
fun <T : Number> trunc(a: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("trunc"), args = arrayOf(a))
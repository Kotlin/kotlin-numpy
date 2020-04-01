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
 * Modified Bessel function of the first kind, order 0.
 */
fun <T : Number> i0(x: KtNDArray<T>): KtNDArray<T> = callFunc(nameMethod = arrayOf("i0"), args = arrayOf(x))

/**
 * Return the sinc function.
 */
fun <T : Number> sinc(x: KtNDArray<T>): KtNDArray<Double> = callFunc(nameMethod = arrayOf("sinc"), args = arrayOf(x))
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
import org.jetbrains.numkt.core.None

/**
 *
 */
enum class ModeCorr(val str: String) {
    /**
     * Mode ‘valid’ returns output of length max(M, N) - min(M, N) + 1.
     * The convolution product is only given for points where the signals overlap completely.
     * Values outside the signal boundary have no effect.
     */
    VALID("valid"),

    /**
     * Mode ‘same’ returns output of length max(M, N). Boundary effects are still visible.
     */
    SAME("same"),

    /**
     * By default, mode is ‘full’.
     * This returns the convolution at each point of overlap, with an output shape of (N+M-1,).
     * At the end-points of the convolution, the signals do not overlap completely, and boundary
     * effects may be seen.
     */
    FULL("full")
}

/**
 * Return Pearson product-moment correlation coefficients.
 */
fun <T : Number, E : Number> corrcoef(
    x: KtNDArray<T>,
    y: KtNDArray<E>? = null,
    rowvar: Boolean = true
): KtNDArray<Double> = callFunc(nameMethod = arrayOf("corrcoef"), args = arrayOf(x, y ?: None.none, rowvar))

/**
 * Cross-correlation of two 1-dimensional sequences.
 */
fun <T : Number, E : Number> correlate(
    a: KtNDArray<T>,
    v: KtNDArray<E>,
    mode: ModeCorr = ModeCorr.VALID
): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("correlate"), args = arrayOf(a, v, mode.str))

/**
 * Estimate a covariance matrix, given data and weights.
 */
fun <T : Number, E : Number> cov(
    m: KtNDArray<T>,
    y: KtNDArray<E>? = null,
    rowvar: Boolean = true,
    bias: Boolean = false,
    ddof: Int? = null,
    fweights: IntArray? = null,
    aweights: IntArray? = null
): KtNDArray<Double> =
    callFunc(
        nameMethod = arrayOf("cov"),
        args = arrayOf(m, y ?: None.none, rowvar, bias, ddof ?: None.none, fweights ?: None.none, aweights ?: None.none)
    )
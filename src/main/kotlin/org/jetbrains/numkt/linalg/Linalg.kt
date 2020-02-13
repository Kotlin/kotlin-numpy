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

package org.jetbrains.numkt.linalg

import org.jetbrains.numkt.Casting
import org.jetbrains.numkt.Order
import org.jetbrains.numkt.callFunc
import org.jetbrains.numkt.core.KtNDArray
import org.jetbrains.numkt.core.None

private const val LINALG_STR = "linalg"

/**
 *
 */
class Linalg {
    companion object {
        /**
         *
         */
        fun <T : Number> multiDot(vararg arrays: KtNDArray<T>): KtNDArray<T> =
            callFunc(nameMethod = arrayOf(LINALG_STR, "multi_dot"), args = arrayOf(arrays))

        /**
         *
         */
        fun <T : Number> matrixPower(a: KtNDArray<T>, n: Int): KtNDArray<T> =
            callFunc(arrayOf(LINALG_STR, "matrix_power"), args = arrayOf(a, n))

        /**
         *
         */
        fun <T : Number> cholesky(a: KtNDArray<T>): KtNDArray<Double> =
            callFunc(arrayOf(LINALG_STR, "cholesky"), args = arrayOf(a))

        /**
         * a - matrix (M, N)
         */
        fun <T : Any> qr(a: KtNDArray<T>, mode: String = "reduced"): Pair<KtNDArray<Double>, KtNDArray<Double>> =
            callFunc(
                nameMethod = arrayOf(LINALG_STR, "qr"),
                args = arrayOf(a, mode),
                kClass = Pair::class
            ) as Pair<KtNDArray<Double>, KtNDArray<Double>>


        /**
         * a.ndim >= 2.
         */
        fun <T : Any> svd(
            a: KtNDArray<T>,
            fullMatrices: Boolean = true,
            computeUV: Boolean = true,
            hermitian: Boolean = false
        ): List<KtNDArray<Double>> =
            callFunc(
                nameMethod = arrayOf(LINALG_STR, "svd"),
                args = arrayOf(a, fullMatrices, computeUV, hermitian),
                kClass = List::class
            ) as List<KtNDArray<Double>>

        /**
         * a: (..., M, M)
         * w: (..., M)
         * v: (..., M, M)
         */
        fun <T : Any> eig(a: KtNDArray<T>): Pair<KtNDArray<Double>, KtNDArray<Double>> =
            callFunc(
                nameMethod = arrayOf(LINALG_STR, "eig"),
                args = arrayOf(a),
                kClass = Pair::class
            ) as Pair<KtNDArray<Double>, KtNDArray<Double>>

        /**
         *
         */
        fun <T : Number> eigvals(a: KtNDArray<T>): KtNDArray<Double> =
            callFunc(arrayOf("linalg", "eigvals"), args = arrayOf(a))

        /**
         *
         */
        fun <T : Number> eigvalsh(a: KtNDArray<T>): KtNDArray<Double> =
            callFunc(arrayOf("linalg", "eigvalsh"), args = arrayOf(a))

        /**
         *
         */
        fun <T : Number> norm(x: KtNDArray<T>, ord: Int? = null): Double =
            callFunc(arrayOf("linalg", "norm"), args = arrayOf(x, ord ?: None.none), kClass = Double::class)

        /**
         *
         */
        fun <T : Number> norm(x: KtNDArray<T>, ord: Int? = null, axis: Int): KtNDArray<Double> =
            callFunc(arrayOf("linalg", "norm"), args = arrayOf(x, ord ?: None.none, axis))

        /**
         *
         */
        fun <T : Number> cond(x: KtNDArray<T>, p: Int? = null): Double =
            callFunc(arrayOf("linalg", "cond"), args = arrayOf(x, p ?: None.none), kClass = Double::class)

        /**
         *
         */
        fun <T : Number> det(a: KtNDArray<T>): Double =
            callFunc(arrayOf("linalg", "det"), args = arrayOf(a), kClass = Double::class)

        /**
         *
         */
        fun <T : Number> matrixRank(m: KtNDArray<T>): Int =
            callFunc(arrayOf("linalg", "matrix_rank"), args = arrayOf(m), kClass = Int::class)

        /**
         *
         */
        fun <T : Any> slogdet(a: KtNDArray<T>): Pair<Double, Double> =
            callFunc(
                nameMethod = arrayOf(LINALG_STR, "slogdet"),
                args = arrayOf(a),
                kClass = Pair::class
            ) as Pair<Double, Double>

        /**
         *
         */
        fun <T : Number, E : Number> solve(a: KtNDArray<T>, b: KtNDArray<E>): KtNDArray<Double> =
            callFunc(arrayOf("linalg", "solve"), args = arrayOf(a, b))

        /**
         *
         */
        fun <T : Number, E : Number> lstsq(
            a: KtNDArray<T>,
            b: KtNDArray<E>,
            rcond: Double? = null
        ): List<*> =
            callFunc(
                nameMethod = arrayOf(LINALG_STR, "lstsq"),
                args = arrayOf(a, b, rcond ?: None.none), kClass = List::class
            )

        /**
         *
         */
        fun <T : Number> inv(a: KtNDArray<T>): KtNDArray<Double> =
            callFunc(arrayOf("linalg", "inv"), args = arrayOf(a))

        /**
         *
         */
        fun <T : Number> pinv(a: KtNDArray<T>, rcond: Double = 1e-15, hermitian: Boolean = false): KtNDArray<Double> =
            callFunc(arrayOf("linalg", "pinv"), args = arrayOf(a, rcond, hermitian))
    }
}

/**
 *
 */
class LinAlgError

/**
 *
 */
fun <T : Number> dot(a: KtNDArray<T>, b: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("dot"), args = arrayOf(a, b))

/**
 *
 */
inline fun <reified T : Number> vdot(a: KtNDArray<T>, b: KtNDArray<T>): T =
    callFunc(nameMethod = arrayOf("vdot"), args = arrayOf(a, b), kClass = T::class)

/**
 *
 */
fun <T : Number> inner(a: KtNDArray<T>, b: KtNDArray<T>): KtNDArray<T> =
    callFunc(arrayOf("inner"), args = arrayOf(a, b))

/**
 *
 */
fun <T : Number> outer(a: KtNDArray<T>, b: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("outer"), args = arrayOf(a, b))

/**
 *
 */
fun <T : Number> matmul(x1: KtNDArray<T>, x2: KtNDArray<T>): KtNDArray<T> =
    callFunc(arrayOf("matmul"), args = arrayOf(x1, x2))

/**
 *
 */
inline fun <reified T : Number> einsum(
    subscripts: String,
    vararg operands: KtNDArray<out Number>,
    order: Order = Order.K,
    casting: Casting = Casting.SAFE,
    optimize: Boolean = false
): KtNDArray<T> =
    callFunc(
        nameMethod = arrayOf("einsum"),
        args = arrayOf(subscripts, *operands),
        dtype = T::class,
        order = order,
        casting = casting
    )

/**
 *
 */
fun <T : Number> kron(a: KtNDArray<T>, b: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("kron"), args = arrayOf(a, b))

/**
 *
 */
inline fun <reified T : Number> trace2D(a: KtNDArray<T>, offset: Int = 0, axis1: Int = 0, axis2: Int = 1): T =
    callFunc(nameMethod = arrayOf("trace"), args = arrayOf(a, offset, axis1, axis2), kClass = T::class)

fun <T : Number> traceND(a: KtNDArray<T>, offset: Int = 0, axis1: Int = 0, axis2: Int = 1): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("trace"), args = arrayOf(a, offset, axis1, axis2))
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
         * Compute the dot product of two or more arrays in a single function call,
         * while automatically selecting the fastest evaluation order.
         * @param arrays if the first argument is 1-D it is treated as row vector.
         * If the last argument is 1-D it is treated as column vector. The other arguments must be 2-D.
         * @return Returns the dot product of the supplied arrays.
         */
        fun <T : Number> multiDot(vararg arrays: KtNDArray<T>): KtNDArray<T> =
            callFunc(nameMethod = arrayOf(LINALG_STR, "multi_dot"), args = arrayOf(arrays))

        /**
         * Raise a square matrix to the (integer) power n,
         * @param a matrix to be "powered".
         * @param n the exponent can be any integer, positive, negative, or zero.
         * @return The return value is the same shape and type as [a];
         * if the exponent is positive or zero then the type of the elements is the same as those of [a].
         * If the exponent is negative the elements are floating-point.
         */
        fun <T : Number> matrixPower(a: KtNDArray<T>, n: Int): KtNDArray<T> =
            callFunc(arrayOf(LINALG_STR, "matrix_power"), args = arrayOf(a, n))

        /**
         * Cholesky decomposition.
         * @param a hermitian, positive-definite input matrix.
         * @return Upper or lower-triangular Cholesky factor of [a]. Returns a matrix object if [a] is a matrix.
         */
        fun <T : Number> cholesky(a: KtNDArray<T>): KtNDArray<Double> =
            callFunc(arrayOf(LINALG_STR, "cholesky"), args = arrayOf(a))

        /**
         * Compute the qr factorization of a matrix.
         * @param a matrix to be factored.
         * @param mode - 'reduced' - returns q, r with dimensions (M, K), (K, N) (default)
         * - 'complete' - returns q, r with dimensions (M, M), (M, N)
         * - 'r' - returns r only with dimensions (K, N)
         * - 'raw' - returns h, tau with dimensions (N, M), (K,)
         * - 'full' - alias of ‘reduced’, deprecated
         * - 'economic' - returns h from ‘raw’, deprecated.
         * @return A matrix with orthonormal columns.
         */
        fun <T : Any> qr(a: KtNDArray<T>, mode: String = "reduced"): Pair<KtNDArray<Double>, KtNDArray<Double>> =
            callFunc(
                nameMethod = arrayOf(LINALG_STR, "qr"),
                args = arrayOf(a, mode),
                kClass = Pair::class
            ) as Pair<KtNDArray<Double>, KtNDArray<Double>>


        /**
         * Singular Value Decomposition.
         * @param a a real array with a.ndim >= 2.
         * @param fullMatrices if True (default), u and vh have the shapes (..., M, M) and (..., N, N), respectively.
         * Otherwise, the shapes are (..., M, K) and (..., K, N), respectively, where K = min(M, N).
         * @param computeUV whether or not to compute u and vh in addition to s. True by default.
         * @param hermitian if *true*, a is assumed to be Hermitian (symmetric if real-valued),
         * enabling a more efficient method for finding singular values. Defaults to *false*.
         * @return svd.
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
         * Compute the eigenvalues and right eigenvectors of a square array.
         *
         * @param a (..., M, M)
         * @return w: (..., M); v: (..., M, M)
         */
        fun <T : Any> eig(a: KtNDArray<T>): Pair<KtNDArray<Double>, KtNDArray<Double>> =
            callFunc(
                nameMethod = arrayOf(LINALG_STR, "eig"),
                args = arrayOf(a),
                kClass = Pair::class
            ) as Pair<KtNDArray<Double>, KtNDArray<Double>>

        /**
         * Compute the eigenvalues of a general matrix.
         * @param a real-valued matrix whose eigenvalues will be computed.
         * @return the eigenvalues, each repeated according to its multiplicity.
         */
        fun <T : Number> eigvals(a: KtNDArray<T>): KtNDArray<Double> =
            callFunc(arrayOf("linalg", "eigvals"), args = arrayOf(a))

        /**
         * Compute the eigenvalues of real symmetric matrix.
         * @param a real-valued matrix whose eigenvalues are to be computed.
         * @return The eigenvalues in ascending order, each repeated according to its multiplicity.
         */
        fun <T : Number> eigvalsh(a: KtNDArray<T>): KtNDArray<Double> =
            callFunc(arrayOf("linalg", "eigvalsh"), args = arrayOf(a))

        /**
         * Matrix or vector norm.
         * @param x input array.
         * @param ord order of the norm.
         * @return norm of the matrix or vectors.
         */
        fun <T : Number> norm(x: KtNDArray<T>, ord: Int? = null): Double =
            callFunc(arrayOf("linalg", "norm"), args = arrayOf(x, ord ?: None.none), kClass = Double::class)

        /**
         *
         */
        fun <T : Number> norm(x: KtNDArray<T>, ord: Int? = null, axis: Int): KtNDArray<Double> =
            callFunc(arrayOf("linalg", "norm"), args = arrayOf(x, ord ?: None.none, axis))

        /**
         * Compute the condition number of a matrix.
         * @param x the matrix whose condition number is sought.
         * @param p order of the norm
         *
         * - 1 - max(sum(abs(x), axis=0)
         * - -1 - min(sum(abs(x), axis=0)
         * - 2 - 2-norm(largest sing. value)
         * - -2 - smallest singular value
         */
        fun <T : Number> cond(x: KtNDArray<T>, p: Int? = null): Double =
            callFunc(arrayOf("linalg", "cond"), args = arrayOf(x, p ?: None.none), kClass = Double::class)

        /**
         * Compute the determinant of an array.
         * @param a input arrays.
         * @return Determinant of [a].
         */
        fun <T : Number> det(a: KtNDArray<T>): Double =
            callFunc(arrayOf("linalg", "det"), args = arrayOf(a), kClass = Double::class)

        /**
         * Return matrix rank of array using SVD method.
         * @param m input vector or stack of matrices.
         * @return rank of [m].
         */
        fun <T : Number> matrixRank(m: KtNDArray<T>): Int =
            callFunc(arrayOf("linalg", "matrix_rank"), args = arrayOf(m), kClass = Int::class)

        /**
         * Compute the sign and (natural) logarithm of the determinant of an array.
         * @param a input array, has to be a square 2-D array.
         * @return A number representing the sign of the determinant and
         * the natural log of the absolute value of the determinant.
         */
        fun <T : Any> slogdet(a: KtNDArray<T>): Pair<Double, Double> =
            callFunc(
                nameMethod = arrayOf(LINALG_STR, "slogdet"),
                args = arrayOf(a),
                kClass = Pair::class
            ) as Pair<Double, Double>

        /**
         * Solve a linear matrix equation, or system of linear scalar equations.
         * @param a coefficient matrix.
         * @param b ordinate or “dependent variable” values.
         * @return Solution to the system a x = b. Returned shape is identical to [b].
         */
        fun <T : Number, E : Number> solve(a: KtNDArray<T>, b: KtNDArray<E>): KtNDArray<Double> =
            callFunc(arrayOf("linalg", "solve"), args = arrayOf(a, b))

        /**
         * Return the least-squares solution to a linear matrix equation.
         * @param a "coefficient" matrix.
         * @param b ordinate or "dependent variable" values.
         * @param rcond cut-off ratio for small singular values of [a].
         * @return Least-squares solution.
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
         * Compute the (multiplicative) inverse of a matrix.
         * @param a matrix to be inverted.
         * @return inverse of the matrix [a].
         */
        fun <T : Number> inv(a: KtNDArray<T>): KtNDArray<Double> =
            callFunc(arrayOf("linalg", "inv"), args = arrayOf(a))

        /**
         * Compute the (Moore-Penrose) pseudo-inverse of a matrix.
         * @param a matrix or stack of matrices to be pseudo-inverted.
         * @param rcond cut-off for small singular values.
         * @param hermitian if True, a is assumed to be Hermitian (symmetric if real-valued),
         * enabling a more efficient method for finding singular values. Defaults to False.
         * @return the pseudo-inverse of [a]. If [a] is matrix instance, the so is _B_.
         */
        fun <T : Number> pinv(a: KtNDArray<T>, rcond: Double = 1e-15, hermitian: Boolean = false): KtNDArray<Double> =
            callFunc(arrayOf("linalg", "pinv"), args = arrayOf(a, rcond, hermitian))
    }
}


/**
 * Dot product of two arrays.
 */
fun <T : Number> dot(a: KtNDArray<T>, b: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("dot"), args = arrayOf(a, b))

/**
 * 	Return the dot product of two vectors.
 */
inline fun <reified T : Number> vdot(a: KtNDArray<T>, b: KtNDArray<T>): T =
    callFunc(nameMethod = arrayOf("vdot"), args = arrayOf(a, b), kClass = T::class)

/**
 * 	Inner product of two arrays.
 */
fun <T : Number> inner(a: KtNDArray<T>, b: KtNDArray<T>): KtNDArray<T> =
    callFunc(arrayOf("inner"), args = arrayOf(a, b))

/**
 * 	Compute the outer product of two vectors.
 */
fun <T : Number> outer(a: KtNDArray<T>, b: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("outer"), args = arrayOf(a, b))

/**
 * Matrix product of two arrays.
 */
fun <T : Number> matmul(x1: KtNDArray<T>, x2: KtNDArray<T>): KtNDArray<T> =
    callFunc(arrayOf("matmul"), args = arrayOf(x1, x2))

/**
 * Evaluates the Einstein summation convention on the operands.
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
 * 	Kronecker product of two arrays.
 */
fun <T : Number> kron(a: KtNDArray<T>, b: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("kron"), args = arrayOf(a, b))

/**
 * Return the sum along diagonals of the 2-D array.
 */
inline fun <reified T : Number> trace2D(a: KtNDArray<T>, offset: Int = 0, axis1: Int = 0, axis2: Int = 1): T =
    callFunc(nameMethod = arrayOf("trace"), args = arrayOf(a, offset, axis1, axis2), kClass = T::class)

/**
 * Return the sum along diagonals of the n-D array.
 */
fun <T : Number> traceND(a: KtNDArray<T>, offset: Int = 0, axis1: Int = 0, axis2: Int = 1): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("trace"), args = arrayOf(a, offset, axis1, axis2))
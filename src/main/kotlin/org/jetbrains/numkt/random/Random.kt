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

package org.jetbrains.numkt.random

import org.jetbrains.numkt.callFunc
import org.jetbrains.numkt.core.KtNDArray
import org.jetbrains.numkt.core.None

class Random {
    companion object {

        // Simple random data
        /**
         *
         */
        fun rand(): Double =
            callFunc(nameMethod = arrayOf("random", "rand"), kClass = Double::class)

        /**
         * Create an array of the given shape and populate it with random samples from a uniform distribution over [0, 1)
         */
        fun rand(vararg d: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "rand"), args = arrayOf(d))

        /**
         *
         */
        fun randn(): Double =
            callFunc(nameMethod = arrayOf("random", "rand"), kClass = Double::class)

        /**
         *
         */
        fun randn(vararg d: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "rand"), args = arrayOf(*(d.toTypedArray())))

        /**
         *
         */
        inline fun <reified T : Number> randint(low: Int, high: Int? = null): T =
            callFunc(
                nameMethod = arrayOf("random", "randint"),
                args = arrayOf(low, high ?: None.none, None.none, T::class.java),
                kClass = T::class
            )

        /**
         *
         */
        inline fun <reified T : Number> randint(low: Int, high: Int? = null, vararg size: Int): KtNDArray<T> =
            callFunc(
                nameMethod = arrayOf("random", "randint"),
                args = arrayOf(low, high ?: None.none, size, T::class.java)
            )

        /**
         *
         */
        fun randomIntegers(low: Int, high: Int? = null): Long =
            callFunc(
                nameMethod = arrayOf("random", "random_integers"),
                args = arrayOf(low, high ?: None.none),
                kClass = Long::class
            )

        /**
         *
         */
        fun randomIntegers(low: Int, high: Int? = null, vararg size: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "random_integers"), args = arrayOf(low, high ?: None.none, size))

        /**
         *
         */
        fun randomSample(): Double =
            callFunc(nameMethod = arrayOf("random", "random_sample"), kClass = Double::class)

        /**
         *
         */
        fun randomSample(vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "random_sample"), args = arrayOf(size))

        /**
         *
         */
        fun random(): Double =
            callFunc(nameMethod = arrayOf("random", "random"), kClass = Double::class)

        /**
         *
         */
        fun random(vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "random"), args = arrayOf(size))

        /**
         *
         */
        fun ranf(): Double =
            callFunc(nameMethod = arrayOf("random", "ranf"), kClass = Double::class)

        /**
         *
         */
        fun ranf(vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "ranf"), args = arrayOf(size))

        /**
         *
         */
        fun sample(): Double =
            callFunc(nameMethod = arrayOf("random", "sample"), kClass = Double::class)

        /**
         *
         */
        fun sample(vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "sample"), args = arrayOf(size))

        /**
         *
         */
        fun choice(a: Int, replace: Boolean = true): Long =
            callFunc(
                nameMethod = arrayOf("random", "choice"),
                args = arrayOf(a, None.none, replace),
                kClass = Long::class
            )

        /**
         *
         */
        fun choice(a: Int, vararg size: Int, replace: Boolean = true, p: DoubleArray? = null): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "choice"), args = arrayOf(a, size, replace, p ?: None.none))

        /**
         *
         */
        fun bytes(length: Int): ByteArray =
            callFunc(nameMethod = arrayOf("random", "bytes"), args = arrayOf(length), kClass = ByteArray::class)

        // Permutations

        /**
         *
         */
        fun <T : Any> shuffle(x: KtNDArray<T>): Unit =
            callFunc(nameMethod = arrayOf("random", "shuffle"), args = arrayOf(x), kClass = Unit::class)


        /**
         *
         */
        fun permutation(x: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "permutation"), args = arrayOf(x))

        fun permutation(x: Long): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "permutation"), args = arrayOf(x))

        fun permutation(x: IntArray): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "permutation"), args = arrayOf(x))


        fun <T : Number> permutation(x: KtNDArray<T>): KtNDArray<T> =
            callFunc(nameMethod = arrayOf("random", "permutation"), args = arrayOf(x))

        // Distributions

        /**
         *
         */
        fun beta(a: Double, b: Double): Double =
            callFunc(nameMethod = arrayOf("random", "beta"), args = arrayOf(a, b), kClass = Double::class)

        fun beta(a: DoubleArray, b: Double): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "beta"), args = arrayOf(a, b))

        fun beta(a: Double, b: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "beta"), args = arrayOf(a, b))

        fun beta(a: DoubleArray, b: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "beta"), args = arrayOf(a, b))

        fun beta(a: Double, b: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "beta"), args = arrayOf(a, b, size))

        fun beta(a: DoubleArray, b: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "beta"), args = arrayOf(a, b, size))

        fun beta(a: Double, b: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "beta"), args = arrayOf(a, b, size))

        fun beta(a: DoubleArray, b: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "beta"), args = arrayOf(a, b, size))

        /**
         *
         */
        fun binomial(n: Int, p: Double): Long =
            callFunc(nameMethod = arrayOf("random", "binomial"), args = arrayOf(n, p), kClass = Long::class)

        fun binomial(n: IntArray, p: Double): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "binomial"), args = arrayOf(n, p))

        fun binomial(n: Int, p: DoubleArray): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "binomial"), args = arrayOf(n, p))

        fun binomial(n: IntArray, p: DoubleArray): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "binomial"), args = arrayOf(n, p))

        fun binomial(n: Int, p: Double, vararg size: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "binomial"), args = arrayOf(n, p, size))

        fun binomial(n: IntArray, p: Double, vararg size: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "binomial"), args = arrayOf(n, p, size))

        fun binomial(n: Int, p: DoubleArray, vararg size: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "binomial"), args = arrayOf(n, p, size))

        fun binomial(n: IntArray, p: DoubleArray, vararg size: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "binomial"), args = arrayOf(n, p, size))

        /**
         *
         */
        fun chisquare(df: Double): Double =
            callFunc(nameMethod = arrayOf("random", "chisquare"), args = arrayOf(df), kClass = Double::class)

        fun chisquare(df: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "chisquare"), args = arrayOf(df))

        fun chisquare(df: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "chisquare"), args = arrayOf(df, size))

        fun chisquare(df: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "chisquare"), args = arrayOf(df, size))

        /**
         *
         */
        fun <T : Number> dirichlet(alpha: Array<T>, vararg size: Int? = emptyArray()): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "dirichlet"), args = arrayOf(alpha, size.ifEmpty { None.none }))

        /**
         *
         */
        fun exponential(scale: Double = 1.0): Double =
            callFunc(nameMethod = arrayOf("random", "exponential"), args = arrayOf(scale), kClass = Double::class)

        fun exponential(scale: Double = 1.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "exponential"), args = arrayOf(scale, size))

        /**
         *
         */
        fun f(dfnum: Double, dfden: Double): Double =
            callFunc(nameMethod = arrayOf("random", "f"), args = arrayOf(dfnum, dfden), kClass = Double::class)

        fun f(dfnum: DoubleArray, dfden: Double): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "f"), args = arrayOf(dfnum, dfden))

        fun f(dfnum: Double, dfden: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "f"), args = arrayOf(dfnum, dfden))

        fun f(dfnum: DoubleArray, dfden: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "f"), args = arrayOf(dfnum, dfden))

        fun f(dfnum: Double, dfden: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "f"), args = arrayOf(dfnum, dfden, size))

        fun f(dfnum: DoubleArray, dfden: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "f"), args = arrayOf(dfnum, dfden, size))

        fun f(dfnum: Double, dfden: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "f"), args = arrayOf(dfnum, dfden, size))

        fun f(dfnum: DoubleArray, dfden: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "f"), args = arrayOf(dfnum, dfden, size))

        /**
         *
         */
        fun gamma(shape: Double, scale: Double = 1.0): Double =
            callFunc(nameMethod = arrayOf("random", "gamma"), args = arrayOf(shape, scale), kClass = Double::class)

        fun gamma(shape: DoubleArray, scale: Double = 1.0): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "gamma"), args = arrayOf(shape, scale))

        fun gamma(shape: Double, scale: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "gamma"), args = arrayOf(shape, scale))

        fun gamma(shape: DoubleArray, scale: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "gamma"), args = arrayOf(shape, scale))

        fun gamma(shape: Double, scale: Double = 1.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "gamma"), args = arrayOf(shape, scale, size))

        fun gamma(shape: DoubleArray, scale: Double = 1.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "gamma"), args = arrayOf(shape, scale, size))

        fun gamma(shape: Double, scale: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "gamma"), args = arrayOf(shape, scale, size))

        fun gamma(shape: DoubleArray, scale: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "gamma"), args = arrayOf(shape, scale, size))

        /**
         *
         */
        fun geometric(p: Double): Long =
            callFunc(nameMethod = arrayOf("random", "geometric"), args = arrayOf(p), kClass = Long::class)

        fun geometric(p: DoubleArray): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "geometric"), args = arrayOf(p))

        fun geometric(p: Double, vararg size: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "geometric"), args = arrayOf(p, size))

        fun geometric(p: DoubleArray, vararg size: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "geometric"), args = arrayOf(p, size))

        /**
         *
         */
        fun gumbel(loc: Double = 0.0, scale: Double = 1.0): Double =
            callFunc(nameMethod = arrayOf("random", "gumbel"), args = arrayOf(loc, scale), kClass = Double::class)

        fun gumbel(loc: DoubleArray, scale: Double = 1.0): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "gumbel"), args = arrayOf(loc, scale))

        fun gumbel(loc: Double = 0.0, scale: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "gumbel"), args = arrayOf(loc, scale))

        fun gumbel(loc: DoubleArray, scale: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "gumbel"), args = arrayOf(loc, scale))

        fun gumbel(loc: Double = 0.0, scale: Double = 1.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "gumbel"), args = arrayOf(loc, scale, size))

        fun gumbel(loc: DoubleArray, scale: Double = 1.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "gumbel"), args = arrayOf(loc, scale, size))

        fun gumbel(loc: Double = 0.0, scale: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "gumbel"), args = arrayOf(loc, scale, size))

        fun gumbel(loc: DoubleArray, scale: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "gumbel"), args = arrayOf(loc, scale, size))

        /**
         *
         */
        fun hypergeometric(ngood: Int, nbad: Int, nsample: Int): Double =
            callFunc(
                nameMethod = arrayOf("random", "hypergeometric"),
                args = arrayOf(ngood, nbad, nsample),
                kClass = Double::class
            )

        fun hypergeometric(ngood: IntArray, nbad: Int, nsample: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "hypergeometric"), args = arrayOf(ngood, nbad, nsample))

        fun hypergeometric(ngood: Int, nbad: IntArray, nsample: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "hypergeometric"), args = arrayOf(ngood, nbad, nsample))

        fun hypergeometric(ngood: Int, nbad: Int, nsample: IntArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "hypergeometric"), args = arrayOf(ngood, nbad, nsample))

        fun hypergeometric(ngood: IntArray, nbad: IntArray, nsample: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "hypergeometric"), args = arrayOf(ngood, nbad, nsample))

        fun hypergeometric(ngood: Int, nbad: IntArray, nsample: IntArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "hypergeometric"), args = arrayOf(ngood, nbad, nsample))

        fun hypergeometric(ngood: IntArray, nbad: Int, nsample: IntArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "hypergeometric"), args = arrayOf(ngood, nbad, nsample))

        fun hypergeometric(ngood: IntArray, nbad: IntArray, nsample: IntArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "hypergeometric"), args = arrayOf(ngood, nbad, nsample))

        fun hypergeometric(ngood: Int, nbad: Int, nsample: Int, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "hypergeometric"), args = arrayOf(ngood, nbad, nsample, size))

        fun hypergeometric(ngood: IntArray, nbad: Int, nsample: Int, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "hypergeometric"), args = arrayOf(ngood, nbad, nsample, size))

        fun hypergeometric(ngood: Int, nbad: IntArray, nsample: Int, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "hypergeometric"), args = arrayOf(ngood, nbad, nsample, size))

        fun hypergeometric(ngood: Int, nbad: Int, nsample: IntArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "hypergeometric"), args = arrayOf(ngood, nbad, nsample, size))

        fun hypergeometric(ngood: IntArray, nbad: IntArray, nsample: Int, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "hypergeometric"), args = arrayOf(ngood, nbad, nsample, size))

        fun hypergeometric(ngood: Int, nbad: IntArray, nsample: IntArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "hypergeometric"), args = arrayOf(ngood, nbad, nsample, size))

        fun hypergeometric(ngood: IntArray, nbad: Int, nsample: IntArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "hypergeometric"), args = arrayOf(ngood, nbad, nsample, size))

        fun hypergeometric(ngood: IntArray, nbad: IntArray, nsample: IntArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "hypergeometric"), args = arrayOf(ngood, nbad, nsample, size))

        /**
         *
         */
        fun laplace(loc: Double = 0.0, scale: Double = 1.0): Double =
            callFunc(nameMethod = arrayOf("random", "laplace"), args = arrayOf(loc, scale), kClass = Double::class)

        fun laplace(loc: DoubleArray, scale: Double = 1.0): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "laplace"), args = arrayOf(loc, scale))

        fun laplace(loc: Double = 0.0, scale: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "laplace"), args = arrayOf(loc, scale))

        fun laplace(loc: DoubleArray, scale: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "laplace"), args = arrayOf(loc, scale))

        fun laplace(loc: Double = 0.0, scale: Double = 1.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "laplace"), args = arrayOf(loc, scale, size))

        fun laplace(loc: DoubleArray, scale: Double = 1.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "laplace"), args = arrayOf(loc, scale, size))

        fun laplace(loc: Double = 0.0, scale: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "laplace"), args = arrayOf(loc, scale, size))

        fun laplace(loc: DoubleArray, scale: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "laplace"), args = arrayOf(loc, scale, size))

        /**
         *
         */
        fun logistic(loc: Double = 0.0, scale: Double = 1.0): Double =
            callFunc(nameMethod = arrayOf("random", "logistic"), args = arrayOf(loc, scale), kClass = Double::class)

        fun logistic(loc: DoubleArray, scale: Double = 1.0): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "logistic"), args = arrayOf(loc, scale))

        fun logistic(loc: Double = 0.0, scale: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "logistic"), args = arrayOf(loc, scale))

        fun logistic(loc: DoubleArray, scale: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "logistic"), args = arrayOf(loc, scale))

        fun logistic(loc: Double = 0.0, scale: Double = 1.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "logistic"), args = arrayOf(loc, scale, size))

        fun logistic(loc: DoubleArray, scale: Double = 1.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "logistic"), args = arrayOf(loc, scale, size))

        fun logistic(loc: Double = 0.0, scale: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "logistic"), args = arrayOf(loc, scale, size))

        fun logistic(loc: DoubleArray, scale: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "logistic"), args = arrayOf(loc, scale, size))

        /**
         *
         */
        fun lognormal(mean: Double = 0.0, sigma: Double = 1.0): Double =
            callFunc(nameMethod = arrayOf("random", "lognormal"), args = arrayOf(mean, sigma), kClass = Double::class)

        fun lognormal(mean: DoubleArray, sigma: Double = 1.0): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "lognormal"), args = arrayOf(mean, sigma))

        fun lognormal(mean: Double = 0.0, sigma: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "lognormal"), args = arrayOf(mean, sigma))

        fun lognormal(mean: DoubleArray, sigma: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "lognormal"), args = arrayOf(mean, sigma))

        fun lognormal(mean: Double = 0.0, sigma: Double = 1.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "lognormal"), args = arrayOf(mean, sigma, size))

        fun lognormal(mean: DoubleArray, sigma: Double = 1.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "lognormal"), args = arrayOf(mean, sigma, size))

        fun lognormal(mean: Double = 0.0, sigma: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "lognormal"), args = arrayOf(mean, sigma, size))

        fun lognormal(mean: DoubleArray, sigma: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "lognormal"), args = arrayOf(mean, sigma, size))

        /**
         *
         */
        fun logseries(p: Double): Long =
            callFunc(nameMethod = arrayOf("random", "logseries"), args = arrayOf(p), kClass = Long::class)

        fun logseries(p: DoubleArray): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "logseries"), args = arrayOf(p))

        fun logseries(p: Double, vararg size: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "logseries"), args = arrayOf(p, size))

        fun logseries(p: DoubleArray, vararg size: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "logseries"), args = arrayOf(p, size))

        /**
         *
         */
        fun multinomial(n: Int, pvals: DoubleArray, vararg size: Int? = emptyArray()): KtNDArray<Long> =
            callFunc(
                nameMethod = arrayOf("random", "multinomial"),
                args = arrayOf(n, pvals, size.ifEmpty { None.none })
            )

        /**
         *
         */
        fun <T : Number, E : Number> multivariateNormal(
            mean: KtNDArray<T>,
            cov: KtNDArray<E>,
            size: IntArray? = null,
            checkValid: String? = null,
            tol: Double? = null
        ): KtNDArray<Double> =
            callFunc(
                nameMethod = arrayOf("random", "multivariate_normal"),
                args = arrayOf(mean, cov, size ?: None.none, checkValid ?: None.none, tol ?: None.none)
            )

        /**
         *
         */
        fun negativeBinomial(n: Int, p: Double): Long =
            callFunc(nameMethod = arrayOf("random", "negative_binomial"), args = arrayOf(n, p), kClass = Long::class)

        fun negativeBinomial(n: IntArray, p: Double): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "negative_binomial"), args = arrayOf(n, p))

        fun negativeBinomial(n: Int, p: DoubleArray): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "negative_binomial"), args = arrayOf(n, p))

        fun negativeBinomial(n: IntArray, p: DoubleArray): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "negative_binomial"), args = arrayOf(n, p))

        fun negativeBinomial(n: Int, p: Double, vararg size: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "negative_binomial"), args = arrayOf(n, p, size))

        fun negativeBinomial(n: IntArray, p: Double, vararg size: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "negative_binomial"), args = arrayOf(n, p, size))

        fun negativeBinomial(n: Int, p: DoubleArray, vararg size: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "negative_binomial"), args = arrayOf(n, p, size))

        fun negativeBinomial(n: IntArray, p: DoubleArray, vararg size: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "negative_binomial"), args = arrayOf(n, p, size))

        /**
         *
         */
        fun noncentralChisquare(df: Double, nonc: Double): Double =
            callFunc(
                nameMethod = arrayOf("random", "noncentral_chisquare"),
                args = arrayOf(df, nonc),
                kClass = Double::class
            )

        fun noncentralChisquare(df: DoubleArray, nonc: Double): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_chisquare"), args = arrayOf(df, nonc))

        fun noncentralChisquare(df: Double, nonc: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_chisquare"), args = arrayOf(df, nonc))

        fun noncentralChisquare(df: DoubleArray, nonc: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_chisquare"), args = arrayOf(df, nonc))

        fun noncentralChisquare(df: Double, nonc: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_chisquare"), args = arrayOf(df, nonc, size))

        fun noncentralChisquare(df: DoubleArray, nonc: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_chisquare"), args = arrayOf(df, nonc, size))

        fun noncentralChisquare(df: Double, nonc: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_chisquare"), args = arrayOf(df, nonc, size))

        fun noncentralChisquare(df: DoubleArray, nonc: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_chisquare"), args = arrayOf(df, nonc, size))

        /**
         *
         */
        fun noncentralF(dfnum: Double, dfden: Double, nonc: Double): Double =
            callFunc(
                nameMethod = arrayOf("random", "noncentral_f"),
                args = arrayOf(dfnum, dfden, nonc),
                kClass = Double::class
            )

        fun noncentralF(dfnum: DoubleArray, dfden: Double, nonc: Double): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_f"), args = arrayOf(dfnum, dfden, nonc))

        fun noncentralF(dfnum: Double, dfden: DoubleArray, nonc: Double): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_f"), args = arrayOf(dfnum, dfden, nonc))

        fun noncentralF(dfnum: Double, dfden: Double, nonc: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_f"), args = arrayOf(dfnum, dfden, nonc))

        fun noncentralF(dfnum: DoubleArray, dfden: DoubleArray, nonc: Double): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_f"), args = arrayOf(dfnum, dfden, nonc))

        fun noncentralF(dfnum: Double, dfden: DoubleArray, nonc: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_f"), args = arrayOf(dfnum, dfden, nonc))

        fun noncentralF(dfnum: DoubleArray, dfden: Double, nonc: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_f"), args = arrayOf(dfnum, dfden, nonc))

        fun noncentralF(dfnum: DoubleArray, dfden: DoubleArray, nonc: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_f"), args = arrayOf(dfnum, dfden, nonc))

        fun noncentralF(dfnum: Double, dfden: Double, nonc: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_f"), args = arrayOf(dfnum, dfden, nonc, size))

        fun noncentralF(dfnum: DoubleArray, dfden: Double, nonc: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_f"), args = arrayOf(dfnum, dfden, nonc, size))

        fun noncentralF(dfnum: Double, dfden: DoubleArray, nonc: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_f"), args = arrayOf(dfnum, dfden, nonc, size))

        fun noncentralF(dfnum: Double, dfden: Double, nonc: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_f"), args = arrayOf(dfnum, dfden, nonc, size))

        fun noncentralF(dfnum: DoubleArray, dfden: DoubleArray, nonc: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_f"), args = arrayOf(dfnum, dfden, nonc, size))

        fun noncentralF(dfnum: Double, dfden: DoubleArray, nonc: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_f"), args = arrayOf(dfnum, dfden, nonc, size))

        fun noncentralF(dfnum: DoubleArray, dfden: Double, nonc: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_f"), args = arrayOf(dfnum, dfden, nonc, size))

        fun noncentralF(
            dfnum: DoubleArray,
            dfden: DoubleArray,
            nonc: DoubleArray,
            vararg size: Int
        ): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "noncentral_f"), args = arrayOf(dfnum, dfden, nonc, size))


        /**
         *
         */
        fun normal(loc: Double = 0.0, scale: Double = 0.0): Double =
            callFunc(nameMethod = arrayOf("random", "normal"), args = arrayOf(loc, scale), kClass = Double::class)

        fun normal(loc: DoubleArray, scale: Double = 0.0): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "normal"), args = arrayOf(loc, scale))

        fun normal(loc: Double = 0.0, scale: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "normal"), args = arrayOf(loc, scale))

        fun normal(loc: DoubleArray, scale: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "normal"), args = arrayOf(loc, scale))

        fun normal(loc: Double = 0.0, scale: Double = 0.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "normal"), args = arrayOf(loc, scale, size))

        fun normal(loc: DoubleArray, scale: Double = 0.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "normal"), args = arrayOf(loc, scale, size))

        fun normal(loc: Double = 0.0, scale: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "normal"), args = arrayOf(loc, scale, size))

        fun normal(loc: DoubleArray, scale: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "normal"), args = arrayOf(loc, scale, size))

        /**
         *
         */
        fun pareto(a: Double): Double =
            callFunc(nameMethod = arrayOf("random", "pareto"), args = arrayOf(a), kClass = Double::class)

        fun pareto(a: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "pareto"), args = arrayOf(a))

        fun pareto(a: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "pareto"), args = arrayOf(a, size))

        fun pareto(a: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "pareto"), args = arrayOf(a, size))

        /**
         *
         */
        fun poisson(lam: Double = 1.0): Double =
            callFunc(nameMethod = arrayOf("random", "poisson"), args = arrayOf(lam), kClass = Double::class)

        fun poisson(lam: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "poisson"), args = arrayOf(lam))

        fun poisson(lam: Double = 1.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "poisson"), args = arrayOf(lam, size))

        fun poisson(lam: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "poisson"), args = arrayOf(lam, size))

        /**
         *
         */
        fun power(a: Double): Double =
            callFunc(nameMethod = arrayOf("random", "power"), args = arrayOf(a), kClass = Double::class)

        fun power(a: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "power"), args = arrayOf(a))

        fun power(a: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "power"), args = arrayOf(a, size))

        fun power(a: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "power"), args = arrayOf(a, size))

        /**
         *
         */
        fun rayleigh(scale: Double = 1.0): Double =
            callFunc(nameMethod = arrayOf("random", "rayleigh"), args = arrayOf(scale), kClass = Double::class)

        fun rayleigh(scale: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "rayleigh"), args = arrayOf(scale))

        fun rayleigh(scale: Double = 1.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "rayleigh"), args = arrayOf(scale, size))

        fun rayleigh(scale: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "rayleigh"), args = arrayOf(scale, size))

        /**
         *
         */
        fun standartCauchy(): Double =
            callFunc(nameMethod = arrayOf("random", "standard_cauchy"), kClass = Double::class)

        fun standartCauchy(vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "standard_cauchy"), args = arrayOf(size))

        /**
         *
         */
        fun standardExponential(): Double =
            callFunc(nameMethod = arrayOf("random", "standard_exponential"), kClass = Double::class)

        fun standardExponential(vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "standard_exponential"), args = arrayOf(size))

        /**
         *
         */
        fun standardGamma(shape: Double): Double =
            callFunc(nameMethod = arrayOf("random", "standard_gamma"), args = arrayOf(shape), kClass = Double::class)

        fun standardGamma(shape: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "standard_gamma"), args = arrayOf(shape))

        fun standardGamma(shape: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "standard_gamma"), args = arrayOf(shape, size))

        fun standardGamma(shape: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "standard_gamma"), args = arrayOf(shape, size))

        /**
         *
         */
        fun standardNormal(): Double =
            callFunc(nameMethod = arrayOf("random", "standard_normal"), kClass = Double::class)

        fun standardNormal(vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "standard_normal"), args = arrayOf(size))

        /**
         *
         */
        fun standardT(df: Double): Double =
            callFunc(nameMethod = arrayOf("random", "standard_t"), args = arrayOf(df), kClass = Double::class)

        fun standardT(df: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "standard_t"), args = arrayOf(df))

        fun standardT(df: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "standard_t"), args = arrayOf(df, size))

        fun standardT(df: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "standard_t"), args = arrayOf(df, size))

        /**
         *
         */
        fun triangular(left: Double, mode: Double, right: Double): Double =
            callFunc(
                nameMethod = arrayOf("random", "triangular"),
                args = arrayOf(left, mode, right),
                kClass = Double::class
            )

        fun triangular(left: DoubleArray, mode: Double, right: Double): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "triangular"), args = arrayOf(left, mode, right))

        fun triangular(left: Double, mode: DoubleArray, right: Double): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "triangular"), args = arrayOf(left, mode, right))

        fun triangular(left: Double, mode: Double, right: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "triangular"), args = arrayOf(left, mode, right))

        fun triangular(left: DoubleArray, mode: DoubleArray, right: Double): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "triangular"), args = arrayOf(left, mode, right))

        fun triangular(left: Double, mode: DoubleArray, right: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "triangular"), args = arrayOf(left, mode, right))

        fun triangular(left: DoubleArray, mode: Double, right: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "triangular"), args = arrayOf(left, mode, right))

        fun triangular(left: DoubleArray, mode: DoubleArray, right: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "triangular"), args = arrayOf(left, mode, right))

        fun triangular(left: Double, mode: Double, right: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "triangular"), args = arrayOf(left, mode, right, size))

        fun triangular(left: DoubleArray, mode: Double, right: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "triangular"), args = arrayOf(left, mode, right, size))

        fun triangular(left: Double, mode: DoubleArray, right: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "triangular"), args = arrayOf(left, mode, right, size))

        fun triangular(left: Double, mode: Double, right: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "triangular"), args = arrayOf(left, mode, right, size))

        fun triangular(left: DoubleArray, mode: DoubleArray, right: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "triangular"), args = arrayOf(left, mode, right, size))

        fun triangular(left: Double, mode: DoubleArray, right: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "triangular"), args = arrayOf(left, mode, right, size))

        fun triangular(left: DoubleArray, mode: Double, right: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "triangular"), args = arrayOf(left, mode, right, size))

        fun triangular(left: DoubleArray, mode: DoubleArray, right: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "triangular"), args = arrayOf(left, mode, right, size))

        /**
         *
         */
        fun uniform(low: Double = 0.0, high: Double = 1.0): Double =
            callFunc(nameMethod = arrayOf("random", "uniform"), args = arrayOf(low, high), kClass = Double::class)

        fun uniform(low: DoubleArray, high: Double = 1.0): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "uniform"), args = arrayOf(low, high))

        fun uniform(low: Double = 0.0, high: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "uniform"), args = arrayOf(low, high))

        fun uniform(low: DoubleArray, high: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "uniform"), args = arrayOf(low, high))

        fun uniform(low: Double = 0.0, high: Double = 1.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "uniform"), args = arrayOf(low, high, size))

        fun uniform(low: DoubleArray, high: Double = 1.0, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "uniform"), args = arrayOf(low, high, size))

        fun uniform(low: Double = 0.0, high: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "uniform"), args = arrayOf(low, high, size))

        fun uniform(low: DoubleArray, high: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "uniform"), args = arrayOf(low, high, size))

        /**
         *
         */
        fun vonmises(mu: Double, kappa: Double): Double =
            callFunc(nameMethod = arrayOf("random", "vonmises"), args = arrayOf(mu, kappa), kClass = Double::class)

        fun vonmises(mu: DoubleArray, kappa: Double): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "vonmises"), args = arrayOf(mu, kappa))

        fun vonmises(mu: Double, kappa: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "vonmises"), args = arrayOf(mu, kappa))

        fun vonmises(mu: DoubleArray, kappa: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "vonmises"), args = arrayOf(mu, kappa))

        fun vonmises(mu: Double, kappa: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "vonmises"), args = arrayOf(mu, kappa, size))

        fun vonmises(mu: DoubleArray, kappa: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "vonmises"), args = arrayOf(mu, kappa, size))

        fun vonmises(mu: Double, kappa: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "vonmises"), args = arrayOf(mu, kappa, size))

        fun vonmises(mu: DoubleArray, kappa: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "vonmises"), args = arrayOf(mu, kappa, size))


        /**
         *
         */
        fun wald(mean: Double, scale: Double): Double =
            callFunc(nameMethod = arrayOf("random", "wald"), args = arrayOf(mean, scale), kClass = Double::class)

        fun wald(mean: DoubleArray, scale: Double): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "wald"), args = arrayOf(mean, scale))

        fun wald(mean: Double, scale: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "wald"), args = arrayOf(mean, scale))

        fun wald(mean: DoubleArray, scale: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "wald"), args = arrayOf(mean, scale))

        fun wald(mean: Double, scale: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "wald"), args = arrayOf(mean, scale, size))

        fun wald(mean: DoubleArray, scale: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "wald"), args = arrayOf(mean, scale, size))

        fun wald(mean: Double, scale: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "wald"), args = arrayOf(mean, scale, size))

        fun wald(mean: DoubleArray, scale: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "wald"), args = arrayOf(mean, scale, size))

        /**
         *
         */
        fun weibull(a: Double): Double =
            callFunc(nameMethod = arrayOf("random", "weibull"), args = arrayOf(a), kClass = Double::class)

        fun weibull(a: DoubleArray): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "weibull"), args = arrayOf(a))

        fun weibull(a: Double, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "weibull"), args = arrayOf(a, size))

        fun weibull(a: DoubleArray, vararg size: Int): KtNDArray<Double> =
            callFunc(nameMethod = arrayOf("random", "weibull"), args = arrayOf(a, size))

        /**
         *
         */
        fun zipf(a: Double): Long =
            callFunc(nameMethod = arrayOf("random", "zipf"), args = arrayOf(a), kClass = Long::class)

        fun zipf(a: DoubleArray): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "zipf"), args = arrayOf(a))

        fun zipf(a: Double, vararg size: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "zipf"), args = arrayOf(a, size))

        fun zipf(a: DoubleArray, vararg size: Int): KtNDArray<Long> =
            callFunc(nameMethod = arrayOf("random", "zipf"), args = arrayOf(a, size))

    }
}
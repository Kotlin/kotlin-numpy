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

package org.jetbrains.numkt

import org.jetbrains.numkt.core.KtNDArray
import org.jetbrains.numkt.core.None.Companion.none
import java.io.File

/** Return a new array of given shape and T type, without initializing entries. */
inline fun <reified T : Any> empty(vararg shape: Int, order: Order = Order.C): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("empty"), args = arrayOf(shape, T::class.javaObjectType, order))

/** Return a new array with the same shape and T type as a given array. */
inline fun <reified T : Any> emptyLike(
    prototype: KtNDArray<T>,
    order: Order = Order.K,
    subok: Boolean = true,
    shape: IntArray? = null
): KtNDArray<T> =
    callFunc(
        nameMethod = arrayOf("empty_like"),
        args = arrayOf(prototype, T::class.javaObjectType),
        order = order,
        subok = subok,
        shape = shape
    )


/** Return a 2-D array with ones on the diagonal and zeros elsewhere. */
inline fun <reified T : Any> eye(n: Int, m: Int? = null, k: Int = 0, order: Order = Order.C): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("eye"), args = arrayOf(n, m ?: none, k, T::class.javaObjectType), order = order)

/** Return the identity array. */
inline fun <reified T : Any> identity(n: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("identity"), args = arrayOf(n, T::class.javaObjectType))

/** Return a new array of given shape and T type, filled with ones. */
inline fun <reified T : Any> ones(vararg shape: Int, order: Order = Order.C): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("ones"), args = arrayOf(shape, T::class.javaObjectType), order = order)

/** Return an array of ones with the same shape and T type as a given array. */
inline fun <reified T : Any> onesLike(
    prototype: KtNDArray<T>,
    order: Order = Order.K,
    subok: Boolean = true,
    shape: IntArray? = null
): KtNDArray<T> =
    callFunc(
        nameMethod = arrayOf("ones_like"), args = arrayOf(prototype, T::class.javaObjectType),
        order = order,
        subok = subok,
        shape = shape
    )

/** Return a new array of given shape and T type, filled with zeros. */
inline fun <reified T : Any> zeros(vararg shape: Int, order: Order = Order.C): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("zeros"), args = arrayOf(shape, T::class.javaObjectType), order = order)

/** Return an array of zeros with the same shape and T type as a given array. */
inline fun <reified T : Any> zerosLike(
    prototype: KtNDArray<T>,
    order: Order = Order.K,
    subok: Boolean = true,
    shape: IntArray? = null
): KtNDArray<T> =
    callFunc(
        nameMethod = arrayOf("zeros_like"), args = arrayOf(prototype, T::class.javaObjectType),
        order = order,
        subok = subok,
        shape = shape
    )

/** Return a new array of given shape and T type, filled with fill_value. */
inline fun <reified T : Any> full(shape: IntArray, fillValue: T, order: Order = Order.C): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("full"), args = arrayOf(shape, fillValue, T::class.javaObjectType), order = order)

/** Return a full array with the same shape and T type as a given array. */
inline fun <reified T : Any> fullLike(
    prototype: KtNDArray<T>, fillValue: T,
    order: Order = Order.K,
    subok: Boolean = true,
    shape: IntArray? = null
): KtNDArray<T> =
    callFunc(
        nameMethod = arrayOf("full_like"), args = arrayOf(prototype, fillValue, T::class.javaObjectType),
        order = order,
        subok = subok,
        shape = shape
    )

inline fun <reified T : Any> array(
    arr: Array<T>,
    order: Order = Order.K,
    subok: Boolean = false,
    ndmin: Int = 0
): KtNDArray<T> =
    callFunc(
        nameMethod = arrayOf("array"),
        args = arrayOf(arr, T::class.javaObjectType),
        order = order,
        subok = subok,
        ndmin = ndmin
    )

// compromise
// always copy
inline fun <reified T : Any> array(
    arr: List<Any>,
    order: Order = Order.K,
    subok: Boolean = false,
    ndmin: Int = 0
): KtNDArray<T> =
    callFunc(
        nameMethod = arrayOf("array"),
        args = arrayOf(arr, T::class.javaObjectType),
        order = order,
        subok = subok,
        ndmin = ndmin
    )

fun <T : Any> copy(a: KtNDArray<T>, order: Order = Order.K): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("copy"), args = arrayOf(a), order = order)


inline fun <reified T : Any> fromfile(file: File, count: Int = -1, sep: String = "", offset: Int = 0): KtNDArray<T> =
    callFunc(
        nameMethod = arrayOf("fromfile"),
        args = arrayOf(file.absolutePath, T::class.javaObjectType, count, sep, offset)
    )

inline fun <reified T : Any> fromfile(file: String, count: Int = -1, sep: String = "", offset: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("fromfile"), args = arrayOf(file, T::class.javaObjectType, count, sep, offset))


inline fun <reified T : Any> fromstring(string: String, count: Int = -1, sep: String): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("fromstring"), args = arrayOf(string, T::class.javaObjectType, count, sep))


// todo
inline fun <reified T : Any> loadtxt(
    fname: String,
    comments: String = "#",
    delimiter: String? = null,
    converters: Map<Any, Any>? = null,
    skiprows: Int = 0,
    usecols: IntArray? = null,
    unpack: Boolean = false,
    ndmin: Int = 0,
    encoding: String = "bytes",
    max_rows: Int? = null
): KtNDArray<T> =
    callFunc(
        nameMethod = arrayOf("loadtxt"),
        args = arrayOf(fname, T::class.javaObjectType, comments, delimiter ?: none)
    )

/**
 *
 */
inline fun <reified T : Number> arange(start: Number, stop: Number, step: Number): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("arange"), args = arrayOf(start, stop, step, T::class.javaObjectType))

/**
 *
 */
inline fun <reified T : Number> arange(range: T): KtNDArray<T> =
    arange(start = 0, stop = range, step = 1)


/**
 *
 */
inline fun <reified T : Number> linspace(
    start: Number,
    stop: Number,
    num: Int = 50,
    endpoint: Boolean = true,
    retstep: Boolean = false,
    axis: Int = 0
): KtNDArray<T> = callFunc(
    nameMethod = arrayOf("linspace"), args = arrayOf(start, stop, num, endpoint, retstep, T::class.javaObjectType, axis)
)

inline fun <reified T : Number> logspace(
    start: Number,
    stop: Number,
    num: Int = 50,
    endpoint: Boolean = true,
    base: Double = 10.0,
    axis: Int = 0
): KtNDArray<T> =
    callFunc(
        nameMethod = arrayOf("logspace"),
        args = arrayOf(start, stop, num, endpoint, base, T::class.javaObjectType, axis)
    )

inline fun <reified T : Number> geomspace(
    start: Number,
    stop: Number,
    num: Int = 50,
    endpoint: Boolean = true,
    axis: Int = 0
): KtNDArray<T> =
    callFunc(
        nameMethod = arrayOf("geomspace"),
        args = arrayOf(start, stop, num, endpoint, T::class.javaObjectType, axis)
    )


fun <T : Number> diag(vararg v: T, k: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("diag"), args = arrayOf(v, k))

fun <T : Number> diag(v: KtNDArray<T>, k: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("diag"), args = arrayOf(v, k))

fun <T : Number> diagflat(v: KtNDArray<T>, k: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("diagflat"), args = arrayOf(v, k))

fun <T : Number> diagflat(v: List<Any>, k: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("diagflat"), args = arrayOf(v, k))

inline fun <reified T : Any> tri(n: Int, m: Int? = null, k: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("tri"), args = arrayOf(n, m ?: none, k, T::class.javaObjectType))

@JvmName("trilByte")
fun tril(m: List<List<Byte>>, k: Int = 0): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("tril"), args = arrayOf(m, k))

@JvmName("trilShort")
fun tril(m: List<List<Short>>, k: Int = 0): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("tril"), args = arrayOf(m, k))

@JvmName("trilInt")
fun tril(m: List<List<Int>>, k: Int = 0): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("tril"), args = arrayOf(m, k))

@JvmName("trilLong")
fun tril(m: List<List<Long>>, k: Int = 0): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("tril"), args = arrayOf(m, k))

@JvmName("trilFloat")
fun tril(m: List<List<Float>>, k: Int = 0): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("tril"), args = arrayOf(m, k))

@JvmName("trilDouble")
fun tril(m: List<List<Double>>, k: Int = 0): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("tril"), args = arrayOf(m, k))

@JvmName("triuByte")
fun triu(m: List<List<Byte>>, k: Int = 0): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("triu"), args = arrayOf(m, k))

@JvmName("triuShort")
fun triu(m: List<List<Short>>, k: Int = 0): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("triu"), args = arrayOf(m, k))

@JvmName("triuInt")
fun triu(m: List<List<Int>>, k: Int = 0): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("triu"), args = arrayOf(m, k))

@JvmName("triuLong")
fun triu(m: List<List<Long>>, k: Int = 0): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("triu"), args = arrayOf(m, k))

@JvmName("triuFloat")
fun triu(m: List<List<Float>>, k: Int = 0): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("triu"), args = arrayOf(m, k))

@JvmName("triuDouble")
fun triu(m: List<List<Double>>, k: Int = 0): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("triu"), args = arrayOf(m, k))

fun <T : Number> vander(x: KtNDArray<T>, n: Int? = null, increasing: Boolean = false): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("vander"), args = arrayOf(x, n ?: none, increasing))

fun <T : Number> vander(x: Array<T>, n: Int? = null, increasing: Boolean = false): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("vander"), args = arrayOf(x, n ?: none, increasing))

fun <T : Number> vander(x: List<T>, n: Int? = null, increasing: Boolean = false): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("vander"), args = arrayOf(x, n ?: none, increasing))

// this is matrix, but it is no longer recommended to use this class.
@JvmName("mat1D")
inline fun <reified T : Any> mat(data: List<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("mat"), args = arrayOf(data, T::class.javaObjectType))

@JvmName("mat2D")
inline fun <reified T : Any> mat(data: List<List<T>>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("mat"), args = arrayOf(data, T::class.javaObjectType))

inline fun <reified T : Any> mat(data: String): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("mat"), args = arrayOf(data, T::class.javaObjectType))

@JvmName("bmat1D")
inline fun <reified T : Any> bmat(data: List<KtNDArray<T>>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("bmat"), args = arrayOf(data, T::class.javaObjectType))

@JvmName("bmat2D")
inline fun <reified T : Any> bmat(data: List<List<KtNDArray<T>>>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("bmat"), args = arrayOf(data, T::class.javaObjectType))

fun <T: Number> meshgrid(vararg xi: KtNDArray<T>): List<KtNDArray<T>> =
    callFunc(nameMethod = arrayOf("meshgrid"), args = arrayOf(*xi), kClass = List::class) as List<KtNDArray<T>>

inline fun <reified T : Any> type(l: KtNDArray<T>) = T::class.java
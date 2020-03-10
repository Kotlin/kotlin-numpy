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

/**
 * Return a new array of given shape and T type, without initializing entries.
 *
 * @param shape of the empty array.
 * @param order see [Order].
 * @return [KtNDArray] of uninitialized data of the given [shape], [T], [order].
 */
inline fun <reified T : Any> empty(vararg shape: Int, order: Order = Order.C): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("empty"), args = arrayOf(shape, T::class.javaObjectType, order))

/**
 * Return a new array with the same shape and T type as a given array.
 *
 * @param prototype the shape and type of prototype define same attributes of the returned array.
 * @param order see [Order].
 * @param subok if *true*, then sub-classes will be passed-through,
 * otherwise the returned array will be forced to be a base-class array (default).
 * @param shape overrides the shape of the result.
 * @return [KtNDArray] of uninitialized data with the same shape and type as prototype.
 */
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


/**
 * Return a 2-D array with ones on the diagonal and zeros elsewhere.
 *
 * @param n number of rows in the output.
 * @param m number of columns in the output. If null, defaults to [n].
 * @param k index of the diagonal.
 * @param order see [Order]
 * @return [KtNDArray] where all elements are equal to zero, except for the k-th diagonal,
 * whose values are equal to one.
 * @see identity
 * @see diag
 */
inline fun <reified T : Any> eye(n: Int, m: Int? = null, k: Int = 0, order: Order = Order.C): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("eye"), args = arrayOf(n, m ?: none, k, T::class.javaObjectType), order = order)

/**
 * Return the identity array.
 *
 * @param n number of rows and columns in *n*x*n* output.
 * @return 2-D [KtNDArray] *n*x*n* with its main diagonal set to one, and all other elements 0.
 */
inline fun <reified T : Any> identity(n: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("identity"), args = arrayOf(n, T::class.javaObjectType))

/**
 * Return a new array of given shape and T type, filled with ones.
 *
 * @param shape of the new array.
 * @param order see [Order]
 * @return [KtNDArray] of ones with the given [shape], type [T] and [order].
 * @see onesLike
 * @see empty
 * @see zeros
 * @see full
 */
inline fun <reified T : Any> ones(vararg shape: Int, order: Order = Order.C): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("ones"), args = arrayOf(shape, T::class.javaObjectType), order = order)

/**
 * Return an array of ones with the same shape and T type as a given array.
 *
 * @param prototype the shape and type of prototype define same attributes of the returned array.
 * @param order see [Order]
 * @param subok if *true*, then sub-classes will be passed-through,
 * otherwise the returned array will be forced to be a base-class array (default).
 * @param shape overrides the shape of the result.
 * @return [KtNDArray] of ones with the same shape and type as prototype.
 * @see emptyLike
 * @see zerosLike
 * @see fullLike
 * @see ones
 */
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

/**
 * Return a new array of given shape and T type, filled with zeros.
 *
 * @param shape shape of the new array.
 * @param order see [Order]
 * @return Array of zeros with the given [shape], type [T] and [order].
 * @see zerosLike
 * @see empty
 * @see ones
 * @see full
 */
inline fun <reified T : Any> zeros(vararg shape: Int, order: Order = Order.C): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("zeros"), args = arrayOf(shape, T::class.javaObjectType), order = order)

/**
 * Return an array of zeros with the same shape and T type as a given array.
 *
 * @param prototype the shape and type of prototype define same attributes of the returned array.
 * @param order see [Order]
 * @param subok if *true*, then sub-classes will be passed-through,
 * otherwise the returned array will be forced to be a base-class array (default).
 * @param shape overrides the shape of the result.
 * @return Array of zeros with the same shape and type as [prototype].
 * @see emptyLike
 * @see onesLike
 * @see fullLike
 * @see zeros
 */
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

/**
 * Return a new array of given shape and T type, filled with fill_value.
 *
 * @param shape of the new array.
 * @param order see [Order]
 * @return [KtNDArray] of [fillValue] the given [shape], [T] and [order].
 * @see fullLike
 * @see empty
 * @see ones
 * @see zeros
 */
inline fun <reified T : Any> full(shape: IntArray, fillValue: T, order: Order = Order.C): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("full"), args = arrayOf(shape, fillValue, T::class.javaObjectType), order = order)

/**
 * Return a full array with the same shape and T type as a given array.
 *
 * @param prototype the shape and type of prototype define same attributes of the returned array.
 * @param fillValue fill value.
 * @param order see [Order]
 * @return [KtNDArray] of [fillValue] with the same [shape] and type [T] as [prototype].
 * @see emptyLike
 * @see onesLike
 * @see zerosLike
 * @see full
 */
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

/**
 *  Create an flattened array.
 *
 *  @param arr any [Array].
 *  @param order specify the memory layout of the array.
 *  @param subok if *true*, then sub-classes will be passed-through,
 *  otherwise the returned array will be forced to be a base-class array (default).
 *  @param ndmin specifies the minimum number of dimensions that the resulting array should have.
 *  @return [KtNDArray]
 *  @see emptyLike
 *  @see onesLike
 *  @see zerosLike
 *  @see fullLike
 *  @see empty
 *  @see ones
 *  @see zeros
 *  @see full
 */
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

/**
 * Create an array.
 * Dimension is set by nesting lists.
 * > Note that the [List] type is [Any]; use the same type everywhere.
 *
 * @param arr list of any.
 * @param order specify the memory layout of the array.
 * @param subok if *true*, then sub-classes will be passed-through,
 * otherwise the returned array will be forced to be a base-class array (default).
 * @param ndmin specifies the minimum number of dimensions that the resulting array should have.
 * @return [KtNDArray]
 * @see emptyLike
 * @see onesLike
 * @see zerosLike
 * @see fullLike
 * @see empty
 * @see ones
 * @see zeros
 * @see full
 */
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

/**
 * Return an array copy of the given object.
 *
 * @param a input data.
 * @param order see [Order]
 * @return Array interpretation of [a].
 */
fun <T : Any> copy(a: KtNDArray<T>, order: Order = Order.K): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("copy"), args = arrayOf(a), order = order)


/**
 * Construct an array from data in a text or binary file.
 *
 * @param file open file object.
 * @param count number of items to read. -1 means all items.
 * @param sep separator between items if file is a text file.
 * @param offset (in bytes) from the file's current position.
 * @see loadtxt
 */
inline fun <reified T : Any> fromfile(file: File, count: Int = -1, sep: String = "", offset: Int = 0): KtNDArray<T> =
    callFunc(
        nameMethod = arrayOf("fromfile"),
        args = arrayOf(file.absolutePath, T::class.javaObjectType, count, sep, offset)
    )

/**
 * @param file filename.
 */
inline fun <reified T : Any> fromfile(file: String, count: Int = -1, sep: String = "", offset: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("fromfile"), args = arrayOf(file, T::class.javaObjectType, count, sep, offset))

/**
 * A new 1-D array initialized from text data in a string.
 *
 * @param string containing the data.
 * @param count number of items to read. -1 means all items.
 * @param sep separatoring numbers in the data.
 * @see fromfile
 */
inline fun <reified T : Any> fromstring(string: String, count: Int = -1, sep: String): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("fromstring"), args = arrayOf(string, T::class.javaObjectType, count, sep))


/**
 * Load data from a text file.
 *
 * @param fname filename.
 * @param comments the characters used to indicate the start of a comment.
 * @param delimiter the string used to separate values.
 * @param converters A [Map] mapping column number to a function that will parse the column string in the desired value.
 * Not impl.
 * @param skiprows skip the first lines.
 * @param usecols which columns to read.
 * @return [KtNDArray] from the text file.
 * @see fromstring
 */
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
 * Return evenly spaced values within a given interval.
 * @param start start of interval.
 * @param stop end of interval.
 * @param step spacing between values.
 * @return new [KtNDArray] of type [T].
 */
inline fun <reified T : Number> arange(start: Number, stop: Number, step: Number): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("arange"), args = arrayOf(start, stop, step, T::class.javaObjectType))

/**
 * Return evenly spaced values ​​in the interval of 0 to [range].
 *
 * @param range end of interval.
 * @see arange
 */
inline fun <reified T : Number> arange(range: T): KtNDArray<T> =
    arange(start = 0, stop = range, step = 1)


/**
 * Return evenly spaced numbers over a specified interval.
 *
 * @param start the starting value of the sequence.
 * @param stop the end value of the sequence.
 * @param num number of samples to generate. Default is 50.
 * @param endpoint if *true*, [stop] is the last sample.
 * @param retstep if *true*, return (samples, step).
 * @param axis the axis in the result to store the samples.
 * @return There are [num] equally spaced samples in the closed interval ```[start, stop]```.
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

/**
 * Return numbers spaced evenly on a geometric progression.
 *
 * @param start the starting value of the sequence.
 * @param stop the final value of the sequence.
 * @param num number of samples to generate. Default is 50.
 * @param endpoint if *true*, [stop] is the las sample.
 * @param axis in the result to store the samples.
 * @return [num] samples, equally spaced on a log scale.
 * @see logspace
 * @see linspace
 * @see arange
 */
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

/**
 * Extract a diagonal or construct a diagonal array.
 * @param v values.
 * @param k diagonal
 * @return [KtNDArray]
 * @see diagflat
 * @see triu
 * @see tril
 */
fun <T : Number> diag(vararg v: T, k: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("diag"), args = arrayOf(v, k))

/**
 * @param v if [v] a 2-D array, return a copy of its k-th diagonal. If [v] is a 1-D array, return a 2-D array
 * with [v] on the [k]-th diagonal
 */
fun <T : Number> diag(v: KtNDArray<T>, k: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("diag"), args = arrayOf(v, k))

/**
 * Create a 2-D array with the flattened input as a diagonal.
 * @param v input data.
 * @param k diagonal.
 * @return 2-D output [KtNDArray]
 * @see diag
 */
fun <T : Number> diagflat(v: KtNDArray<T>, k: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("diagflat"), args = arrayOf(v, k))

fun <T : Number> diagflat(v: List<Any>, k: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("diagflat"), args = arrayOf(v, k))

/**
 * An array with ones at and below the given diagonal and zeros elsewhere.
 *
 * @param n number of rows in the array.
 * @param m number of columns in the array. By default, [m] is taken equal to [n].
 * @param k the sub-diagonal at and below which the array is filled.
 * @return Array with its lower triangle filled with ones and zero elsewhere.
 */
inline fun <reified T : Any> tri(n: Int, m: Int? = null, k: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("tri"), args = arrayOf(n, m ?: none, k, T::class.javaObjectType))

/**
 * Lower triangle of an array.
 *
 * @param m input array.
 * @param k diagonal above which to zero elements.
 * @return Return a copy of an array with elements above the k-th diagonal zeroed.
 * @see triu
 */
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

/**
 * Upper triangle of an array.
 *
 * @param m input array.
 * @param k diagonal above which to zeros elements.
 * @return Return a copy of a matrix with elements below the k-th diagonal zeroed.
 * @see tril
 */
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

/**
 * Generate a Vandermonde matrix.
 *
 * @param x 1-D input array.
 * @param n number of columns in the output.
 * @param increasing order of the powers if the columns.
 * @return Vendermonde matrix.
 */
fun <T : Number> vander(x: KtNDArray<T>, n: Int? = null, increasing: Boolean = false): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("vander"), args = arrayOf(x, n ?: none, increasing))

fun <T : Number> vander(x: Array<T>, n: Int? = null, increasing: Boolean = false): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("vander"), args = arrayOf(x, n ?: none, increasing))

fun <T : Number> vander(x: List<T>, n: Int? = null, increasing: Boolean = false): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("vander"), args = arrayOf(x, n ?: none, increasing))

/**
 * Interpret the input as a matrix (2-D [KtNDArray]).
 *
 * @param data input data.
 * @return 2-D [KtNDArray]
 */
@JvmName("mat1D")
inline fun <reified T : Any> mat(data: List<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("mat"), args = arrayOf(data, T::class.javaObjectType))

@JvmName("mat2D")
inline fun <reified T : Any> mat(data: List<List<T>>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("mat"), args = arrayOf(data, T::class.javaObjectType))

inline fun <reified T : Any> mat(data: String): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("mat"), args = arrayOf(data, T::class.javaObjectType))

/**
 * Build a matrix object from a [List].
 *
 * @param data input data.
 * @return a 2-D [KtNDArray].
 * @see block
 */
@JvmName("bmat1D")
inline fun <reified T : Any> bmat(data: List<KtNDArray<T>>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("bmat"), args = arrayOf(data, T::class.javaObjectType))

/**
 * Build a matrix object from a [List] of [List].
 * @param data input data.
 * @return a 2-D [KtNDArray].
 * @see block
 */
@JvmName("bmat2D")
inline fun <reified T : Any> bmat(data: List<List<KtNDArray<T>>>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("bmat"), args = arrayOf(data, T::class.javaObjectType))

/**
 * Return coordinate matrices from coordinate vectors.
 *
 * @param xi 1-D arrays representing the coordinates of a grid.
 */
fun <T : Number> meshgrid(vararg xi: KtNDArray<T>): List<KtNDArray<T>> =
    callFunc(nameMethod = arrayOf("meshgrid"), args = arrayOf(*xi), kClass = List::class) as List<KtNDArray<T>>

/**
 * Return [KtNDArray] type.
 */
inline fun <reified T : Any> type(l: KtNDArray<T>) = T::class.java
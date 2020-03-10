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
import org.jetbrains.numkt.core.None

/**
 * Sorting algorithm. [STABLE] and [MERGESORT] use timsort under the covers.
 */
enum class KindSort(val str: String) {
    QUICKSORT("quicksort"),
    MERGESORT("mergesort"),
    HEAPSORT("heapsort"),
    STABLE("stable")
}

/** used in [searchSorted] */
enum class Side(val str: String) {
    /** First suitable location found is given. */
    LEFT("left"),

    /** Last suitable location found is given. */
    RIGHT("right")
}

/**
 * Return a sorted copy of an array.
 *
 * @param a array to be sorted.
 * @param axis along which to sort.
 * @param kind sorting algorithm. see [KindSort].
 * @return [KtNDArray] of the same type and shape as [a].
 * @see argSort
 * @see lexSort
 * @see searchSorted
 * @see partition
 */
fun <T : Any> sort(a: KtNDArray<T>, axis: Int = -1, kind: KindSort? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("sort"), args = arrayOf(a, axis, kind?.str ?: None.none))

/**
 * Perform an indirect stable sort using a sequence of keys.
 *
 * @param keys the k different 'columns' to be sorted.
 * @param axis axis to be indirectly sorted.
 * @return [KtNDArray] of indeces that sort the keys along the specified axis.
 * @see argSort
 * @see sort
 */
fun <T : Any> lexSort(keys: Array<KtNDArray<T>>, axis: Int = -1): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("lexsort"), args = arrayOf(keys, axis))


/**
 * Returns the indices that would sort an array.
 *
 * @param a array to sort
 * @param axis along which to sort. The default is the last index (-1).
 * @param kind sorting algorithm. The default is `quicksort`.
 * @return [KtNDArray] of indices that sort [a] along the specified [axis].
 * @see sort
 * @see lexSort
 * @see argPartition
 */
fun <T : Any> argSort(a: KtNDArray<T>, axis: Int = -1, kind: KindSort? = null): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("argsort"), args = arrayOf(a, axis, kind?.str ?: None.none))

/**
 * Return of an array sorted along the first axis.
 *
 * @param a array to be sorted.
 * @return [KtNDArray] of the same type and shape as [a].
 * @see sort
 */
fun <T : Any> msort(a: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("msort"), args = arrayOf(a))

/**
 * Return a partitioned copy of an array.
 *
 * @param a array to be sorted.
 * @param kth element index to partition by.
 * @param axis along which to sort.
 * @param kind selection algorithm. Default is ‘introselect’.
 * @return [KtNDArray] of the same type and shape as [a].
 * @see argPartition
 * @see sort
 */
fun <T : Any> partition(
    a: KtNDArray<T>,
    kth: Int,
    axis: Int = -1,
    kind: String = "introselect"
): KtNDArray<T> = callFunc(nameMethod = arrayOf("partition"), args = arrayOf(a, kth, axis, kind))

/**
 * Perform an indirect partition along the given axis using the algorithm specified by the kind keyword.
 *
 * @param a array to sort.
 * @param kth element index to partition by.
 * @param axis along which to sort. Th default is the last axis (-1).
 * @param kind selection algorithm. Default is `introselect`.
 * @return [KtNDArray] of indices that partition [a] along the specified axis.
 * @see partition
 * @see argSort
 */
fun <T : Any> argPartition(
    a: KtNDArray<T>,
    kth: Int,
    axis: Int = -1,
    kind: String = "introselect"
): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("argpartition"), args = arrayOf(a, kth, axis, kind))

/**
 * Returns the index of the maximum value along the flattened array.
 *
 * @param a input array.
 * @return index of type [Long].
 * @see argMin
 */
fun <T : Number> argMax(a: KtNDArray<T>): Long =
    callFunc(nameMethod = arrayOf("argmax"), args = arrayOf(a), kClass = Long::class)

/**
 * Returns the indices of the maximum values along an axis.
 *
 * @param a input array.
 * @param axis indices along the specified axis.
 * @return [KtNDArray] of indices into the array. It has the same shape as [a] with the dimension along axis removed.
 * @see argMin
 */
fun <T : Number> argMax(a: KtNDArray<T>, axis: Int): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("argmax"), args = arrayOf(a, axis))

/**
 * Return the indices of the maximum values in the specified axis ignoring [Double.NaN].
 *
 * @param a input data.
 * @return index value.
 * @see argMax
 * @see nanArgMin
 */
fun <T : Number> nanArgMax(a: KtNDArray<T>): Long =
    callFunc(nameMethod = arrayOf("nanargmax"), args = arrayOf(a), kClass = Long::class)

/**
 * @param axis along which to operate.
 * @return An [KtNDArray] of indices.
 */
fun <T : Number> nanArgMax(a: KtNDArray<T>, axis: Int): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("nanargmax"), args = arrayOf(a, axis))

/**
 * Returns the index of the maximum value along the flattened array.
 *
 * @param a input array.
 * @return index of type [Long].
 * @see argMax
 */
fun <T : Number> argMin(a: KtNDArray<T>): Long =
    callFunc(nameMethod = arrayOf("argmin"), args = arrayOf(a), kClass = Long::class)

/**
 * Returns the indices of the minimum values along an axis.
 *
 * @param a input array.
 * @param axis indices along the specified axis.
 * @return [KtNDArray] of indices into the array. It has the same shape as [a] with the dimension along axis removed.
 * @see argMax
 */
fun <T : Number> argMin(a: KtNDArray<T>, axis: Int): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("argmin"), args = arrayOf(a, axis))

/**
 * Return the indices of the minimum values in the specified axis ignoring [Double.NaN].
 *
 * @param a input data.
 * @return Index value.
 * @see nanArgMax
 * @see argMin
 */
fun <T : Number> nanArgMin(a: KtNDArray<T>): Long =
    callFunc(nameMethod = arrayOf("nanargmin"), args = arrayOf(a), kClass = Long::class)

/**
 * @param axis along which to operate.
 * @return An [KtNDArray] of indices.
 */
fun <T : Number> nanArgMin(a: KtNDArray<T>, axis: Int): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("nanargmin"), args = arrayOf(a, axis))

/**
 * Return the indices of the elements that are non-zero.
 *
 * @param a input array.
 * @return [Array] of indices of elements that are non-zero.
 * @see flatNoneZero
 * @see countNonZero
 */
fun <T : Any> nonZero(a: KtNDArray<T>): Array<Any> =
    callFunc(nameMethod = arrayOf("nonzero"), args = arrayOf(a), kClass = Array<Any>::class)

/**
 * Return indices that are non-zero in the flattened version of [a].
 *
 * @param a input data.
 * @return [KtNDArray]
 * @see nonZero
 * @see ravel
 */
fun <T : Number> flatNoneZero(a: KtNDArray<T>): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("flatnonzero"), args = arrayOf(a))

/**
 * Find indices where elements should be inserted to maintain order.
 *
 * @param a input 1-D [KtNDArray].
 * @param v value to insert into [a].
 * @param side If *left*, the index of the first suitable location found is given.
 * If *right*, return the last such index.
 * If there is no suitable index, return either 0 or N (where N is the length of [a]).
 * @return index
 * @see sort
 */
fun <T : Number> searchSorted(a: KtNDArray<T>, v: T, side: Side = Side.LEFT): Long =
    callFunc(nameMethod = arrayOf("searchsorted"), args = arrayOf(a, v, side.str), kClass = Long::class)

/**
 * @param v [KtNDArray] of values to insert into [a].
 * @return [KtNDArray] of insertion points with the same shape as v.
 */
fun <T : Number> searchSorted(a: KtNDArray<T>, v: KtNDArray<T>, side: Side = Side.LEFT): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("searchsorted"), args = arrayOf(a, v, side.str))

/**
 * Counts the number of non-zero values in the array [a].
 * @param a inpud array.
 * @return Number of [Long] of non-zero values in the array.
 * @see nonZero
 */
fun <T : Any> countNonZero(a: KtNDArray<T>): Long =
    callFunc(nameMethod = arrayOf("count_nonzero"), args = arrayOf(a), kClass = Long::class)

/**
 * Counts the number of non-zero values in the array [a] along a given axis.
 * @param a inpud array.
 * @param axis along which to count non-zeros.
 * @return Number of [KtNDArray] of non-zeros values in the array along a given axis.
 * @see nonZero
 */
fun <T : Any> countNonZero(a: KtNDArray<T>, axis: Int): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("count_nonzero"), args = arrayOf(a, axis))
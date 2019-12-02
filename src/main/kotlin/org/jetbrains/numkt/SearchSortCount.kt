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
 *
 */
enum class KindSort(val str: String) {
    QUICKSORT("quicksort"),
    MERGESORT("mergesort"),
    HEAPSORT("heapsort"),
    STABLE("stable")
}

enum class Side(val str: String) {
    LEFT("left"),
    RIGHT("right")
}

/**
 *
 */
fun <T : Any> sort(a: KtNDArray<T>, axis: Int = -1, kind: KindSort? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("sort"), args = arrayOf(a, axis, kind?.str ?: None.none))

/**
 *
 */
fun <T : Any> lexSort(keys: Array<KtNDArray<T>>, axis: Int = -1): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("lexsort"), args = arrayOf(keys, axis))


/**
 *
 */
fun <T : Any> argSort(a: KtNDArray<T>, axis: Int = -1, kind: KindSort? = null): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("argsort"), args = arrayOf(a, axis, kind?.str ?: None.none))


fun <T : Any> msort(a: KtNDArray<T>): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("msort"), args = arrayOf(a))


fun <T : Any> partition(
    a: KtNDArray<T>,
    kth: Int,
    axis: Int = -1,
    kind: String = "introselect"
): KtNDArray<T> = callFunc(nameMethod = arrayOf("partition"), args = arrayOf(a, kth, axis, kind))

fun <T : Any> argPartition(
    a: KtNDArray<T>,
    kth: Int,
    axis: Int = -1,
    kind: String = "introselect"
): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("argpartition"), args = arrayOf(a, kth, axis, kind))

fun <T : Number> argMax(a: KtNDArray<T>): Long =
    callFunc(nameMethod = arrayOf("argmax"), args = arrayOf(a), kClass = Long::class)

fun <T : Number> argMax(a: KtNDArray<T>, axis: Int): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("argmax"), args = arrayOf(a, axis))

fun <T : Number> nanArgMax(a: KtNDArray<T>): Long =
    callFunc(nameMethod = arrayOf("nanargmax"), args = arrayOf(a), kClass = Long::class)

fun <T : Number> nanArgMax(a: KtNDArray<T>, axis: Int): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("nanargmax"), args = arrayOf(a, axis))

fun <T : Number> argMin(a: KtNDArray<T>): Long =
    callFunc(nameMethod = arrayOf("argmin"), args = arrayOf(a), kClass = Long::class)

fun <T : Number> argMin(a: KtNDArray<T>, axis: Int): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("argmin"), args = arrayOf(a, axis))

fun <T : Number> nanArgMin(a: KtNDArray<T>): Long =
    callFunc(nameMethod = arrayOf("nanargmin"), args = arrayOf(a), kClass = Long::class)

fun <T : Number> nanArgMin(a: KtNDArray<T>, axis: Int): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("nanargmin"), args = arrayOf(a, axis))

fun <T : Any> nonZero(a: KtNDArray<T>): Array<Any> =
    callFunc(nameMethod = arrayOf("nonzero"), args = arrayOf(a), kClass = Array<Any>::class)

fun <T : Number> flatNoneZero(a: KtNDArray<T>): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("flatnonzero"), args = arrayOf(a))

fun <T : Number> searchSorted(a: KtNDArray<T>, v: T, side: Side = Side.LEFT): Long =
    callFunc(nameMethod = arrayOf("searchsorted"), args = arrayOf(a, v, side.str), kClass = Long::class)

fun <T : Number> searchSorted(a: KtNDArray<T>, v: KtNDArray<T>, side: Side = Side.LEFT): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("searchsorted"), args = arrayOf(a, v, side.str))

fun <T : Any> countNonZero(a: KtNDArray<T>): Long =
    callFunc(nameMethod = arrayOf("count_nonzero"), args = arrayOf(a), kClass = Long::class)

fun <T : Any> countNonZero(a: KtNDArray<T>, axis: Int): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf("count_nonzero"), args = arrayOf(a, axis))
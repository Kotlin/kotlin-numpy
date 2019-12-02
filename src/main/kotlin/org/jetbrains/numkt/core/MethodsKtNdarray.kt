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

package org.jetbrains.numkt.core

import org.jetbrains.numkt.*

private const val NDARRAY_STR = "ndarray"

/**
 * Test whether all array elements along a given axis evaluate to *true*.
 *
 * @param axis: default none, [Int] or [IntArray]- axis along which a logical AND reduction is performed.
 * If [axis] is negative, in which case if counts from the last to the first axis.
 *
 * @return new [KtNDArray] of type [Boolean] or [Boolean] value.
 */
fun <T : Any> KtNDArray<T>.all(): Boolean =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "all"), args = arrayOf(this), kClass = Boolean::class)


fun <T : Any> KtNDArray<T>.all(vararg axis: Int): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "all"), args = arrayOf(this, axis))

/** Returns 'true' if all elements satisfy the predicate.
 * Using buffer.
 */
inline fun <T : Any> KtNDArray<T>.all(predicate: (T) -> Boolean): Boolean {
    for (element in this) if (!predicate(element)) return false
    return true
}


/**
 * Test whether any array element along a given axis evaluates to *true*.
 *
 * @param axis: default none, [Int] or [IntArray] - Axis along which a logical OR reduction is performed.
 * If [axis] is negative, in which case it counts from the last to the first axis.
 *
 * @return new [KtNDArray] of type [Boolean] or [Boolean] value.
 */
fun <T : Any> KtNDArray<T>.any(): Boolean =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "any"), args = arrayOf(this), kClass = Boolean::class)

fun <T : Any> KtNDArray<T>.any(vararg axis: Int): KtNDArray<Boolean> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "any"), args = arrayOf(this, axis))

/** Returns 'true' if any element satisfy the predicate. */
inline fun <T : Any> KtNDArray<T>.any(predicate: (T) -> Boolean): Boolean {
    for (element in this) if (predicate(element)) return true
    return false
}

/**
 * Returns the indices of the maximum values along an axis.
 *
 * @param axis - index is into the specified axis. By default, the index is into the flattened array.
 *
 * @return new [KtNDArray] of type [Long] or [Long] value. Array indices into the array.
 * It has the same shape as [shape] with the dimension along [axis] removed.
 */
fun <T : Number> KtNDArray<T>.argMax(): Long =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "argmax"), args = arrayOf(this), kClass = Long::class)

fun <T : Number> KtNDArray<T>.argMax(axis: Int): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "argmax"), args = arrayOf(this, axis))


/**
 * Return the indices of the minimum values along the given axis of array.
 *
 * @param axis - By default, axis is none, the index is nto the flattened array,
 * otherwise along the specified axis.
 *
 * @return new [KtNDArray] of type [Long] or [Long] value.
 */
fun <T : Number> KtNDArray<T>.argMin(): Long =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "argmin"), args = arrayOf(this), kClass = Long::class)

fun <T : Number> KtNDArray<T>.argMin(axis: Int): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "argmin"), args = arrayOf(this, axis))


/**
 * Returns the indices that would partition this array.
 *
 * @param kth - Element index to partition by.
 * The k-th element will be in its final sorted position and all smaller elements will be moved before it and
 * all larger elements behind it. The order all elements in the partitions is undefined.
 * If provided with a sequence of k-th it will partition all of them into their sorted position at once.
 *
 * @param axis - Axis along which to sort. The default is -1 (the last axis). If null, the flattened array is used.
 *
 * @param kind - Selection algorithm. Default is ‘introselect’.
 *
 * @return [KtNDArray] - Array of indices that partition this array along the specified axis.
 */
fun <T : Any> KtNDArray<T>.argPartition(kth: IntArray, axis: Int? = -1, kind: String = "introselect"): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "argpartition"), args = arrayOf(this, kth, axis ?: None.none, kind))


/**
 * Returns the indices that would sort this array.
 *
 * @param axis - axis along which to sort.
 * The default is -1 (the last axis). If null, the flattened array is used.
 *
 * @param kind - sorting algorithm from {'quicksort', 'mergesort', 'heapsort', 'stable'}. The default is 'quicksort'.
 *
 * @return [KtNDArray] of [Long] type. Array of indices that sort this array along the specified [axis].
 */
fun <T : Any> KtNDArray<T>.argSort(axis: Int? = -1, kind: String? = null): KtNDArray<Long> =
    callFunc(
        nameMethod = arrayOf(NDARRAY_STR, "argsort"),
        args = arrayOf(this, axis ?: None.none, kind ?: None.none)
    )

/**
 * Copy of the array, cast to a specified type.
 *
 * type [T] to which the array is cast.
 *
 * @param order: {'C', 'F', 'A', 'K'} - controls the memory layout order of the result. Default is 'K'.
 *
 * @param casting: [Casting] - controls what kind of data casting may occur.
 * Defaults to ‘unsafe’ for backwards compatibility.
 *
 * @param subok - If true, then sub-classes will be passed-through (default),
 * otherwise the returned array will be forced to be a base-class array.
 *
 * @param copy - By default, asType always returns a newly allocated array.
 * If this is set to false, and the type [R], [order], and [subok] requirements are satisfied,
 * the input array is returned instead of a copy.
 *
 * @return [KtNDArray] of type [R].
 * Unless [copy] is false and the other conditions for returning the input array are satisfied
 * (see description for copy input parameter),
 * return array is a new array of the same shape as the input array, with [R], [order] given by [R], [order].
 */
inline fun <T : Any, reified R : Any> KtNDArray<T>.asType(
    order: Order = Order.K,
    casting: Casting = Casting.UNSAFE,
    subok: Boolean = true,
    copy: Boolean = true
): KtNDArray<R> =
    callFunc(
        nameMethod = arrayOf("ndarray", "astype"),
        args = arrayOf(this, R::class.javaObjectType, order.name, casting.str, subok, copy)
    )


/**
 * Swap the bytes of the array elements.
 *
 * @param inplace if 'true', swap swap in-place. Default value 'false'.
 *
 * @return [KtNDArray].  If inplace 'true' return view, else copy data to new buffer.
 * */
fun <T : Any> KtNDArray<T>.byteSwap(inplace: Boolean = false): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "byteswap"), args = arrayOf(this, inplace))


/**
 * Use an index array to construct a new array from a set of choices.
 *
 * @param choices - choice arrays.
 * @param mode - specifies how indices outside [0, n-1] will be treated.
 *
 * @return The merged [KtNDArray] of type [T].
 */
fun <E : Number, T : Number> KtNDArray<T>.choose(choices: Array<E>, mode: Mode = Mode.RAISE): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "choose"), args = arrayOf(this, choices, None.none, mode.str))

fun <E : Number, T : Number> KtNDArray<T>.choose(choices: List<E>, mode: Mode = Mode.RAISE): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "choose"), args = arrayOf(this, choices, None.none, mode.str))

fun <E : Number, T : Number> KtNDArray<T>.choose(choices: KtNDArray<E>, mode: Mode = Mode.RAISE): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "choose"), args = arrayOf(this, choices, None.none, mode.str))


/**
 * Return an array whose values are limited to [min, max]. One of max or min must be given.
 *
 * @param min - minimum value. If null, clipping is not performed on lower interval edge.
 * Not more than one of [min] and [max] may be null.
 * @param max - maximum value. If null, clipping is not performed on upper interval edge.
 * Not more than one of [min] and [max] may be null.
 *
 * @return [KtNDArray] of type [T].
 * */
fun <T : Any> KtNDArray<T>.clip(min: T? = null, max: T? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "clip"), args = arrayOf(this, min ?: None.none, max ?: None.none))


/** Return selected slices of this array along given axis.
 *
 * @param condition - array of boolean that selects which entries to return.
 * @param axis - axis along which to take slices. If null (default), work on the flattened array.
 *
 * @return a copy of a without the slices along axis for which condition is false.
 * */
fun <T : Any> KtNDArray<T>.compress(condition: BooleanArray, axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "compress"), args = arrayOf(this, condition, axis ?: None.none))

/** Return a copy of the array. */
fun <T : Any> KtNDArray<T>.copy(order: Order = Order.C): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "copy"), args = arrayOf(this, order.name))

/**
 * Return the cumulative product of the elements along the given axis.
 *
 * @param axis along which the cumulative product is computed.
 * The default (null) is to compute the [cumProd] over the flattened array.
 *
 * @return a new [KtNDArray] of type [T].
 */
fun <T : Any> KtNDArray<T>.cumProd(axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "cumprod"), args = arrayOf(this, axis ?: None.none, this.dtype))


/**
 * Return the cumulative sum of the elements along the given axis.
 *
 * @param axis along which the cumulative sum is computed.
 * The default (null) is to compute the [cumSum] over the flattened array.
 *
 * @return a new [KtNDArray] of type [T].
 * */
fun <T : Any> KtNDArray<T>.cumSum(axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "cumsum"), args = arrayOf(this, axis ?: None.none, this.dtype))


/** Return specified diagonals.
 *
 * @param offset of the digonal from the org.jetbrains.numkt.main diagonal. Can be positive or negative.
 * Defaults to org.jetbrains.numkt.main diagonal, offset is 0.
 *
 * @param axis1 to be used as the first axis of the 2D subarrays from which the diagonals should be taken.
 * Defaults to first axis is 0.
 *
 * @param axis2 to be used as the second axis of the 2D subarrays from which the diagonals should be taken.
 * Defaults to second axis is 1
 *
 * @return
 * */
fun <T : Any> KtNDArray<T>.diagonal(offset: Int = 0, axis1: Int = 0, axis2: Int = 1): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "diagonal"), args = arrayOf(this, offset, axis1, axis2))

/**
 * Dot product of two arrays.
 *
 * @param b - second array.
 *
 * @return returns the dot product this array and b array.
 */
private fun <T : Number, E : Number, R : Number> KtNDArray<T>.prDot(b: KtNDArray<E>): KtNDArray<R> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "dot"), args = arrayOf(this, b))

@JvmName("byteDotOther")
fun <T : Number> KtNDArray<Byte>.dot(b: KtNDArray<T>): KtNDArray<T> = this.prDot(b)

@JvmName("shortDotByte")
fun KtNDArray<Short>.dot(b: KtNDArray<Byte>): KtNDArray<Short> = this.prDot(b)

@JvmName("shortDotOther")
fun <T : Number> KtNDArray<Short>.dot(b: KtNDArray<T>): KtNDArray<T> = this.prDot(b)

@JvmName("intDotByte")
fun KtNDArray<Int>.dot(b: KtNDArray<Byte>): KtNDArray<Int> = this.prDot(b)

@JvmName("intDotShort")
fun KtNDArray<Int>.dot(b: KtNDArray<Short>): KtNDArray<Short> = this.prDot(b)

@JvmName("intDotFloat")
fun KtNDArray<Int>.dot(b: KtNDArray<Float>): KtNDArray<Double> = this.prDot(b)

@JvmName("intDotOther")
fun <T : Number> KtNDArray<Int>.dot(b: KtNDArray<T>): KtNDArray<T> = this.prDot(b)

@JvmName("longDotByte")
fun KtNDArray<Long>.dot(b: KtNDArray<Byte>): KtNDArray<Long> = this.prDot(b)

@JvmName("longDotShort")
fun KtNDArray<Long>.dot(b: KtNDArray<Short>): KtNDArray<Long> = this.prDot(b)

@JvmName("longDotInt")
fun KtNDArray<Long>.dot(b: KtNDArray<Int>): KtNDArray<Long> = this.prDot(b)

@JvmName("longDotFloat")
fun KtNDArray<Long>.dot(b: KtNDArray<Float>): KtNDArray<Double> = this.prDot(b)

@JvmName("longDotOther")
fun <T : Number> KtNDArray<Long>.dot(b: KtNDArray<T>): KtNDArray<T> = this.prDot(b)

@JvmName("floatDotByte")
fun KtNDArray<Float>.dot(b: KtNDArray<Byte>): KtNDArray<Float> = this.prDot(b)

@JvmName("floatDotShort")
fun KtNDArray<Float>.dot(b: KtNDArray<Short>): KtNDArray<Float> = this.prDot(b)

@JvmName("floatDotFloat")
fun KtNDArray<Float>.dot(b: KtNDArray<Float>): KtNDArray<Float> = this.prDot(b)

@JvmName("floatDotOther")
fun <T : Number> KtNDArray<Float>.dot(b: KtNDArray<T>): KtNDArray<Double> = this.prDot(b)

@JvmName("doubleDotOther")
fun <T : Number> KtNDArray<Double>.dot(b: KtNDArray<T>): KtNDArray<Double> = this.prDot(b)

/**
 * Dump a pickle of the array to the specified file.
 */
fun <T : Any> KtNDArray<T>.dump(file: String) {
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "dump"), args = arrayOf(this, file), kClass = Unit::class)
}

/**
 * Returns the pickle of the array as a string.
 */
fun <T : Any> KtNDArray<T>.dumps(): String =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "dumps"), args = arrayOf(this), kClass = String::class)

/**
 * Fill the array with a scalar value.
 *
 * @param value - all elements of a will be assigned this value.
 */
fun <T : Any> KtNDArray<T>.fill(value: T) {
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "fill"), args = arrayOf(this, value), kClass = Unit::class)
}

/**
 * Return a copy of the array collapsed into one dimension.
 *
 * @param order - [Order]
 *
 * @return a flatten [KtNDArray] of type [T].
 */
fun <T : Any> KtNDArray<T>.flatten(order: Order = Order.C): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "flatten"), args = arrayOf(this, order.name))

/**
 * Returns a field of the given array as a certain type.
 * A field is a view of the array data with a given data-type.
 *
 * @param T - type of array.
 * @param R - type of field.
 * @param offset - numbers of bytes to skip before beginning the element view.
 *
 * @return view of [KtNDArray] of type [R].
 * */
inline fun <T : Any, reified R : Any> KtNDArray<T>.getfield(offset: Int = 0): KtNDArray<R> =
    callFunc(nameMethod = arrayOf("ndarray", "getfield"), args = arrayOf(this, R::class.javaObjectType, offset))

/**
 * Copy an element of an array to a standard Python/Java scalar and return it.
 */
inline fun <reified T : Any> KtNDArray<T>.item(vararg arg: Int): T =
    callFunc(nameMethod = arrayOf("ndarray", "item"), args = arrayOf(this, arg), kClass = T::class)

/**
 * Insert scalar into an array (scalar is cast to array’s dtype, if possible)
 * faster than set()
 */
fun <T : Any> KtNDArray<T>.itemset(vararg indices: Long, element: T) {
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "itemset"), args = arrayOf(this, indices, element), kClass = Unit::class)
}

/**
 * Maximum of an array or maximum along an axis.
 *
 * @param axis - axis or axes along which to operate. By default, flattened input is used.
 *
 * @return new [KtNDArray] of type [T] or [T] value.
 */
inline fun <reified T : Number> KtNDArray<T>.max(): T? =
    callFunc(nameMethod = arrayOf("ndarray", "max"), args = arrayOf(this), kClass = T::class)

fun <T : Any> KtNDArray<T>.max(vararg axis: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "max"), args = arrayOf(this, axis))

/**
 * Returns the average of the array elements along given axis.
 *
 * @param axis: none, Int or IntArray - axis or axes along which the means are computed.
 *
 * @return [Double] or [KtNDArray] of [Double].
 */
fun <T : Number> KtNDArray<T>.mean(): Double =
    callFunc(
        nameMethod = arrayOf(NDARRAY_STR, "mean"),
        args = arrayOf(this, None.none, Double::class.javaObjectType),
        kClass = Double::class
    )

fun <T : Number> KtNDArray<T>.mean(vararg axis: Int): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "mean"), args = arrayOf(this, axis, Double::class.javaObjectType))

/**
 * Return the minimum along a given axis.
 *
 * @param axis - axis or axes along which to operate. By default, flattened input is used.
 *
 * @return new [KtNDArray] of type [T] or [T] value.
 */
inline fun <reified T : Any> KtNDArray<T>.min(): T? =
    callFunc(nameMethod = arrayOf("ndarray", "min"), args = arrayOf(this), kClass = T::class)

fun <T : Any> KtNDArray<T>.min(vararg axis: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "min"), args = arrayOf(this, axis))

/**
 * Return the array with the same data viewed with a different byte order.
 *
 * @param newOrder - 'S' - swap dtype from current to opposite endian
 * {'<', 'L'} - little endian
 * {'>', 'B'} - big endian
 * {'=', 'N'} - native endian
 * {‘|’, ‘I’} - ignore (no change to byte order)
 * default 'S'
 */
fun <T : Any> KtNDArray<T>.newByteOrder(newOrder: Char = 'S'): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "newbyteorder"), args = arrayOf(this, newOrder))

/**
 * Return the indices of the elements that are non-zero.
 */
fun <T : Any> KtNDArray<T>.nonZero(): Array<Any> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "nonzero"), args = arrayOf(this), kClass = Array<Any>::class)


/**
 * Rearranges the elements in the array in such a way that the value of
 * the element in kth position is in the position it would be in a sorted array.
 *
 * @param kth - element index to partition by.
 * @param axis - axis along which to sort. Default is -1, which means sort along the last axis.
 * @param kind - selection algorithm. Default is 'introselect'.
 */
fun <T : Any> KtNDArray<T>.partition(kth: IntArray, axis: Int = -1, kind: String = "introselect") {
    callFunc(
        nameMethod = arrayOf(NDARRAY_STR, "partition"),
        args = arrayOf(this, kth, axis, kind),
        kClass = Unit::class
    )
}


/**
 * Return the product of the array elements over the given axis.
 *
 * @param axis: none, [Int] or [IntArray] - axis or axes along which a product is performed.
 *
 * @return new [KtNDArray] of type [T] or [T] value.
 */
inline fun <reified T : Number> KtNDArray<T>.prod(): T =
    callFunc(
        nameMethod = arrayOf("ndarray", "prod"),
        args = arrayOf(this, None.none, T::class.javaObjectType),
        kClass = T::class
    )

fun <T : Number> KtNDArray<T>.prod(vararg axis: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "prod"), args = arrayOf(this, axis, this.dtype))

/**
 * Peak to peak (maximum - minimum) value along a given axis.
 *
 * @param axis: null, [Int], [IntArray] - Axis along which to find the peaks.
 *
 * @return A new array holding the result.
 */
fun <T : Number> KtNDArray<T>.ptp(vararg axis: Int? = emptyArray()): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "ptp"), args = arrayOf(this, axis.ifEmpty { None.none }))

/**
 * Set array.flat.set(n) = values.set(n) for all n in indices.
 */
fun <T : Any> KtNDArray<T>.put(indices: IntArray, values: Array<T>, mode: Mode = Mode.RAISE) {
    callFunc(
        nameMethod = arrayOf(NDARRAY_STR, "put"),
        args = arrayOf(this, indices, values, mode.str),
        kClass = Unit::class
    )
}

/**
 * Return a flattened array.
 *
 * Return a contiguous flattened array.
 * A 1-D array, containing the elements of the input, is returned.
 *
 * @return view of [KtNDArray].
 * */
fun <T : Any> KtNDArray<T>.ravel(order: Order = Order.C): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "ravel"), args = arrayOf(this, order.name))

/**
 * Repeat elements of an array.
 *
 * @param repeats - The number of repetitions for each element.
 * @param axis - The axis along which to repeat values.
 * By default, use the flattened input array, and return a flat output array.
 *
 * @return [KtNDArray]. Output array which has the same shape as input array, except along the given axis.
 */
fun <T : Any> KtNDArray<T>.repeat(repeats: Int, axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "repeat"), args = arrayOf(this, repeats, axis ?: None.none))


fun <T : Any> KtNDArray<T>.repeat(repeats: IntArray, axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "repeat"), args = arrayOf(this, repeats, axis ?: None.none))

/**
 * Returns an array containing the same data with a new shape.
 */
fun <T : Any> KtNDArray<T>.reshape(vararg dims: Int, order: Order = Order.C): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "reshape"), args = arrayOf(this, dims), order = order)

/**
 * Change shape and size of array in-place.
 */
fun <T : Any> KtNDArray<T>.resize(vararg dims: Int) {
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "resize"), args = arrayOf(this, dims), kClass = Unit::class)
}

/**
 * Return a with each element rounded to the given number of decimals.
 *
 * @param decimals - Number of decimal places to round to (default: 0).
 * If decimals is negative, it specifies the number of positions to the left of the decimal point.
 *
 * @return [KtNDArray]
 */
fun <T : Number> KtNDArray<T>.round(decimals: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "round"), args = arrayOf(this, decimals))

/**
 * Find indices where elements of v should be inserted in a to maintain order.
 */
fun <T : Any> KtNDArray<T>.searchSorted(v: Int, side: String = "left"): Long =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "searchsorted"), args = arrayOf(this, v, side), kClass = Long::class)

fun <T : Any> KtNDArray<T>.searchSorted(v: Long, side: String = "left"): Long =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "searchsorted"), args = arrayOf(this, v, side), kClass = Long::class)

fun <T : Any> KtNDArray<T>.searchSorted(v: KtNDArray<Long>, side: String = "left"): KtNDArray<Long> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "searchsorted"), args = arrayOf(this, v, side))

/**
 * Put a value into a specified place in a field defined by a data-type.
 */
inline fun <T : Any, reified R : Any> KtNDArray<T>.setfield(value: R, offset: Int = 0) {
    callFunc(
        nameMethod = arrayOf("ndarray", "setfield"),
        args = arrayOf(this, value, R::class.javaObjectType, offset),
        kClass = Unit::class
    )
}

/**
 * Set array flags WRITEABLE, ALIGNED, (WRITEBACKIFCOPY and UPDATEIFCOPY), respectively.
 */
fun <T : Any> KtNDArray<T>.setFlags(write: Int? = null, align: Int? = null, uic: Int? = null) =
    callFunc(
        nameMethod = arrayOf(NDARRAY_STR, "setflags"),
        args = arrayOf(this, write ?: None.none, align ?: None.none, uic ?: None.none),
        kClass = Unit::class
    )

/**
 * Sort an array, in-place.
 */
fun <T : Any> KtNDArray<T>.sort(axis: Int = -1, kind: KindSort? = null) =
    callFunc(
        nameMethod = arrayOf(NDARRAY_STR, "sort"),
        args = arrayOf(this, axis, kind ?: None.none),
        kClass = Unit::class
    )

/**
 * Remove single-dimensional entries from the shape of a.
 *
 * @return View.
 */
fun <T : Any> KtNDArray<T>.squeeze(axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "squeeze"), args = arrayOf(this, axis ?: None.none))

/**
 * Returns the standard deviation of the array elements along given axis.
 *
 * @param axis
 * @param ddof
 *
 * @return [Double] or [KtNDArray] of [Double].
 */
fun <T : Number> KtNDArray<T>.std(ddof: Int = 0): Double =
    callFunc(
        nameMethod = arrayOf(NDARRAY_STR, "std"),
        args = arrayOf(this, None.none, Double::class.javaObjectType, None.none, ddof),
        kClass = Double::class
    )

fun <T : Number> KtNDArray<T>.std(axis: Int, ddof: Int = 0): KtNDArray<Double> =
    callFunc(
        nameMethod = arrayOf(NDARRAY_STR, "std"),
        args = arrayOf(this, axis, Double::class.javaObjectType, None.none, ddof)
    )

/**
 * Sum of array elements over a given axis.
 *
 * @param axis - Axis or axes along which a sum is performed.
 * If [axis] is negative it counts from the last to the first axis.
 *
 * @return new [KtNDArray] of type [T]. An array with the same shape as *this*,
 * with the specified axis removed.
 */
inline fun <reified T : Number> KtNDArray<T>.sum(): T? =
    callFunc(
        nameMethod = arrayOf("ndarray", "sum"),
        args = arrayOf(this, None.none, T::class.javaObjectType),
        kClass = T::class
    )

fun <T : Number> KtNDArray<T>.sum(axis: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "sum"), args = arrayOf(this, axis, this.dtype))

/**
 * Return a view of the array with axis1 and axis2 interchanged.
 *
 * @return View
 */
fun <T : Any> KtNDArray<T>.swapAxes(axis1: Int, axis2: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "swapaxes"), args = arrayOf(this, axis1, axis2))

/**
 * Return an array formed from the elements of a at the given indices.
 */
fun <T : Any> KtNDArray<T>.take(indices: KtNDArray<Long>, axis: Int? = null, mode: Mode = Mode.RAISE): KtNDArray<T> =
    callFunc(
        nameMethod = arrayOf(NDARRAY_STR, "take"),
        args = arrayOf(this, indices, axis ?: None.none, None.none, mode.str)
    )

fun <T : Any> KtNDArray<T>.take(indices: Array<Int>, axis: Int? = null, mode: Mode = Mode.RAISE): KtNDArray<T> =
    callFunc(
        nameMethod = arrayOf(NDARRAY_STR, "take"),
        args = arrayOf(this, indices, axis ?: None.none, None.none, mode.str)
    )

fun <T : Any> KtNDArray<T>.take(indices: List<Int>, axis: Int? = null, mode: Mode = Mode.RAISE): KtNDArray<T> =
    callFunc(
        nameMethod = arrayOf(NDARRAY_STR, "take"),
        args = arrayOf(this, indices, axis ?: None.none, None.none, mode.str)
    )

/**
 * Construct Python bytes containing the raw data bytes in the array.
 */
fun <T : Any> KtNDArray<T>.toBytes(order: Order = Order.C): ByteArray =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "tobytes"), args = arrayOf(this, order.name), kClass = ByteArray::class)

/**
 * Write array to a file as text or binary (default).
 */
fun <T : Any> KtNDArray<T>.toFile(fid: String, sep: String = "", format: String = "%s") {
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "tofile"), args = arrayOf(this, fid, sep, format), kClass = Unit::class)
}

/**
 * Return the sum along diagonals of the array.
 */
fun <T : Any> KtNDArray<T>.trace(offset: Int = 0, axis1: Int = 0, axis2: Int = 1): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "trace"), args = arrayOf(this, offset, axis1, axis2))

/**
 * Returns a view of the array with axes transposed.
 */
fun <T : Any> KtNDArray<T>.transpose(vararg axes: Int? = emptyArray()): KtNDArray<T> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "transpose"), args = arrayOf(this, axes.ifEmpty { None.none }))

/**
 * Returns the variance of the array elements, along given axis.
 */
fun <T : Number> KtNDArray<T>.`var`(ddof: Int = 0): Double =
    callFunc(
        nameMethod = arrayOf(NDARRAY_STR, "transpose"),
        args = arrayOf(this, None.none, Double::class.javaObjectType, None.none, ddof),
        kClass = Double::class
    )

fun <T : Number> KtNDArray<T>.`var`(vararg axis: Int, ddof: Int = 0): KtNDArray<Double> =
    callFunc(
        nameMethod = arrayOf(NDARRAY_STR, "transpose"),
        args = arrayOf(this, axis, Double::class.javaObjectType, None.none, ddof)
    )

/**
 * New view of array with the same data.
 */
fun <T : Any, NT : Any> KtNDArray<T>.view(): KtNDArray<NT> =
    callFunc(nameMethod = arrayOf(NDARRAY_STR, "view"), args = arrayOf(this))
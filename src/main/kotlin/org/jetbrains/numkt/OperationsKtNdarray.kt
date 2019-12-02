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

import org.jetbrains.numkt.core.*


/**
 * Copies values from one array to another, broadcasting as necessary.
 *
 * Two arrays must be of the same type and match in size, otherwise raise ValueError.
 * @param dst - the array into which values are copied.
 * @param src - the array from which values are copied.
 * @param casting - [Casting]
 */
fun <R : Any, T : Any> copyto(dst: KtNDArray<R>, src: KtNDArray<T>, casting: Casting = Casting.SAME_KIND): Unit =
    callFunc(nameMethod = arrayOf("copyto"), args = arrayOf(dst, src, casting.str), kClass = Unit::class)


/**
 * Return a contiguous flattened array.
 *
 * @param a - input array [KtNDArray] of type [T]
 *
 * @return view of an array.
 * @see KtNDArray.ravel
 */
fun <T : Any> ravel(a: KtNDArray<T>, order: Order = Order.C) = a.ravel(order)

/**
 * Move axis of an array to new positions.
 * Other axis remain in their original order.
 *
 * @param a - input array [KtNDArray] of type [T]
 * @param source - original positions of the axes to move. These must be unique.
 * @param destination - destination positions for each of the original axes. These must be unique.
 *
 * @return view of the input array.
 */
fun <T : Any> moveAxis(a: KtNDArray<T>, source: Int, destination: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("moveaxis"), args = arrayOf(a, source, destination))

fun <T : Any> moveAxis(a: KtNDArray<T>, source: IntArray, destination: IntArray): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("moveaxis"), args = arrayOf(a, source, destination))

/**
 * Roll the specified axis backwards, until it lies in a given position.
 *
 * @param a - input array [KtNDArray] of type [T]
 * @param axis - the axis to roll backwards. The positions of the other axes do not change relative to one another.
 * @param start - the axis is rolled until it lies before this position. The default - 0.
 *
 * @return view of the input array.
 */
fun <T : Any> rollAxis(a: KtNDArray<T>, axis: Int, start: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("rollaxis"), args = arrayOf(a, axis, start))

/**
 * Interchange two axes of an array.
 *
 * @param a - input array [KtNDArray] of type [T]
 * @param axis1 - first axis.
 * @param axis2 - second axis.
 *
 * @return view of the input array.
 * @see KtNDArray.swapAxes
 */
fun <T : Any> swapAxes(a: KtNDArray<T>, axis1: Int, axis2: Int): KtNDArray<T> =
    a.swapAxes(axis1, axis2)


/**
 * Permute the dimensions of an array.
 *
 * @param a - input array [KtNDArray] of type [T]
 * @param axis - permute the axes according to the values given. By default, reverse dimensions.
 *
 * @return view of input array.
 * @see KtNDArray.transpose
 */
fun <T : Any> transpose(a: KtNDArray<T>, vararg axis: Int? = emptyArray()): KtNDArray<T> = a.transpose(*axis)

/**
 *
 */
fun atleast1D(vararg arys: Any): List<KtNDArray<Any>> =
    callFunc(nameMethod = arrayOf("atleast_1d"), args = arrayOf(*arys), kClass = List::class) as List<KtNDArray<Any>>

/**
 *
 */
fun atleast2D(vararg arys: Any): List<KtNDArray<Any>> =
    callFunc(nameMethod = arrayOf("atleast_2d"), args = arrayOf(*arys), kClass = List::class) as List<KtNDArray<Any>>

/**
 *
 */
fun atleast3D(vararg arys: Any): List<KtNDArray<Any>> =
    callFunc(nameMethod = arrayOf("atleast_3d"), args = arrayOf(*arys), kClass = List::class) as List<KtNDArray<Any>>


/**
 * Convert the input list to an ndarray.
 *
 * @param a - [List] of type [T]
 *
 * @return new array.
 */
inline fun <reified T : Any> asArray(a: List<Any>, order: Order? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("asarray"), args = arrayOf(a, T::class.javaObjectType), order = order)

inline fun <reified T : Any> asAnyArray(a: Array<out Any>, order: Order? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("asanyarray"), args = arrayOf(a, T::class.javaObjectType), order = order)

inline fun <reified T : Any> asAnyArray(a: List<Any>, order: Order? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("asanyarray"), args = arrayOf(a, T::class.javaObjectType), order = order)

inline fun <reified T : Any> asAnyArray(a: KtNDArray<out Any>, order: Order? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("asanyarray"), args = arrayOf(a, T::class.javaObjectType), order = order)

inline fun <reified T : Any> asContiguousArray(a: KtNDArray<out Any>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("ascontiguousarray"), args = arrayOf(a, T::class.javaObjectType))

/**
 * Return an array converted to a float type.
 * @param a - [KtNDArray] of type [T]
 *
 * @return new array - [KtNDArray] of type [Double]
 */
fun <T : Any> asFArray(a: KtNDArray<T>): KtNDArray<Double> =
    callFunc(nameMethod = arrayOf("asfarray"), args = arrayOf(a))

/**
 * Join a sequence of arrays along an existing axis.
 *
 * @param arrs - the input arrays.
 * The arrays must have the same shape, except in the dimension corresponding to axis (the first, by default).
 * @param axis - the axis along which the arrays will be joined. If axis is None, arrays are flattened before use. Default is 0.
 *
 * @return new concatenated array.
 */
fun <T : Any> concatenate(vararg arrs: KtNDArray<T>, axis: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("concatenate"), args = arrayOf(arrs, axis))


/**
 * Join a sequence of arrays along a new axis.
 *
 * @param arrs - the input arrays. Each array must have the same shape.
 * @param axis - the axis in the result array along which the input arrays are stacked. Default is 0.
 *
 * @return - new array. The stacked array has one more dimension than the input arrays.
 */
fun <T : Any> stack(vararg arrs: KtNDArray<T>, axis: Int = 0): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("stack"), args = arrayOf(arrs, axis))

/**
 * Stack 1-D arrays as columns into a 2-D array.
 *
 * @param tup - arrays to stack. All of them must have the same first dimension.
 *
 * @return 2D array. The array formed by stacking the given arrays.
 */
fun <T : Any> columnStack(vararg tup: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("column_stack"), args = arrayOf(tup))


/**
 * Stack arrays in sequence depth wise (along third axis).
 *
 * @param tup - the arrays must have the same shape along all but the third axis. 1-D or 2-D arrays must have the same shape.
 *
 * @return The array formed by stacking the given arrays, will be at least 3-D.
 */
fun <T : Any> dstack(vararg tup: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("dstack"), args = arrayOf(tup))


/**
 * Stack arrays in sequence horizontally (column wise).
 *
 * @param tup - the arrays must have the same shape along all but the second axis, except 1-D arrays which can be any length.
 *
 * @return The array formed by stacking the given arrays.
 */
fun <T : Any> hstack(vararg tup: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("hstack"), args = arrayOf(tup))


/**
 * Stack arrays in sequence vertically (row wise).
 *
 * @param tup - the arrays must have the same shape along all but the first axis. 1-D arrays must have the same length.
 *
 * @return The array formed by stacking the given arrays, will be at least 2-D.
 */
fun <T : Any> vstack(vararg tup: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("vstack"), args = arrayOf(tup))

/**
 *
 */
fun <T : Any> block(list: KtNDArray<KtNDArray<T>>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("block"), args = arrayOf(list))

/**
 *
 */
fun <T : Any> hsplit(arr: KtNDArray<T>, idx: Int): List<KtNDArray<T>> =
    callFunc(nameMethod = arrayOf("hsplit"), args = arrayOf(arr, idx), kClass = List::class) as List<KtNDArray<T>>


/**
 * Construct an array by repeating [a] the number of times given by reps.
 *
 * @param a - input array.
 * @param reps - the number of repetitions array along each axis.
 *
 * @return the tiled output array (view).
 */
fun <T : Any> tile(a: KtNDArray<T>, reps: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("tile"), args = arrayOf(a, reps))

/**
 * Construct an array by repeating [a] the number of times given by reps.
 *
 * @param a - input array.
 * @param reps - the number of repetitions array along each axis.
 *
 * @return the tiled output array (view).
 */
fun <T : Any> tile(a: KtNDArray<T>, reps: IntArray): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("tile"), args = arrayOf(a, reps))

/**
 * Repeat elements of an array.
 *
 * @param a - input array.
 * @param reps - the number of repetitions for each element. repeats is broadcasted to fit the shape of the given axis.
 * @param axis - the axis along which to repeat values. By default, use the flattened input array, and return a flat output array.
 *
 * @return output array which has the same shape as [a], except along the given axis.
 */
fun <T : Any> repeat(a: KtNDArray<T>, reps: Int, axis: Int? = null): KtNDArray<T> = a.repeat(reps, axis)

/**
 * Repeat elements of an array.
 *
 * @param a - input array.
 * @param reps - the list number of repetitions for each element. repeats is broadcasted to fit the shape of the given axis.
 * @param axis - the axis along which to repeat values. By default, use the flattened input array, and return a flat output array.
 *
 * @return output array which has the same shape as [a], except along the given axis.
 */
fun <T : Any> repeat(a: KtNDArray<T>, reps: IntArray, axis: Int? = null): KtNDArray<T> = a.repeat(reps, axis)

/**
 * Return a new array with sub-arrays along an axis deleted. For a one dimensional array, this returns those entries not returned by arr.get(obj).
 *
 * @param arr - input array
 * @param obj - indices
 * @param axis - The axis along which to delete the subarray defined by obj. If axis is null, obj is applied to the flattened array.
 *
 * @return A copy of [arr] with the elements specified by [obj] removed. Note that [delete] does not occur in-place.
 * If [axis] is null, return is a flattened array.
 */
fun <T : Any> delete(arr: KtNDArray<T>, obj: Int, axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("delete"), args = arrayOf(arr, obj, axis ?: None.none))

/**
 *
 */
fun <T : Any> delete(arr: KtNDArray<T>, obj: IntArray, axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("delete"), args = arrayOf(arr, obj, axis ?: None.none))

/**
 *
 */
fun <T : Any> delete(arr: KtNDArray<T>, obj: Array<Slice>, axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("delete"), args = arrayOf(arr, obj, axis ?: None.none))

/**
 * Insert values along the given axis before the given indices.
 *
 * @param arr - input array
 * @param obj - object that defines the index or indices before which [values] is inserted.
 * @param values - values to insert into [arr].
 * @param axis - axis along which to insert [values]. If axis is null then [arr] is flattened first.
 *
 * @return A copy of [arr] with [values] inserted. Note that insert does not occur in-place: a new array is returned.
 * If [axis] is null, return is a flattened array.
 */
fun <T : Any> insert(arr: KtNDArray<T>, obj: Int, values: KtNDArray<T>, axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("insert"), args = arrayOf(arr, obj, values, axis ?: None.none))

/**
 *
 */
fun <T : Any> insert(arr: KtNDArray<T>, obj: IntArray, values: KtNDArray<T>, axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("insert"), args = arrayOf(arr, obj, values, axis ?: None.none))

/**
 *
 */
fun <T : Any> insert(arr: KtNDArray<T>, obj: Array<Slice>, values: KtNDArray<T>, axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("insert"), args = arrayOf(arr, obj, values, axis ?: None.none))


/**
 * Append values to the end of an array.
 *
 * @param arr - input array.
 * @param values - these values are appended to a copy of [arr].
 * @param axis - the axis along which [values] are appended.
 * If [axis] is not given, both [arr] and [values] are flattened before use.
 *
 * @return A copy of [arr] with [values] appended to [axis].
 * Note that append does not occur in-place: a new array is allocated and filled.
 * If [axis] is null, return is a flattened array.
 */
fun <T : Any> append(arr: KtNDArray<T>, values: KtNDArray<T>, axis: Int? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("append"), args = arrayOf(arr, values, axis ?: None.none))


/**
 *
 */
fun <T : Any> resize(a: KtNDArray<T>, vararg newshape: Int): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("resize"), args = arrayOf(a, newshape))


/**
 * Trim the leading and/or trailing zeros from a 1-D array or sequence.
 */
fun <T : Any> trimZeros(filt: KtNDArray<T>, trim: String = "fb"): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("trim_zeros"), args = arrayOf(filt, trim))

/**
 *
 */
fun <T : Any> unique(
    ar: KtNDArray<T>,
    returnIndex: Boolean = false,
    returnInverse: Boolean = false,
    returnCounts: Boolean = false,
    axis: Int? = null
): KtNDArray<Long> = callFunc(
    nameMethod = arrayOf("unique"),
    args = arrayOf(ar, returnIndex, returnInverse, returnCounts, axis ?: None.none)
)


/**
 *
 */
fun <T : Any> flip(m: KtNDArray<T>, vararg axis: Int? = emptyArray()): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("flip"), args = arrayOf(m, if (axis.isNotEmpty()) axis else None.none))


/**
 * Input array, must be at least 2-D.
 */
fun <T : Any> fliplr(m: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("flipr"), args = arrayOf(m))


/**
 *
 */
fun <T : Any> flipud(m: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("flipud"), args = arrayOf(m))


/**
 * Gives a new shape to an array without changing its data.
 *
 * @param a - input array [KtNDArray] of type [T]
 * @param newshape - the new shape should be compatible with the original shape.
 * If an integer, then the result will be a 1-D array of that length.
 * One shape dimension can be -1. In this case, the value is inferred from the length of the array and remaining dimensions.
 *
 * @return view of an array containing the same data with a new shape.
 * @see KtNDArray.reshape
 */
fun <T : Any> reshape(a: KtNDArray<T>, vararg newshape: Int, order: Order = Order.C): KtNDArray<T> =
    a.reshape(*newshape, order = order)

/**
 *
 */
fun <T : Any> roll(a: KtNDArray<T>, shift: IntArray, axis: IntArray? = null): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("roll"), args = arrayOf(a, shift, axis ?: None.none))


/**
 *
 */
fun <T : Any> rot90(m: KtNDArray<T>, k: Int = 1, axis: IntArray = intArrayOf(0, 1)): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("rot90"), args = arrayOf(m, k, axis))
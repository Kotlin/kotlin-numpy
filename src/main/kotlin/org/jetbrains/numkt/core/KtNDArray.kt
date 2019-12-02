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

import org.jetbrains.numkt.Interpreter
import org.jetbrains.numkt.logic.arrayEqual
import java.nio.ByteBuffer
import java.nio.ByteOrder


/**
 */
class KtNDArray<T : Any> private constructor(pointer: Long, dataBuffer: ByteBuffer) {

    private val interp: Interpreter = Interpreter.interpreter!!

    private var pointer: Long = pointer
        get() = field

    val data: ByteBuffer = dataBuffer.order(ByteOrder.nativeOrder())

    // IntArray of array dimensions.
    val shape: IntArray
        get() = interp.getField("shape", pointer, IntArray::class.java)

    // Number of array dimensions.
    val ndim: Int
        get() = interp.getField("ndim", pointer, Int::class.javaObjectType)

    // Length of one array element in bytes.
    val itemsize: Int by lazy {
        interp.getField("itemsize", pointer, Int::class.javaObjectType)
    }

    // Number of elements in the array.
    val size: Int
        get() = interp.getField("size", pointer, Int::class.javaObjectType)

    // strides - array int of bytes to step in each dimension when traversing an array.
    // may changed
    val strides: IntArray
        get() = interp.getField("strides", pointer, IntArray::class.java)

    // Data-type in numpy of the array’s elements.
    val dtype: Class<T>
        get() = interp.getField("dtype", pointer, Class::class.java)

    // the transposed array
    val t: KtNDArray<T> by lazy {
        this.transpose()
    }

    // base object, if memory is from some other object (e.g view)
    var base: KtNDArray<*>? = null
        private set


    operator fun get(vararg index: Int): T = interp.getValue(pointer, index.map { it.toLong() }.toLongArray())

    operator fun get(vararg index: Long): T = interp.getValue(pointer, index)

    operator fun get(vararg slices: Slice): KtNDArray<T> = interp.getValue(pointer, slices)

    operator fun get(intRange: IntRange): KtNDArray<T> =
        this[intRange.toSlice()]

    operator fun set(vararg index: Int, element: T) {
        interp.setValue(pointer, index.map { it.toLong() }.toLongArray(), element)
    }

    operator fun set(vararg slices: Slice, element: T) {
        interp.setValue(pointer, slices, element)
    }

    /**
     * Iterator over ndarray elements.
     *
     * Iteration takes place on the direct buffer indexes obtained from the nditer.
     * This iterator is equivalent to ndarray.flat or nditer with order 'C'.
     */
    operator fun iterator(): Iterator<T> {
        return KtNDArrayIterator(
            this.data,
            this.ndim,
            this.strides,
            this.itemsize,
            this.shape,
            this.dtype
        )
    }

    override fun equals(other: Any?): Boolean {
        if (other !is KtNDArray<*>)
            return false
        return arrayEqual(this, other)
    }

    override fun hashCode(): Int =
        interp.getField("hashCode", pointer, Int::class.javaObjectType)

    override fun toString(): String {
        return interp.getField("toString", pointer, String::class.java)
    }


    protected fun finalize() {
        interp.freeArray(pointer, data)
    }
}
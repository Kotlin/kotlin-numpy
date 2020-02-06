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
import org.jetbrains.numkt.NumKtException
import org.jetbrains.numkt.logic.arrayEqual
import java.nio.ByteBuffer
import java.nio.ByteOrder


/**
 */
class KtNDArray<T : Any> private constructor(private val pointer: Long, dataBuffer: ByteBuffer?, scalar: T?) {

    private val interp: Interpreter = Interpreter.interpreter!!

    val data: ByteBuffer? = dataBuffer?.order(ByteOrder.nativeOrder())

    // IntArray of array dimensions.
    val shape: IntArray
        get() = interp.getField("shape", getPointer(), IntArray::class.java)

    // Number of array dimensions.
    val ndim: Int
        get() = interp.getField("ndim", getPointer(), Int::class.javaObjectType)

    // Length of one array element in bytes.
    val itemsize: Int by lazy {
        interp.getField("itemsize", getPointer(), Int::class.javaObjectType)
    }

    // Number of elements in the array.
    val size: Int
        get() = interp.getField("size", getPointer(), Int::class.javaObjectType)

    // strides - array int of bytes to step in each dimension when traversing an array.
    // may changed
    val strides: IntArray
        get() = interp.getField("strides", getPointer(), IntArray::class.java)

    // Data-type in numpy of the array’s elements.
    val dtype: Class<T>
        get() = interp.getField("dtype", getPointer(), Class::class.java)

    // the transposed array
    val t: KtNDArray<T> by lazy {
        this.transpose()
    }

    // base object, if memory is from some other object (e.g view)
    var base: KtNDArray<*>? = null
        private set

    var scalar: T? = scalar
        private set

    private fun getPointer(): Long = if (isNotScalar()) pointer else throw NumKtException("KtNDArray is scalar.")

    fun isScalar(): Boolean = !isNotScalar()

    fun isNotScalar(): Boolean = scalar == null


    operator fun get(vararg index: Int): KtNDArray<T> =
        interp.getValue(getPointer(), index.map { it.toLong() }.toLongArray())

    operator fun get(vararg index: Long): KtNDArray<T> = interp.getValue(getPointer(), index)

    operator fun get(vararg slices: Slice): KtNDArray<T> = interp.getValue(getPointer(), slices)

    operator fun get(intRange: IntRange): KtNDArray<T> =
        this[intRange.toSlice()]

    //experimental
    operator fun get(vararg indexes: Any): KtNDArray<T> {
        return if (indexes.size == 1) {
            when (val ind = indexes[0]) {
                is IntArray -> get(*ind)
                is LongArray -> get(*ind)
                else -> interp.getValue(getPointer(), indexes)
            }
        } else {
            interp.getValue(getPointer(), indexes)
        }
    }

    @JvmName("setVarArg")
    operator fun set(vararg index: Int, element: T) {
        interp.setValue(getPointer(), index.map { it.toLong() }.toLongArray(), element)
    }

    @JvmName("setArray")
    operator fun set(indexes: IntArray, element: T) {
        interp.setValue(getPointer(), indexes.map { it.toLong() }.toLongArray(), element)
    }

    operator fun set(vararg slices: Slice, element: T) {
        interp.setValue(getPointer(), slices, element)
    }

    operator fun set(vararg indexes: Int, element: KtNDArray<T>) {
        interp.setValue(getPointer(), indexes.map { it.toLong() }.toLongArray(), element)
    }

    operator fun set(vararg indexes: Any, element: KtNDArray<T>) {
        interp.setValue(getPointer(), indexes, element)
    }

    fun flatIter(): Iterator<T> {
        return FlatIterator(
            this.data ?: throw NumKtException("KtNDArray is scalar."),
            this.ndim,
            this.strides,
            this.itemsize,
            this.shape,
            this.dtype
        )
    }

    /**
     * Iterator over ndarray elements.
     *
     * Iteration takes place on the direct buffer indexes obtained from the nditer.
     * This iterator is equivalent to ndarray.flat or nditer with order 'C'.
     */
    operator fun iterator(): Iterator<KtNDArray<T>> = NDIterator(this, shape.first())

    override fun equals(other: Any?): Boolean {
        if (other !is KtNDArray<*>)
            return false
        if (isScalar()) {
            return this.scalar == other.scalar
        }
        return arrayEqual(this, other)
    }

    override fun hashCode(): Int =
        if (isScalar())
            scalar.hashCode()
        else
            interp.getField("hashCode", pointer, Int::class.javaObjectType)

    override fun toString(): String =
        if (isScalar())
            scalar.toString()
        else
            interp.getField("toString", pointer, String::class.java)


    protected fun finalize() {
        if (isNotScalar())
            interp.freeArray(pointer, data!!)
    }
}
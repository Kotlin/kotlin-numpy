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

import org.jetbrains.numkt.Interpreter.Companion.interpreter
import org.jetbrains.numkt.NumKtException
import java.nio.ByteBuffer

/**
 * An iterator for an [KtNDArray]. Returns an view even for one-dimensional arrays.
 */
class NDIterator<T : Any>(pointer: Long) : Iterator<KtNDArray<T>> {
    private var ret: KtNDArray<T>? = null
    private val iterator: Long = interpreter!!.getIter(pointer)

    override fun hasNext(): Boolean {
        ret = interpreter!!.iterNext(iterator)
        return if (ret != null)
            true
        else {
            interpreter!!.iterDealloc(iterator)
            return false
        }
    }

    override fun next(): KtNDArray<T> = ret!!
}

/**
 * Iterator over DirectBuffer.
 *
 * Iterates through a flattened array. This iterator can work with view.
 */
internal class FlatIterator<T : Any>(
    private val data: ByteBuffer,
    private val ndim: Int,
    private val strides: IntArray,
    private val itemsize: Int,
    private val shape: IntArray,
    private val type: Class<T>,
    offset: Long
) : Iterator<T> {
    private val index = IntArray(ndim)
    private val point = offset.toInt()


    override fun hasNext(): Boolean {
        for (i in 0 until ndim) {
            if (index[i] >= shape[i]) {
                return false
            } else if (index[i] < 0) {
                return false
            }
        }
        return true
    }

    override fun next(): T {
        var p = point / itemsize
        val res: Any =
            when (type) {
                Byte::class.javaObjectType -> {
                    for (i in 0 until ndim) {
                        p += (strides[i] / itemsize) * index[i]
                    }
                    data[p]
                }
                Short::class.javaObjectType -> {
                    for (i in 0 until ndim) {
                        p += (strides[i] / itemsize) * index[i]
                    }
                    data.asShortBuffer()[p]
                }
                Int::class.javaObjectType -> {
                    for (i in 0 until ndim) {
                        p += (strides[i] / itemsize) * index[i]
                    }
                    data.asIntBuffer()[p]
                }
                Long::class.javaObjectType -> {
                    for (i in 0 until ndim) {
                        p += (strides[i] / itemsize) * index[i]
                    }
                    data.asLongBuffer()[p]
                }
                Float::class.javaObjectType -> {
                    for (i in 0 until ndim) {
                        p += (strides[i] / itemsize) * index[i]
                    }
                    data.asFloatBuffer()[p]
                }
                Double::class.javaObjectType -> {
                    for (i in 0 until ndim) {
                        p += (strides[i] / itemsize) * index[i]
                    }
                    data.asDoubleBuffer()[p]
                }
                Boolean::class.javaObjectType -> {
                    for (i in 0 until ndim) {
                        p += (strides[i] / itemsize) * index[i]
                    }
                    data[p] != 0.toByte()
                }
                Char::class.javaObjectType -> {
                    for (i in 0 until ndim) {
                        p += (strides[i] / itemsize) * index[i]
                    }
                    data.asIntBuffer()[p].toChar()
                }
                else -> throw NumKtException("Error to iterating: unknown type")
            }
        for (i in ndim - 1 downTo 0) {
            val t = index[i] + 1
            if (t >= shape[i] && i != 0) {
                index[i] = 0
            } else {
                index[i] = t
                break
            }
        }

        return res as T
    }
}
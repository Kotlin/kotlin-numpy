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

import org.jetbrains.numkt.NumKtException
import java.nio.ByteBuffer

class ArrayIterator<T : Any>(private val ndArray: KtNDArray<T>, private val size: Int) : Iterator<KtNDArray<T>> {
    private var index = 0

    override fun hasNext(): Boolean = index < size

    override fun next(): KtNDArray<T> =
        ndArray[index++]
}

class KtNDArrayIterator<T : Any>(
    private val data: ByteBuffer,
    private val ndim: Int,
    private val strides: IntArray,
    private val itemsize: Int,
    private val shape: IntArray,
    private val type: Class<T>
) : Iterator<T> {
    private val flagDirect = BooleanArray(ndim)
    private val index = IntArray(ndim).apply {
        for (i in 0 until ndim) {
            if (strides[i] > 0) {
                this[i] = 0
                flagDirect[i] = true
            } else {
                this[i] = shape[i] - 1
                strides[i] = -strides[i]
                flagDirect[i] = false
            }
        }
    }


    override fun hasNext(): Boolean {
        for (i in 0 until ndim) {
            if (flagDirect[i] && index[i] >= shape[i]) {
                return false
            } else if (index[i] < 0) {
                return false
            }
        }
        return true
    }

    override fun next(): T {
        var p = 0
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
            if (flagDirect[i]) {
                val t = index[i] + 1
                if (t >= shape[i] && i != 0) {
                    index[i] = 0
                } else {
                    index[i] = t
                    break
                }
            } else {
                val t = index[i] - 1
                if (t < 0 && i != 0) {
                    index[i] = shape[i] - 1
                } else {
                    index[i] = t
                    break
                }
            }
        }

        return res as T
    }
}
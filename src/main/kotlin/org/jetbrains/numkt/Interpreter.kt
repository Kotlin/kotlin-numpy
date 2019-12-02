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
import org.jetbrains.numkt.core.Slice
import java.nio.Buffer


internal class Interpreter private constructor() {
    companion object {
        var interpreter: Interpreter? = null
            get() {
                if (field == null) {
                    try {
                        field = Interpreter()
                        field!!.initialize()
                    } catch (e: Error) {
                        field!!.close()
                        throw e
                    }
                } else if (field!!.error != null) {
                    throw Error("The python interpreter failed to initialize.", field!!.error)
                }
                return field
            }
    }

    private var error: Throwable? = null

    private fun initialize() {
        try {
            loadLibrary("ktnumpy")
        } catch (e: UnsatisfiedLinkError) {
            e.printStackTrace() // test
        }

        initializePython()
        if (error != null) {
            throw Error(error)
        }
    }

    private external fun initializePython()

    fun close() {
        closePython()
    }

    // call
    @Throws(NumKtException::class)
    internal external fun <T : Any> callFunc(
        nameMethod: Array<String>,
        args: Array<out Any>? = null,
        kwargs: Map<String, Any?>? = null
    ): KtNDArray<T>

    @Throws(NumKtException::class)
    internal external fun <T : Any> callFunc(
        nameMethod: Array<String>,
        args: Array<out Any>? = null,
        kwargs: Map<String, Any?>? = null,
        jClass: Class<out T>
    ): T


    internal external fun <T : Any> getField(field: String, pointer: Long, jClass: Class<in T>): T

    internal external fun <T : Any> getValue(pointer: Long, index: LongArray): T

    internal external fun <T : Any> getValue(pointer: Long, slices: Array<out Slice>): KtNDArray<T>

    internal external fun <T : Any> setValue(pointer: Long, index: LongArray, element: T)

    internal external fun <T : Any> setValue(pointer: Long, slices: Array<out Slice>, element: T)

    internal external fun freeArray(pointer: Long, data: Buffer): Int

    private external fun closePython()
}

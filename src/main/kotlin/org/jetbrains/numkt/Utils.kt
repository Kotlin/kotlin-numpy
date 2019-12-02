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

import org.jetbrains.numkt.Interpreter.Companion.interpreter
import org.jetbrains.numkt.core.KtNDArray
import kotlin.reflect.KClass

fun <T : Any> callFunc(
    nameMethod: Array<String>,
    args: Array<out Any>? = null,
    out: KtNDArray<T>? = null,
    where: BooleanArray? = null,
    axes: List<Int>? = null,
    axis: Int? = null,
    keepdims: Boolean? = null,
    casting: Casting? = null,
    order: Order? = null,
    dtype: KClass<out Any>? = null,
    subok: Boolean? = null,
    shape: IntArray? = null,
    ndmin: Int? = null
): KtNDArray<T> =
    interpreter!!.callFunc(
        nameMethod = nameMethod, args = args,
        kwargs = argsToKwargs(out, where, axes, axis, keepdims, casting, order, dtype, subok, shape, ndmin)
    )

fun <T : Any> callFunc(
    nameMethod: Array<String>,
    args: Array<out Any>? = null,
    out: KtNDArray<T>? = null,
    where: BooleanArray? = null,
    axes: List<Int>? = null,
    axis: Int? = null,
    keepdims: Boolean? = null,
    casting: Casting? = null,
    order: Order? = null,
    dtype: KClass<Any>? = null,
    subok: Boolean? = null,
    kClass: KClass<out T>
): T =
    interpreter!!.callFunc(
        nameMethod = nameMethod, args = args,
        kwargs = argsToKwargs(out, where, axes, axis, keepdims, casting, order, dtype, subok),
        jClass = kClass.javaObjectType
    )

private fun <T : Any> argsToKwargs(
    out: KtNDArray<T>? = null,
    where: BooleanArray? = null,
    axes: List<Int>? = null,
    axis: Int? = null,
    keepdims: Boolean? = null,
    casting: Casting? = null,
    order: Order? = null,
    dtype: KClass<out Any>? = null,
    subok: Boolean? = null,
    shape: IntArray? = null,
    ndmin: Int? = null
): Map<String, Any>? {
    val kwargs: Map<String, Any> = HashMap<String, Any>().apply {
        if (out != null) this["out"] = out
        if (where != null) this["where"] = where
        if (axes != null) this["axes"] = axes
        if (axis != null) this["axis"] = axis
        if (keepdims != null) this["keepdims"] = keepdims
        if (casting != null) this["casting"] = casting.str
        if (order != null) this["order"] = order.name
        if (dtype != null) this["dtype"] = dtype.javaObjectType
        if (subok != null) this["subok"] = subok
        if (shape != null) this["shape"] = shape
        if (ndmin != null) this["ndmin"] = ndmin
    }
    return if (kwargs.isNotEmpty()) kwargs else null
}
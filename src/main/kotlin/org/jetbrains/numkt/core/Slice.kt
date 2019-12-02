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

fun Array<out Slice>.slicePythonView(): String = this.joinToString(prefix = "[", postfix = "]") { it.slice }

class Slice private constructor(val start: Int?, val stop: Int?, val step: Int?) {

    val slice: String = buildString {
        append(start ?: "None")
        append(':')
        append(stop ?: "None")
        append(':')
        append(step ?: "None")
    }

    operator fun rangeTo(step: Int): Slice = Slice(start, stop, step)

    operator fun rangeTo(step: None.Companion): Slice = Slice(start, stop, null)

    companion object {
        fun fromClosedSlice(sliceStart: Int?, sliceEnd: Int?, step: Int?): Slice =
            Slice(sliceStart, sliceEnd, step)
    }

    override fun toString(): String {
        return slice
    }
}

operator fun Int.rangeTo(other: None.Companion): Slice {
    return Slice.fromClosedSlice(this, null, 1)
}

operator fun IntProgression.rangeTo(step: Int): Slice {
    return Slice.fromClosedSlice(first, last, step)
}

operator fun IntProgression.rangeTo(other: None.Companion): Slice {
    return Slice.fromClosedSlice(first, last, step)
}

fun IntProgression.toSlice(): Slice {
    return Slice.fromClosedSlice(this.first, this.last, this.step)
}
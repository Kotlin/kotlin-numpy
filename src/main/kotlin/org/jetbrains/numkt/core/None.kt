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

/**
 * Need to convert to python None and impl slice.
 */
class None {
    companion object {
        operator fun rangeTo(other: Int): Slice = Slice.fromClosedSlice(null, other, 1)

        operator fun rangeTo(other: Companion): Slice =
            Slice.fromClosedSlice(null, null, 1)

        const val value: String = "None"
        val none = None()
    }

    override fun toString(): String {
        return value
    }
}
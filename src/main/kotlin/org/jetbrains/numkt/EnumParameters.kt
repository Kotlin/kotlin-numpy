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

/**
 * Controls what kind of data casting may occur when copying.
 */
enum class Casting(val str: String) {
    /** means the data types should not be cast at all. */
    NO("no"),
    /** means only byte-order changes are allowed. */
    EQUIV("equiv"),
    /** means only casts which can preserve values are allowed. */
    SAFE("safe"),
    /** means only safe casts or casts within a kind, like [Double] (float64) to [Float] (float32), are allowed. */
    SAME_KIND("same_kind"),
    /** means any data conversions may be done. */
    UNSAFE("unsafe"),
}

/**
 * Specifies how indices outside ```[0, n-1]``` will be treated
 */
enum class Mode(val str: String) {
    /** an exception is raised. */
    RAISE("raise"),
    /** value becomes value mod n. */
    WRAP("wrap"),
    /** values < 0 are mapped to 0, values > n - 1 are mapped to n - 1. */
    CLIP("clip")
}

/**
 * Specifies the order of iterations over an array.
 */
enum class Order {
    /** C-contiguous. */
    C,
    /** F-contiguous. */
    F,
    /** F-contiguous if the inputs are F-contiguous, C-contiguous otherwise. */
    A,
    /** default. */
    K
}
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
 *
 * @property NO - means the data types should not be cast at all.
 * @property EQUIV - means only byte-order changes are allowed.
 * @property SAFE - means only casts which can preserve values are allowed.
 * @property SAME_KIND - means only safe casts or casts within a kind, like [Double] (float64) to [Float] (float32), are allowed.
 * @property UNSAFE - means any data conversions may be done.
 */
enum class Casting(val str: String) {
    NO("no"),
    EQUIV("equiv"),
    SAFE("safe"),
    SAME_KIND("same_kind"),
    UNSAFE("unsafe"),
}

/**
 * Specifies how indices outside [0, n-1] will be treated
 *
 * @property RAISE - an exception is raised.
 * @property WRAP - value becomes value mod n.
 * @property CLIP - values < 0 are mapped to 0, values > n - 1 are mapped to n - 1.
 */
enum class Mode(val str: String) {
    RAISE("raise"),
    WRAP("wrap"),
    CLIP("clip")
}

enum class Order {
    C, F, A, K
}
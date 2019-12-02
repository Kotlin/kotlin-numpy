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

package org.jetbrains.numkt.math

import org.jetbrains.numkt.callFunc
import org.jetbrains.numkt.core.KtNDArray
import org.jetbrains.numkt.linalg.dot

// Plus
private fun <L: KtNDArray<*>, R: KtNDArray<*>> plusAssignTwoKtNDArray(first: L, second: R) {
    callFunc(nameMethod = arrayOf("ndarray", "__iadd__"), args = arrayOf(first, second), kClass = Unit::class)
}
private fun <T: KtNDArray<*>, S: Number> plusAssignScalar(array: T, scalar: S) {
    callFunc(nameMethod = arrayOf("ndarray", "__iadd__"), args = arrayOf(array, scalar), kClass = Unit::class)
}

private fun <T: Any, L: Any, R: Any> add(left: L, right: R): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("add"), args = arrayOf(left, right))

// Byte
@JvmName("bytePlusByte") operator fun KtNDArray<Byte>.plus(other: KtNDArray<Byte>): KtNDArray<Byte> = add(this, other)
@JvmName("bytePlusShort") operator fun KtNDArray<Byte>.plus(other: KtNDArray<Short>): KtNDArray<Short> = add(this, other)
@JvmName("bytePlusInt") operator fun KtNDArray<Byte>.plus(other: KtNDArray<Int>): KtNDArray<Int> = add(this, other)
@JvmName("bytePlusLong") operator fun KtNDArray<Byte>.plus(other: KtNDArray<Long>): KtNDArray<Long> = add(this, other)
@JvmName("bytePlusFloat") operator fun KtNDArray<Byte>.plus(other: KtNDArray<Float>): KtNDArray<Float> = add(this, other)
@JvmName("bytePlusDouble") operator fun KtNDArray<Byte>.plus(other: KtNDArray<Double>): KtNDArray<Double> = add(this, other)

@JvmName("bytePlusScalarByte") operator fun KtNDArray<Byte>.plus(other: Byte): KtNDArray<Byte> = add(this, other)
@JvmName("bytePlusScalarShort") operator fun KtNDArray<Byte>.plus(other: Short): KtNDArray<Byte> = add(this, other)
@JvmName("bytePlusScalarInt") operator fun KtNDArray<Byte>.plus(other: Int): KtNDArray<Byte> = add(this, other)
@JvmName("bytePlusScalarLong") operator fun KtNDArray<Byte>.plus(other: Long): KtNDArray<Byte> = add(this, other)
@JvmName("bytePlusScalarFloat") operator fun KtNDArray<Byte>.plus(other: Float): KtNDArray<Double> = add(this, other)
@JvmName("bytePlusScalarDouble") operator fun KtNDArray<Byte>.plus(other: Double): KtNDArray<Double> = add(this, other)

@JvmName("scalarBytePlusByte") operator fun <R: Number> Byte.plus(other: KtNDArray<R>): KtNDArray<R> = add(this, other)

@JvmName("bytePlusAssignNDByte") operator fun KtNDArray<Byte>.plusAssign(other: KtNDArray<Byte>) = plusAssignTwoKtNDArray(this, other)
@JvmName("bytePlusAssignNDShort") operator fun KtNDArray<Byte>.plusAssign(other: KtNDArray<Short>) = plusAssignTwoKtNDArray(this, other)
@JvmName("bytePlusAssignNDInt") operator fun KtNDArray<Byte>.plusAssign(other: KtNDArray<Int>) = plusAssignTwoKtNDArray(this, other)
@JvmName("bytePlusAssignNDLong") operator fun KtNDArray<Byte>.plusAssign(other: KtNDArray<Long>) = plusAssignTwoKtNDArray(this, other)

@JvmName("bytePlusAssignByte") operator fun KtNDArray<Byte>.plusAssign(other: Byte) = plusAssignScalar(this, other)
@JvmName("bytePlusAssignShort") operator fun KtNDArray<Byte>.plusAssign(other: Short) = plusAssignScalar(this, other)
@JvmName("bytePlusAssignInt") operator fun KtNDArray<Byte>.plusAssign(other: Int) = plusAssignScalar(this, other)
@JvmName("bytePlusAssignLong") operator fun KtNDArray<Byte>.plusAssign(other: Long) = plusAssignScalar(this, other)

// Short
@JvmName("shortPlusByte") operator fun KtNDArray<Short>.plus(other: KtNDArray<Byte>): KtNDArray<Short> = add(this, other)
@JvmName("shortPlusShort") operator fun KtNDArray<Short>.plus(other: KtNDArray<Short>): KtNDArray<Short> = add(this, other)
@JvmName("shortPlusInt") operator fun KtNDArray<Short>.plus(other: KtNDArray<Int>): KtNDArray<Int> = add(this, other)
@JvmName("shortPlusLong") operator fun KtNDArray<Short>.plus(other: KtNDArray<Long>): KtNDArray<Long> = add(this, other)
@JvmName("shortPlusFloat") operator fun KtNDArray<Short>.plus(other: KtNDArray<Float>): KtNDArray<Float> = add(this, other)
@JvmName("shortPlusDouble") operator fun KtNDArray<Short>.plus(other: KtNDArray<Double>): KtNDArray<Double> = add(this, other)

@JvmName("shortPlusScalarByte") operator fun KtNDArray<Short>.plus(other: Byte): KtNDArray<Short> = add(this, other)
@JvmName("shortPlusScalarShort") operator fun KtNDArray<Short>.plus(other: Short): KtNDArray<Short> = add(this, other)
@JvmName("shortPlusScalarInt") operator fun KtNDArray<Short>.plus(other: Int): KtNDArray<Short> = add(this, other)
@JvmName("shortPlusScalarLong") operator fun KtNDArray<Short>.plus(other: Long): KtNDArray<Short> = add(this, other)
@JvmName("shortPlusScalarFloat") operator fun KtNDArray<Short>.plus(other: Float): KtNDArray<Double> = add(this, other)
@JvmName("shortPlusScalarDouble") operator fun KtNDArray<Short>.plus(other: Double): KtNDArray<Double> = add(this, other)

@JvmName("scalarShortPlus") operator fun <R: Number> Short.plus(other: KtNDArray<R>): KtNDArray<R> = add(this, other)

@JvmName("shortPlusAssignNDByte") operator fun KtNDArray<Short>.plusAssign(other: KtNDArray<Byte>) = plusAssignTwoKtNDArray(this, other)
@JvmName("shortPlusAssignNDShort") operator fun KtNDArray<Short>.plusAssign(other: KtNDArray<Short>) = plusAssignTwoKtNDArray(this, other)
@JvmName("shortPlusAssignNDInt") operator fun KtNDArray<Short>.plusAssign(other: KtNDArray<Int>) = plusAssignTwoKtNDArray(this, other)
@JvmName("shortPlusAssignNDLong") operator fun KtNDArray<Short>.plusAssign(other: KtNDArray<Long>) = plusAssignTwoKtNDArray(this, other)

@JvmName("shortPlusAssignByte") operator fun KtNDArray<Short>.plusAssign(other: Byte) = plusAssignScalar(this, other)
@JvmName("shortPlusAssignShort") operator fun KtNDArray<Short>.plusAssign(other: Short) = plusAssignScalar(this, other)
@JvmName("shortPlusAssignInt") operator fun KtNDArray<Short>.plusAssign(other: Int) = plusAssignScalar(this, other)
@JvmName("shortPlusAssignLong") operator fun KtNDArray<Short>.plusAssign(other: Long) = plusAssignScalar(this, other)

// Int
@JvmName("intPlusByte") operator fun KtNDArray<Int>.plus(other: KtNDArray<Byte>): KtNDArray<Int> = add(this, other)
@JvmName("intPlusShort") operator fun KtNDArray<Int>.plus(other: KtNDArray<Short>): KtNDArray<Int> = add(this, other)
@JvmName("intPlusInt") operator fun KtNDArray<Int>.plus(other: KtNDArray<Int>): KtNDArray<Int> = add(this, other)
@JvmName("intPlusLong") operator fun KtNDArray<Int>.plus(other: KtNDArray<Long>): KtNDArray<Long> = add(this, other)
@JvmName("intPlusFloat") operator fun KtNDArray<Int>.plus(other: KtNDArray<Float>): KtNDArray<Double> = add(this, other)
@JvmName("intPlusDouble") operator fun KtNDArray<Int>.plus(other: KtNDArray<Double>): KtNDArray<Double> = add(this, other)

@JvmName("intPlusScalarByte") operator fun KtNDArray<Int>.plus(other: Byte): KtNDArray<Int> = add(this, other)
@JvmName("intPlusScalarShort") operator fun KtNDArray<Int>.plus(other: Short): KtNDArray<Int> = add(this, other)
@JvmName("intPlusScalarInt") operator fun KtNDArray<Int>.plus(other: Int): KtNDArray<Int> = add(this, other)
@JvmName("intPlusScalarLong") operator fun KtNDArray<Int>.plus(other: Long): KtNDArray<Int> = add(this, other)
@JvmName("intPlusScalarFloat") operator fun KtNDArray<Int>.plus(other: Float): KtNDArray<Double> = add(this, other)
@JvmName("intPlusScalarDouble") operator fun KtNDArray<Int>.plus(other: Double): KtNDArray<Double> = add(this, other)

@JvmName("scalarIntPlus") operator fun <R: Number> Int.plus(other: KtNDArray<R>): KtNDArray<R> = add(this, other)

@JvmName("intPlusAssignNDByte") operator fun KtNDArray<Int>.plusAssign(other: KtNDArray<Byte>) = plusAssignTwoKtNDArray(this, other)
@JvmName("intPlusAssignNDShort") operator fun KtNDArray<Int>.plusAssign(other: KtNDArray<Short>) = plusAssignTwoKtNDArray(this, other)
@JvmName("intPlusAssignNDInt") operator fun KtNDArray<Int>.plusAssign(other: KtNDArray<Int>) = plusAssignTwoKtNDArray(this, other)
@JvmName("intPlusAssignNDLong") operator fun KtNDArray<Int>.plusAssign(other: KtNDArray<Long>) = plusAssignTwoKtNDArray(this, other)

@JvmName("intPlusAssignByte") operator fun KtNDArray<Int>.plusAssign(other: Byte) = plusAssignScalar(this, other)
@JvmName("intPlusAssignShort") operator fun KtNDArray<Int>.plusAssign(other: Short) = plusAssignScalar(this, other)
@JvmName("intPlusAssignInt") operator fun KtNDArray<Int>.plusAssign(other: Int) = plusAssignScalar(this, other)
@JvmName("intPlusAssignLong") operator fun KtNDArray<Int>.plusAssign(other: Long) = plusAssignScalar(this, other)

// Long
@JvmName("longPlusByte") operator fun KtNDArray<Long>.plus(other: KtNDArray<Byte>): KtNDArray<Long> = add(this, other)
@JvmName("longPlusShort") operator fun KtNDArray<Long>.plus(other: KtNDArray<Short>): KtNDArray<Long> = add(this, other)
@JvmName("longPlusInt") operator fun KtNDArray<Long>.plus(other: KtNDArray<Int>): KtNDArray<Long> = add(this, other)
@JvmName("longPlusLong") operator fun KtNDArray<Long>.plus(other: KtNDArray<Long>): KtNDArray<Long> = add(this, other)
@JvmName("longPlusFloat") operator fun KtNDArray<Long>.plus(other: KtNDArray<Float>): KtNDArray<Double> = add(this, other)
@JvmName("longPlusDouble") operator fun KtNDArray<Long>.plus(other: KtNDArray<Double>): KtNDArray<Double> = add(this, other)

@JvmName("longPlusScalarByte") operator fun KtNDArray<Long>.plus(other: Byte): KtNDArray<Long> = add(this, other)
@JvmName("longPlusScalarShort") operator fun KtNDArray<Long>.plus(other: Short): KtNDArray<Long> = add(this, other)
@JvmName("longPlusScalarInt") operator fun KtNDArray<Long>.plus(other: Int): KtNDArray<Long> = add(this, other)
@JvmName("longPlusScalarLong") operator fun KtNDArray<Long>.plus(other: Long): KtNDArray<Long> = add(this, other)
@JvmName("longPlusScalarFloat") operator fun KtNDArray<Long>.plus(other: Float): KtNDArray<Double> = add(this, other)
@JvmName("longPlusScalarDouble") operator fun KtNDArray<Long>.plus(other: Double): KtNDArray<Double> = add(this, other)

@JvmName("scalarLongPlus") operator fun <R: Number> Long.plus(other: KtNDArray<R>): KtNDArray<R> = add(this, other)

@JvmName("longPlusAssignNDByte") operator fun KtNDArray<Long>.plusAssign(other: KtNDArray<Byte>) = plusAssignTwoKtNDArray(this, other)
@JvmName("longPlusAssignNDShort") operator fun KtNDArray<Long>.plusAssign(other: KtNDArray<Short>) = plusAssignTwoKtNDArray(this, other)
@JvmName("longPlusAssignNDInt") operator fun KtNDArray<Long>.plusAssign(other: KtNDArray<Int>) = plusAssignTwoKtNDArray(this, other)
@JvmName("longPlusAssignNDLong") operator fun KtNDArray<Long>.plusAssign(other: KtNDArray<Long>) = plusAssignTwoKtNDArray(this, other)

@JvmName("longPlusAssignByte") operator fun KtNDArray<Long>.plusAssign(other: Byte) = plusAssignScalar(this, other)
@JvmName("longPlusAssignShort") operator fun KtNDArray<Long>.plusAssign(other: Short) = plusAssignScalar(this, other)
@JvmName("longPlusAssignInt") operator fun KtNDArray<Long>.plusAssign(other: Int) = plusAssignScalar(this, other)
@JvmName("longPlusAssignLong") operator fun KtNDArray<Long>.plusAssign(other: Long) = plusAssignScalar(this, other)

// Float
@JvmName("floatPlusByte") operator fun KtNDArray<Float>.plus(other: KtNDArray<Byte>): KtNDArray<Float> = add(this, other)
@JvmName("floatPlusShort") operator fun KtNDArray<Float>.plus(other: KtNDArray<Short>): KtNDArray<Float> = add(this, other)
@JvmName("floatPlusInt") operator fun KtNDArray<Float>.plus(other: KtNDArray<Int>): KtNDArray<Double> = add(this, other)
@JvmName("floatPlusLong") operator fun KtNDArray<Float>.plus(other: KtNDArray<Long>): KtNDArray<Double> = add(this, other)
@JvmName("floatPlusFloat") operator fun KtNDArray<Float>.plus(other: KtNDArray<Float>): KtNDArray<Float> = add(this, other)
@JvmName("floatPlusDouble") operator fun KtNDArray<Float>.plus(other: KtNDArray<Double>): KtNDArray<Double> = add(this, other)

@JvmName("floatPlusScalarByte") operator fun KtNDArray<Float>.plus(other: Byte): KtNDArray<Float> = add(this, other)
@JvmName("floatPlusScalarShort") operator fun KtNDArray<Float>.plus(other: Short): KtNDArray<Float> = add(this, other)
@JvmName("floatPlusScalarInt") operator fun KtNDArray<Float>.plus(other: Int): KtNDArray<Float> = add(this, other)
@JvmName("floatPlusScalarLong") operator fun KtNDArray<Float>.plus(other: Long): KtNDArray<Float> = add(this, other)
@JvmName("floatPlusScalarFloat") operator fun KtNDArray<Float>.plus(other: Float): KtNDArray<Float> = add(this, other)
@JvmName("floatPlusScalarDouble") operator fun KtNDArray<Float>.plus(other: Double): KtNDArray<Float> = add(this, other)

@JvmName("scalarFloatPlusByte") operator fun Float.plus(other: KtNDArray<Byte>): KtNDArray<Double> = add(this, other)
@JvmName("scalarFloatPlusShort") operator fun Float.plus(other: KtNDArray<Short>): KtNDArray<Double> = add(this, other)
@JvmName("scalarFloatPlusInt") operator fun Float.plus(other: KtNDArray<Int>): KtNDArray<Double> = add(this, other)
@JvmName("scalarFloatPlusLong") operator fun Float.plus(other: KtNDArray<Long>): KtNDArray<Double> = add(this, other)
@JvmName("scalarFloatPlusFloat") operator fun Float.plus(other: KtNDArray<Float>): KtNDArray<Float> = add(this, other)
@JvmName("scalarFloatPlusDouble") operator fun Float.plus(other: KtNDArray<Double>): KtNDArray<Double> = add(this, other)

@JvmName("floatPlusAssignNDByte") operator fun KtNDArray<Float>.plusAssign(other: KtNDArray<Byte>) = plusAssignTwoKtNDArray(this, other)
@JvmName("floatPlusAssignNDShort") operator fun KtNDArray<Float>.plusAssign(other: KtNDArray<Short>) = plusAssignTwoKtNDArray(this, other)
@JvmName("floatPlusAssignNDInt") operator fun KtNDArray<Float>.plusAssign(other: KtNDArray<Int>) = plusAssignTwoKtNDArray(this, other)
@JvmName("floatPlusAssignNDLong") operator fun KtNDArray<Float>.plusAssign(other: KtNDArray<Long>) = plusAssignTwoKtNDArray(this, other)
@JvmName("floatPlusAssignNDFloat") operator fun KtNDArray<Float>.plusAssign(other: KtNDArray<Float>) = plusAssignTwoKtNDArray(this, other)
@JvmName("floatPlusAssignNDDouble") operator fun KtNDArray<Float>.plusAssign(other: KtNDArray<Double>) = plusAssignTwoKtNDArray(this, other)

@JvmName("floatPlusAssignByte") operator fun KtNDArray<Float>.plusAssign(other: Byte) = plusAssignScalar(this, other)
@JvmName("floatPlusAssignShort") operator fun KtNDArray<Float>.plusAssign(other: Short) = plusAssignScalar(this, other)
@JvmName("floatPlusAssignInt") operator fun KtNDArray<Float>.plusAssign(other: Int) = plusAssignScalar(this, other)
@JvmName("floatPlusAssignLong") operator fun KtNDArray<Float>.plusAssign(other: Long) = plusAssignScalar(this, other)
@JvmName("floatPlusAssignFloat") operator fun KtNDArray<Float>.plusAssign(other: Float) = plusAssignScalar(this, other)
@JvmName("floatPlusAssignDouble") operator fun KtNDArray<Float>.plusAssign(other: Double) = plusAssignScalar(this, other)

// Double
@JvmName("doublePlusByte") operator fun KtNDArray<Double>.plus(other: KtNDArray<Byte>): KtNDArray<Double> = add(this, other)
@JvmName("doublePlusShort") operator fun KtNDArray<Double>.plus(other: KtNDArray<Short>): KtNDArray<Double> = add(this, other)
@JvmName("doublePlusInt") operator fun KtNDArray<Double>.plus(other: KtNDArray<Int>): KtNDArray<Double> = add(this, other)
@JvmName("doublePlusLong") operator fun KtNDArray<Double>.plus(other: KtNDArray<Long>): KtNDArray<Double> = add(this, other)
@JvmName("doublePlusFloat") operator fun KtNDArray<Double>.plus(other: KtNDArray<Float>): KtNDArray<Double> = add(this, other)
@JvmName("doublePlusDouble") operator fun KtNDArray<Double>.plus(other: KtNDArray<Double>): KtNDArray<Double> = add(this, other)

@JvmName("doublePlusScalarByte") operator fun KtNDArray<Double>.plus(other: Byte): KtNDArray<Double> = add(this, other)
@JvmName("doublePlusScalarShort") operator fun KtNDArray<Double>.plus(other: Short): KtNDArray<Double> = add(this, other)
@JvmName("doublePlusScalarInt") operator fun KtNDArray<Double>.plus(other: Int): KtNDArray<Double> = add(this, other)
@JvmName("doublePlusScalarLong") operator fun KtNDArray<Double>.plus(other: Long): KtNDArray<Double> = add(this, other)
@JvmName("doublePlusScalarFloat") operator fun KtNDArray<Double>.plus(other: Float): KtNDArray<Double> = add(this, other)
@JvmName("doublePlusScalarDouble") operator fun KtNDArray<Double>.plus(other: Double): KtNDArray<Double> = add(this, other)

@JvmName("scalarDoublePlusByte") operator fun Double.plus(other: KtNDArray<Byte>): KtNDArray<Double> = add(this, other)
@JvmName("scalarDoublePlusShort") operator fun Double.plus(other: KtNDArray<Short>): KtNDArray<Double> = add(this, other)
@JvmName("scalarDoublePlusInt") operator fun Double.plus(other: KtNDArray<Int>): KtNDArray<Double> = add(this, other)
@JvmName("scalarDoublePlusLong") operator fun Double.plus(other: KtNDArray<Long>): KtNDArray<Double> = add(this, other)
@JvmName("scalarDoublePlusFloat") operator fun Double.plus(other: KtNDArray<Float>): KtNDArray<Float> = add(this, other)
@JvmName("scalarDoublePlusDouble") operator fun Double.plus(other: KtNDArray<Double>): KtNDArray<Double> = add(this, other)

@JvmName("doublePlusAssignByte") operator fun KtNDArray<Double>.plusAssign(other: KtNDArray<Byte>) = plusAssignTwoKtNDArray(this, other)
@JvmName("doublePlusAssignShort") operator fun KtNDArray<Double>.plusAssign(other: KtNDArray<Short>) = plusAssignTwoKtNDArray(this, other)
@JvmName("doublePlusAssignInt") operator fun KtNDArray<Double>.plusAssign(other: KtNDArray<Int>) = plusAssignTwoKtNDArray(this, other)
@JvmName("doublePlusAssignLong") operator fun KtNDArray<Double>.plusAssign(other: KtNDArray<Long>) = plusAssignTwoKtNDArray(this, other)
@JvmName("doublePlusAssignFloat") operator fun KtNDArray<Double>.plusAssign(other: KtNDArray<Float>) = plusAssignTwoKtNDArray(this, other)
@JvmName("doublePlusAssignDouble") operator fun KtNDArray<Double>.plusAssign(other: KtNDArray<Double>) = plusAssignTwoKtNDArray(this, other)

@JvmName("doublePlusAssignByte") operator fun KtNDArray<Double>.plusAssign(other: Byte) = plusAssignScalar(this, other)
@JvmName("doublePlusAssignShort") operator fun KtNDArray<Double>.plusAssign(other: Short) = plusAssignScalar(this, other)
@JvmName("doublePlusAssignInt") operator fun KtNDArray<Double>.plusAssign(other: Int) = plusAssignScalar(this, other)
@JvmName("doublePlusAssignLong") operator fun KtNDArray<Double>.plusAssign(other: Long) = plusAssignScalar(this, other)
@JvmName("doublePlusAssignFloat") operator fun KtNDArray<Double>.plusAssign(other: Float) = plusAssignScalar(this, other)
@JvmName("doublePlusAssignDouble") operator fun KtNDArray<Double>.plusAssign(other: Double) = plusAssignScalar(this, other)


// Subtract
private fun <L: KtNDArray<*>, R: KtNDArray<*>> minusAssignTwoKtNDArray(first: L, second: R) {
    callFunc(nameMethod = arrayOf("ndarray", "__isub__"), args = arrayOf(first, second), kClass = Unit::class)
}
private fun <T: KtNDArray<*>, S: Number> minusAssignScalar(array: T, scalar: S) {
    callFunc(nameMethod = arrayOf("ndarray", "__isub__"), args = arrayOf(array, scalar), kClass = Unit::class)
}

private fun <T: Any, L: Any, R: Any> subtract(left: L, right: R): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("subtract"), args = arrayOf(left, right))


// Byte
@JvmName("byteMinusByte") operator fun KtNDArray<Byte>.minus(other: KtNDArray<Byte>): KtNDArray<Byte> = subtract(this, other)
@JvmName("byteMinusShort") operator fun KtNDArray<Byte>.minus(other: KtNDArray<Short>): KtNDArray<Short> = subtract(this, other)
@JvmName("byteMinusInt") operator fun KtNDArray<Byte>.minus(other: KtNDArray<Int>): KtNDArray<Int> = subtract(this, other)
@JvmName("byteMinusLong") operator fun KtNDArray<Byte>.minus(other: KtNDArray<Long>): KtNDArray<Long> = subtract(this, other)
@JvmName("byteMinusFloat") operator fun KtNDArray<Byte>.minus(other: KtNDArray<Float>): KtNDArray<Float> = subtract(this, other)
@JvmName("byteMinusDouble") operator fun KtNDArray<Byte>.minus(other: KtNDArray<Double>): KtNDArray<Double> = subtract(this, other)

@JvmName("byteMinusScalarByte") operator fun KtNDArray<Byte>.minus(other: Byte): KtNDArray<Byte> = subtract(this, other)
@JvmName("byteMinusScalarShort") operator fun KtNDArray<Byte>.minus(other: Short): KtNDArray<Byte> = subtract(this, other)
@JvmName("byteMinusScalarInt") operator fun KtNDArray<Byte>.minus(other: Int): KtNDArray<Byte> = subtract(this, other)
@JvmName("byteMinusScalarLong") operator fun KtNDArray<Byte>.minus(other: Long): KtNDArray<Byte> = subtract(this, other)
@JvmName("byteMinusScalarFloat") operator fun KtNDArray<Byte>.minus(other: Float): KtNDArray<Double> = subtract(this, other)
@JvmName("byteMinusScalarDouble") operator fun KtNDArray<Byte>.minus(other: Double): KtNDArray<Double> = subtract(this, other)

@JvmName("scalarByteMinus") operator fun <R: Number> Byte.minus(other: KtNDArray<R>): KtNDArray<R> = subtract(this, other)

@JvmName("byteMinusAssignNDByte") operator fun KtNDArray<Byte>.minusAssign(other: KtNDArray<Byte>) = minusAssignTwoKtNDArray(this, other)
@JvmName("byteMinusAssignNDShort") operator fun KtNDArray<Byte>.minusAssign(other: KtNDArray<Short>) = minusAssignTwoKtNDArray(this, other)
@JvmName("byteMinusAssignNDInt") operator fun KtNDArray<Byte>.minusAssign(other: KtNDArray<Int>) = minusAssignTwoKtNDArray(this, other)
@JvmName("byteMinusAssignNDLong") operator fun KtNDArray<Byte>.minusAssign(other: KtNDArray<Long>) = minusAssignTwoKtNDArray(this, other)

@JvmName("byteMinusAssignByte") operator fun KtNDArray<Byte>.minusAssign(other: Byte) = minusAssignScalar(this, other)
@JvmName("byteMinusAssignShort") operator fun KtNDArray<Byte>.minusAssign(other: Short) = minusAssignScalar(this, other)
@JvmName("byteMinusAssignInt") operator fun KtNDArray<Byte>.minusAssign(other: Int) = minusAssignScalar(this, other)
@JvmName("byteMinusAssignLong") operator fun KtNDArray<Byte>.minusAssign(other: Long) = minusAssignScalar(this, other)

// Short
@JvmName("shortMinusByte") operator fun KtNDArray<Short>.minus(other: KtNDArray<Byte>): KtNDArray<Short> = subtract(this, other)
@JvmName("shortMinusShort") operator fun KtNDArray<Short>.minus(other: KtNDArray<Short>): KtNDArray<Short> = subtract(this, other)
@JvmName("shortMinusInt") operator fun KtNDArray<Short>.minus(other: KtNDArray<Int>): KtNDArray<Int> = subtract(this, other)
@JvmName("shortMinusLong") operator fun KtNDArray<Short>.minus(other: KtNDArray<Long>): KtNDArray<Long> = subtract(this, other)
@JvmName("shortMinusFloat") operator fun KtNDArray<Short>.minus(other: KtNDArray<Float>): KtNDArray<Float> = subtract(this, other)
@JvmName("shortMinusDouble") operator fun KtNDArray<Short>.minus(other: KtNDArray<Double>): KtNDArray<Double> = subtract(this, other)

@JvmName("shortMinusScalarByte") operator fun KtNDArray<Short>.minus(other: Byte): KtNDArray<Short> = subtract(this, other)
@JvmName("shortMinusScalarShort") operator fun KtNDArray<Short>.minus(other: Short): KtNDArray<Short> = subtract(this, other)
@JvmName("shortMinusScalarInt") operator fun KtNDArray<Short>.minus(other: Int): KtNDArray<Short> = subtract(this, other)
@JvmName("shortMinusScalarLong") operator fun KtNDArray<Short>.minus(other: Long): KtNDArray<Short> = subtract(this, other)
@JvmName("shortMinusScalarFloat") operator fun KtNDArray<Short>.minus(other: Float): KtNDArray<Double> = subtract(this, other)
@JvmName("shortMinusScalarDouble") operator fun KtNDArray<Short>.minus(other: Double): KtNDArray<Double> = subtract(this, other)

@JvmName("scalarShortMinus") operator fun <R: Number> Short.minus(other: KtNDArray<R>): KtNDArray<R> = subtract(this, other)

@JvmName("shortMinusAssignNDByte") operator fun KtNDArray<Short>.minusAssign(other: KtNDArray<Byte>) = minusAssignTwoKtNDArray(this, other)
@JvmName("shortMinusAssignNDShort") operator fun KtNDArray<Short>.minusAssign(other: KtNDArray<Short>) = minusAssignTwoKtNDArray(this, other)
@JvmName("shortMinusAssignNDInt") operator fun KtNDArray<Short>.minusAssign(other: KtNDArray<Int>) = minusAssignTwoKtNDArray(this, other)
@JvmName("shortMinusAssignNDLong") operator fun KtNDArray<Short>.minusAssign(other: KtNDArray<Long>) = minusAssignTwoKtNDArray(this, other)

@JvmName("shortMinusAssignByte") operator fun KtNDArray<Short>.minusAssign(other: Byte) = minusAssignScalar(this, other)
@JvmName("shortMinusAssignShort") operator fun KtNDArray<Short>.minusAssign(other: Short) = minusAssignScalar(this, other)
@JvmName("shortMinusAssignInt") operator fun KtNDArray<Short>.minusAssign(other: Int) = minusAssignScalar(this, other)
@JvmName("shortMinusAssignLong") operator fun KtNDArray<Short>.minusAssign(other: Long) = minusAssignScalar(this, other)

// Int
@JvmName("intMinusByte") operator fun KtNDArray<Int>.minus(other: KtNDArray<Byte>): KtNDArray<Int> = subtract(this, other)
@JvmName("intMinusShort") operator fun KtNDArray<Int>.minus(other: KtNDArray<Short>): KtNDArray<Int> = subtract(this, other)
@JvmName("intMinusInt") operator fun KtNDArray<Int>.minus(other: KtNDArray<Int>): KtNDArray<Int> = subtract(this, other)
@JvmName("intMinusLong") operator fun KtNDArray<Int>.minus(other: KtNDArray<Long>): KtNDArray<Long> = subtract(this, other)
@JvmName("intMinusFloat") operator fun KtNDArray<Int>.minus(other: KtNDArray<Float>): KtNDArray<Double> = subtract(this, other)
@JvmName("intMinusDouble") operator fun KtNDArray<Int>.minus(other: KtNDArray<Double>): KtNDArray<Double> = subtract(this, other)

@JvmName("intMinusScalarByte") operator fun KtNDArray<Int>.minus(other: Byte): KtNDArray<Int> = subtract(this, other)
@JvmName("intMinusScalarShort") operator fun KtNDArray<Int>.minus(other: Short): KtNDArray<Int> = subtract(this, other)
@JvmName("intMinusScalarInt") operator fun KtNDArray<Int>.minus(other: Int): KtNDArray<Int> = subtract(this, other)
@JvmName("intMinusScalarLong") operator fun KtNDArray<Int>.minus(other: Long): KtNDArray<Int> = subtract(this, other)
@JvmName("intMinusScalarFloat") operator fun KtNDArray<Int>.minus(other: Float): KtNDArray<Double> = subtract(this, other)
@JvmName("intMinusScalarDouble") operator fun KtNDArray<Int>.minus(other: Double): KtNDArray<Double> = subtract(this, other)

@JvmName("scalarIntMinus") operator fun <R: Number> Int.minus(other: KtNDArray<R>): KtNDArray<R> = subtract(this, other)

@JvmName("intMinusAssignNDByte") operator fun KtNDArray<Int>.minusAssign(other: KtNDArray<Byte>) = minusAssignTwoKtNDArray(this, other)
@JvmName("intMinusAssignNDShort") operator fun KtNDArray<Int>.minusAssign(other: KtNDArray<Short>) = minusAssignTwoKtNDArray(this, other)
@JvmName("intMinusAssignNDInt") operator fun KtNDArray<Int>.minusAssign(other: KtNDArray<Int>) = minusAssignTwoKtNDArray(this, other)
@JvmName("intMinusAssignNDLong") operator fun KtNDArray<Int>.minusAssign(other: KtNDArray<Long>) = minusAssignTwoKtNDArray(this, other)

@JvmName("intMinusAssignByte") operator fun KtNDArray<Int>.minusAssign(other: Byte) = minusAssignScalar(this, other)
@JvmName("intMinusAssignShort") operator fun KtNDArray<Int>.minusAssign(other: Short) = minusAssignScalar(this, other)
@JvmName("intMinusAssignInt") operator fun KtNDArray<Int>.minusAssign(other: Int) = minusAssignScalar(this, other)
@JvmName("intMinusAssignLong") operator fun KtNDArray<Int>.minusAssign(other: Long) = minusAssignScalar(this, other)

// Long
@JvmName("longMinusByte") operator fun KtNDArray<Long>.minus(other: KtNDArray<Byte>): KtNDArray<Long> = subtract(this, other)
@JvmName("longMinusShort") operator fun KtNDArray<Long>.minus(other: KtNDArray<Short>): KtNDArray<Long> = subtract(this, other)
@JvmName("longMinusInt") operator fun KtNDArray<Long>.minus(other: KtNDArray<Int>): KtNDArray<Long> = subtract(this, other)
@JvmName("longMinusLong") operator fun KtNDArray<Long>.minus(other: KtNDArray<Long>): KtNDArray<Long> = subtract(this, other)
@JvmName("longMinusFloat") operator fun KtNDArray<Long>.minus(other: KtNDArray<Float>): KtNDArray<Double> = subtract(this, other)
@JvmName("longMinusDouble") operator fun KtNDArray<Long>.minus(other: KtNDArray<Double>): KtNDArray<Double> = subtract(this, other)

@JvmName("longMinusScalarByte") operator fun KtNDArray<Long>.minus(other: Byte): KtNDArray<Long> = subtract(this, other)
@JvmName("longMinusScalarShort") operator fun KtNDArray<Long>.minus(other: Short): KtNDArray<Long> = subtract(this, other)
@JvmName("longMinusScalarInt") operator fun KtNDArray<Long>.minus(other: Int): KtNDArray<Long> = subtract(this, other)
@JvmName("longMinusScalarLong") operator fun KtNDArray<Long>.minus(other: Long): KtNDArray<Long> = subtract(this, other)
@JvmName("longMinusScalarFloat") operator fun KtNDArray<Long>.minus(other: Float): KtNDArray<Double> = subtract(this, other)
@JvmName("longMinusScalarDouble") operator fun KtNDArray<Long>.minus(other: Double): KtNDArray<Double> = subtract(this, other)

@JvmName("scalarLongMinus") operator fun <R: Number> Long.minus(other: KtNDArray<R>): KtNDArray<R> = subtract(this, other)

@JvmName("longMinusAssignNDByte") operator fun KtNDArray<Long>.minusAssign(other: KtNDArray<Byte>) = minusAssignTwoKtNDArray(this, other)
@JvmName("longMinusAssignNDShort") operator fun KtNDArray<Long>.minusAssign(other: KtNDArray<Short>) = minusAssignTwoKtNDArray(this, other)
@JvmName("longMinusAssignNDInt") operator fun KtNDArray<Long>.minusAssign(other: KtNDArray<Int>) = minusAssignTwoKtNDArray(this, other)
@JvmName("longMinusAssignNDLong") operator fun KtNDArray<Long>.minusAssign(other: KtNDArray<Long>) = minusAssignTwoKtNDArray(this, other)

@JvmName("longMinusAssignByte") operator fun KtNDArray<Long>.minusAssign(other: Byte) = minusAssignScalar(this, other)
@JvmName("longMinusAssignShort") operator fun KtNDArray<Long>.minusAssign(other: Short) = minusAssignScalar(this, other)
@JvmName("longMinusAssignInt") operator fun KtNDArray<Long>.minusAssign(other: Int) = minusAssignScalar(this, other)
@JvmName("longMinusAssignLong") operator fun KtNDArray<Long>.minusAssign(other: Long) = minusAssignScalar(this, other)


// Float
@JvmName("floatMinusByte") operator fun KtNDArray<Float>.minus(other: KtNDArray<Byte>): KtNDArray<Float> = subtract(this, other)
@JvmName("floatMinusShort") operator fun KtNDArray<Float>.minus(other: KtNDArray<Short>): KtNDArray<Float> = subtract(this, other)
@JvmName("floatMinusInt") operator fun KtNDArray<Float>.minus(other: KtNDArray<Int>): KtNDArray<Double> = subtract(this, other)
@JvmName("floatMinusLong") operator fun KtNDArray<Float>.minus(other: KtNDArray<Long>): KtNDArray<Double> = subtract(this, other)
@JvmName("floatMinusFloat") operator fun KtNDArray<Float>.minus(other: KtNDArray<Float>): KtNDArray<Float> = subtract(this, other)
@JvmName("floatMinusDouble") operator fun KtNDArray<Float>.minus(other: KtNDArray<Double>): KtNDArray<Double> = subtract(this, other)

@JvmName("floatMinusScalarByte") operator fun KtNDArray<Float>.minus(other: Byte): KtNDArray<Float> = subtract(this, other)
@JvmName("floatMinusScalarShort") operator fun KtNDArray<Float>.minus(other: Short): KtNDArray<Float> = subtract(this, other)
@JvmName("floatMinusScalarInt") operator fun KtNDArray<Float>.minus(other: Int): KtNDArray<Float> = subtract(this, other)
@JvmName("floatMinusScalarLong") operator fun KtNDArray<Float>.minus(other: Long): KtNDArray<Float> = subtract(this, other)
@JvmName("floatMinusScalarFloat") operator fun KtNDArray<Float>.minus(other: Float): KtNDArray<Float> = subtract(this, other)
@JvmName("floatMinusScalarDouble") operator fun KtNDArray<Float>.minus(other: Double): KtNDArray<Float> = subtract(this, other)

@JvmName("scalarFloatMinusByte") operator fun Float.minus(other: KtNDArray<Byte>): KtNDArray<Double> = subtract(this, other)
@JvmName("scalarFloatMinusShort") operator fun Float.minus(other: KtNDArray<Short>): KtNDArray<Double> = subtract(this, other)
@JvmName("scalarFloatMinusInt") operator fun Float.minus(other: KtNDArray<Int>): KtNDArray<Double> = subtract(this, other)
@JvmName("scalarFloatMinusLong") operator fun Float.minus(other: KtNDArray<Long>): KtNDArray<Double> = subtract(this, other)
@JvmName("scalarFloatMinusFloat") operator fun Float.minus(other: KtNDArray<Float>): KtNDArray<Float> = subtract(this, other)
@JvmName("scalarFloatMinusDouble") operator fun Float.minus(other: KtNDArray<Double>): KtNDArray<Double> = subtract(this, other)

@JvmName("floatMinusAssignNDByte") operator fun KtNDArray<Float>.minusAssign(other: KtNDArray<Byte>) = minusAssignTwoKtNDArray(this, other)
@JvmName("floatMinusAssignNDShort") operator fun KtNDArray<Float>.minusAssign(other: KtNDArray<Short>) = minusAssignTwoKtNDArray(this, other)
@JvmName("floatMinusAssignNDInt") operator fun KtNDArray<Float>.minusAssign(other: KtNDArray<Int>) = minusAssignTwoKtNDArray(this, other)
@JvmName("floatMinusAssignNDLong") operator fun KtNDArray<Float>.minusAssign(other: KtNDArray<Long>) = minusAssignTwoKtNDArray(this, other)
@JvmName("floatMinusAssignNDFloat") operator fun KtNDArray<Float>.minusAssign(other: KtNDArray<Float>) = minusAssignTwoKtNDArray(this, other)
@JvmName("floatMinusAssignNDDouble") operator fun KtNDArray<Float>.minusAssign(other: KtNDArray<Double>) = minusAssignTwoKtNDArray(this, other)

@JvmName("floatMinusAssignByte") operator fun KtNDArray<Float>.minusAssign(other: Byte) = minusAssignScalar(this, other)
@JvmName("floatMinusAssignShort") operator fun KtNDArray<Float>.minusAssign(other: Short) = minusAssignScalar(this, other)
@JvmName("floatMinusAssignInt") operator fun KtNDArray<Float>.minusAssign(other: Int) = minusAssignScalar(this, other)
@JvmName("floatMinusAssignLong") operator fun KtNDArray<Float>.minusAssign(other: Long) = minusAssignScalar(this, other)
@JvmName("floatMinusAssignFloat") operator fun KtNDArray<Float>.minusAssign(other: Float) = minusAssignScalar(this, other)
@JvmName("floatMinusAssignDouble") operator fun KtNDArray<Float>.minusAssign(other: Double) = minusAssignScalar(this, other)

// Double
@JvmName("doubleMinusByte") operator fun KtNDArray<Double>.minus(other: KtNDArray<Byte>): KtNDArray<Double> = subtract(this, other)
@JvmName("doubleMinusShort") operator fun KtNDArray<Double>.minus(other: KtNDArray<Short>): KtNDArray<Double> = subtract(this, other)
@JvmName("doubleMinusInt") operator fun KtNDArray<Double>.minus(other: KtNDArray<Int>): KtNDArray<Double> = subtract(this, other)
@JvmName("doubleMinusLong") operator fun KtNDArray<Double>.minus(other: KtNDArray<Long>): KtNDArray<Double> = subtract(this, other)
@JvmName("doubleMinusFloat") operator fun KtNDArray<Double>.minus(other: KtNDArray<Float>): KtNDArray<Double> = subtract(this, other)
@JvmName("doubleMinusDouble") operator fun KtNDArray<Double>.minus(other: KtNDArray<Double>): KtNDArray<Double> = subtract(this, other)

@JvmName("doubleMinusScalarByte") operator fun KtNDArray<Double>.minus(other: Byte): KtNDArray<Double> = subtract(this, other)
@JvmName("doubleMinusScalarShort") operator fun KtNDArray<Double>.minus(other: Short): KtNDArray<Double> = subtract(this, other)
@JvmName("doubleMinusScalarInt") operator fun KtNDArray<Double>.minus(other: Int): KtNDArray<Double> = subtract(this, other)
@JvmName("doubleMinusScalarLong") operator fun KtNDArray<Double>.minus(other: Long): KtNDArray<Double> = subtract(this, other)
@JvmName("doubleMinusScalarFloat") operator fun KtNDArray<Double>.minus(other: Float): KtNDArray<Double> = subtract(this, other)
@JvmName("doubleMinusScalarDouble") operator fun KtNDArray<Double>.minus(other: Double): KtNDArray<Double> = subtract(this, other)

@JvmName("scalarDoubleMinusByte") operator fun Double.minus(other: KtNDArray<Byte>): KtNDArray<Double> = subtract(this, other)
@JvmName("scalarDoubleMinusShort") operator fun Double.minus(other: KtNDArray<Short>): KtNDArray<Double> = subtract(this, other)
@JvmName("scalarDoubleMinusInt") operator fun Double.minus(other: KtNDArray<Int>): KtNDArray<Double> = subtract(this, other)
@JvmName("scalarDoubleMinusLong") operator fun Double.minus(other: KtNDArray<Long>): KtNDArray<Double> = subtract(this, other)
@JvmName("scalarDoubleMinusFloat") operator fun Double.minus(other: KtNDArray<Float>): KtNDArray<Float> = subtract(this, other)
@JvmName("scalarDoubleMinusDouble") operator fun Double.minus(other: KtNDArray<Double>): KtNDArray<Double> = subtract(this, other)

@JvmName("doubleMinusAssignNDByte") operator fun KtNDArray<Double>.minusAssign(other: KtNDArray<Byte>) = minusAssignTwoKtNDArray(this, other)
@JvmName("doubleMinusAssignNDShort") operator fun KtNDArray<Double>.minusAssign(other: KtNDArray<Short>) = minusAssignTwoKtNDArray(this, other)
@JvmName("doubleMinusAssignNDInt") operator fun KtNDArray<Double>.minusAssign(other: KtNDArray<Int>) = minusAssignTwoKtNDArray(this, other)
@JvmName("doubleMinusAssignNDLong") operator fun KtNDArray<Double>.minusAssign(other: KtNDArray<Long>) = minusAssignTwoKtNDArray(this, other)
@JvmName("doubleMinusAssignNDFloat") operator fun KtNDArray<Double>.minusAssign(other: KtNDArray<Float>) = minusAssignTwoKtNDArray(this, other)
@JvmName("doubleMinusAssignNDDouble") operator fun KtNDArray<Double>.minusAssign(other: KtNDArray<Double>) = minusAssignTwoKtNDArray(this, other)

@JvmName("doubleMinusAssignByte") operator fun KtNDArray<Double>.minusAssign(other: Byte) = minusAssignScalar(this, other)
@JvmName("doubleMinusAssignShort") operator fun KtNDArray<Double>.minusAssign(other: Short) = minusAssignScalar(this, other)
@JvmName("doubleMinusAssignInt") operator fun KtNDArray<Double>.minusAssign(other: Int) = minusAssignScalar(this, other)
@JvmName("doubleMinusAssignLong") operator fun KtNDArray<Double>.minusAssign(other: Long) = minusAssignScalar(this, other)
@JvmName("doubleMinusAssignFloat") operator fun KtNDArray<Double>.minusAssign(other: Float) = minusAssignScalar(this, other)
@JvmName("doubleMinusAssignDouble") operator fun KtNDArray<Double>.minusAssign(other: Double) = minusAssignScalar(this, other)

// Multiply
private fun <L: KtNDArray<*>, R: KtNDArray<*>> timesAssignTwoKtNDArray(first: L, second: R) {
    callFunc(nameMethod = arrayOf("ndarray", "__imul__"), args = arrayOf(first, second), kClass = Unit::class)
}
private fun <T: KtNDArray<*>, S: Number> timesAssignScalar(array: T, scalar: S) {
    callFunc(nameMethod = arrayOf("ndarray", "__imul__"), args = arrayOf(array, scalar), kClass = Unit::class)
}

private fun <T: Any, L: Any, R: Any> multiply(left: L, right: R): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("multiply"), args = arrayOf(left, right))


// Byte
@JvmName("byteMultiplyByte") operator fun KtNDArray<Byte>.times(other: KtNDArray<Byte>): KtNDArray<Byte> = multiply(this, other)
@JvmName("byteMultiplyShort") operator fun KtNDArray<Byte>.times(other: KtNDArray<Short>): KtNDArray<Short> = multiply(this, other)
@JvmName("byteMultiplyInt") operator fun KtNDArray<Byte>.times(other: KtNDArray<Int>): KtNDArray<Int> = multiply(this, other)
@JvmName("byteMultiplyLong") operator fun KtNDArray<Byte>.times(other: KtNDArray<Long>): KtNDArray<Long> = multiply(this, other)
@JvmName("byteMultiplyFloat") operator fun KtNDArray<Byte>.times(other: KtNDArray<Float>): KtNDArray<Float> = multiply(this, other)
@JvmName("byteMultiplyDouble") operator fun KtNDArray<Byte>.times(other: KtNDArray<Double>): KtNDArray<Double> = multiply(this, other)

@JvmName("byteMultiplyScalarByte") operator fun KtNDArray<Byte>.times(other: Byte): KtNDArray<Byte> = multiply(this, other)
@JvmName("byteMultiplyScalarShort") operator fun KtNDArray<Byte>.times(other: Short): KtNDArray<Byte> = multiply(this, other)
@JvmName("byteMultiplyScalarInt") operator fun KtNDArray<Byte>.times(other: Int): KtNDArray<Byte> = multiply(this, other)
@JvmName("byteMultiplyScalarLong") operator fun KtNDArray<Byte>.times(other: Long): KtNDArray<Byte> = multiply(this, other)
@JvmName("byteMultiplyScalarFloat") operator fun KtNDArray<Byte>.times(other: Float): KtNDArray<Double> = multiply(this, other)
@JvmName("byteMultiplyScalarDouble") operator fun KtNDArray<Byte>.times(other: Double): KtNDArray<Double> = multiply(this, other)

@JvmName("scalarByteMultiply") operator fun <R: Number> Byte.times(other: KtNDArray<R>): KtNDArray<R> = multiply(this, other)

@JvmName("byteMultiplyAssignNDByte") operator fun KtNDArray<Byte>.timesAssign(other: KtNDArray<Byte>) = timesAssignTwoKtNDArray(this, other)
@JvmName("byteMultiplyAssignNDShort") operator fun KtNDArray<Byte>.timesAssign(other: KtNDArray<Short>) = timesAssignTwoKtNDArray(this, other)
@JvmName("byteMultiplyAssignNDInt") operator fun KtNDArray<Byte>.timesAssign(other: KtNDArray<Int>) = timesAssignTwoKtNDArray(this, other)
@JvmName("byteMultiplyAssignNDLong") operator fun KtNDArray<Byte>.timesAssign(other: KtNDArray<Long>) = timesAssignTwoKtNDArray(this, other)

@JvmName("byteMultiplyAssignByte") operator fun KtNDArray<Byte>.timesAssign(other: Byte) = timesAssignScalar(this, other)
@JvmName("byteMultiplyAssignShort") operator fun KtNDArray<Byte>.timesAssign(other: Short) = timesAssignScalar(this, other)
@JvmName("byteMultiplyAssignInt") operator fun KtNDArray<Byte>.timesAssign(other: Int) = timesAssignScalar(this, other)
@JvmName("byteMultiplyAssignLong") operator fun KtNDArray<Byte>.timesAssign(other: Long) = timesAssignScalar(this, other)

// Short
@JvmName("shortMultiplyByte") operator fun KtNDArray<Short>.times(other: KtNDArray<Byte>): KtNDArray<Short> = multiply(this, other)
@JvmName("shortMultiplyShort") operator fun KtNDArray<Short>.times(other: KtNDArray<Short>): KtNDArray<Short> = multiply(this, other)
@JvmName("shortMultiplyInt") operator fun KtNDArray<Short>.times(other: KtNDArray<Int>): KtNDArray<Int> = multiply(this, other)
@JvmName("shortMultiplyLong") operator fun KtNDArray<Short>.times(other: KtNDArray<Long>): KtNDArray<Long> = multiply(this, other)
@JvmName("shortMultiplyFloat") operator fun KtNDArray<Short>.times(other: KtNDArray<Float>): KtNDArray<Float> = multiply(this, other)
@JvmName("shortMultiplyDouble") operator fun KtNDArray<Short>.times(other: KtNDArray<Double>): KtNDArray<Double> = multiply(this, other)

@JvmName("shortMultiplyScalarByte") operator fun KtNDArray<Short>.times(other: Byte): KtNDArray<Short> = multiply(this, other)
@JvmName("shortMultiplyScalarShort") operator fun KtNDArray<Short>.times(other: Short): KtNDArray<Short> = multiply(this, other)
@JvmName("shortMultiplyScalarInt") operator fun KtNDArray<Short>.times(other: Int): KtNDArray<Short> = multiply(this, other)
@JvmName("shortMultiplyScalarLong") operator fun KtNDArray<Short>.times(other: Long): KtNDArray<Short> = multiply(this, other)
@JvmName("shortMultiplyScalarFloat") operator fun KtNDArray<Short>.times(other: Float): KtNDArray<Double> = multiply(this, other)
@JvmName("shortMultiplyScalarDouble") operator fun KtNDArray<Short>.times(other: Double): KtNDArray<Double> = multiply(this, other)

@JvmName("scalarShortMultiply") operator fun <R: Number> Short.times(other: KtNDArray<R>): KtNDArray<R> = multiply(this, other)

@JvmName("shortMultiplyAssignNDByte") operator fun KtNDArray<Short>.timesAssign(other: KtNDArray<Byte>) = timesAssignTwoKtNDArray(this, other)
@JvmName("shortMultiplyAssignNDShort") operator fun KtNDArray<Short>.timesAssign(other: KtNDArray<Short>) = timesAssignTwoKtNDArray(this, other)
@JvmName("shortMultiplyAssignNDInt") operator fun KtNDArray<Short>.timesAssign(other: KtNDArray<Int>) = timesAssignTwoKtNDArray(this, other)
@JvmName("shortMultiplyAssignNDLong") operator fun KtNDArray<Short>.timesAssign(other: KtNDArray<Long>) = timesAssignTwoKtNDArray(this, other)

@JvmName("shortMultiplyAssignByte") operator fun KtNDArray<Short>.timesAssign(other: Byte) = timesAssignScalar(this, other)
@JvmName("shortMultiplyAssignShort") operator fun KtNDArray<Short>.timesAssign(other: Short) = timesAssignScalar(this, other)
@JvmName("shortMultiplyAssignInt") operator fun KtNDArray<Short>.timesAssign(other: Int) = timesAssignScalar(this, other)
@JvmName("shortMultiplyAssignLong") operator fun KtNDArray<Short>.timesAssign(other: Long) = timesAssignScalar(this, other)

// Int
@JvmName("intMultiplyByte") operator fun KtNDArray<Int>.times(other: KtNDArray<Byte>): KtNDArray<Int> = multiply(this, other)
@JvmName("intMultiplyShort") operator fun KtNDArray<Int>.times(other: KtNDArray<Short>): KtNDArray<Int> = multiply(this, other)
@JvmName("intMultiplyInt") operator fun KtNDArray<Int>.times(other: KtNDArray<Int>): KtNDArray<Int> = multiply(this, other)
@JvmName("intMultiplyLong") operator fun KtNDArray<Int>.times(other: KtNDArray<Long>): KtNDArray<Long> = multiply(this, other)
@JvmName("intMultiplyFloat") operator fun KtNDArray<Int>.times(other: KtNDArray<Float>): KtNDArray<Double> = multiply(this, other)
@JvmName("intMultiplyDouble") operator fun KtNDArray<Int>.times(other: KtNDArray<Double>): KtNDArray<Double> = multiply(this, other)

@JvmName("intMultiplycalarByte") operator fun KtNDArray<Int>.times(other: Byte): KtNDArray<Int> = multiply(this, other)
@JvmName("intMultiplycalarShort") operator fun KtNDArray<Int>.times(other: Short): KtNDArray<Int> = multiply(this, other)
@JvmName("intMultiplycalarInt") operator fun KtNDArray<Int>.times(other: Int): KtNDArray<Int> = multiply(this, other)
@JvmName("intMultiplycalarLong") operator fun KtNDArray<Int>.times(other: Long): KtNDArray<Int> = multiply(this, other)
@JvmName("intMultiplycalarFloat") operator fun KtNDArray<Int>.times(other: Float): KtNDArray<Double> = multiply(this, other)
@JvmName("intMultiplycalarDouble") operator fun KtNDArray<Int>.times(other: Double): KtNDArray<Double> = multiply(this, other)

@JvmName("scalarIntMultiply") operator fun <R: Number> Int.times(other: KtNDArray<R>): KtNDArray<R> = multiply(this, other)

@JvmName("intMultiplyAssignNDByte") operator fun KtNDArray<Int>.timesAssign(other: KtNDArray<Byte>) = timesAssignTwoKtNDArray(this, other)
@JvmName("intMultiplyAssignNDShort") operator fun KtNDArray<Int>.timesAssign(other: KtNDArray<Short>) = timesAssignTwoKtNDArray(this, other)
@JvmName("intMultiplyAssignNDInt") operator fun KtNDArray<Int>.timesAssign(other: KtNDArray<Int>) = timesAssignTwoKtNDArray(this, other)
@JvmName("intMultiplyAssignNDLong") operator fun KtNDArray<Int>.timesAssign(other: KtNDArray<Long>) = timesAssignTwoKtNDArray(this, other)

@JvmName("intMultiplyAssignByte") operator fun KtNDArray<Int>.timesAssign(other: Byte) = timesAssignScalar(this, other)
@JvmName("intMultiplyAssignShort") operator fun KtNDArray<Int>.timesAssign(other: Short) = timesAssignScalar(this, other)
@JvmName("intMultiplyAssignInt") operator fun KtNDArray<Int>.timesAssign(other: Int) = timesAssignScalar(this, other)
@JvmName("intMultiplyAssignLong") operator fun KtNDArray<Int>.timesAssign(other: Long) = timesAssignScalar(this, other)

// Long
@JvmName("longMultiplyByte") operator fun KtNDArray<Long>.times(other: KtNDArray<Byte>): KtNDArray<Long> = multiply(this, other)
@JvmName("longMultiplyShort") operator fun KtNDArray<Long>.times(other: KtNDArray<Short>): KtNDArray<Long> = multiply(this, other)
@JvmName("longMultiplyInt") operator fun KtNDArray<Long>.times(other: KtNDArray<Int>): KtNDArray<Long> = multiply(this, other)
@JvmName("longMultiplyLong") operator fun KtNDArray<Long>.times(other: KtNDArray<Long>): KtNDArray<Long> = multiply(this, other)
@JvmName("longMultiplyFloat") operator fun KtNDArray<Long>.times(other: KtNDArray<Float>): KtNDArray<Double> = multiply(this, other)
@JvmName("longMultiplyDouble") operator fun KtNDArray<Long>.times(other: KtNDArray<Double>): KtNDArray<Double> = multiply(this, other)

@JvmName("longMultiplyScalarByte") operator fun KtNDArray<Long>.times(other: Byte): KtNDArray<Long> = multiply(this, other)
@JvmName("longMultiplyScalarShort") operator fun KtNDArray<Long>.times(other: Short): KtNDArray<Long> = multiply(this, other)
@JvmName("longMultiplyScalarInt") operator fun KtNDArray<Long>.times(other: Int): KtNDArray<Long> = multiply(this, other)
@JvmName("longMultiplyScalarLong") operator fun KtNDArray<Long>.times(other: Long): KtNDArray<Long> = multiply(this, other)
@JvmName("longMultiplyScalarFloat") operator fun KtNDArray<Long>.times(other: Float): KtNDArray<Double> = multiply(this, other)
@JvmName("longMultiplyScalarDouble") operator fun KtNDArray<Long>.times(other: Double): KtNDArray<Double> = multiply(this, other)

@JvmName("scalarLongMultiply") operator fun <R: Number> Long.times(other: KtNDArray<R>): KtNDArray<R> = multiply(this, other)

@JvmName("longMultiplyAssignNDByte") operator fun KtNDArray<Long>.timesAssign(other: KtNDArray<Byte>) = timesAssignTwoKtNDArray(this, other)
@JvmName("longMultiplyAssignNDShort") operator fun KtNDArray<Long>.timesAssign(other: KtNDArray<Short>) = timesAssignTwoKtNDArray(this, other)
@JvmName("longMultiplyAssignNDInt") operator fun KtNDArray<Long>.timesAssign(other: KtNDArray<Int>) = timesAssignTwoKtNDArray(this, other)
@JvmName("longMultiplyAssignNDLong") operator fun KtNDArray<Long>.timesAssign(other: KtNDArray<Long>) = timesAssignTwoKtNDArray(this, other)

@JvmName("longMultiplyAssignByte") operator fun KtNDArray<Long>.timesAssign(other: Byte) = timesAssignScalar(this, other)
@JvmName("longMultiplyAssignShort") operator fun KtNDArray<Long>.timesAssign(other: Short) = timesAssignScalar(this, other)
@JvmName("longMultiplyAssignInt") operator fun KtNDArray<Long>.timesAssign(other: Int) = timesAssignScalar(this, other)
@JvmName("longMultiplyAssignLong") operator fun KtNDArray<Long>.timesAssign(other: Long) = timesAssignScalar(this, other)

// Float
@JvmName("floatMultiplyByte") operator fun KtNDArray<Float>.times(other: KtNDArray<Byte>): KtNDArray<Float> = multiply(this, other)
@JvmName("floatMultiplyShort") operator fun KtNDArray<Float>.times(other: KtNDArray<Short>): KtNDArray<Float> = multiply(this, other)
@JvmName("floatMultiplyInt") operator fun KtNDArray<Float>.times(other: KtNDArray<Int>): KtNDArray<Double> = multiply(this, other)
@JvmName("floatMultiplyLong") operator fun KtNDArray<Float>.times(other: KtNDArray<Long>): KtNDArray<Double> = multiply(this, other)
@JvmName("floatMultiplyFloat") operator fun KtNDArray<Float>.times(other: KtNDArray<Float>): KtNDArray<Float> = multiply(this, other)
@JvmName("floatMultiplyDouble") operator fun KtNDArray<Float>.times(other: KtNDArray<Double>): KtNDArray<Double> = multiply(this, other)

@JvmName("floatMultiplyScalarByte") operator fun KtNDArray<Float>.times(other: Byte): KtNDArray<Float> = multiply(this, other)
@JvmName("floatMultiplyScalarShort") operator fun KtNDArray<Float>.times(other: Short): KtNDArray<Float> = multiply(this, other)
@JvmName("floatMultiplyScalarInt") operator fun KtNDArray<Float>.times(other: Int): KtNDArray<Float> = multiply(this, other)
@JvmName("floatMultiplyScalarLong") operator fun KtNDArray<Float>.times(other: Long): KtNDArray<Float> = multiply(this, other)
@JvmName("floatMultiplyScalarFloat") operator fun KtNDArray<Float>.times(other: Float): KtNDArray<Float> = multiply(this, other)
@JvmName("floatMultiplyScalarDouble") operator fun KtNDArray<Float>.times(other: Double): KtNDArray<Float> = multiply(this, other)

@JvmName("scalarFloatMultiplyByte") operator fun Float.times(other: KtNDArray<Byte>): KtNDArray<Double> = multiply(this, other)
@JvmName("scalarFloatMultiplyShort") operator fun Float.times(other: KtNDArray<Short>): KtNDArray<Double> = multiply(this, other)
@JvmName("scalarFloatMultiplyInt") operator fun Float.times(other: KtNDArray<Int>): KtNDArray<Double> = multiply(this, other)
@JvmName("scalarFloatMultiplyLong") operator fun Float.times(other: KtNDArray<Long>): KtNDArray<Double> = multiply(this, other)
@JvmName("scalarFloatMultiplyFloat") operator fun Float.times(other: KtNDArray<Float>): KtNDArray<Float> = multiply(this, other)
@JvmName("scalarFloatMultiplyDouble") operator fun Float.times(other: KtNDArray<Double>): KtNDArray<Double> = multiply(this, other)

@JvmName("floatMultiplyAssignNDByte") operator fun KtNDArray<Float>.timesAssign(other: KtNDArray<Byte>) = timesAssignTwoKtNDArray(this, other)
@JvmName("floatMultiplyAssignNDShort") operator fun KtNDArray<Float>.timesAssign(other: KtNDArray<Short>) = timesAssignTwoKtNDArray(this, other)
@JvmName("floatMultiplyAssignNDInt") operator fun KtNDArray<Float>.timesAssign(other: KtNDArray<Int>) = timesAssignTwoKtNDArray(this, other)
@JvmName("floatMultiplyAssignNDLong") operator fun KtNDArray<Float>.timesAssign(other: KtNDArray<Long>) = timesAssignTwoKtNDArray(this, other)
@JvmName("floatMultiplyAssignNDFloat") operator fun KtNDArray<Float>.timesAssign(other: KtNDArray<Float>) = timesAssignTwoKtNDArray(this, other)
@JvmName("floatMultiplyAssignNDFouble") operator fun KtNDArray<Float>.timesAssign(other: KtNDArray<Double>) = timesAssignTwoKtNDArray(this, other)

@JvmName("floatMultiplyAssignByte") operator fun KtNDArray<Float>.timesAssign(other: Byte) = timesAssignScalar(this, other)
@JvmName("floatMultiplyAssignShort") operator fun KtNDArray<Float>.timesAssign(other: Short) = timesAssignScalar(this, other)
@JvmName("floatMultiplyAssignInt") operator fun KtNDArray<Float>.timesAssign(other: Int) = timesAssignScalar(this, other)
@JvmName("floatMultiplyAssignLong") operator fun KtNDArray<Float>.timesAssign(other: Long) = timesAssignScalar(this, other)
@JvmName("floatMultiplyAssignFloat") operator fun KtNDArray<Float>.timesAssign(other: Float) = timesAssignScalar(this, other)
@JvmName("floatMultiplyAssignDouble") operator fun KtNDArray<Float>.timesAssign(other: Double) = timesAssignScalar(this, other)

// Double
@JvmName("doubleMultiplyByte") operator fun KtNDArray<Double>.times(other: KtNDArray<Byte>): KtNDArray<Double> = multiply(this, other)
@JvmName("doubleMultiplyShort") operator fun KtNDArray<Double>.times(other: KtNDArray<Short>): KtNDArray<Double> = multiply(this, other)
@JvmName("doubleMultiplyInt") operator fun KtNDArray<Double>.times(other: KtNDArray<Int>): KtNDArray<Double> = multiply(this, other)
@JvmName("doubleMultiplyLong") operator fun KtNDArray<Double>.times(other: KtNDArray<Long>): KtNDArray<Double> = multiply(this, other)
@JvmName("doubleMultiplyFloat") operator fun KtNDArray<Double>.times(other: KtNDArray<Float>): KtNDArray<Double> = multiply(this, other)
@JvmName("doubleMultiplyDouble") operator fun KtNDArray<Double>.times(other: KtNDArray<Double>): KtNDArray<Double> = multiply(this, other)

@JvmName("doubleMultiplyScalarByte") operator fun KtNDArray<Double>.times(other: Byte): KtNDArray<Double> = multiply(this, other)
@JvmName("doubleMultiplyScalarShort") operator fun KtNDArray<Double>.times(other: Short): KtNDArray<Double> = multiply(this, other)
@JvmName("doubleMultiplyScalarInt") operator fun KtNDArray<Double>.times(other: Int): KtNDArray<Double> = multiply(this, other)
@JvmName("doubleMultiplyScalarLong") operator fun KtNDArray<Double>.times(other: Long): KtNDArray<Double> = multiply(this, other)
@JvmName("doubleMultiplyScalarFloat") operator fun KtNDArray<Double>.times(other: Float): KtNDArray<Double> = multiply(this, other)
@JvmName("doubleMultiplyScalarDouble") operator fun KtNDArray<Double>.times(other: Double): KtNDArray<Double> = multiply(this, other)

@JvmName("scalarDoubleMultiplyByte") operator fun Double.times(other: KtNDArray<Byte>): KtNDArray<Double> = multiply(this, other)
@JvmName("scalarDoubleMultiplyShort") operator fun Double.times(other: KtNDArray<Short>): KtNDArray<Double> = multiply(this, other)
@JvmName("scalarDoubleMultiplyInt") operator fun Double.times(other: KtNDArray<Int>): KtNDArray<Double> = multiply(this, other)
@JvmName("scalarDoubleMultiplyLong") operator fun Double.times(other: KtNDArray<Long>): KtNDArray<Double> = multiply(this, other)
@JvmName("scalarDoubleMultiplyFloat") operator fun Double.times(other: KtNDArray<Float>): KtNDArray<Float> = multiply(this, other)
@JvmName("scalarDoubleMultiplyDouble") operator fun Double.times(other: KtNDArray<Double>): KtNDArray<Double> = multiply(this, other)

@JvmName("doubleMultiplyAssignNDByte") operator fun KtNDArray<Double>.timesAssign(other: KtNDArray<Byte>) = timesAssignTwoKtNDArray(this, other)
@JvmName("doubleMultiplyAssignNDShort") operator fun KtNDArray<Double>.timesAssign(other: KtNDArray<Short>) = timesAssignTwoKtNDArray(this, other)
@JvmName("doubleMultiplyAssignNDInt") operator fun KtNDArray<Double>.timesAssign(other: KtNDArray<Int>) = timesAssignTwoKtNDArray(this, other)
@JvmName("doubleMultiplyAssignNDLong") operator fun KtNDArray<Double>.timesAssign(other: KtNDArray<Long>) = timesAssignTwoKtNDArray(this, other)
@JvmName("doubleMultiplyAssignNDFloat") operator fun KtNDArray<Double>.timesAssign(other: KtNDArray<Float>) = timesAssignTwoKtNDArray(this, other)
@JvmName("doubleMultiplyAssignNDFouble") operator fun KtNDArray<Double>.timesAssign(other: KtNDArray<Double>) = timesAssignTwoKtNDArray(this, other)

@JvmName("doubleMultiplyAssignByte") operator fun KtNDArray<Double>.timesAssign(other: Byte) = timesAssignScalar(this, other)
@JvmName("doubleMultiplyAssignShort") operator fun KtNDArray<Double>.timesAssign(other: Short) = timesAssignScalar(this, other)
@JvmName("doubleMultiplyAssignInt") operator fun KtNDArray<Double>.timesAssign(other: Int) = timesAssignScalar(this, other)
@JvmName("doubleMultiplyAssignLong") operator fun KtNDArray<Double>.timesAssign(other: Long) = timesAssignScalar(this, other)
@JvmName("doubleMultiplyAssignFloat") operator fun KtNDArray<Double>.timesAssign(other: Float) = timesAssignScalar(this, other)
@JvmName("doubleMultiplyAssignDouble") operator fun KtNDArray<Double>.timesAssign(other: Double) = timesAssignScalar(this, other)


// Divide
private fun <L: KtNDArray<*>, R: KtNDArray<*>> divAssignTwoKtNDArray(first: L, second: R) {
    callFunc(nameMethod = arrayOf("ndarray", "__itruediv__"), args = arrayOf(first, second), kClass = Unit::class)
}
private fun <T: KtNDArray<*>, S: Number> divAssignScalar(array: T, scalar: S) {
    callFunc(nameMethod = arrayOf("ndarray", "__itruediv__"), args = arrayOf(array, scalar), kClass = Unit::class)
}

private fun <T: Any, L: Any, R: Any> divide(left: L, right: R): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("divide"), args = arrayOf(left, right))


@JvmName("arrayDivideArray") operator fun <T: Number, T1: Number, L : KtNDArray<T>, R : KtNDArray<T1>> L.div(other: R): KtNDArray<Double> = divide(this, other)
@JvmName("arrayDivideScalar") operator fun <T: Number, T1: Number, L: KtNDArray<T>> L.div(other: T1): KtNDArray<Double> = divide(this, other)
@JvmName("scalarDivideArray") operator fun <T: Number, T1: Number, R: KtNDArray<T>> T1.div(other: R): KtNDArray<Double> = divide(this, other)

@JvmName("scalarDivideFloat") operator fun <T: Number> T.div(other: KtNDArray<Float>): KtNDArray<Float> = divide(this, other)

// Byte
@JvmName("byteDivideFloat") operator fun KtNDArray<Byte>.div(other: KtNDArray<Float>): KtNDArray<Float> = divide(this, other)

@JvmName("byteDivideAssignNDByte") operator fun KtNDArray<Byte>.divAssign(other: KtNDArray<Byte>) = divAssignTwoKtNDArray(this, other)
@JvmName("byteDivideAssignNDShort") operator fun KtNDArray<Byte>.divAssign(other: KtNDArray<Short>) = divAssignTwoKtNDArray(this, other)
@JvmName("byteDivideAssignNDInt") operator fun KtNDArray<Byte>.divAssign(other: KtNDArray<Int>) = divAssignTwoKtNDArray(this, other)
@JvmName("byteDivideAssignNDLong") operator fun KtNDArray<Byte>.divAssign(other: KtNDArray<Long>) = divAssignTwoKtNDArray(this, other)


@JvmName("byteDivideAssignByte") operator fun KtNDArray<Byte>.divAssign(other: Byte) = divAssignScalar(this, other)
@JvmName("byteDivideAssignShort") operator fun KtNDArray<Byte>.divAssign(other: Short) = divAssignScalar(this, other)
@JvmName("byteDivideAssignInt") operator fun KtNDArray<Byte>.divAssign(other: Int) = divAssignScalar(this, other)
@JvmName("byteDivideAssignLong") operator fun KtNDArray<Byte>.divAssign(other: Long) = divAssignScalar(this, other)

// Short
@JvmName("shortDivideFloat") operator fun KtNDArray<Short>.div(other: KtNDArray<Float>): KtNDArray<Float> = divide(this, other)

@JvmName("shortDivideAssignNDByte") operator fun KtNDArray<Short>.divAssign(other: KtNDArray<Byte>) = divAssignTwoKtNDArray(this, other)
@JvmName("shortDivideAssignNDShort") operator fun KtNDArray<Short>.divAssign(other: KtNDArray<Short>) = divAssignTwoKtNDArray(this, other)
@JvmName("shortDivideAssignNDInt") operator fun KtNDArray<Short>.divAssign(other: KtNDArray<Int>) = divAssignTwoKtNDArray(this, other)
@JvmName("shortDivideAssignNDLong") operator fun KtNDArray<Short>.divAssign(other: KtNDArray<Long>) = divAssignTwoKtNDArray(this, other)


@JvmName("shortDivideAssignByte") operator fun KtNDArray<Short>.divAssign(other: Byte) = divAssignScalar(this, other)
@JvmName("shortDivideAssignShort") operator fun KtNDArray<Short>.divAssign(other: Short) = divAssignScalar(this, other)
@JvmName("shortDivideAssignInt") operator fun KtNDArray<Short>.divAssign(other: Int) = divAssignScalar(this, other)
@JvmName("shortDivideAssignLong") operator fun KtNDArray<Short>.divAssign(other: Long) = divAssignScalar(this, other)

// Int
@JvmName("intDivideAssignNDByte") operator fun KtNDArray<Int>.divAssign(other: KtNDArray<Byte>) = divAssignTwoKtNDArray(this, other)
@JvmName("intDivideAssignNDShort") operator fun KtNDArray<Int>.divAssign(other: KtNDArray<Short>) = divAssignTwoKtNDArray(this, other)
@JvmName("intDivideAssignNDInt") operator fun KtNDArray<Int>.divAssign(other: KtNDArray<Int>) = divAssignTwoKtNDArray(this, other)
@JvmName("intDivideAssignNDLong") operator fun KtNDArray<Int>.divAssign(other: KtNDArray<Long>) = divAssignTwoKtNDArray(this, other)


@JvmName("intDivideAssignByte") operator fun KtNDArray<Int>.divAssign(other: Byte) = divAssignScalar(this, other)
@JvmName("intDivideAssignShort") operator fun KtNDArray<Int>.divAssign(other: Short) = divAssignScalar(this, other)
@JvmName("intDivideAssignInt") operator fun KtNDArray<Int>.divAssign(other: Int) = divAssignScalar(this, other)
@JvmName("intDivideAssignLong") operator fun KtNDArray<Int>.divAssign(other: Long) = divAssignScalar(this, other)

// Long
@JvmName("longDivideAssignNDByte") operator fun KtNDArray<Long>.divAssign(other: KtNDArray<Byte>) = divAssignTwoKtNDArray(this, other)
@JvmName("longDivideAssignNDShort") operator fun KtNDArray<Long>.divAssign(other: KtNDArray<Short>) = divAssignTwoKtNDArray(this, other)
@JvmName("longDivideAssignNDInt") operator fun KtNDArray<Long>.divAssign(other: KtNDArray<Int>) = divAssignTwoKtNDArray(this, other)
@JvmName("longDivideAssignNDLong") operator fun KtNDArray<Long>.divAssign(other: KtNDArray<Long>) = divAssignTwoKtNDArray(this, other)


@JvmName("longDivideAssignByte") operator fun KtNDArray<Long>.divAssign(other: Byte) = divAssignScalar(this, other)
@JvmName("longDivideAssignShort") operator fun KtNDArray<Long>.divAssign(other: Short) = divAssignScalar(this, other)
@JvmName("longDivideAssignInt") operator fun KtNDArray<Long>.divAssign(other: Int) = divAssignScalar(this, other)
@JvmName("longDivideAssignLong") operator fun KtNDArray<Long>.divAssign(other: Long) = divAssignScalar(this, other)

// Float
@JvmName("floatDivideByte") operator fun KtNDArray<Float>.div(other: KtNDArray<Byte>): KtNDArray<Float> = divide(this, other)
@JvmName("floatDivideShort") operator fun KtNDArray<Float>.div(other: KtNDArray<Short>): KtNDArray<Float> = divide(this, other)
@JvmName("floatDivideFloat") operator fun KtNDArray<Float>.div(other: KtNDArray<Float>): KtNDArray<Float> = divide(this, other)

@JvmName("floatDivideScalar") operator fun <T: Number> KtNDArray<Float>.div(other: T): KtNDArray<Float> = divide(this, other)

@JvmName("floatDivideAssignNDByte") operator fun KtNDArray<Float>.divAssign(other: KtNDArray<Byte>) = divAssignTwoKtNDArray(this, other)
@JvmName("floatDivideAssignNDShort") operator fun KtNDArray<Float>.divAssign(other: KtNDArray<Short>) = divAssignTwoKtNDArray(this, other)
@JvmName("floatDivideAssignNDInt") operator fun KtNDArray<Float>.divAssign(other: KtNDArray<Int>) = divAssignTwoKtNDArray(this, other)
@JvmName("floatDivideAssignNDLong") operator fun KtNDArray<Float>.divAssign(other: KtNDArray<Long>) = divAssignTwoKtNDArray(this, other)
@JvmName("floatDivideAssignNDFloat") operator fun KtNDArray<Float>.divAssign(other: KtNDArray<Float>) = divAssignTwoKtNDArray(this, other)
@JvmName("floatDivideAssignNDDouble") operator fun KtNDArray<Float>.divAssign(other: KtNDArray<Double>) = divAssignTwoKtNDArray(this, other)

@JvmName("floatDivideAssignByte") operator fun KtNDArray<Float>.divAssign(other: Byte) = divAssignScalar(this, other)
@JvmName("floatDivideAssignShort") operator fun KtNDArray<Float>.divAssign(other: Short) = divAssignScalar(this, other)
@JvmName("floatDivideAssignInt") operator fun KtNDArray<Float>.divAssign(other: Int) = divAssignScalar(this, other)
@JvmName("floatDivideAssignLong") operator fun KtNDArray<Float>.divAssign(other: Long) = divAssignScalar(this, other)
@JvmName("floatDivideAssignFloat") operator fun KtNDArray<Float>.divAssign(other: Float) = divAssignScalar(this, other)
@JvmName("floatDivideAssignDouble") operator fun KtNDArray<Float>.divAssign(other: Double) = divAssignScalar(this, other)


// Double
@JvmName("doubleDivideAssignNDByte") operator fun KtNDArray<Double>.divAssign(other: KtNDArray<Byte>) = divAssignTwoKtNDArray(this, other)
@JvmName("doubleDivideAssignNDShort") operator fun KtNDArray<Double>.divAssign(other: KtNDArray<Short>) = divAssignTwoKtNDArray(this, other)
@JvmName("doubleDivideAssignNDInt") operator fun KtNDArray<Double>.divAssign(other: KtNDArray<Int>) = divAssignTwoKtNDArray(this, other)
@JvmName("doubleDivideAssignNDLong") operator fun KtNDArray<Double>.divAssign(other: KtNDArray<Long>) = divAssignTwoKtNDArray(this, other)
@JvmName("doubleDivideAssignNDFloat") operator fun KtNDArray<Double>.divAssign(other: KtNDArray<Float>) = divAssignTwoKtNDArray(this, other)
@JvmName("doubleDivideAssignNDDouble") operator fun KtNDArray<Double>.divAssign(other: KtNDArray<Double>) = divAssignTwoKtNDArray(this, other)

@JvmName("doubleDivideAssignByte") operator fun KtNDArray<Double>.divAssign(other: Byte) = divAssignScalar(this, other)
@JvmName("doubleDivideAssignShort") operator fun KtNDArray<Double>.divAssign(other: Short) = divAssignScalar(this, other)
@JvmName("doubleDivideAssignInt") operator fun KtNDArray<Double>.divAssign(other: Int) = divAssignScalar(this, other)
@JvmName("doubleDivideAssignLong") operator fun KtNDArray<Double>.divAssign(other: Long) = divAssignScalar(this, other)
@JvmName("doubleDivideAssignFloat") operator fun KtNDArray<Double>.divAssign(other: Float) = divAssignScalar(this, other)
@JvmName("doubleDivideAssignDouble") operator fun KtNDArray<Double>.divAssign(other: Double) = divAssignScalar(this, other)



inline infix fun <reified T: Number> KtNDArray<T>.`@`(other: KtNDArray<T>) = dot(this, other)


infix fun <T: Number> KtNDArray<T>.`**`(other: Byte): KtNDArray<T> = power(this, other)

infix fun <T: Number> KtNDArray<T>.`**`(other: Short): KtNDArray<T> = power(this, other)

infix fun <T: Number> KtNDArray<T>.`**`(other: Int): KtNDArray<T> = power(this, other)

infix fun <T: Number> KtNDArray<T>.`**`(other: Long): KtNDArray<T> = power(this, other)

infix fun <T: Number> KtNDArray<T>.`**`(other: Float): KtNDArray<Float> = power(this, other)

infix fun <T: Number> KtNDArray<T>.`**`(other: Double): KtNDArray<Double> = power(this, other)
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
import org.jetbrains.numkt.core.None

private inline fun <L: Any, R: Any, reified T: Any> bitwiseAndOp(x1: KtNDArray<L>, x2: KtNDArray<R>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("bitwise_and"), args = arrayOf(x1,  x2))

/**
 * Compute the bit-wise AND of two arrays element-wise.
 */
@JvmName("boolAndBool") fun bitwiseAnd(x1: KtNDArray<Boolean>, x2: KtNDArray<Boolean>): KtNDArray<Boolean> = bitwiseAndOp(x1, x2)
@JvmName("boolAndByte") fun bitwiseAnd(x1: KtNDArray<Boolean>, x2: KtNDArray<Byte>): KtNDArray<Byte> = bitwiseAndOp(x1, x2)
@JvmName("boolAndShort") fun bitwiseAnd(x1: KtNDArray<Boolean>, x2: KtNDArray<Short>): KtNDArray<Short> = bitwiseAndOp(x1, x2)
@JvmName("boolAndInt") fun bitwiseAnd(x1: KtNDArray<Boolean>, x2: KtNDArray<Int>): KtNDArray<Int> = bitwiseAndOp(x1, x2)
@JvmName("boolAndLong") fun bitwiseAnd(x1: KtNDArray<Boolean>, x2: KtNDArray<Long>): KtNDArray<Long> = bitwiseAndOp(x1, x2)
@JvmName("byteAndBool") fun bitwiseAnd(x1: KtNDArray<Byte>, x2: KtNDArray<Boolean>): KtNDArray<Byte> = bitwiseAndOp(x1, x2)
@JvmName("shortAndBool") fun bitwiseAnd(x1: KtNDArray<Short>, x2: KtNDArray<Boolean>): KtNDArray<Short> = bitwiseAndOp(x1, x2)
@JvmName("intAndBool") fun bitwiseAnd(x1: KtNDArray<Int>, x2: KtNDArray<Boolean>): KtNDArray<Int> = bitwiseAndOp(x1, x2)
@JvmName("longAndBool") fun bitwiseAnd(x1: KtNDArray<Long>, x2: KtNDArray<Boolean>): KtNDArray<Long> = bitwiseAndOp(x1, x2)
@JvmName("byteAndByte") fun bitwiseAnd(x1: KtNDArray<Byte>, x2: KtNDArray<Byte>): KtNDArray<Byte> = bitwiseAndOp(x1, x2)
@JvmName("shortAndByte") fun bitwiseAnd(x1: KtNDArray<Short>, x2: KtNDArray<Byte>): KtNDArray<Short> = bitwiseAndOp(x1, x2)
@JvmName("intAndByte") fun bitwiseAnd(x1: KtNDArray<Int>, x2: KtNDArray<Byte>): KtNDArray<Int> = bitwiseAndOp(x1, x2)
@JvmName("longAndByte") fun bitwiseAnd(x1: KtNDArray<Long>, x2: KtNDArray<Byte>): KtNDArray<Long> = bitwiseAndOp(x1, x2)
@JvmName("byteAndShort") fun bitwiseAnd(x1: KtNDArray<Byte>, x2: KtNDArray<Short>): KtNDArray<Short> = bitwiseAndOp(x1, x2)
@JvmName("shortAndShort") fun bitwiseAnd(x1: KtNDArray<Short>, x2: KtNDArray<Short>): KtNDArray<Short> = bitwiseAndOp(x1, x2)
@JvmName("intAndShort") fun bitwiseAnd(x1: KtNDArray<Int>, x2: KtNDArray<Short>): KtNDArray<Int> = bitwiseAndOp(x1, x2)
@JvmName("longAndShort") fun bitwiseAnd(x1: KtNDArray<Long>, x2: KtNDArray<Short>): KtNDArray<Long> = bitwiseAndOp(x1, x2)
@JvmName("byteAndInt") fun bitwiseAnd(x1: KtNDArray<Byte>, x2: KtNDArray<Int>): KtNDArray<Int> = bitwiseAndOp(x1, x2)
@JvmName("shortAndInt") fun bitwiseAnd(x1: KtNDArray<Short>, x2: KtNDArray<Int>): KtNDArray<Int> = bitwiseAndOp(x1, x2)
@JvmName("intAndInt") fun bitwiseAnd(x1: KtNDArray<Int>, x2: KtNDArray<Int>): KtNDArray<Int> = bitwiseAndOp(x1, x2)
@JvmName("longAndInt") fun bitwiseAnd(x1: KtNDArray<Long>, x2: KtNDArray<Int>): KtNDArray<Long> = bitwiseAndOp(x1, x2)
@JvmName("byteAndLong") fun bitwiseAnd(x1: KtNDArray<Byte>, x2: KtNDArray<Long>): KtNDArray<Long> = bitwiseAndOp(x1, x2)
@JvmName("shortAndLong") fun bitwiseAnd(x1: KtNDArray<Short>, x2: KtNDArray<Long>): KtNDArray<Long> = bitwiseAndOp(x1, x2)
@JvmName("intAndLong") fun bitwiseAnd(x1: KtNDArray<Int>, x2: KtNDArray<Long>): KtNDArray<Long> = bitwiseAndOp(x1, x2)
@JvmName("longAndLong") fun bitwiseAnd(x1: KtNDArray<Long>, x2: KtNDArray<Long>): KtNDArray<Long> = bitwiseAndOp(x1, x2)


private inline fun <L: Any, R: Any, reified T: Any> bitwiseOrOp(x1: KtNDArray<L>, x2: KtNDArray<R>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("bitwise_or"), args = arrayOf(x1,  x2))

/**
 * Compute the bit-wise OR of two arrays element-wise.
 */
@JvmName("boolOrBool") fun bitwiseOr(x1: KtNDArray<Boolean>, x2: KtNDArray<Boolean>): KtNDArray<Boolean> = bitwiseOrOp(x1, x2)
@JvmName("boolOrByte") fun bitwiseOr(x1: KtNDArray<Boolean>, x2: KtNDArray<Byte>): KtNDArray<Byte> = bitwiseOrOp(x1, x2)
@JvmName("boolOrShort") fun bitwiseOr(x1: KtNDArray<Boolean>, x2: KtNDArray<Short>): KtNDArray<Short> = bitwiseOrOp(x1, x2)
@JvmName("boolOrInt") fun bitwiseOr(x1: KtNDArray<Boolean>, x2: KtNDArray<Int>): KtNDArray<Int> = bitwiseOrOp(x1, x2)
@JvmName("boolOrLong") fun bitwiseOr(x1: KtNDArray<Boolean>, x2: KtNDArray<Long>): KtNDArray<Long> = bitwiseOrOp(x1, x2)
@JvmName("byteOrBool") fun bitwiseOr(x1: KtNDArray<Byte>, x2: KtNDArray<Boolean>): KtNDArray<Byte> = bitwiseOrOp(x1, x2)
@JvmName("shortOrBool") fun bitwiseOr(x1: KtNDArray<Short>, x2: KtNDArray<Boolean>): KtNDArray<Short> = bitwiseOrOp(x1, x2)
@JvmName("intOrBool") fun bitwiseOr(x1: KtNDArray<Int>, x2: KtNDArray<Boolean>): KtNDArray<Int> = bitwiseOrOp(x1, x2)
@JvmName("longOrBool") fun bitwiseOr(x1: KtNDArray<Long>, x2: KtNDArray<Boolean>): KtNDArray<Long> = bitwiseOrOp(x1, x2)
@JvmName("byteOrByte") fun bitwiseOr(x1: KtNDArray<Byte>, x2: KtNDArray<Byte>): KtNDArray<Byte> = bitwiseOrOp(x1, x2)
@JvmName("shortOrByte") fun bitwiseOr(x1: KtNDArray<Short>, x2: KtNDArray<Byte>): KtNDArray<Short> = bitwiseOrOp(x1, x2)
@JvmName("intOrByte") fun bitwiseOr(x1: KtNDArray<Int>, x2: KtNDArray<Byte>): KtNDArray<Int> = bitwiseOrOp(x1, x2)
@JvmName("longOrByte") fun bitwiseOr(x1: KtNDArray<Long>, x2: KtNDArray<Byte>): KtNDArray<Long> = bitwiseOrOp(x1, x2)
@JvmName("byteOrShort") fun bitwiseOr(x1: KtNDArray<Byte>, x2: KtNDArray<Short>): KtNDArray<Short> = bitwiseOrOp(x1, x2)
@JvmName("shortOrShort") fun bitwiseOr(x1: KtNDArray<Short>, x2: KtNDArray<Short>): KtNDArray<Short> = bitwiseOrOp(x1, x2)
@JvmName("intOrShort") fun bitwiseOr(x1: KtNDArray<Int>, x2: KtNDArray<Short>): KtNDArray<Int> = bitwiseOrOp(x1, x2)
@JvmName("longOrShort") fun bitwiseOr(x1: KtNDArray<Long>, x2: KtNDArray<Short>): KtNDArray<Long> = bitwiseOrOp(x1, x2)
@JvmName("byteOrInt") fun bitwiseOr(x1: KtNDArray<Byte>, x2: KtNDArray<Int>): KtNDArray<Int> = bitwiseOrOp(x1, x2)
@JvmName("shortOrInt") fun bitwiseOr(x1: KtNDArray<Short>, x2: KtNDArray<Int>): KtNDArray<Int> = bitwiseOrOp(x1, x2)
@JvmName("intOrInt") fun bitwiseOr(x1: KtNDArray<Int>, x2: KtNDArray<Int>): KtNDArray<Int> = bitwiseOrOp(x1, x2)
@JvmName("longOrInt") fun bitwiseOr(x1: KtNDArray<Long>, x2: KtNDArray<Int>): KtNDArray<Long> = bitwiseOrOp(x1, x2)
@JvmName("byteOrLong") fun bitwiseOr(x1: KtNDArray<Byte>, x2: KtNDArray<Long>): KtNDArray<Long> = bitwiseOrOp(x1, x2)
@JvmName("shortOrLong") fun bitwiseOr(x1: KtNDArray<Short>, x2: KtNDArray<Long>): KtNDArray<Long> = bitwiseOrOp(x1, x2)
@JvmName("intOrLong") fun bitwiseOr(x1: KtNDArray<Int>, x2: KtNDArray<Long>): KtNDArray<Long> = bitwiseOrOp(x1, x2)
@JvmName("longOrLong") fun bitwiseOr(x1: KtNDArray<Long>, x2: KtNDArray<Long>): KtNDArray<Long> = bitwiseOrOp(x1, x2)


private inline fun <L: Any, R: Any, reified T: Any> bitwiseXorOp(x1: KtNDArray<L>, x2: KtNDArray<R>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("bitwise_xor"), args = arrayOf(x1,  x2))

/**
 * Compute the bit-wise XOR of two arrays element-wise.
 */
@JvmName("boolXorBool") fun bitwiseXor(x1: KtNDArray<Boolean>, x2: KtNDArray<Boolean>): KtNDArray<Boolean> = bitwiseXorOp(x1, x2)
@JvmName("boolXorByte") fun bitwiseXor(x1: KtNDArray<Boolean>, x2: KtNDArray<Byte>): KtNDArray<Byte> = bitwiseXorOp(x1, x2)
@JvmName("boolXorShort") fun bitwiseXor(x1: KtNDArray<Boolean>, x2: KtNDArray<Short>): KtNDArray<Short> = bitwiseXorOp(x1, x2)
@JvmName("boolXorInt") fun bitwiseXor(x1: KtNDArray<Boolean>, x2: KtNDArray<Int>): KtNDArray<Int> = bitwiseXorOp(x1, x2)
@JvmName("boolXorLong") fun bitwiseXor(x1: KtNDArray<Boolean>, x2: KtNDArray<Long>): KtNDArray<Long> = bitwiseXorOp(x1, x2)
@JvmName("byteXorBool") fun bitwiseXor(x1: KtNDArray<Byte>, x2: KtNDArray<Boolean>): KtNDArray<Byte> = bitwiseXorOp(x1, x2)
@JvmName("shortXorBool") fun bitwiseXor(x1: KtNDArray<Short>, x2: KtNDArray<Boolean>): KtNDArray<Short> = bitwiseXorOp(x1, x2)
@JvmName("intXorBool") fun bitwiseXor(x1: KtNDArray<Int>, x2: KtNDArray<Boolean>): KtNDArray<Int> = bitwiseXorOp(x1, x2)
@JvmName("longXorBool") fun bitwiseXor(x1: KtNDArray<Long>, x2: KtNDArray<Boolean>): KtNDArray<Long> = bitwiseXorOp(x1, x2)
@JvmName("byteXorByte") fun bitwiseXor(x1: KtNDArray<Byte>, x2: KtNDArray<Byte>): KtNDArray<Byte> = bitwiseXorOp(x1, x2)
@JvmName("shortXorByte") fun bitwiseXor(x1: KtNDArray<Short>, x2: KtNDArray<Byte>): KtNDArray<Short> = bitwiseXorOp(x1, x2)
@JvmName("intXorByte") fun bitwiseXor(x1: KtNDArray<Int>, x2: KtNDArray<Byte>): KtNDArray<Int> = bitwiseXorOp(x1, x2)
@JvmName("longXorByte") fun bitwiseXor(x1: KtNDArray<Long>, x2: KtNDArray<Byte>): KtNDArray<Long> = bitwiseXorOp(x1, x2)
@JvmName("byteXorShort") fun bitwiseXor(x1: KtNDArray<Byte>, x2: KtNDArray<Short>): KtNDArray<Short> = bitwiseXorOp(x1, x2)
@JvmName("shortXorShort") fun bitwiseXor(x1: KtNDArray<Short>, x2: KtNDArray<Short>): KtNDArray<Short> = bitwiseXorOp(x1, x2)
@JvmName("intXorShort") fun bitwiseXor(x1: KtNDArray<Int>, x2: KtNDArray<Short>): KtNDArray<Int> = bitwiseXorOp(x1, x2)
@JvmName("longXorShort") fun bitwiseXor(x1: KtNDArray<Long>, x2: KtNDArray<Short>): KtNDArray<Long> = bitwiseXorOp(x1, x2)
@JvmName("byteXorInt") fun bitwiseXor(x1: KtNDArray<Byte>, x2: KtNDArray<Int>): KtNDArray<Int> = bitwiseXorOp(x1, x2)
@JvmName("shortXorInt") fun bitwiseXor(x1: KtNDArray<Short>, x2: KtNDArray<Int>): KtNDArray<Int> = bitwiseXorOp(x1, x2)
@JvmName("intXorInt") fun bitwiseXor(x1: KtNDArray<Int>, x2: KtNDArray<Int>): KtNDArray<Int> = bitwiseXorOp(x1, x2)
@JvmName("longXorInt") fun bitwiseXor(x1: KtNDArray<Long>, x2: KtNDArray<Int>): KtNDArray<Long> = bitwiseXorOp(x1, x2)
@JvmName("byteXorLong") fun bitwiseXor(x1: KtNDArray<Byte>, x2: KtNDArray<Long>): KtNDArray<Long> = bitwiseXorOp(x1, x2)
@JvmName("shortXorLong") fun bitwiseXor(x1: KtNDArray<Short>, x2: KtNDArray<Long>): KtNDArray<Long> = bitwiseXorOp(x1, x2)
@JvmName("intXorLong") fun bitwiseXor(x1: KtNDArray<Int>, x2: KtNDArray<Long>): KtNDArray<Long> = bitwiseXorOp(x1, x2)
@JvmName("longXorLong") fun bitwiseXor(x1: KtNDArray<Long>, x2: KtNDArray<Long>): KtNDArray<Long> = bitwiseXorOp(x1, x2)


private fun <T: Any> invertOp(x: KtNDArray<T>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("invert"), args = arrayOf(x))

/**
 * Compute bit-wise inversion, or bit-wise NOT, element-wise.
 */
@JvmName("invertBool") fun invert(x: KtNDArray<Boolean>): KtNDArray<Boolean> = invertOp(x)
@JvmName("invertByte") fun invert(x: KtNDArray<Byte>): KtNDArray<Byte> = invertOp(x)
@JvmName("invertShort") fun invert(x: KtNDArray<Short>): KtNDArray<Short> = invertOp(x)
@JvmName("invertInt") fun invert(x: KtNDArray<Int>): KtNDArray<Int> = invertOp(x)
@JvmName("invertLong") fun invert(x: KtNDArray<Long>): KtNDArray<Long> = invertOp(x)


private inline fun <L: Any, R: Any, reified T: Any> leftShiftOp(x1: KtNDArray<L>, x2: KtNDArray<R>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("left_shift"), args = arrayOf(x1, x2))

/**
 * Shift the bits of an integer to the left.
 */
@JvmName("byteLeftShiftByte") fun leftShift(x1: KtNDArray<Byte>, x2: KtNDArray<Byte>): KtNDArray<Byte> = leftShiftOp(x1, x2)
@JvmName("shortLeftShiftByte") fun leftShift(x1: KtNDArray<Short>, x2: KtNDArray<Byte>): KtNDArray<Short> = leftShiftOp(x1, x2)
@JvmName("intLeftShiftByte") fun leftShift(x1: KtNDArray<Int>, x2: KtNDArray<Byte>): KtNDArray<Int> = leftShiftOp(x1, x2)
@JvmName("longLeftShiftByte") fun leftShift(x1: KtNDArray<Long>, x2: KtNDArray<Byte>): KtNDArray<Long> = leftShiftOp(x1, x2)
@JvmName("byteLeftShiftShort") fun leftShift(x1: KtNDArray<Byte>, x2: KtNDArray<Short>): KtNDArray<Short> = leftShiftOp(x1, x2)
@JvmName("shortLeftShiftShort") fun leftShift(x1: KtNDArray<Short>, x2: KtNDArray<Short>): KtNDArray<Short> = leftShiftOp(x1, x2)
@JvmName("intLeftShiftShort") fun leftShift(x1: KtNDArray<Int>, x2: KtNDArray<Short>): KtNDArray<Int> = leftShiftOp(x1, x2)
@JvmName("longLeftShiftShort") fun leftShift(x1: KtNDArray<Long>, x2: KtNDArray<Short>): KtNDArray<Long> = leftShiftOp(x1, x2)
@JvmName("byteLeftShiftInt") fun leftShift(x1: KtNDArray<Byte>, x2: KtNDArray<Int>): KtNDArray<Int> = leftShiftOp(x1, x2)
@JvmName("shortLeftShiftInt") fun leftShift(x1: KtNDArray<Short>, x2: KtNDArray<Int>): KtNDArray<Int> = leftShiftOp(x1, x2)
@JvmName("intLeftShiftInt") fun leftShift(x1: KtNDArray<Int>, x2: KtNDArray<Int>): KtNDArray<Int> = leftShiftOp(x1, x2)
@JvmName("longLeftShiftInt") fun leftShift(x1: KtNDArray<Long>, x2: KtNDArray<Int>): KtNDArray<Long> = leftShiftOp(x1, x2)
@JvmName("byteLeftShiftLong") fun leftShift(x1: KtNDArray<Byte>, x2: KtNDArray<Long>): KtNDArray<Long> = leftShiftOp(x1, x2)
@JvmName("shortLeftShiftLong") fun leftShift(x1: KtNDArray<Short>, x2: KtNDArray<Long>): KtNDArray<Long> = leftShiftOp(x1, x2)
@JvmName("intLeftShiftLong") fun leftShift(x1: KtNDArray<Int>, x2: KtNDArray<Long>): KtNDArray<Long> = leftShiftOp(x1, x2)
@JvmName("longLeftShiftLong") fun leftShift(x1: KtNDArray<Long>, x2: KtNDArray<Long>): KtNDArray<Long> = leftShiftOp(x1, x2)


private inline fun <L: Any, R: Any, reified T: Any> rightShiftOp(x1: KtNDArray<L>, x2: KtNDArray<R>): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("right_shift"), args = arrayOf(x1, x2))

/**
 * Shift the bits of an integer to the right.
 */
@JvmName("byteRightShiftByte") fun rightShift(x1: KtNDArray<Byte>, x2: KtNDArray<Byte>): KtNDArray<Byte> = rightShiftOp(x1, x2)
@JvmName("shortRightShiftByte") fun rightShift(x1: KtNDArray<Short>, x2: KtNDArray<Byte>): KtNDArray<Short> = rightShiftOp(x1, x2)
@JvmName("intRightShiftByte") fun rightShift(x1: KtNDArray<Int>, x2: KtNDArray<Byte>): KtNDArray<Int> = rightShiftOp(x1, x2)
@JvmName("longRightShiftByte") fun rightShift(x1: KtNDArray<Long>, x2: KtNDArray<Byte>): KtNDArray<Long> = rightShiftOp(x1, x2)
@JvmName("byteRightShiftShort") fun rightShift(x1: KtNDArray<Byte>, x2: KtNDArray<Short>): KtNDArray<Short> = rightShiftOp(x1, x2)
@JvmName("shortRightShiftShort") fun rightShift(x1: KtNDArray<Short>, x2: KtNDArray<Short>): KtNDArray<Short> = rightShiftOp(x1, x2)
@JvmName("intRightShiftShort") fun rightShift(x1: KtNDArray<Int>, x2: KtNDArray<Short>): KtNDArray<Int> = rightShiftOp(x1, x2)
@JvmName("longRightShiftShort") fun rightShift(x1: KtNDArray<Long>, x2: KtNDArray<Short>): KtNDArray<Long> = rightShiftOp(x1, x2)
@JvmName("byteRightShiftInt") fun rightShift(x1: KtNDArray<Byte>, x2: KtNDArray<Int>): KtNDArray<Int> = rightShiftOp(x1, x2)
@JvmName("shortRightShiftInt") fun rightShift(x1: KtNDArray<Short>, x2: KtNDArray<Int>): KtNDArray<Int> = rightShiftOp(x1, x2)
@JvmName("intRightShiftInt") fun rightShift(x1: KtNDArray<Int>, x2: KtNDArray<Int>): KtNDArray<Int> = rightShiftOp(x1, x2)
@JvmName("longRightShiftInt") fun rightShift(x1: KtNDArray<Long>, x2: KtNDArray<Int>): KtNDArray<Long> = rightShiftOp(x1, x2)
@JvmName("byteRightShiftLong") fun rightShift(x1: KtNDArray<Byte>, x2: KtNDArray<Long>): KtNDArray<Long> = rightShiftOp(x1, x2)
@JvmName("shortRightShiftLong") fun rightShift(x1: KtNDArray<Short>, x2: KtNDArray<Long>): KtNDArray<Long> = rightShiftOp(x1, x2)
@JvmName("intRightShiftLong") fun rightShift(x1: KtNDArray<Int>, x2: KtNDArray<Long>): KtNDArray<Long> = rightShiftOp(x1, x2)
@JvmName("longRightShiftLong") fun rightShift(x1: KtNDArray<Long>, x2: KtNDArray<Long>): KtNDArray<Long> = rightShiftOp(x1, x2)


private fun <T: Any> packbitsOp(a: KtNDArray<T>, axis: Int? = null, bitorder: String): KtNDArray<Byte> =
    callFunc(nameMethod = arrayOf("packbits"), args = arrayOf(a, axis ?: None.none, bitorder))

/**
 * Packs the elements of a binary-valued array into bits in a uint8 array.
 */
@JvmName("packbitsBool") fun packbits(x: KtNDArray<Boolean>, axis: Int? = null, bitorder: String = "big"): KtNDArray<Byte> = packbitsOp(x, axis, bitorder)
@JvmName("packbitsByte") fun packbits(x: KtNDArray<Byte>, axis: Int? = null, bitorder: String = "big"): KtNDArray<Byte> = packbitsOp(x, axis, bitorder)
@JvmName("packbitsShort") fun packbits(x: KtNDArray<Short>, axis: Int? = null, bitorder: String = "big"): KtNDArray<Byte> = packbitsOp(x, axis, bitorder)
@JvmName("packbitsInt") fun packbits(x: KtNDArray<Int>, axis: Int? = null, bitorder: String = "big"): KtNDArray<Byte> = packbitsOp(x, axis, bitorder)
@JvmName("packbitsLong") fun packbits(x: KtNDArray<Long>, axis: Int? = null, bitorder: String = "big"): KtNDArray<Byte> = packbitsOp(x, axis, bitorder)


/**
 * Unpacks elements of a uint8 array into a binary-valued output array.
 *
 * @param a input array.
 * @param axis the dimension over which bit-unpacking is done.
 * @param count the number of elements to unpack along [axis],
 * provided as a way of undoing the effect of packing a size that is not a multiple of eight.
 * @param bitorder the order of the returned bits.
 * @return the elements are binary-valued (0, or 1).
 * @see packbits
 */
fun unpackbits(a: KtNDArray<Byte>, axis: Int? = null, count: Int? = null, bitorder: String = "big"): KtNDArray<Byte> =
    callFunc(nameMethod = arrayOf("unpackbits"), args = arrayOf(a, axis ?: None.none, count ?: None.none, bitorder))

/**
 * Return the binary representation of the input number as a string.
 */
fun binaryRepr(num: Int, width: Int? = null): String =
    callFunc(nameMethod = arrayOf("binary_repr"), args = arrayOf(num, width ?: None.none), kClass = String::class)
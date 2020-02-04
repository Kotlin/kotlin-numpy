package org.jetbrains.numkt.core

import org.jetbrains.numkt.Casting

enum class IterFlags {
    NPY_ITER_DONT_NEGATE_STRIDES,
    NPY_ITER_REFS_OK, NPY_ITER_ZEROSIZE_OK,
    NPY_ITER_BUFFERED, NPY_ITER_DELAY_BUFALLOC
}

class KtNDIter<T : Any> constructor(
    op: KtNDArray<T>,
    vararg val flags: IterFlags,
    private val casting: Casting = Casting.SAFE
) : AutoCloseable {

    private var pointer: Long = iterNew(op, flags.map { it.name }.toTypedArray(), casting.str)

    val finished: Boolean
        get() = finishedGetCritical(pointer)

    val hasDelayedBufalloc: Boolean = false

    val hasIndex: Boolean = true

    var hasMultiIndex: Boolean = true
        private set

    var index: Int
        get() = indexGet(pointer)
        set(value) = indexSet(pointer, value)

    var iterIndex: Int
        get() = iterIndexGet(pointer)
        set(value) = iterIndexSet(pointer, value)

    val iterSize: Int
        get() = iterSizeGet(pointer)

    var multiIndex: IntArray
        get() = multiIndexGet(pointer)
        set(value) = multiIndexSet(pointer, value)

    var iterRange: Pair<Int, Int>
        get() = iterRangeGet(pointer)
        set(value) = iterRangeSet(pointer, value)

    val ndim: Int
        get() = ndimGet(pointer)

    val shape: IntArray
        get() = shapeGet(pointer)

    val value: T
        get() = valueGet(pointer)

    val operand: KtNDArray<T> = op

//    constructor(op: KtNDArray<T>, vararg flags: IterFlags, casting: Casting = Casting.SAFE) : this() {
//        this.pointer = iterNew(op, flags.map { it.name }.toTypedArray(), casting.str)
//        this.operand = op
//    }

    // java critical
    companion object {
        @JvmStatic
        private external fun finishedGetCritical(ptr: Long): Boolean

        @JvmStatic
        private external fun iterDebugPrintCritical(ptr: Long): Boolean
    }

    private external fun iterNew(op: KtNDArray<T>, flags: Array<String>, casting: String): Long

    private external fun indexGet(ptr: Long): Int

    private external fun indexSet(ptr: Long, value: Int)

    private external fun iterIndexGet(ptr: Long): Int

    private external fun iterIndexSet(ptr: Long, value: Int)

    private external fun iterSizeGet(ptr: Long): Int

    private external fun multiIndexGet(ptr: Long): IntArray

    private external fun multiIndexSet(ptr: Long, value: IntArray)

    private external fun iterRangeGet(ptr: Long): Pair<Int, Int>

    private external fun iterRangeSet(ptr: Long, value: Pair<Int, Int>)

    private external fun ndimGet(ptr: Long): Int

    private external fun shapeGet(ptr: Long): IntArray

    private external fun valueGet(ptr: Long): T

    private external fun dealloc(ptr: Long)

    fun next() = nextC(pointer)
    private external fun nextC(ptr: Long): T

    override fun close() = iterClose(pointer)
    private external fun iterClose(ptr: Long)

    fun copy(): KtNDIter<T> = KtNDIter(operand, *flags, casting = casting)

    fun debugPrint(): Boolean = iterDebugPrintCritical(pointer)

    fun iterNext() = iterNextC(pointer)
    private external fun iterNextC(ptr: Long): Boolean

    fun removeAxis(axis: Int): Boolean = iterRemoveAxis(pointer, axis)
    private external fun iterRemoveAxis(ptr: Long, axis: Int): Boolean

    fun removeMultiIndex(): Boolean {
        hasMultiIndex = false
        return iterRemoveMultiIndex(pointer)
    }

    private external fun iterRemoveMultiIndex(ptr: Long): Boolean

    fun reset(): Boolean = iterReset(pointer)
    private external fun iterReset(ptr: Long): Boolean

    operator fun iterator(): Iterator<T> = object : Iterator<T> {
        override fun hasNext(): Boolean = !finished

        override fun next(): T {
            val v = nextC(pointer)
            return v
        }
    }

    protected fun finalize() {
        dealloc(pointer)
    }
}

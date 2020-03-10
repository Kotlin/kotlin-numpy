package org.jetbrains.numkt.core

import org.jetbrains.numkt.Casting
import org.jetbrains.numkt.NumKtException

/**
 * Enum class flags for [KtNDIter] iterator.
 */
enum class IterFlag {
    /**
     * This only affects the iterator when NPY_KEEPORDER is specified for the order parameter.
     * By default with NPY_KEEPORDER, the iterator reverses axes which have negative strides,
     * so that memory is traversed in a forward direction.
     * This disables this step. Use this flag if you want to use the underlying memory-ordering of the axes,
     * but don’t want an axis reversed.
     */
    NPY_ITER_DONT_NEGATE_STRIDES,

    /**
     * Not impl.
     */
    NPY_ITER_REFS_OK,

    /**
     * Indicates that arrays with a size of zero should be permitted.
     */
    NPY_ITER_ZEROSIZE_OK,

    /**
     * Causes the iterator to store buffering data,
     * and use buffering to satisfy data type, alignment, and byte-order requirements.
     */
    NPY_ITER_BUFFERED,

    /**
     * When buffering is enabled,
     * this delays allocation of the buffers until NpyIter_Reset or another reset function is called.
     */
    NPY_ITER_DELAY_BUFALLOC
}

/**
 * It is a mapping of the C array iterator API.
 */
class KtNDIter<T : Any> constructor(
    op: KtNDArray<T>,
    vararg val flags: IterFlag,
    private val casting: Casting = Casting.SAFE
) : AutoCloseable, Iterable<T> {

    private var pointer: Long = if (op.size != 0) iterNew(
        op,
        flags.map { it.name }.toTypedArray(),
        casting.str
    ) else throw NumKtException("Cannot create KtNDIter from an empty array")

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
    private external fun nextC(ptr: Long): T?

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

    override operator fun iterator(): Iterator<T> = object : Iterator<T> {
        var ret: T? = null
        override fun hasNext(): Boolean {
            ret = nextC(pointer)
            return ret != null
        }

        override fun next(): T {
            return ret!!
        }
    }

    protected fun finalize() {
        dealloc(pointer)
    }
}

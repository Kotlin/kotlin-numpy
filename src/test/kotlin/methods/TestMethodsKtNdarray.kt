package methods

import org.jetbrains.numkt.*
import org.jetbrains.numkt.core.*
import org.jetbrains.numkt.math.plus
import org.jetbrains.numkt.math.times
import kotlin.test.*

class TestMethodsKtNdarray {
    @Test
    fun testAllMethod() {
        val a = array(arrayOf(true, false, true, true)).reshape(2, 2)
        assertFalse(a.all())

        val check = array(arrayOf(true, false))
        assertEquals(check, a.all(0))

        val b = array(arrayOf(-1, 4, 5))
        assertTrue(b.all())

        val c = array(arrayOf(1.0, Double.NaN))
        assertTrue(c.all())
    }

    @Test
    fun testAnyMethod() {
        val a = array(arrayOf(true, false, true, true))
        assertTrue(a.any())

        val check = array(arrayOf(true, false))
        val b = array(arrayOf(true, false, false, false)).reshape(2, 2)
        assertEquals(check, b.any(0))

        val c = array(arrayOf(-1, 0, 5))
        assertTrue(c.any())

        val d = array(arrayOf(0.0, Double.NaN))
        assertTrue(d.any())
    }

    @Test
    fun testArgMaxMethod() {
        val a = arange(6).reshape(2, 3) + 10
        assertEquals(5, a.argMax())

        val check = ones<Long>(3)
        assertEquals(check, a.argMax(0))

        val check2 = ones<Long>(2) + 1
        assertEquals(check2, a.argMax(1))
    }

    @Test
    fun testArgMinMethod() {
        val a = arange(6).reshape(2, 3) + 10
        assertEquals(0, a.argMin())

        val check = zeros<Long>(3)
        assertEquals(check, a.argMin(0))

        assertEquals(check[None..2], a.argMin(1))
    }

    @Test
    fun testArgPartitionMethod() {
        val x = array(arrayOf(3, 4, 2, 1))
        val check = array(arrayOf(2L, 3L, 0L, 1L))
        assertEquals(check, x.argPartition(intArrayOf(3)))

        val check2 = array(arrayOf(3L, 2L, 0L, 1L))
        assertEquals(check2, x.argPartition(intArrayOf(1, 3)))
    }

    @Test
    fun testArgSortMethod() {
        val x = array(arrayOf(3, 1, 2))
        val check = array(arrayOf(1L, 2L, 0L))
        assertEquals(check, x.argSort())
    }

    @Test
    fun testAsTypeMethod() {
        val x = array(arrayOf(1.0, 2.0, 2.5))
        val check = array(arrayOf(1, 2, 2))
        assertEquals(check, x.asType<Double, Int>())
    }

    @Test
    fun testByteSwapMethod() {
        val a = array(shortArrayOf(1, 256, 8755).toTypedArray())
        val check = array(shortArrayOf(256, 1, 13090).toTypedArray())
        assertEquals(check, a.byteSwap())
    }

    @Test
    fun testClipMethod() {
        val a = arange(10)
        val check = array(arrayOf(1, 1, 2, 3, 4, 5, 6, 7, 8, 8))
        assertEquals(check, a.clip(1, 8))
    }

    @Test
    fun testCompressMethod() {
        val a = array(arrayOf(1, 2, 3, 4, 5, 6)).reshape(3, 2)

        val check0 = array(arrayOf(3, 4)).reshape(1, 2)
        assertEquals(check0, a.compress(booleanArrayOf(false, true), 0))

        val check1 = array(arrayOf(3, 4, 5, 6)).reshape(2, 2)
        assertEquals(check1, a.compress(booleanArrayOf(false, true, true), 0))

        val check2 = array(arrayOf(2, 4, 6)).reshape(3, 1)
        assertEquals(check2, a.compress(booleanArrayOf(false, true), 1))
    }

    @Test
    fun testCopyMethod() {
        val x = array(arrayOf(1, 2, 3, 4, 5, 6)).reshape(2, 3)
        val y = x.copy()
        assertEquals(y, x)

        x.fill(0)
        assertNotEquals(y, x)
    }

    @Test
    fun testCumProdMethod() {
        val a = array(arrayOf(1, 2, 3))
        val checkA = array(arrayOf(1, 2, 6))

        assertEquals(checkA, a.cumProd())

        val b = array(arrayOf(1, 2, 3, 4, 5, 6)).reshape(2, 3)
        val checkB = array(arrayOf(1, 2, 3, 4, 10, 18)).reshape(2, 3)

        assertEquals(checkB, b.cumProd(0))

        val checkC = array(arrayOf(1, 2, 6, 4, 20, 120)).reshape(2, 3)
        assertEquals(checkC, b.cumProd(1))
    }

    @Test
    fun testCumSumMethod() {
        val a = array(arrayOf(1, 2, 3, 4, 5, 6)).reshape(2, 3)
        val checkA = array(arrayOf(1, 3, 6, 10, 15, 21))
        val checkB = array(arrayOf(1, 3, 6, 4, 9, 15)).reshape(2, 3)

        assertEquals(checkA, a.cumSum())
        assertEquals(checkB, a.cumSum(1))
    }

    @Test
    fun testDiagonalMethod() {
        val a = arange(4).apply {
            resize(2, 2)
        }
        val checkA = array(arrayOf(0, 3))
        val checkB = array(arrayOf(1))

        assertEquals(checkA, a.diagonal())
        assertEquals(checkB, a.diagonal(1))
    }

    @Test
    fun testDotMethod() {
        val a = eye<Int>(2)
        val b = ones<Double>(2, 2) * 2
        val check0 = array(arrayOf(2.0, 2.0, 2.0, 2.0)).apply { resize(2, 2) }
        val check1 = array(arrayOf(8.0, 8.0, 8.0, 8.0)).apply { resize(2, 2) }

        assertEquals(check0, a.dot(b))
        assertEquals(check1, a.dot(b).dot(b))
    }

    @Test
    fun testFillMethod() {
        val a = array(arrayOf(1, 2))
        val check = zeros<Int>(2)

        a.fill(0)
        assertEquals(check, a)
    }

    @Test
    fun testFlattenMethod() {
        val a = array(arrayOf(1, 2, 3, 4))
        val b = a.reshape(2, 2)

        val check = array(arrayOf(1, 3, 2, 4))
        assertEquals(a, b.flatten())
        assertEquals(check, b.flatten(Order.F))
    }

    @Test
    fun testGetField() {
        val a = arange(4)
        assertEquals(Short::class.javaObjectType, a.getfield<Int, Short>().dtype)
        assertEquals(Float::class.javaObjectType, a.getfield<Int, Float>().dtype)
    }

    @Test
    fun testItemMethod() {
        val x = arange(9).apply { resize(3, 3) }

        assertEquals(3, x.item(3))
        assertEquals(7, x.item(7))
        assertEquals(1, x.item(0, 1))
        assertEquals(8, x.item(2, 2))
    }

    @Test
    fun testItemSetMethod() {
        val x = arange(9).apply { resize(3, 3) }
        x.itemset(4, element = 0)
        x.itemset(2, 2, element = 13)

        val check = array(arrayOf(0, 1, 2, 3, 0, 5, 6, 7, 13)).apply { resize(3, 3) }
        assertEquals(check, x)
    }

    @Test
    fun testMaxMethod() {
        val a = arange(4).apply { resize(2, 2) }
        assertEquals(3, a.max())

        val check = array(arrayOf(2, 3))
        assertEquals(check, a.max(0))
    }

    @Test
    fun testMeanMethod() {
        val a = array(arrayOf(1, 2, 3, 4)).apply { resize(2, 2) }

        assertEquals(2.5, a.mean())
        assertEquals(array(arrayOf(2.0, 3.0)), a.mean(0))
    }

    @Test
    fun testMinMethod() {
        val a = arange(4).apply { resize(2, 2) }
        assertEquals(0, a.min())

        val check = array(arrayOf(0, 1))
        assertEquals(check, a.min(0))
    }

    @Test
    fun testPartitionMethod() {
        val a = array(arrayOf(3, 4, 2, 1))
        a.partition(intArrayOf(3))
        val check = array(arrayOf(2, 1, 3, 4))
        assertEquals(check, a)

        a.partition(intArrayOf(1, 3))
        val check1 = array(arrayOf(1, 2, 3, 4))
        assertEquals(check1, a)
    }

    @Test
    fun testProdMethod() {
        val x = array(arrayOf(536870910, 536870910, 536870910, 536870910))
        assertEquals(16, x.prod())

        assertEquals(1, array<Int>(emptyArray()).prod())

        val a = array(arrayOf(1.0, 2.0, 3.0, 4.0)).apply { resize(2, 2) }
        assertEquals(array(arrayOf(2.0, 12.0)), a.prod(1))
    }

    @Test
    fun testPtpMethod() {
        val x = arange(4).apply { resize(2, 2) }

        assertEquals(array(arrayOf(2, 2)), x.ptp(0))
        assertEquals(array(arrayOf(1, 1)), x.ptp(1))
    }

    @Test
    fun testPutMethod() {
        val x = arange(5)
        val check = array(arrayOf(-44, 1, 2, 3, -55))
        x.put(intArrayOf(0, 10), arrayOf(-44, -55), Mode.CLIP)
        assertEquals(check, x)
    }

    @Test
    fun testRavelMethod() {
        val x = arange(6).apply { resize(2, 3) }
        val a = x.ravel()
        val b = x.flatten()
        assertEquals(b, a)
        a[3] = 0
        assertEquals(x[1, 0], a[3])
    }

    @Test
    fun testRepeatMethod() {
        val x = array(arrayOf(1, 2, 3, 4)).apply { resize(2, 2) }
        val check1 = array(arrayOf(1, 1, 2, 2, 3, 3, 4, 4))
        val check2 = array(arrayOf(1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4)).apply { resize(2, 6) }

        assertEquals(check1, x.repeat(intArrayOf(2)))
        assertEquals(check2, x.repeat(intArrayOf(3), 1))
    }

    @Test
    fun testRoundMethod() {
        val x = array(arrayOf(0.37, 1.64))
        val check1 = array(arrayOf(0.0, 2.0))
        val check2 = array(arrayOf(0.4, 1.6))

        assertEquals(check1, x.round())
        assertEquals(check2, x.round(1))
    }

    @Test
    fun testSearchSortedMethod() {
        val x = array(arrayOf(1, 2, 3, 4, 5))
        assertEquals(2L, x.searchSorted(3))
        assertEquals(array(arrayOf(0L, 5L, 1L, 2L)), x.searchSorted(array(arrayOf(-10L, 10L, 2L, 3L))))
    }

    @Test
    fun testSetFieldMethod() {
        val x = eye<Double>(3)
        x.getfield<Double, Double>()
        x.setfield<Double, Int>(3)
        x.getfield<Double, Int>()
        println(x)
    }

    @Test
    fun testSortMethod() {
        val a = array(arrayOf(1, 4, 3, 1)).apply { resize(2, 2) }
        val check1 = array(arrayOf(1, 4, 1, 3)).apply { resize(2, 2) }

        a.sort(1)
        assertEquals(check1, a)

        val check2 = array(arrayOf(1, 3, 1, 4)).apply { resize(2, 2) }

        a.sort(0)
        assertEquals(check2, a)
    }

    @Test
    fun testSqueezeMethod() {
        val x = arange(3).apply { resize(1, 3, 1) }
        assertTrue(intArrayOf(3).contentEquals(x.squeeze().shape))
    }

    @Test
    fun testStdMethod() {
        val a = array(arrayOf(1, 2, 3, 4)).apply { resize(2, 2) }
        val check = array(arrayOf(0.5, 0.5))

        assertEquals(1.1180339887498949, a.std())
        assertEquals(check, a.std(axis = 1))

    }

    @Test
    fun testSumMethod() {
        val a = array(arrayOf(0, 1, 0, 5)).apply { resize(2, 2) }
        val check = array(arrayOf(0, 6))

        assertEquals(6, a.sum())
        assertEquals(check, a.sum(0))
    }

    @Test
    fun testSwapAxesMethod() {
        val x = array(arrayOf(1, 2, 3)).apply { resize(1, 3) }
        val check = array(arrayOf(1, 2, 3)).apply { resize(3, 1) }

        assertEquals(check, x.swapAxes(0, 1))
    }

    @Test
    fun testTakeMethod() {
        val a = array(arrayOf(4, 3, 5, 7, 6, 8))
        val indices1 = array(arrayOf(0L, 1L, 4L))
        val indices2 = listOf(0, 1, 4)
        val indices3 = arrayOf(0, 1, 4)

        val check = array(arrayOf(4, 3, 6))

        assertEquals(check, a.take(indices1))
        assertEquals(check, a.take(indices2))
        assertEquals(check, a.take(indices3))
    }
}
import org.jetbrains.numkt.*
import org.jetbrains.numkt.core.KtNDArray
import org.jetbrains.numkt.core.reshape
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class TestOperationsArray {

    @Test
    fun testCopyTo() {
        val x = array(arrayOf(1, 2, 3))
        val y = array<Int>(listOf(4, 5, 6))
        copyto(x, y)
        println(x)
    }

    @Test
    fun testRavel() {
        val x = array(arrayOf(1, 2, 3, 4, 5, 6))
        val y = x.reshape(2, 3)
        assertEquals(x, ravel(y))
    }

    @Test
    fun testMoveAxis() {
        val x = zeros<Long>(3, 4, 5)
        assertTrue(intArrayOf(4, 5, 3).contentEquals(moveAxis(x, 0, -1).shape))
        assertTrue(intArrayOf(5, 3, 4).contentEquals(moveAxis(x, intArrayOf(-1), intArrayOf(0)).shape))
    }

    @Test
    fun testRollAxis() {
        val a = ones<Long>(3, 4, 5, 6)
        assertTrue(intArrayOf(3, 6, 4, 5).contentEquals(rollAxis(a, 3, 1).shape))
        assertTrue(intArrayOf(5, 3, 4, 6).contentEquals(rollAxis(a, 2).shape))
        assertTrue(intArrayOf(3, 5, 6, 4).contentEquals(rollAxis(a, 1, 4).shape))
    }

    @Test
    fun testSwapAxes() {
        val x = array<Long>(listOf(listOf(1, 2, 3)))
        val check = array<Long>(arrayOf(1L, 2L, 3L)).reshape(3, 1)
        assertEquals(check, swapAxes(x, 0, 1))
    }

    @Test
    fun testTranspose() {
        val x = arange(4).reshape(2, 2)
        val check = array<Int>(listOf(listOf(0, 2), listOf(1, 3)))
        assertEquals(check, transpose(x))
    }

    @Test
    fun testAtleast1D() {
        val y = atleast1D(1, listOf(3, 4))
        assertTrue(array(arrayOf(3, 4)) == (y[1]))
    }

    @Test
    fun testAtleast2D() {
        val x = atleast2D(1, listOf(1, 2), listOf(listOf(1, 2)))

        val l = mutableListOf<KtNDArray<out Any>>()
        l.add(0, array(arrayOf(1)).reshape(1, 1))
        l.add(1, array(arrayOf(1, 2)).reshape(1, 2))
        l.add(2, array(arrayOf(1, 2)).reshape(1, 2))
        assertTrue(l == x)
    }

    @Test
    fun testAtleast3D() {
        val x = atleast3D(listOf(1, 2), listOf(listOf(1, 2)), listOf(listOf(listOf(1, 2))))
        assertTrue(intArrayOf(1, 2, 1).contentEquals(x[0].shape))
        assertTrue(intArrayOf(1, 2, 1).contentEquals(x[1].shape))
        assertTrue(intArrayOf(1, 1, 2).contentEquals(x[2].shape))
    }


    @Test
    fun testAsArray() {
        val a = listOf(1, 2)
        assertEquals(array(arrayOf(1, 2)), asArray(a, order = Order.C))

        val b = array(arrayOf(1f, 2f))
        assertEquals(b, asArray(a))
        assertFalse(b.dtype == asArray<Double>(a).dtype)
    }

    @Test
    fun testAsAnyArray() {
        val a = listOf(1, 2)
        assertEquals(array(arrayOf(1, 2)), asAnyArray(a))

        val b = array<Float>(listOf(arrayOf(1.0, 2), arrayOf(3.0, 4)))
        assertEquals(b, asAnyArray(b))

        assertEquals(array(arrayOf(1, 2)), asAnyArray(arrayOf(1, 2)))
    }

    @Test
    fun testAsContiguousArray() {
        val x = arange(6).reshape(2, 3)
        assertEquals(Float::class.javaObjectType, asContiguousArray<Float>(x).dtype)
    }
}
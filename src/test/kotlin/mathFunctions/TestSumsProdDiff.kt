package mathFunctions

import org.jetbrains.numkt.arange
import org.jetbrains.numkt.array
import org.jetbrains.numkt.core.reshape
import org.jetbrains.numkt.math.*
import kotlin.test.Test
import kotlin.test.assertEquals

class TestSumsProdDiff {

    @Test
    fun testProd() {
        val a = array(arrayOf(536870910, 536870910, 536870910, 536870910))
        assertEquals(prod(a), 16)

        val a1 = array(arrayOf(1.0, 2.0, 3.0, 4.0)).reshape(2, 2)
        assertEquals(prod(a1, axis = 1), array(arrayOf(2.0, 12.0)))
    }

    @Test
    fun testSum() {
        val a = array(arrayOf(0.5, 1.5))
        assertEquals(sum(a), 2.0)

        val a1 = array(arrayOf(0, 1, 0, 5)).reshape(2, 2)
        assertEquals(sum(a1, axis = 0), array(arrayOf(0, 6)))
        assertEquals(sum(a1, axis = 1), array(arrayOf(1, 5)))
    }

    @Test
    fun testCumProd() {
        val a = array(arrayOf(1, 2, 3))
        assertEquals(cumprod(a), array(arrayOf(1, 2, 6)))
    }

    @Test
    fun testCumSum() {
        val a = array(arrayOf(1, 2, 3, 4, 5, 6)).reshape(2, 3)
        assertEquals(cumsum(a), array(arrayOf(1, 3, 6, 10, 15, 21)))
    }

    @Test
    fun testDiff() {
        val a = array(arrayOf(1.toByte(), 0.toByte()))
        assertEquals(diff(a), array(arrayOf(255.toByte())))

        val b = array(arrayOf(1.toShort(), 0.toShort()))
        assertEquals(diff(b), array(arrayOf((-1).toShort())))

        val x = array(arrayOf(1, 2, 4, 7, 0))
        assertEquals(diff(x), array(arrayOf(1, 2, 3, -7)))
    }

    @Test
    fun testEdiff1d() {
        val x = array(arrayOf(1, 2, 4, 7, 0))
        assertEquals(ediff1d(x), array(arrayOf(1, 2, 3, -7)))
    }

    @Test
    fun testGradient() {
        val check = array(arrayOf(1.0, 1.5, 2.5, 3.5, 4.5, 5.0))
        val x = array(arrayOf(1.0, 2.0, 4.0, 7.0, 11.0, 16.0))
        assertEquals(gradient1D(x), check)
    }

    @Test
    fun testCross() {
        val x = array(arrayOf(1, 2, 3))
        val y = array(arrayOf(4, 5, 6))
        assertEquals(cross(x, y), array(arrayOf(-3.0, 6.0, -3.0)))
    }

    @Test
    fun testTrapz() {
        val a = array(arrayOf(1, 2, 3))
        val x = array(arrayOf(4, 6, 8))

        val t1 = trapz(a)
        assertEquals(4.0, t1)

        val t2 = trapz(a, x)
        assertEquals(8.0, t2)

        val t3 = trapz(a, dx = 2.0)
        assertEquals(8.0, t3)

        val b = arange(6).reshape(2, 3)
        val t4 = trapz(b, axis = 0)
        assertEquals(array(arrayOf(1.5, 2.5, 3.5)), t4)

        val t5 = trapz(b, axis = 1)
        assertEquals(array(arrayOf(2.0, 8.0)), t5)
    }
}
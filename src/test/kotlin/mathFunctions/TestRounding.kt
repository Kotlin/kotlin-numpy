package mathFunctions

import org.jetbrains.numkt.array
import org.jetbrains.numkt.math.*
import kotlin.test.Test
import kotlin.test.assertEquals

class TestRounding {

    @Test
    fun testAround() {
        val check0 = array(arrayOf(0.0, 2.0))
        val a0 = array(arrayOf(0.37, 1.64))
        assertEquals(around(a0), check0)

        val check1 = array(arrayOf(0.4, 1.6))
        assertEquals(around(a0, decimals = 1), check1)

        val check2 = array(arrayOf(0.0, 2.0, 2.0, 4.0, 4.0))
        val a2 = array(arrayOf(0.5, 1.5, 2.5, 3.5, 4.5))
        assertEquals(around(a2), check2)

        val check3 = array(arrayOf(1, 2, 3, 11))
        val a3 = array(arrayOf(1, 2, 3, 11))
        assertEquals(around(a3, decimals = 1), check3)

        val check4 = array(arrayOf(0, 0, 0, 10))
        assertEquals(around(a3, decimals = -1), check4)
    }

    @Test
    fun testRint() {
        val check = array(arrayOf(-2.0, -2.0, -0.0, 0.0, 2.0, 2.0, 2.0))
        val a = array(arrayOf(-1.7, -1.5, -0.2, 0.2, 1.5, 1.7, 2.0))
        assertEquals(rint(a), check)
    }

    @Test
    fun testFix() {
        val check = array(arrayOf(3.0, 2.0, 2.0, -2.0, -2.0))
        val a = array(arrayOf(3.14, 2.1, 2.9, -2.1, -2.9))
        assertEquals(fix(a), check)
    }

    @Test
    fun testFloor() {
        val check = array(arrayOf(-2.0, -2.0, -1.0, 0.0, 1.0, 1.0, 2.0))
        val a = array(arrayOf(-1.7, -1.5, -0.2, 0.2, 1.5, 1.7, 2.0))
        assertEquals(floor(a), check)
    }

    @Test
    fun testCeil() {
        val check = array(arrayOf(-1.0, -1.0, -0.0, 1.0, 2.0, 2.0, 2.0))
        val a = array(arrayOf(-1.7, -1.5, -0.2, 0.2, 1.5, 1.7, 2.0))
        assertEquals(ceil(a), check)
    }

    @Test
    fun testTrunc() {
        val check = array(arrayOf(-1.0, -1.0, -0.0, 0.0, 1.0, 1.0, 2.0))
        val a = array(arrayOf(-1.7, -1.5, -0.2, 0.2, 1.5, 1.7, 2.0))
        assertEquals(trunc(a), check)
    }
}
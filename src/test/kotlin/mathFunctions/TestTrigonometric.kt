package mathFunctions

import org.jetbrains.numkt.*
import org.jetbrains.numkt.core.None
import org.jetbrains.numkt.core.rangeTo
import org.jetbrains.numkt.math.*
import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals

class TestTrigonometric {

    @Test
    fun testSin() {
        val a = array(arrayOf(PI / 2))
        assertEquals(sin(a)[0].scalar, 1.0)

        val check = array(arrayOf(0.0, 0.5, 0.70711, 0.86603, 1.0))
        val b = array(arrayOf(0.0, 30.0, 45.0, 60.0, 90.0))
        assertEquals(check, around(sin(b * PI / 180.0), 5))
    }

    @Test
    fun testCos() {
        val check = array(arrayOf(1.0, 6.123233995736766E-17, -1.0))
        val a = array(arrayOf(0.0, PI / 2, PI))
        assertEquals(cos(a), check)
    }

    @Test
    fun testTan() {
        val check = array(arrayOf(0.0, 1.633123935319537E16, -1.2246467991473532E-16))
        val a = array(arrayOf(0.0, PI / 2, PI))
        assertEquals(tan(a), check)
    }

    @Test
    fun testArcsin() {
        val check = array(arrayOf(1.5707963267948966, -1.5707963267948966, 0.0))
        val a = array(arrayOf(1, -1, 0))
        assertEquals(arcsin(a), check)
    }

    @Test
    fun testArccos() {
        val check = array(arrayOf(0.0, 3.141592653589793))
        val a = array(arrayOf(1, -1))
        assertEquals(arccos(a), check)
    }

    @Test
    fun testArctan() {
        val check = array(arrayOf(0.0, PI / 4))
        val a = array(arrayOf(0.0, 1.0))
        assertEquals(arctan(a), check)
    }

    @Test
    fun testHypot() {
        val check = full(intArrayOf(3, 3), 5.0)
        val a = 3 * ones<Long>(3, 3)
        val b = 4 * ones<Long>(3, 3)
        assertEquals(hypot(a, b), check)

        val s = array(arrayOf(4))
        assertEquals(hypot(a, s), check)
    }

    @Test
    fun testArctan2() {
        val check = array(arrayOf(-135.0, -45.0, 45.0, 135.0))
        val x = array(arrayOf(-1, +1, +1, -1))
        val y = array(arrayOf(-1, -1, +1, +1))
        assertEquals(arctan2(y, x) * 180 / PI, check)

        val check1 = array(arrayOf(1.5707963267948966, -1.5707963267948966))
        val x1 = array(arrayOf(1.0, -1.0))
        val y1 = array(arrayOf(0.0, 0.0))
        assertEquals(arctan2(x1, y1), check1)
    }

    @Test
    fun testDegrees() {
        val check = array(
            arrayOf(
                0.0,
                29.999999999999996,
                59.99999999999999,
                90.0,
                119.99999999999999,
                150.0,
                180.0,
                210.0,
                239.99999999999997,
                270.0,
                300.0,
                330.0
            )
        )
        val rad = arange(12.0) * PI / 6
        assertEquals(degrees(rad), check)
    }

    @Test
    fun testRadians() {
        val check = array(
            arrayOf(
                0.0,
                0.5235987755982988,
                1.0471975511965976,
                1.5707963267948966,
                2.0943951023931953,
                2.6179938779914944,
                3.141592653589793,
                3.6651914291880923,
                4.1887902047863905,
                4.71238898038469,
                5.235987755982989,
                5.759586531581287
            )
        )
        val deg = arange(12.0) * 30.0
        assertEquals(radians(deg), check)
    }

    @Test
    fun testUnwrap() {
        val phase = linspace<Double>(0, PI, num = 5)
        phase[3..None].plusAssign(PI)

        val check = array(
            arrayOf(
                0.0,
                0.7853981633974483,
                1.5707963267948966,
                -0.7853981633974483,
                0.0
            )
        )
        assertEquals(unwrap(phase), check)
    }

    @Test
    fun testDeg2rad() {
        val a = array(arrayOf(180))
        assertEquals(deg2rad(a)[0].scalar, PI)
    }

    @Test
    fun testRad2deg() {
        val a = array(arrayOf(PI / 2))
        assertEquals(rad2deg(a)[0].scalar, 90.0)
    }
}

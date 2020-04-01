package mathFunctions

import org.jetbrains.numkt.array
import org.jetbrains.numkt.math.*
import kotlin.math.E
import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals

class TestHyperbolic {

    @Test
    fun testSinh() {
        val a = array(arrayOf(0))
        assertEquals(0.0, sinh(a)[0].scalar)
    }

    @Test
    fun testCosh() {
        val a = array(arrayOf(0))
        assertEquals(1.0, cosh(a)[0].scalar)
    }

    @Test
    fun testTanh() {
        val check = array(arrayOf(0.0, 0.9171523356672744, 0.99627207622075))
        val a = array(arrayOf(0.0, PI / 2, PI))
        assertEquals(check, tanh(a))
    }

    @Test
    fun testArcsinh() {
        val check = array(arrayOf(1.725382558852315, 2.99822295029797))
        val a = array(arrayOf(E, 10.0))
        assertEquals(check, arcsinh(a))
    }

    @Test
    fun testArccosh() {
        val check = array(arrayOf(1.6574544541530771, 2.993222846126381))
        val a = array(arrayOf(E, 10.0))
        assertEquals(check, arccosh(a))
    }

    @Test
    fun testArctanh() {
        val check = array(arrayOf(0.0, -0.54931))
        val a = array(arrayOf(0.0, -0.5))
        assertEquals(check, around(arctanh(a), 5))
    }
}
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
        assertEquals(sinh(a)[0].scalar, 0.0)
    }

    @Test
    fun testCosh() {
        val a = array(arrayOf(0))
        assertEquals(cosh(a)[0].scalar, 1.0)
    }

    @Test
    fun testTanh() {
        val check = array(arrayOf(0.0, 0.9171523356672744, 0.99627207622075))
        val a = array(arrayOf(0.0, PI / 2, PI))
        assertEquals(tanh(a), check)
    }

    @Test
    fun testArcsinh() {
        val check = array(arrayOf(1.725382558852315, 2.99822295029797))
        val a = array(arrayOf(E, 10.0))
        assertEquals(arcsinh(a), check)
    }

    @Test
    fun testArccosh() {
        val check = array(arrayOf(1.6574544541530771, 2.993222846126381))
        val a = array(arrayOf(E, 10.0))
        assertEquals(arccosh(a), check)
    }

    @Test
    fun testArctanh() {
        val check = array(arrayOf(0.0, -0.5493061443340549))
        val a = array(arrayOf(0.0, -0.5))
        assertEquals(arctanh(a), check)
    }
}
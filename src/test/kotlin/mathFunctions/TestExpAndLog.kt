package mathFunctions

import org.jetbrains.numkt.array
import org.jetbrains.numkt.core.None
import org.jetbrains.numkt.math.*
import kotlin.math.E
import kotlin.math.pow
import kotlin.test.Test
import kotlin.test.assertEquals

class TestExpAndLog {
    @Test
    fun testExpAndExpm1() {
        val check = array(arrayOf(1.00000000005e-10, 1.000000082740371e-10))
        val a = array(arrayOf(1e-10))
        assertEquals(expm1(a)[0], check[0])
        assertEquals(exp(a)[0] - 1.0, check[1])
    }

    @Test
    fun testExp2() {
        val check = array(arrayOf(4.0, 8.0))
        val a = array(arrayOf(2, 3))
        assertEquals(exp2(a), check)
    }

    @Test
    fun testLog() {
        val check = array(arrayOf(0.0, 1.0, 2.0))
        val a = array(arrayOf(1.0, E, E.pow(2), 0.0))
        assertEquals(log(a)[None..3], check)
        assertEquals(log(a)[3], Double.NEGATIVE_INFINITY)
    }

    @Test
    fun testLog10() {
        val a = array(arrayOf(1e-15, -3.0))
        assertEquals(log10(a)[0], -15.0)
        assertEquals(log10(a)[1], Double.NaN)
    }

    @Test
    fun testLog2() {
        val check = array(arrayOf(0.0, 1.0, 4.0))
        val a = array(arrayOf(0, 1, 2, 2.0.pow(4).toInt()))
        assertEquals(log2(a)[1..4], check)
        assertEquals(log2(a)[0], Double.NEGATIVE_INFINITY)
    }

    @Test
    fun testLog1p() {
        val check = array(arrayOf(1e-99))
        val a = array(arrayOf(1e-99))
        assertEquals(log1p(a), check)
    }

    @Test
    fun testLogAddExp() {
        val prob1 = log(array(arrayOf(1e-50)))
        val prob2 = log(array(arrayOf(2.5e-50)))
        val prob12 = logaddexp(prob1, prob2)
        assertEquals(prob12[0], -113.87649168120691)
        assertEquals(exp(prob12)[0], 3.5000000000000057e-50)
    }

    @Test
    fun testLogAddExp2() {
        val prob1 = log2(array(arrayOf(1e-50)))
        val prob2 = log2(array(arrayOf(2.5e-50)))
        val prob12 = logaddexp2(prob1, prob2)
        assertEquals(prob12[0], -164.28904982231052)
    }
}
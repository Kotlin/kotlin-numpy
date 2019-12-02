import org.jetbrains.numkt.arange
import org.jetbrains.numkt.math.exp
import org.jetbrains.numkt.math.sqrt
import kotlin.test.Test
import kotlin.test.assertEquals

class TestUniversalFunctions {

    @Test
    fun testUniversalFunctions() {
        val b = arange(3)

        println(b)

//            array([ 1.0, 2.71828183, 7.38905609893065])
        assertEquals(2.718281828459045, exp(b)[1])


//            array([ 0.0, 1.0, 1.4142135623730951])
        assertEquals(1.4142135623730951, sqrt(b)[2])
    }
}
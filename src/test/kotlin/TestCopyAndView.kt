import org.jetbrains.numkt.arange
import org.jetbrains.numkt.core.copy
import org.jetbrains.numkt.core.reshape
import org.jetbrains.numkt.core.transpose
import org.jetbrains.numkt.math.plusAssign
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class TestCopyAndView {

    @Test
    fun testCopy() {
        val a = arange(12)
        val b = a.copy()

        assertEquals(a, b)

        b += 1

        assertNotEquals(a, b)
    }

    @Test
    fun testTranspose() {
        val x = arange(4).reshape(2, 2)
        println(x)
        val a = x.transpose()
        println(x)
        println(a)
        for (el in a) {
            println(el)
        }
    }
}
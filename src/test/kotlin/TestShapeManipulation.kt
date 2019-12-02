import org.jetbrains.numkt.arange
import org.jetbrains.numkt.core.ravel
import org.jetbrains.numkt.core.reshape
import org.jetbrains.numkt.core.resize
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestShapeManipulation {


    @Test
    fun test() {
        val shape = intArrayOf(3, 4)
        val a = arange(12.0)//.reshape(3, 4)
        val b = a.reshape(*shape)

        assertTrue(shape.contentEquals(b.shape))

        println(b.ravel())
        assertEquals(a, b.ravel())

        println(b.reshape(6, 2))

        println(b.t)
        assertTrue(shape.contentEquals(b.t.shape.reversedArray()))


        a.resize(*shape)
        assertTrue(shape.contentEquals(a.shape))

        val c = a.reshape(3, -1)
        assertTrue(shape.contentEquals(c.shape))
    }
}
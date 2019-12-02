import org.jetbrains.numkt.arange
import org.jetbrains.numkt.array
import org.jetbrains.numkt.core.resize
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestBeginExample {
    @Test
    fun testBeginExample() {
        val shape = intArrayOf(3, 5)
        val a = arange(15).apply {
            println(this)
            resize(*shape)
        }
        val s = arange(15)


        println(s)

        println(s.dtype)

        val shape1 = a.shape

        assertTrue(shape1.contentEquals(shape))

        assertEquals(2, a.ndim)

        assertEquals(Int::class.javaObjectType, a.dtype)

        assertEquals(Int.SIZE_BYTES, a.itemsize)

        val b = array(arrayOf(6, 7, 8))
        println(b)
    }
}


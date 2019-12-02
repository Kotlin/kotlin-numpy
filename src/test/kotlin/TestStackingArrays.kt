import org.jetbrains.numkt.array
import org.jetbrains.numkt.columnStack
import org.jetbrains.numkt.core.reshape
import org.jetbrains.numkt.hstack
import org.jetbrains.numkt.vstack
import kotlin.test.Test
import kotlin.test.assertEquals

class TestStackingArrays {

    @Test
    fun testStackTwoArrays() {
        val checkVStack = array(arrayOf(0, 1, 2, 3, 4, 5, 6, 7)).reshape(4, 2)
        val checkHStack = array(arrayOf(0, 1, 4, 5, 2, 3, 6, 7)).reshape(2, 4)

        val a = array(arrayOf(0, 1, 2, 3)).reshape(2, 2)

        val b = array(arrayOf(4, 5, 6, 7)).reshape(2, 2)

        println(a)
        println(b)

        println("vstack:")
        println(vstack(a, b))
        assertEquals(checkVStack, vstack(a, b))

        println("hstack:")
        println(hstack(a, b))
        assertEquals(checkHStack, hstack(a, b))
    }

    @Test
    fun testNewAxis() {
        val a = array(arrayOf(0, 1, 2, 3)).reshape(2, 2)
        val b = array(arrayOf(4, 5, 6, 7)).reshape(2, 2)

        println(columnStack(a, b))

        val q = array(arrayOf(4.0, 2.0))
        val w = array(arrayOf(3.0, 8.0))

        println(columnStack(q, w))
        println(hstack(q, w))
    }
}
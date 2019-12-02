import org.jetbrains.numkt.arange
import org.jetbrains.numkt.array
import org.jetbrains.numkt.core.reshape
import org.jetbrains.numkt.hsplit
import kotlin.test.Test
import kotlin.test.assertEquals

class TestSplitting {

    @Test
    fun testSplit() {
        val check = array(arrayOf(0, 1, 2, 3, 12, 13, 14, 15)).reshape(2, 4)
        val a = arange(24).reshape(2, 12)

        val b = hsplit(a, 3)
        assertEquals(check, b[0])
    }
}
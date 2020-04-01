import org.jetbrains.numkt.arange
import org.jetbrains.numkt.core.None
import org.jetbrains.numkt.core.rangeTo
import org.jetbrains.numkt.math.power
import kotlin.math.pow
import kotlin.test.Test
import kotlin.test.assertEquals

class TestIndexingSlicingAndIterating {

    @Test
    fun testOneDimension() {
        val a = power(arange(10L), 3)
        println(a)

        //  a[2]
        assertEquals(8, a[2].scalar)

        println(a[2..5..1]) // a[2:5]

        // equivalent to a[0:6:2] = -1000 in python; from start to position 6, set every 2nd element to -1000
        a[0..6..2] = -1000
        println(a)

        // reverse
        println(a[None..None..-1])


        for (el in a.flatIter()) {
            println((el.toDouble().pow(1.0 / 3.0)))
        }
    }
}
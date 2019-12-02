import org.jetbrains.numkt.*
import org.jetbrains.numkt.core.reshape
import org.jetbrains.numkt.core.resize
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class TestArrayCreation {
    @Test
    fun testCreateNdarray() {
        val a = array(arrayOf(2, 3, 4))
        assertEquals(Int::class.javaObjectType, a.dtype)
        println(a)

        val b = array(arrayOf(1.2, 3.5, 5.1))
        assertEquals(Double::class.javaObjectType, b.dtype)
        println(b)

    }

    @Test
    fun testCreateNDArrayThroughAnotherArray() {
        val b = array(arrayOf(1.5f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f)).reshape(2, 3)
        assertEquals(Float::class.javaObjectType, b.dtype)
    }

    @Test
    fun testZerosArray() {
        val shape = intArrayOf(3, 4)
        val b = zeros<Byte>(*shape)
        assertTrue(shape.contentEquals(b.shape))
        assertEquals(Byte::class.javaObjectType, b.dtype)
    }

    @Test
    fun testOnesArray() {
        val shape = intArrayOf(2, 3, 4)
        val b = ones<Short>(*shape)
        assertTrue(shape.contentEquals(b.shape))
        assertEquals(Short::class.javaObjectType, b.dtype)
    }

    @Test
    fun testEmptyArray() {
        val shape = intArrayOf(2, 3)
        val b = empty<Int>(*shape)
        assertTrue(shape.contentEquals(b.shape))
        assertEquals(Int::class.javaObjectType, b.dtype)

        val c = empty<Float>(1, 1, 1, 1, 1, 1, 1)
        val str = c.toString()
        val count = str.count { it == '[' || it == ']' }
        assertEquals(14, count)
    }

    @Test
    fun testLinspaceArray() {
        val b = linspace<Float>(0, 2, 9)
        assertEquals(Float::class.javaObjectType, b.dtype)
        println(b)

        val x = linspace<Double>(0, 2 * Math.PI, 100)
        assertEquals(Double::class.javaObjectType, x.dtype)
        println(x)
    }

    @Test
    fun testEyeArray() {
        val b = eye<Int>(2)
        assertTrue(intArrayOf(2, 2).contentEquals(b.shape))

        val s = eye<Float>(3, k = 1)
        assertEquals(1f, s[0, 1])
        assertEquals(Float::class.javaObjectType, s.dtype)
    }

    @Test
    fun testEmptyLikeArray() {
        val a = array(arrayOf(1.0, 2.0, 3.0, 4.0, 5.0, 6.0)).apply { resize(2, 3) }
        val b = emptyLike(a)
        assertTrue(a.shape.contentEquals(b.shape))
    }

    @Test
    fun testIdentityArray() {
        val a = identity<Long>(3)
        val b = eye<Long>(3)
        assertEquals(a, b)
    }

    @Test
    fun testOnesLikeArray() {
        val a = array(arrayOf(1.5f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f)).reshape(2, 3)
        val b = onesLike(a)

        val c = ones<Float>(2, 3)
        assertEquals(c, b)
    }

    @Test
    fun testZerosLikeArray() {
        val a = array(arrayOf(1.5f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f)).reshape(2, 3)
        val b = zerosLike(a)

        val c = zeros<Float>(2, 3)
        assertEquals(c, b)
    }

    @Test
    fun testFullArray() {
        val a = full(intArrayOf(2, 2), 10)
        val b = array(arrayOf(10, 10, 10, 10)).reshape(2, 2)
        assertEquals(b, a)
    }

    @Test
    fun testFullLikeArray() {
        val a = array(arrayOf(1.5f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f)).reshape(2, 3)
        val b = fullLike(a, 13.2f)

        val c = full(intArrayOf(2, 3), 13.2f)
        assertEquals(c, b)
    }

    @Test
    fun testCopy() {
        val x = array<Long>(listOf(1, 2, 3))
        val y = x
        val z = copy(x)
        assertEquals(y, z)
    }

    @Test
    fun testFromString() {
        val x = fromstring<Double>("1 2", sep = " ")
        assertEquals(array(arrayOf(1.0, 2.0)), x)

        val y = fromstring<Int>("1, 2, 3", sep = ",")
        assertEquals(array(arrayOf(1, 2, 3)), y)
    }

    @Test
    fun testLogSpace() {
        val y = logspace<Double>(2.0, 3.0, num = 4)
        val check = array<Double>(listOf(100.0, 215.44346900318845, 464.15888336127773, 1000.0))
        assertEquals(check, y)
    }

    @Test
    fun testGeomSpace() {
        val y = geomspace<Double>(1, 1000, num = 4)
        val check = array<Double>(listOf(1.0, 10.0, 100.0, 1000.0))
        assertEquals(check, y)
    }

    @Test
    fun testTri() {
        val x = tri<Int>(3, 5, 2)
        val check = array<Int>(
            listOf(
                listOf(1, 1, 1, 0, 0),
                listOf(1, 1, 1, 1, 0),
                listOf(1, 1, 1, 1, 1)
            )
        )
        assertEquals(check, x)
    }

    @Test
    fun testTril() {
        val x = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9), listOf(10, 11, 12))
        val check = array<Long>(
            listOf(
                listOf(0, 0, 0),
                listOf(4, 0, 0),
                listOf(7, 8, 0),
                listOf(10, 11, 12)
            )
        )
        assertEquals(check, tril(x, k = -1))
    }

    @Test
    fun testTriu() {
        val x = listOf(listOf(1, 2, 3), listOf(4, 5, 6), listOf(7, 8, 9), listOf(10, 11, 12))
        val check = array<Long>(
            listOf(
                listOf(1, 2, 3),
                listOf(4, 5, 6),
                listOf(0, 8, 9),
                listOf(0, 0, 12)
            )
        )
        assertEquals(check, triu(x, k = -1))
    }

    @Test
    fun testVander() {
        val n = 3
        val x = array(arrayOf(1, 2, 3, 5))
        val y = arrayOf(1, 2, 3, 5)
        val z = listOf(1, 2, 3, 5)

        val check = array<Int>(
            listOf(
                listOf(1, 1, 1),
                listOf(4, 2, 1),
                listOf(9, 3, 1),
                listOf(25, 5, 1)
            )
        )

        assertEquals(check, vander(x, n))
        assertEquals(check, vander(y, n))
        assertEquals(check, vander(z, n))
    }

    @Test
    fun testMat() {
        val a = "1 2; 3 4"
        val b = listOf<Long>(1, 2, 3, 4)
        val c = listOf(listOf<Long>(1, 2), listOf<Long>(3, 4))

        val check1 = array<Long>(listOf(listOf(1, 2), listOf(3, 4)))
        val check2 = array<Long>(listOf(1, 2, 3, 4)).reshape(1, 4)

        assertEquals(check1, mat(a))
        assertEquals(check1, mat(c))
        assertEquals(check2, mat(b))
    }

    @Test
    fun testBMat() {
        val a = mat<Byte>("1 1; 1 1")
        val b = mat<Byte>("2 2; 2 2")
        val c = mat<Byte>("3 4; 5 6")
        val d = mat<Byte>("7 8; 9 0")

        val data = listOf(listOf(a, b), listOf(c, d))
        val data1 = listOf(a, b, c, d)

        val check = array<Byte>(
            listOf(
                listOf(1, 1, 2, 2),
                listOf(1, 1, 2, 2),
                listOf(3, 4, 7, 8),
                listOf(5, 6, 9, 0)
            )
        )
        val check1 = array<Byte>(
            listOf(
                listOf(1, 1, 2, 2, 3, 4, 7, 8),
                listOf(1, 1, 2, 2, 5, 6, 9, 0)
            )
        )

        assertEquals(check, bmat(data))
        assertEquals(check1, bmat(data1))
    }
}
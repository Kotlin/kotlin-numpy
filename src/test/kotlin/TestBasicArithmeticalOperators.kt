import org.jetbrains.numkt.arange
import org.jetbrains.numkt.array
import org.jetbrains.numkt.core.*
import org.jetbrains.numkt.linalg.dot
import org.jetbrains.numkt.linspace
import org.jetbrains.numkt.math.*
import org.jetbrains.numkt.ones
import kotlin.math.PI
import kotlin.test.Test
import kotlin.test.assertEquals


class TestBasicArithmeticalOperators {

    @Test
    fun testBasicOperations() {
        val a = array(arrayOf(20, 30, 40, 50))
        val b = arange(4f)

        val c = a - b
        val c1 = b - a

        println(c)
        println(b)
        println("c \n $c")
        c1 *= -1
        println("c1 \n $c1")

        assertEquals(c1, c)

        println(c1.dtype)

        assertEquals(38.0, c[2])

        println(power(b, 2f))

        println(sin(a) * 10.0)
    }

    @Test
    fun testMultiplyArray() {
        val a = array(arrayOf(1, 1, 0, 1)).reshape(2, 2)
        val b = array(arrayOf(2, 0, 3, 4)).reshape(2, 2)

        val res1 = array(arrayOf(2, 0, 0, 4)).reshape(2, 2)
        val res2 = array(arrayOf(5, 4, 3, 4)).reshape(2, 2)

        assertEquals(res1, (a * b))
        assertEquals(res2, dot(a, b))
    }

    @Test
    fun testOperationsAssign() {
        val a = ones<Double>(2, 3)
        val b = ones<Double>(2, 3)


        a *= 3.0
        println(a)

        b += a
        assertEquals(a + 1, b)
        println(b)
    }

    @Test
    fun testUpcasting() {
        val a = ones<Int>(3)
        val b = linspace<Double>(0, PI, 3)

        val c = a + b

        assertEquals(2.5707963267948966, c[1])
        println(c)
        assertEquals(Double::class.javaObjectType, c.dtype)
    }


    @Test
    fun testUnaryOperations() {
        val a = array(arrayOf(2, 3, 4, -1, 7, 9)).reshape(2, 3)

        println(a)

        assertEquals(24, a.sum())

        assertEquals(-1, a.min())

        assertEquals(9, a.max())
    }

    @Test
    fun testAxis() {
        val b = arange(12).reshape(3, 4)

        println(b)

        assertEquals(15, (b.sum(0))[1])

        assertEquals(8, (b.min(1))[2])

        println(b.cumSum(axis = 1))
    }

    @Test
    fun testDot() {
        val a = array(arrayOf(1, 1, 0, 1)).reshape(2, 2)
        val b = array(arrayOf(2, 0, 3, 4)).reshape(2, 2)

        println(a * b) // elementwise product

        val res1 = a `@` b // matrix product
        val res2 = dot(a, b) // another product

        println(res1)
        println(res2)

        assertEquals(res1, res2)
    }


    @Test
    fun testBytePlusBetweenTwoArrays() {
        val a = array(byteArrayOf(1, 2, 3).toTypedArray())

        // 1
        val check1 = array(byteArrayOf(2, 4, 6).toTypedArray())
        val res1 = a + array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check1, res1)
        assertEquals(Byte::class.javaObjectType, res1.dtype)

        // 2
        val check2 = array(shortArrayOf(2, 4, 6).toTypedArray())
        val res2 = a + array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check2, res2)
        assertEquals(Short::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(intArrayOf(2, 4, 6).toTypedArray())
        val res3 = a + array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Int::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(longArrayOf(2, 4, 6).toTypedArray())
        val res4 = a + array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Long::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(floatArrayOf(2f, 4f, 6f).toTypedArray())
        val res5 = a + array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Float::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res6 = a + array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testBytePlusBetweenScalarAndArray() {
        val check = array(byteArrayOf(2, 3, 4).toTypedArray())

        val a = array(byteArrayOf(1, 2, 3).toTypedArray())

        // 1
        val res1 = a + 1.toByte()
        assertEquals(check, res1)
        assertEquals(Byte::class.javaObjectType, res1.dtype)
        val res12 = 1.toByte() + a
        assertEquals(check, res12)
        assertEquals(Byte::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a + 1.toShort()
        assertEquals(check, res2)
        assertEquals(Byte::class.javaObjectType, res2.dtype)
        val res22 = 1.toShort() + a
        assertEquals(check, res22)
        assertEquals(Byte::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a + 1
        assertEquals(check, res3)
        assertEquals(Byte::class.javaObjectType, res3.dtype)
        val res32 = 1 + a
        assertEquals(check, res32)
        assertEquals(Byte::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a + 1.toLong()
        assertEquals(check, res4)
        assertEquals(Byte::class.javaObjectType, res4.dtype)
        val res42 = 1.toLong() + a
        assertEquals(check, res42)
        assertEquals(Byte::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(doubleArrayOf(2.0, 3.0, 4.0).toTypedArray())
        val res5 = a + 1.toFloat()
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 1.toFloat() + a
        assertEquals(check5, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(doubleArrayOf(2.0, 3.0, 4.0).toTypedArray())
        val res6 = a + 1.toDouble()
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 1.toDouble() + a
        assertEquals(check6, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    //Begin
    @Test
    fun testShortPlusBetweenTwoArrays() {
        val a = array(shortArrayOf(1, 2, 3).toTypedArray())

        // 1
        val check1 = array(shortArrayOf(2, 4, 6).toTypedArray())
        val res1 = a + array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check1, res1)
        assertEquals(Short::class.javaObjectType, res1.dtype)

        // 2
        val check2 = array(shortArrayOf(2, 4, 6).toTypedArray())
        val res2 = a + array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check2, res2)
        assertEquals(Short::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(intArrayOf(2, 4, 6).toTypedArray())
        val res3 = a + array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Int::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(longArrayOf(2, 4, 6).toTypedArray())
        val res4 = a + array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Long::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(floatArrayOf(2f, 4f, 6f).toTypedArray())
        val res5 = a + array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Float::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res6 = a + array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testShortPlusBetweenScalarAndArray() {
        val check = array(shortArrayOf(2, 3, 4).toTypedArray())

        val a = array(shortArrayOf(1, 2, 3).toTypedArray())

        // 1
        val res1 = a + 1.toByte()
        assertEquals(check, res1)
        assertEquals(Short::class.javaObjectType, res1.dtype)
        val res12 = 1.toByte() + a
        assertEquals(check, res12)
        assertEquals(Short::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a + 1.toShort()
        assertEquals(check, res2)
        assertEquals(Short::class.javaObjectType, res2.dtype)
        val res22 = 1.toShort() + a
        assertEquals(check, res22)
        assertEquals(Short::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a + 1
        assertEquals(check, res3)
        assertEquals(Short::class.javaObjectType, res3.dtype)
        val res32 = 1 + a
        assertEquals(check, res32)
        assertEquals(Short::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a + 1.toLong()
        assertEquals(check, res4)
        assertEquals(Short::class.javaObjectType, res4.dtype)
        val res42 = 1.toLong() + a
        assertEquals(check, res42)
        assertEquals(Short::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(doubleArrayOf(2.0, 3.0, 4.0).toTypedArray())
        val res5 = a + 1.toFloat()
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 1.toFloat() + a
        assertEquals(check5, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(doubleArrayOf(2.0, 3.0, 4.0).toTypedArray())
        val res6 = a + 1.toDouble()
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 1.toDouble() + a
        assertEquals(check6, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testIntPlusBetweenTwoArrays() {
        val a = array(intArrayOf(1, 2, 3).toTypedArray())

        // 1
        val check1 = array(intArrayOf(2, 4, 6).toTypedArray())
        val res1 = a + array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check1, res1)
        assertEquals(Int::class.javaObjectType, res1.dtype)

        // 2
        val check2 = array(intArrayOf(2, 4, 6).toTypedArray())
        val res2 = a + array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check2, res2)
        assertEquals(Int::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(intArrayOf(2, 4, 6).toTypedArray())
        val res3 = a + array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Int::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(longArrayOf(2, 4, 6).toTypedArray())
        val res4 = a + array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Long::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res5 = a + array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res6 = a + array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testIntPlusBetweenScalarAndArray() {
        val check = array(intArrayOf(2, 3, 4).toTypedArray())

        val a = array(intArrayOf(1, 2, 3).toTypedArray())

        // 1
        val res1 = a + 1.toByte()
        assertEquals(check, res1)
        assertEquals(Int::class.javaObjectType, res1.dtype)
        val res12 = 1.toByte() + a
        assertEquals(check, res12)
        assertEquals(Int::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a + 1.toShort()
        assertEquals(check, res2)
        assertEquals(Int::class.javaObjectType, res2.dtype)
        val res22 = 1.toShort() + a
        assertEquals(check, res22)
        assertEquals(Int::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a + 1
        assertEquals(check, res3)
        assertEquals(Int::class.javaObjectType, res3.dtype)
        val res32 = 1 + a
        assertEquals(check, res32)
        assertEquals(Int::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a + 1.toLong()
        assertEquals(check, res4)
        assertEquals(Int::class.javaObjectType, res4.dtype)
        val res42 = 1.toLong() + a
        assertEquals(check, res42)
        assertEquals(Int::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(doubleArrayOf(2.0, 3.0, 4.0).toTypedArray())
        val res5 = a + 1.toFloat()
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 1.toFloat() + a
        assertEquals(check5, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(doubleArrayOf(2.0, 3.0, 4.0).toTypedArray())
        val res6 = a + 1.toDouble()
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 1.toDouble() + a
        assertEquals(check6, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testLongPlusBetweenTwoArrays() {
        val a = array(longArrayOf(1, 2, 3).toTypedArray())

        // 1
        val check1 = array(longArrayOf(2, 4, 6).toTypedArray())
        val res1 = a + array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check1, res1)
        assertEquals(Long::class.javaObjectType, res1.dtype)

        // 2
        val check2 = array(longArrayOf(2, 4, 6).toTypedArray())
        val res2 = a + array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check2, res2)
        assertEquals(Long::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(longArrayOf(2, 4, 6).toTypedArray())
        val res3 = a + array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Long::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(longArrayOf(2, 4, 6).toTypedArray())
        val res4 = a + array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Long::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res5 = a + array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res6 = a + array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testLongPlusBetweenScalarAndArray() {
        val check = array(longArrayOf(2, 3, 4).toTypedArray())

        val a = array(longArrayOf(1, 2, 3).toTypedArray())

        // 1
        val res1 = a + 1.toByte()
        assertEquals(check, res1)
        assertEquals(Long::class.javaObjectType, res1.dtype)
        val res12 = 1.toByte() + a
        assertEquals(check, res12)
        assertEquals(Long::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a + 1.toShort()
        assertEquals(check, res2)
        assertEquals(Long::class.javaObjectType, res2.dtype)
        val res22 = 1.toShort() + a
        assertEquals(check, res22)
        assertEquals(Long::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a + 1
        assertEquals(check, res3)
        assertEquals(Long::class.javaObjectType, res3.dtype)
        val res32 = 1 + a
        assertEquals(check, res32)
        assertEquals(Long::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a + 1.toLong()
        assertEquals(check, res4)
        assertEquals(Long::class.javaObjectType, res4.dtype)
        val res42 = 1.toLong() + a
        assertEquals(check, res42)
        assertEquals(Long::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(doubleArrayOf(2.0, 3.0, 4.0).toTypedArray())
        val res5 = a + 1.toFloat()
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)

        val check52 = array(doubleArrayOf(2.0, 3.0, 4.0).toTypedArray())
        val res52 = 1.toFloat() + a
        assertEquals(check52, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(doubleArrayOf(2.0, 3.0, 4.0).toTypedArray())
        val res6 = a + 1.toDouble()
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 1.toDouble() + a
        assertEquals(check6, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testFloatPlusBetweenTwoArrays() {
        val a = array(floatArrayOf(1f, 2f, 3f).toTypedArray())

        // 1
        val check1 = array(floatArrayOf(2f, 4f, 6f).toTypedArray())
        val res1 = a + array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check1, res1)
        assertEquals(Float::class.javaObjectType, res1.dtype)

        // 2
        val check2 = array(floatArrayOf(2f, 4f, 6f).toTypedArray())
        val res2 = a + array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check2, res2)
        assertEquals(Float::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res3 = a + array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res4 = a + array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(floatArrayOf(2f, 4f, 6f).toTypedArray())
        val res5 = a + array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Float::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res6 = a + array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testFloatPlusBetweenScalarAndArray() {
        val check = array(floatArrayOf(2f, 3f, 4f).toTypedArray())

        val a = array(floatArrayOf(1f, 2f, 3f).toTypedArray())

        // 1
        val res1 = a + 1.toByte()
        assertEquals(check, res1)
        assertEquals(Float::class.javaObjectType, res1.dtype)
        val res12 = 1.toByte() + a
        assertEquals(check, res12)
        assertEquals(Float::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a + 1.toShort()
        assertEquals(check, res2)
        assertEquals(Float::class.javaObjectType, res1.dtype)
        val res22 = 1.toShort() + a
        assertEquals(check, res22)
        assertEquals(Float::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a + 1
        assertEquals(check, res3)
        assertEquals(Float::class.javaObjectType, res1.dtype)
        val res32 = 1 + a
        assertEquals(check, res32)
        assertEquals(Float::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a + 1.toLong()
        assertEquals(check, res4)
        assertEquals(Float::class.javaObjectType, res1.dtype)
        val res42 = 1.toLong() + a
        assertEquals(check, res42)
        assertEquals(Float::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(floatArrayOf(2f, 3f, 4f).toTypedArray())
        val res5 = a + 1.toFloat()
        assertEquals(check5, res5)
        assertEquals(Float::class.javaObjectType, res1.dtype)
        val res52 = 1.toFloat() + a
        assertEquals(check5, res52)
        assertEquals(Float::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(floatArrayOf(2f, 3f, 4f).toTypedArray())
        val res6 = a + 1.toDouble()
        assertEquals(check6, res6)
        assertEquals(Float::class.javaObjectType, res1.dtype)
        val res62 = 1.toDouble() + a
        assertEquals(check6, res62)
        assertEquals(Float::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testDoublePlusBetweenTwoArrays() {
        val a = array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())

        // 1
        val check1 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res1 = a + array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check1, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)

        // 2
        val check2 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res2 = a + array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check2, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res3 = a + array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res4 = a + array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res5 = a + array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res6 = a + array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testDoublePlusBetweenScalarAndArray() {
        val check = array(doubleArrayOf(2.0, 3.0, 4.0).toTypedArray())

        val a = array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())

        // 1
        val res1 = a + 1.toByte()
        assertEquals(check, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)
        val res12 = 1.toByte() + a
        assertEquals(check, res12)
        assertEquals(Double::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a + 1.toShort()
        assertEquals(check, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)
        val res22 = 1.toShort() + a
        assertEquals(check, res22)
        assertEquals(Double::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a + 1
        assertEquals(check, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)
        val res32 = 1 + a
        assertEquals(check, res32)
        assertEquals(Double::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a + 1.toLong()
        assertEquals(check, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)
        val res42 = 1.toLong() + a
        assertEquals(check, res42)
        assertEquals(Double::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(doubleArrayOf(2.0, 3.0, 4.0).toTypedArray())
        val res5 = a + 1.toFloat()
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 1.toFloat() + a
        assertEquals(check5, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(doubleArrayOf(2.0, 3.0, 4.0).toTypedArray())
        val res6 = a + 1.toDouble()
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 1.toDouble() + a
        assertEquals(check6, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }
    //End


    //begin minus
    @Test
    fun testByteMinusBetweenTwoArrays() {
        val a = array(byteArrayOf(1, 2, 3).toTypedArray())

        // 1
        val check1 = array(byteArrayOf(0, 0, 0).toTypedArray())
        val res1 = a - array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check1, res1)
        assertEquals(Byte::class.javaObjectType, res1.dtype)

        // 2
        val check2 = array(shortArrayOf(0, 0, 0).toTypedArray())
        val res2 = a - array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check2, res2)
        assertEquals(Short::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(intArrayOf(0, 0, 0).toTypedArray())
        val res3 = a - array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Int::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(longArrayOf(0, 0, 0).toTypedArray())
        val res4 = a - array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Long::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(floatArrayOf(0f, 0f, 0f).toTypedArray())
        val res5 = a - array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Float::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(0.0, 0.0, 0.0).toTypedArray())
        val res6 = a - array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testByteMinusBetweenScalarAndArray() {
        val check = array(byteArrayOf(0, 1, 2).toTypedArray())
        val check2 = array(byteArrayOf(0, -1, -2).toTypedArray())

        val a = array(byteArrayOf(1, 2, 3).toTypedArray())

        // 1
        val res1 = a - 1.toByte()
        assertEquals(check, res1)
        assertEquals(Byte::class.javaObjectType, res1.dtype)
        val res12 = 1.toByte() - a
        assertEquals(check2, res12)
        assertEquals(Byte::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a - 1.toShort()
        assertEquals(check, res2)
        assertEquals(Byte::class.javaObjectType, res1.dtype)
        val res22 = 1.toShort() - a
        assertEquals(check2, res22)
        assertEquals(Byte::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a - 1
        assertEquals(check, res3)
        assertEquals(Byte::class.javaObjectType, res1.dtype)
        val res32 = 1 - a
        assertEquals(check2, res32)
        assertEquals(Byte::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a - 1.toLong()
        assertEquals(check, res4)
        assertEquals(Byte::class.javaObjectType, res1.dtype)
        val res42 = 1.toLong() - a
        assertEquals(check2, res42)
        assertEquals(Byte::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(doubleArrayOf(0.0, 1.0, 2.0).toTypedArray())
        val check52 = array(doubleArrayOf(0.0, -1.0, -2.0).toTypedArray())
        val res5 = a - 1.toFloat()
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 1.toFloat() - a
        assertEquals(check52, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(doubleArrayOf(0.0, 1.0, 2.0).toTypedArray())
        val check62 = array(doubleArrayOf(0.0, -1.0, -2.0).toTypedArray())
        val res6 = a - 1.toDouble()
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 1.toDouble() - a
        assertEquals(check62, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testShortMinusBetweenTwoArrays() {
        val a = array(shortArrayOf(1, 2, 3).toTypedArray())

        // 1
        val check1 = array(shortArrayOf(0, 0, 0).toTypedArray())
        val res1 = a - array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check1, res1)
        assertEquals(Short::class.javaObjectType, res1.dtype)

        // 2
        val check2 = array(shortArrayOf(0, 0, 0).toTypedArray())
        val res2 = a - array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check2, res2)
        assertEquals(Short::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(intArrayOf(0, 0, 0).toTypedArray())
        val res3 = a - array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Int::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(longArrayOf(0, 0, 0).toTypedArray())
        val res4 = a - array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Long::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(floatArrayOf(0f, 0f, 0f).toTypedArray())
        val res5 = a - array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Float::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(0.0, 0.0, 0.0).toTypedArray())
        val res6 = a - array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testShortMinusBetweenScalarAndArray() {
        val check = array(shortArrayOf(0, 1, 2).toTypedArray())
        val check2 = array(shortArrayOf(0, -1, -2).toTypedArray())

        val a = array(shortArrayOf(1, 2, 3).toTypedArray())

        // 1
        val res1 = a - 1.toByte()
        assertEquals(check, res1)
        assertEquals(Short::class.javaObjectType, res1.dtype)
        val res12 = 1.toByte() - a
        assertEquals(check2, res12)
        assertEquals(Short::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a - 1.toShort()
        assertEquals(check, res2)
        assertEquals(Short::class.javaObjectType, res2.dtype)
        val res22 = 1.toShort() - a
        assertEquals(check2, res22)
        assertEquals(Short::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a - 1
        assertEquals(check, res3)
        assertEquals(Short::class.javaObjectType, res3.dtype)
        val res32 = 1 - a
        assertEquals(check2, res32)
        assertEquals(Short::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a - 1.toLong()
        assertEquals(check, res4)
        assertEquals(Short::class.javaObjectType, res4.dtype)
        val res42 = 1.toLong() - a
        assertEquals(check2, res42)
        assertEquals(Short::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(doubleArrayOf(0.0, 1.0, 2.0).toTypedArray())
        val check52 = array(doubleArrayOf(0.0, -1.0, -2.0).toTypedArray())
        val res5 = a - 1.toFloat()
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 1.toFloat() - a
        assertEquals(check52, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(doubleArrayOf(0.0, 1.0, 2.0).toTypedArray())
        val check62 = array(doubleArrayOf(0.0, -1.0, -2.0).toTypedArray())
        val res6 = a - 1.toDouble()
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 1.toDouble() - a
        assertEquals(check62, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testIntMinusBetweenTwoArrays() {
        val a = array(intArrayOf(1, 2, 3).toTypedArray())

        // 1
        val check1 = array(intArrayOf(0, 0, 0).toTypedArray())
        val res1 = a - array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check1, res1)
        assertEquals(Int::class.javaObjectType, res1.dtype)

        // 2
        val check2 = array(intArrayOf(0, 0, 0).toTypedArray())
        val res2 = a - array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check2, res2)
        assertEquals(Int::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(intArrayOf(0, 0, 0).toTypedArray())
        val res3 = a - array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Int::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(longArrayOf(0, 0, 0).toTypedArray())
        val res4 = a - array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Long::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(doubleArrayOf(0.0, 0.0, 0.0).toTypedArray())
        val res5 = a - array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(0.0, 0.0, 0.0).toTypedArray())
        val res6 = a - array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testIntMinusBetweenScalarAndArray() {
        val check = array(intArrayOf(0, 1, 2).toTypedArray())
        val check2 = array(intArrayOf(0, -1, -2).toTypedArray())

        val a = array(intArrayOf(1, 2, 3).toTypedArray())

        // 1
        val res1 = a - 1.toByte()
        assertEquals(check, res1)
        assertEquals(Int::class.javaObjectType, res1.dtype)
        val res12 = 1.toByte() - a
        assertEquals(check2, res12)
        assertEquals(Int::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a - 1.toShort()
        assertEquals(check, res2)
        assertEquals(Int::class.javaObjectType, res2.dtype)
        val res22 = 1.toShort() - a
        assertEquals(check2, res22)
        assertEquals(Int::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a - 1
        assertEquals(check, res3)
        assertEquals(Int::class.javaObjectType, res3.dtype)
        val res32 = 1 - a
        assertEquals(check2, res32)
        assertEquals(Int::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a - 1.toLong()
        assertEquals(check, res4)
        assertEquals(Int::class.javaObjectType, res4.dtype)
        val res42 = 1.toLong() - a
        assertEquals(check2, res42)
        assertEquals(Int::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(doubleArrayOf(0.0, 1.0, 2.0).toTypedArray())
        val check52 = array(doubleArrayOf(0.0, -1.0, -2.0).toTypedArray())
        val res5 = a - 1.toFloat()
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 1.toFloat() - a
        assertEquals(check52, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(doubleArrayOf(0.0, 1.0, 2.0).toTypedArray())
        val check62 = array(doubleArrayOf(0.0, -1.0, -2.0).toTypedArray())
        val res6 = a - 1.toDouble()
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 1.toDouble() - a
        assertEquals(check62, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testLongMinusBetweenTwoArrays() {
        val check = array(longArrayOf(0, 0, 0).toTypedArray())
        val a = array(longArrayOf(1, 2, 3).toTypedArray())

        // 1
        val res1 = a - array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res1)
        assertEquals(Long::class.javaObjectType, res1.dtype)

        // 2
        val res2 = a - array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res2)
        assertEquals(Long::class.javaObjectType, res2.dtype)

        // 3
        val res3 = a - array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res3)
        assertEquals(Long::class.javaObjectType, res3.dtype)

        // 4
        val res4 = a - array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res4)
        assertEquals(Long::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(doubleArrayOf(0.0, 0.0, 0.0).toTypedArray())
        val res5 = a - array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(0.0, 0.0, 0.0).toTypedArray())
        val res6 = a - array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testLongMinusBetweenScalarAndArray() {
        val check = array(longArrayOf(0, 1, 2).toTypedArray())
        val check2 = array(longArrayOf(0, -1, -2).toTypedArray())

        val a = array(longArrayOf(1, 2, 3).toTypedArray())

        // 1
        val res1 = a - 1.toByte()
        assertEquals(check, res1)
        assertEquals(Long::class.javaObjectType, res1.dtype)
        val res12 = 1.toByte() - a
        assertEquals(check2, res12)
        assertEquals(Long::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a - 1.toShort()
        assertEquals(check, res2)
        assertEquals(Long::class.javaObjectType, res2.dtype)
        val res22 = 1.toShort() - a
        assertEquals(check2, res22)
        assertEquals(Long::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a - 1
        assertEquals(check, res3)
        assertEquals(Long::class.javaObjectType, res3.dtype)
        val res32 = 1 - a
        assertEquals(check2, res32)
        assertEquals(Long::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a - 1.toLong()
        assertEquals(check, res4)
        assertEquals(Long::class.javaObjectType, res4.dtype)
        val res42 = 1.toLong() - a
        assertEquals(check2, res42)
        assertEquals(Long::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(doubleArrayOf(0.0, 1.0, 2.0).toTypedArray())
        val check52 = array(doubleArrayOf(0.0, -1.0, -2.0).toTypedArray())
        val res5 = a - 1.toFloat()
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 1.toFloat() - a
        assertEquals(check52, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(doubleArrayOf(0.0, 1.0, 2.0).toTypedArray())
        val check62 = array(doubleArrayOf(0.0, -1.0, -2.0).toTypedArray())
        val res6 = a - 1.toDouble()
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 1.toDouble() - a
        assertEquals(check62, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testFloatMinusBetweenTwoArrays() {
        val a = array(floatArrayOf(1f, 2f, 3f).toTypedArray())

        // 1
        val check1 = array(floatArrayOf(0f, 0f, 0f).toTypedArray())
        val res1 = a - array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check1, res1)
        assertEquals(Float::class.javaObjectType, res1.dtype)

        // 2
        val check2 = array(floatArrayOf(0f, 0f, 0f).toTypedArray())
        val res2 = a - array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check2, res2)
        assertEquals(Float::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(doubleArrayOf(0.0, 0.0, 0.0).toTypedArray())
        val res3 = a - array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(doubleArrayOf(0.0, 0.0, 0.0).toTypedArray())
        val res4 = a - array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(floatArrayOf(0f, 0f, 0f).toTypedArray())
        val res5 = a - array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Float::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(0.0, 0.0, 0.0).toTypedArray())
        val res6 = a - array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testFloatMinusBetweenScalarAndArray() {
        val check = array(floatArrayOf(0f, 1f, 2f).toTypedArray())
        val check2 = array(floatArrayOf(0f, -1f, -2f).toTypedArray())

        val a = array(floatArrayOf(1f, 2f, 3f).toTypedArray())

        // 1
        val res1 = a - 1.toByte()
        assertEquals(check, res1)
        assertEquals(Float::class.javaObjectType, res1.dtype)
        val res12 = 1.toByte() - a
        assertEquals(check2, res12)
        assertEquals(Float::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a - 1.toShort()
        assertEquals(check, res2)
        assertEquals(Float::class.javaObjectType, res1.dtype)
        val res22 = 1.toShort() - a
        assertEquals(check2, res22)
        assertEquals(Float::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a - 1
        assertEquals(check, res3)
        assertEquals(Float::class.javaObjectType, res1.dtype)
        val res32 = 1 - a
        assertEquals(check2, res32)
        assertEquals(Float::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a - 1.toLong()
        assertEquals(check, res4)
        assertEquals(Float::class.javaObjectType, res1.dtype)
        val res42 = 1.toLong() - a
        assertEquals(check2, res42)
        assertEquals(Float::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(floatArrayOf(0f, 1f, 2f).toTypedArray())
        val check52 = array(floatArrayOf(0f, -1f, -2f).toTypedArray())
        val res5 = a - 1.toFloat()
        assertEquals(check5, res5)
        assertEquals(Float::class.javaObjectType, res1.dtype)
        val res52 = 1.toFloat() - a
        assertEquals(check52, res52)
        assertEquals(Float::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(floatArrayOf(0f, 1f, 2f).toTypedArray())
        val check62 = array(floatArrayOf(0f, -1f, -2f).toTypedArray())
        val res6 = a - 1.toDouble()
        assertEquals(check6, res6)
        assertEquals(Float::class.javaObjectType, res1.dtype)
        val res62 = 1.toDouble() - a
        assertEquals(check62, res62)
        assertEquals(Float::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testDoubleMinusBetweenTwoArrays() {
        val a = array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())

        // 1
        val check1 = array(doubleArrayOf(0.0, 0.0, 0.0).toTypedArray())
        val res1 = a - array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check1, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)

        // 2
        val check2 = array(doubleArrayOf(0.0, 0.0, 0.0).toTypedArray())
        val res2 = a - array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check2, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(doubleArrayOf(0.0, 0.0, 0.0).toTypedArray())
        val res3 = a - array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(doubleArrayOf(0.0, 0.0, 0.0).toTypedArray())
        val res4 = a - array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(doubleArrayOf(0.0, 0.0, 0.0).toTypedArray())
        val res5 = a - array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(0.0, 0.0, 0.0).toTypedArray())
        val res6 = a - array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testDoubleMinusBetweenScalarAndArray() {
        val check = array(doubleArrayOf(0.0, 1.0, 2.0).toTypedArray())
        val check2 = array(doubleArrayOf(0.0, -1.0, -2.0).toTypedArray())

        val a = array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())

        // 1
        val res1 = a - 1.toByte()
        assertEquals(check, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)
        val res12 = 1.toByte() - a
        assertEquals(check2, res12)
        assertEquals(Double::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a - 1.toShort()
        assertEquals(check, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)
        val res22 = 1.toShort() - a
        assertEquals(check2, res22)
        assertEquals(Double::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a - 1
        assertEquals(check, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)
        val res32 = 1 - a
        assertEquals(check2, res32)
        assertEquals(Double::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a - 1.toLong()
        assertEquals(check, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)
        val res42 = 1.toLong() - a
        assertEquals(check2, res42)
        assertEquals(Double::class.javaObjectType, res42.dtype)

        // 5
        val res5 = a - 1.toFloat()
        assertEquals(check, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 1.toFloat() - a
        assertEquals(check2, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val res6 = a - 1.toDouble()
        assertEquals(check, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 1.toDouble() - a
        assertEquals(check2, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }
    //end minus


    //begin mul
    @Test
    fun testByteMulBetweenTwoArrays() {
        val a = array(byteArrayOf(1, 2, 3).toTypedArray())

        // 1
        val check1 = array(byteArrayOf(1, 4, 9).toTypedArray())
        val res1 = a * array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check1, res1)
        assertEquals(Byte::class.javaObjectType, res1.dtype)

        // 2
        val check2 = array(shortArrayOf(1, 4, 9).toTypedArray())
        val res2 = a * array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check2, res2)
        assertEquals(Short::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(intArrayOf(1, 4, 9).toTypedArray())
        val res3 = a * array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Int::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(longArrayOf(1, 4, 9).toTypedArray())
        val res4 = a * array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Long::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(floatArrayOf(1f, 4f, 9f).toTypedArray())
        val res5 = a * array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Float::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(1.0, 4.0, 9.0).toTypedArray())
        val res6 = a * array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testByteMulBetweenScalarAndArray() {
        val check = array(byteArrayOf(2, 4, 6).toTypedArray())

        val a = array(byteArrayOf(1, 2, 3).toTypedArray())

        // 1
        val res1 = a * 2.toByte()
        assertEquals(check, res1)
        assertEquals(Byte::class.javaObjectType, res1.dtype)
        val res12 = 2.toByte() * a
        assertEquals(check, res12)
        assertEquals(Byte::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a * 2.toShort()
        assertEquals(check, res2)
        assertEquals(Byte::class.javaObjectType, res2.dtype)
        val res22 = 2.toShort() * a
        assertEquals(check, res22)
        assertEquals(Byte::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a * 2
        assertEquals(check, res3)
        assertEquals(Byte::class.javaObjectType, res3.dtype)
        val res32 = 2 * a
        assertEquals(check, res32)
        assertEquals(Byte::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a * 2.toLong()
        assertEquals(check, res4)
        assertEquals(Byte::class.javaObjectType, res4.dtype)
        val res42 = 2.toLong() * a
        assertEquals(check, res42)
        assertEquals(Byte::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res5 = a * 2.toFloat()
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 2.toFloat() * a
        assertEquals(check5, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res6 = a * 2.toDouble()
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 2.toDouble() * a
        assertEquals(check6, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testShortMulBetweenTwoArrays() {
        val a = array(shortArrayOf(1, 2, 3).toTypedArray())

        // 1
        val check1 = array(shortArrayOf(1, 4, 9).toTypedArray())
        val res1 = a * array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check1, res1)
        assertEquals(Short::class.javaObjectType, res1.dtype)

        // 2
        val check2 = array(shortArrayOf(1, 4, 9).toTypedArray())
        val res2 = a * array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check2, res2)
        assertEquals(Short::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(intArrayOf(1, 4, 9).toTypedArray())
        val res3 = a * array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Int::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(longArrayOf(1, 4, 9).toTypedArray())
        val res4 = a * array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Long::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(floatArrayOf(1f, 4f, 9f).toTypedArray())
        val res5 = a * array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Float::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(1.0, 4.0, 9.0).toTypedArray())
        val res6 = a * array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testShortMulBetweenScalarAndArray() {
        val check = array(shortArrayOf(2, 4, 6).toTypedArray())

        val a = array(shortArrayOf(1, 2, 3).toTypedArray())

        // 1
        val res1 = a * 2.toByte()
        assertEquals(check, res1)
        assertEquals(Short::class.javaObjectType, res1.dtype)
        val res12 = 2.toByte() * a
        assertEquals(check, res12)
        assertEquals(Short::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a * 2.toShort()
        assertEquals(check, res2)
        assertEquals(Short::class.javaObjectType, res2.dtype)
        val res22 = 2.toShort() * a
        assertEquals(check, res22)
        assertEquals(Short::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a * 2
        assertEquals(check, res3)
        assertEquals(Short::class.javaObjectType, res3.dtype)
        val res32 = 2 * a
        assertEquals(check, res32)
        assertEquals(Short::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a * 2.toLong()
        assertEquals(check, res4)
        assertEquals(Short::class.javaObjectType, res4.dtype)
        val res42 = 2.toLong() * a
        assertEquals(check, res42)
        assertEquals(Short::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res5 = a * 2.toFloat()
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 2.toFloat() * a
        assertEquals(check5, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res6 = a * 2.toDouble()
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 2.toDouble() * a
        assertEquals(check6, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testIntMulBetweenTwoArrays() {
        val a = array(intArrayOf(1, 2, 3).toTypedArray())

        // 1
        val check1 = array(intArrayOf(1, 4, 9).toTypedArray())
        val res1 = a * array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check1, res1)
        assertEquals(Int::class.javaObjectType, res1.dtype)

        // 2
        val check2 = array(intArrayOf(1, 4, 9).toTypedArray())
        val res2 = a * array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check2, res2)
        assertEquals(Int::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(intArrayOf(1, 4, 9).toTypedArray())
        val res3 = a * array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Int::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(longArrayOf(1, 4, 9).toTypedArray())
        val res4 = a * array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Long::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(doubleArrayOf(1.0, 4.0, 9.0).toTypedArray())
        val res5 = a * array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(1.0, 4.0, 9.0).toTypedArray())
        val res6 = a * array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testIntMulBetweenScalarAndArray() {
        val check = array(intArrayOf(2, 4, 6).toTypedArray())

        val a = array(intArrayOf(1, 2, 3).toTypedArray())

        // 1
        val res1 = a * 2.toByte()
        assertEquals(check, res1)
        assertEquals(Int::class.javaObjectType, res1.dtype)
        val res12 = 2.toByte() * a
        assertEquals(check, res12)
        assertEquals(Int::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a * 2.toShort()
        assertEquals(check, res2)
        assertEquals(Int::class.javaObjectType, res2.dtype)
        val res22 = 2.toShort() * a
        assertEquals(check, res22)
        assertEquals(Int::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a * 2
        assertEquals(check, res3)
        assertEquals(Int::class.javaObjectType, res3.dtype)
        val res32 = 2 * a
        assertEquals(check, res32)
        assertEquals(Int::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a * 2.toLong()
        assertEquals(check, res4)
        assertEquals(Int::class.javaObjectType, res4.dtype)
        val res42 = 2.toLong() * a
        assertEquals(check, res42)
        assertEquals(Int::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res5 = a * 2.toFloat()
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 2.toFloat() * a
        assertEquals(check5, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res6 = a * 2.toDouble()
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 2.toDouble() * a
        assertEquals(check6, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testLongMulBetweenTwoArrays() {
        val a = array(longArrayOf(1, 2, 3).toTypedArray())

        // 1
        val check1 = array(longArrayOf(1, 4, 9).toTypedArray())
        val res1 = a * array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check1, res1)
        assertEquals(Long::class.javaObjectType, res1.dtype)

        // 2
        val check2 = array(longArrayOf(1, 4, 9).toTypedArray())
        val res2 = a * array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check2, res2)
        assertEquals(Long::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(longArrayOf(1, 4, 9).toTypedArray())
        val res3 = a * array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Long::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(longArrayOf(1, 4, 9).toTypedArray())
        val res4 = a * array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Long::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(doubleArrayOf(1.0, 4.0, 9.0).toTypedArray())
        val res5 = a * array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(1.0, 4.0, 9.0).toTypedArray())
        val res6 = a * array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testLongMulBetweenScalarAndArray() {
        val check = array(longArrayOf(2, 4, 6).toTypedArray())

        val a = array(longArrayOf(1, 2, 3).toTypedArray())

        // 1
        val res1 = a * 2.toByte()
        assertEquals(check, res1)
        assertEquals(Long::class.javaObjectType, res1.dtype)
        val res12 = 2.toByte() * a
        assertEquals(check, res12)
        assertEquals(Long::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a * 2.toShort()
        assertEquals(check, res2)
        assertEquals(Long::class.javaObjectType, res2.dtype)
        val res22 = 2.toShort() * a
        assertEquals(check, res22)
        assertEquals(Long::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a * 2
        assertEquals(check, res3)
        assertEquals(Long::class.javaObjectType, res3.dtype)
        val res32 = 2 * a
        assertEquals(check, res32)
        assertEquals(Long::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a * 2.toLong()
        assertEquals(check, res4)
        assertEquals(Long::class.javaObjectType, res4.dtype)
        val res42 = 2.toLong() * a
        assertEquals(check, res42)
        assertEquals(Long::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res5 = a * 2.toFloat()
        assertEquals(check5, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 2.toFloat() * a
        assertEquals(check5, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val res6 = a * 2.toDouble()
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 2.toDouble() * a
        assertEquals(check6, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testFloatMulBetweenTwoArrays() {
        val check = array(floatArrayOf(1f, 4f, 9f).toTypedArray())
        val a = array(floatArrayOf(1f, 2f, 3f).toTypedArray())

        // 1
        val res1 = a * array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res1)
        assertEquals(Float::class.javaObjectType, res1.dtype)

        // 2
        val res2 = a * array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res2)
        assertEquals(Float::class.javaObjectType, res2.dtype)

        // 3
        val check3 = array(doubleArrayOf(1.0, 4.0, 9.0).toTypedArray())
        val res3 = a * array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check3, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)

        // 4
        val check4 = array(doubleArrayOf(1.0, 4.0, 9.0).toTypedArray())
        val res4 = a * array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check4, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)

        // 5
        val res5 = a * array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check, res5)
        assertEquals(Float::class.javaObjectType, res5.dtype)

        // 6
        val check6 = array(doubleArrayOf(1.0, 4.0, 9.0).toTypedArray())
        val res6 = a * array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check6, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testFloatMulBetweenScalarAndArray() {
        val check = array(floatArrayOf(2f, 4f, 6f).toTypedArray())

        val a = array(floatArrayOf(1f, 2f, 3f).toTypedArray())

        // 1
        val res1 = a * 2.toByte()
        assertEquals(check, res1)
        assertEquals(Float::class.javaObjectType, res1.dtype)
        val res12 = 2.toByte() * a
        assertEquals(check, res12)
        assertEquals(Float::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a * 2.toShort()
        assertEquals(check, res2)
        assertEquals(Float::class.javaObjectType, res2.dtype)
        val res22 = 2.toShort() * a
        assertEquals(check, res22)
        assertEquals(Float::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a * 2
        assertEquals(check, res3)
        assertEquals(Float::class.javaObjectType, res3.dtype)
        val res32 = 2 * a
        assertEquals(check, res32)
        assertEquals(Float::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a * 2.toLong()
        assertEquals(check, res4)
        assertEquals(Float::class.javaObjectType, res4.dtype)
        val res42 = 2.toLong() * a
        assertEquals(check, res42)
        assertEquals(Float::class.javaObjectType, res42.dtype)

        // 5
        val check5 = array(floatArrayOf(2f, 4f, 6f).toTypedArray())
        val res5 = a * 2.toFloat()
        assertEquals(check5, res5)
        assertEquals(Float::class.javaObjectType, res5.dtype)
        val res52 = 2.toFloat() * a
        assertEquals(check5, res52)
        assertEquals(Float::class.javaObjectType, res52.dtype)

        // 6
        val check6 = array(floatArrayOf(2f, 4f, 6f).toTypedArray())
        val res6 = a * 2.toDouble()
        assertEquals(check6, res6)
        assertEquals(Float::class.javaObjectType, res6.dtype)
        val res62 = 2.toDouble() * a
        assertEquals(check6, res62)
        assertEquals(Float::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testDoubleMulBetweenTwoArrays() {
        val check = array(doubleArrayOf(1.0, 4.0, 9.0).toTypedArray())
        val a = array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())

        // 1
        val res1 = a * array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)

        // 2
        val res2 = a * array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)

        // 3
        val res3 = a * array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)

        // 4
        val res4 = a * array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)

        // 5
        val res5 = a * array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)

        // 6
        val res6 = a * array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testDoubleMulBetweenScalarAndArray() {
        val check = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())

        val a = array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())

        // 1
        val res1 = a * 2.toByte()
        assertEquals(check, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)
        val res12 = 2.toByte() * a
        assertEquals(check, res12)
        assertEquals(Double::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a * 2.toShort()
        assertEquals(check, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)
        val res22 = 2.toShort() * a
        assertEquals(check, res22)
        assertEquals(Double::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a * 2
        assertEquals(check, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)
        val res32 = 2 * a
        assertEquals(check, res32)
        assertEquals(Double::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a * 2.toLong()
        assertEquals(check, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)
        val res42 = 2.toLong() * a
        assertEquals(check, res42)
        assertEquals(Double::class.javaObjectType, res42.dtype)

        // 5
        val res5 = a * 2.toFloat()
        assertEquals(check, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 2.toFloat() * a
        assertEquals(check, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val res6 = a * 2.toDouble()
        assertEquals(check, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 2.toDouble() * a
        assertEquals(check, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }
    //end mul

    //begin divide
    @Test
    fun testByteDivBetweenTwoArrays() {
        val check = array(doubleArrayOf(1.0, 4.0, 9.0).toTypedArray())
        val a = array(byteArrayOf(1, 8, 27).toTypedArray())

        // 1
        val res1 = a / array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)

        // 2
        val res2 = a / array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)

        // 3
        val res3 = a / array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)

        // 4
        val res4 = a / array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(floatArrayOf(1f, 4f, 9f).toTypedArray())
        val res5 = a / array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Float::class.javaObjectType, res5.dtype)

        // 6
        val res6 = a / array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testByteDivBetweenScalarAndArray() {
        val check = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val check2 = array(doubleArrayOf(6.0, 3.0, 2.0).toTypedArray())

        val a = array(byteArrayOf(4, 8, 12).toTypedArray())

        // 1
        val res1 = a / 2.toByte()
        assertEquals(check, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)
        val res12 = 24.toByte() / a
        assertEquals(check2, res12)
        assertEquals(Double::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a / 2.toShort()
        assertEquals(check, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)
        val res22 = 24.toShort() / a
        assertEquals(check2, res22)
        assertEquals(Double::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a / 2
        assertEquals(check, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)
        val res32 = 24 / a
        assertEquals(check2, res32)
        assertEquals(Double::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a / 2.toLong()
        assertEquals(check, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)
        val res42 = 24.toLong() / a
        assertEquals(check2, res42)
        assertEquals(Double::class.javaObjectType, res42.dtype)

        // 5
        val res5 = a / 2.toFloat()
        assertEquals(check, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 24.toFloat() / a
        assertEquals(check2, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val res6 = a / 2.toDouble()
        assertEquals(check, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 24.toDouble() / a
        assertEquals(check2, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testShortDivBetweenTwoArrays() {
        val check = array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        val a = array(shortArrayOf(1, 4, 9).toTypedArray())

        // 1
        val res1 = a / array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)

        // 2
        val res2 = a / array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)

        // 3
        val res3 = a / array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)

        // 4
        val res4 = a / array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)

        // 5
        val check5 = array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        val res5 = a / array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check5, res5)
        assertEquals(Float::class.javaObjectType, res5.dtype)

        // 6
        val res6 = a / array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testShortDivBetweenScalarAndArray() {
        val check = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val check2 = array(doubleArrayOf(6.0, 3.0, 2.0).toTypedArray())

        val a = array(shortArrayOf(4, 8, 12).toTypedArray())

        // 1
        val res1 = a / 2.toByte()
        assertEquals(check, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)
        val res12 = 24.toByte() / a
        assertEquals(check2, res12)
        assertEquals(Double::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a / 2.toShort()
        assertEquals(check, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)
        val res22 = 24.toShort() / a
        assertEquals(check2, res22)
        assertEquals(Double::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a / 2
        assertEquals(check, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)
        val res32 = 24 / a
        assertEquals(check2, res32)
        assertEquals(Double::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a / 2.toLong()
        assertEquals(check, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)
        val res42 = 24.toLong() / a
        assertEquals(check2, res42)
        assertEquals(Double::class.javaObjectType, res42.dtype)

        // 5
        val res5 = a / 2.toFloat()
        assertEquals(check, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 24.toFloat() / a
        assertEquals(check2, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val res6 = a / 2.toDouble()
        assertEquals(check, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 24.toDouble() / a
        assertEquals(check2, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testIntDivBetweenTwoArrays() {
        val check = array(doubleArrayOf(1.0, 4.0, 9.0).toTypedArray())
        val a = array(intArrayOf(1, 8, 27).toTypedArray())

        // 1
        val res1 = a / array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)

        // 2
        val res2 = a / array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)

        // 3
        val res3 = a / array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)

        // 4
        val res4 = a / array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)

        // 5
        val res5 = a / array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)

        // 6
        val res6 = a / array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testIntDivBetweenScalarAndArray() {
        val check = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val check2 = array(doubleArrayOf(6.0, 3.0, 2.0).toTypedArray())

        val a = array(intArrayOf(4, 8, 12).toTypedArray())

        // 1
        val res1 = a / 2.toByte()
        assertEquals(check, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)
        val res12 = 24.toByte() / a
        assertEquals(check2, res12)
        assertEquals(Double::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a / 2.toShort()
        assertEquals(check, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)
        val res22 = 24.toShort() / a
        assertEquals(check2, res22)
        assertEquals(Double::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a / 2
        assertEquals(check, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)
        val res32 = 24 / a
        assertEquals(check2, res32)
        assertEquals(Double::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a / 2.toLong()
        assertEquals(check, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)
        val res42 = 24.toLong() / a
        assertEquals(check2, res42)
        assertEquals(Double::class.javaObjectType, res42.dtype)

        // 5
        val res5 = a / 2.toFloat()
        assertEquals(check, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 24.toFloat() / a
        assertEquals(check2, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val res6 = a / 2.toDouble()
        assertEquals(check, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 24.toDouble() / a
        assertEquals(check2, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testLongDivBetweenTwoArrays() {
        val check = array(doubleArrayOf(1.0, 4.0, 9.0).toTypedArray())
        val a = array(longArrayOf(1, 8, 27).toTypedArray())

        // 1
        val res1 = a / array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)

        // 2
        val res2 = a / array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)

        // 3
        val res3 = a / array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)

        // 4
        val res4 = a / array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)

        // 5
        val res5 = a / array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)

        // 6
        val res6 = a / array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testLongDivBetweenScalarAndArray() {
        val check = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val check2 = array(doubleArrayOf(6.0, 3.0, 2.0).toTypedArray())

        val a = array(longArrayOf(4, 8, 12).toTypedArray())

        // 1
        val res1 = a / 2.toByte()
        assertEquals(check, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)
        val res12 = 24.toByte() / a
        assertEquals(check2, res12)
        assertEquals(Double::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a / 2.toShort()
        assertEquals(check, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)
        val res22 = 24.toShort() / a
        assertEquals(check2, res22)
        assertEquals(Double::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a / 2
        assertEquals(check, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)
        val res32 = 24 / a
        assertEquals(check2, res32)
        assertEquals(Double::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a / 2.toLong()
        assertEquals(check, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)
        val res42 = 24.toLong() / a
        assertEquals(check2, res42)
        assertEquals(Double::class.javaObjectType, res42.dtype)

        // 5
        val res5 = a / 2.toFloat()
        assertEquals(check, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 24.toFloat() / a
        assertEquals(check2, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val res6 = a / 2.toDouble()
        assertEquals(check, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 24.toDouble() / a
        assertEquals(check2, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testFloatDivBetweenTwoArrays() {
        val check0 = array(floatArrayOf(1f, 4f, 9f).toTypedArray())
        val check = array(doubleArrayOf(1.0, 4.0, 9.0).toTypedArray())
        val a = array(floatArrayOf(1f, 8f, 27f).toTypedArray())

        // 1
        val res1 = a / array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check0, res1)
        assertEquals(Float::class.javaObjectType, res1.dtype)

        // 2
        val res2 = a / array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check0, res2)
        assertEquals(Float::class.javaObjectType, res2.dtype)

        // 3
        val res3 = a / array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)

        // 4
        val res4 = a / array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)

        // 5
        val res5 = a / array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check0, res5)
        assertEquals(Float::class.javaObjectType, res5.dtype)

        // 6
        val res6 = a / array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testFloatDivBetweenScalarAndArray() {
        val check = array(floatArrayOf(2f, 4f, 6f).toTypedArray())
        val check2 = array(floatArrayOf(6f, 3f, 2f).toTypedArray())

        val a = array(floatArrayOf(4f, 8f, 12f).toTypedArray())

        // 1
        val res1 = a / 2.toByte()
        assertEquals(check, res1)
        assertEquals(Float::class.javaObjectType, res1.dtype)
        val res12 = 24.toByte() / a
        assertEquals(check2, res12)
        assertEquals(Float::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a / 2.toShort()
        assertEquals(check, res2)
        assertEquals(Float::class.javaObjectType, res2.dtype)
        val res22 = 24.toShort() / a
        assertEquals(check2, res22)
        assertEquals(Float::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a / 2.toInt()
        assertEquals(check, res3)
        assertEquals(Float::class.javaObjectType, res3.dtype)
        val res32 = 24 / a
        assertEquals(check2, res32)
        assertEquals(Float::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a / 2.toLong()
        assertEquals(check, res4)
        assertEquals(Float::class.javaObjectType, res4.dtype)
        val res42 = 24.toLong() / a
        assertEquals(check2, res42)
        assertEquals(Float::class.javaObjectType, res42.dtype)

        // 5
        val res5 = a / 2.toFloat()
        assertEquals(check, res5)
        assertEquals(Float::class.javaObjectType, res5.dtype)
        val res52 = 24.toFloat() / a
        assertEquals(check2, res52)
        assertEquals(Float::class.javaObjectType, res52.dtype)

        // 6
        val res6 = a / 2.toDouble()
        assertEquals(check, res6)
        assertEquals(Float::class.javaObjectType, res6.dtype)
        val res62 = 24.toDouble() / a
        assertEquals(check2, res62)
        assertEquals(Float::class.javaObjectType, res62.dtype)
    }

    @Test
    fun testDoubleDivBetweenTwoArrays() {
        val check = array(doubleArrayOf(1.0, 4.0, 9.0).toTypedArray())
        val a = array(doubleArrayOf(1.0, 8.0, 27.0).toTypedArray())

        // 1
        val res1 = a / array(byteArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)

        // 2
        val res2 = a / array(shortArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)

        // 3
        val res3 = a / array(intArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)

        // 4
        val res4 = a / array(longArrayOf(1, 2, 3).toTypedArray())
        assertEquals(check, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)

        // 5
        val res5 = a / array(floatArrayOf(1f, 2f, 3f).toTypedArray())
        assertEquals(check, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)

        // 6
        val res6 = a / array(doubleArrayOf(1.0, 2.0, 3.0).toTypedArray())
        assertEquals(check, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
    }

    @Test
    fun testDoubleDivBetweenScalarAndArray() {
        val check = array(doubleArrayOf(2.0, 4.0, 6.0).toTypedArray())
        val check2 = array(doubleArrayOf(6.0, 3.0, 2.0).toTypedArray())

        val a = array(doubleArrayOf(4.0, 8.0, 12.0).toTypedArray())

        // 1
        val res1 = a / 2.toByte()
        assertEquals(check, res1)
        assertEquals(Double::class.javaObjectType, res1.dtype)
        val res12 = 24.toByte() / a
        assertEquals(check2, res12)
        assertEquals(Double::class.javaObjectType, res12.dtype)

        // 2
        val res2 = a / 2.toShort()
        assertEquals(check, res2)
        assertEquals(Double::class.javaObjectType, res2.dtype)
        val res22 = 24.toShort() / a
        assertEquals(check2, res22)
        assertEquals(Double::class.javaObjectType, res22.dtype)

        // 3
        val res3 = a / 2
        assertEquals(check, res3)
        assertEquals(Double::class.javaObjectType, res3.dtype)
        val res32 = 24 / a
        assertEquals(check2, res32)
        assertEquals(Double::class.javaObjectType, res32.dtype)

        // 4
        val res4 = a / 2.toLong()
        assertEquals(check, res4)
        assertEquals(Double::class.javaObjectType, res4.dtype)
        val res42 = 24.toLong() / a
        assertEquals(check2, res42)
        assertEquals(Double::class.javaObjectType, res42.dtype)

        // 5
        val res5 = a / 2.toFloat()
        assertEquals(check, res5)
        assertEquals(Double::class.javaObjectType, res5.dtype)
        val res52 = 24.toFloat() / a
        assertEquals(check2, res52)
        assertEquals(Double::class.javaObjectType, res52.dtype)

        // 6
        val res6 = a / 2.toDouble()
        assertEquals(check, res6)
        assertEquals(Double::class.javaObjectType, res6.dtype)
        val res62 = 24.toDouble() / a
        assertEquals(check2, res62)
        assertEquals(Double::class.javaObjectType, res62.dtype)
    }
    //end divide
}
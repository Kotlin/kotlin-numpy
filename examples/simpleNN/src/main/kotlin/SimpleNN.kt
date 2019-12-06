import org.jetbrains.numkt.array
import org.jetbrains.numkt.core.KtNDArray
import org.jetbrains.numkt.core.dot
import org.jetbrains.numkt.linalg.dot
import org.jetbrains.numkt.math.*
import org.jetbrains.numkt.random.Random
import org.jetbrains.numkt.statistics.mean
import org.jetbrains.numkt.transpose


fun sig(x: KtNDArray<Double>, deriv: Boolean = false): KtNDArray<Double> {
    if (deriv) {
        return x * (1 - x)
    }
    return 1 / (1 + exp(x * -1))
}


fun simpleNN() {
    val x = array<Double>(listOf(listOf(0, 0, 1), listOf(0, 1, 1), listOf(1, 0, 1), listOf(1, 1, 1)))
    val y = array<Double>(listOf(listOf(0, 1, 1, 0))).t

    val syn0 = 2 * Random.random(3, 4) - 1
    val syn1 = 2 * Random.random(4, 1) - 1

    var l0: KtNDArray<Double>
    var l1: KtNDArray<Double>
    var l2: KtNDArray<Double>? = null


    for (i in 0..60000) {
        l0 = x
        l1 = sig(dot(l0, syn0))
        l2 = sig(dot(l1, syn1))

        val l2Error = y - l2
        if ((i % 10000) == 0) {
            println("step: $i, error: ${mean(absolute(l2Error))}")
        }

        val l2Delta = l2Error * sig(l2, true)

        val l1Error = l2Delta.dot(transpose(syn1))
        val l1Delta = l1Error * sig(l1, true)

        syn1 += transpose(l1).dot(l2Delta)
        syn0 += transpose(l0).dot(l1Delta)
    }
    println("Data: \n$l2")
}

fun main() {
    simpleNN()
}
import org.jetbrains.numkt.*
import org.jetbrains.numkt.core.KtNDArray
import org.jetbrains.numkt.core.dot
import org.jetbrains.numkt.core.ravel
import org.jetbrains.numkt.core.reshape
import org.jetbrains.numkt.linalg.Linalg
import org.jetbrains.numkt.math.*
import org.jetbrains.numkt.random.Random
import org.jetbrains.numkt.statistics.`var`

fun weight(x0: KtNDArray<Double>, x: KtNDArray<Double>, tau: Double = 5.0): KtNDArray<Double> {
    var w = exp((-1) * sum((x - x0) `**` 2, axis = 1) / ((2 * tau) * (2 * tau)))
    w = diag(w)
    return w
}

fun weightedLeastSquares(x: KtNDArray<Double>, y: KtNDArray<Double>, weights: KtNDArray<Double>): KtNDArray<Double> =
    Linalg.inv(transpose(x).dot(weights.dot(x))).dot(transpose(x).dot(weights.dot(y)))

fun weightedRegression(x: KtNDArray<Double>, y: KtNDArray<Double>, tau: Double = 5.0): KtNDArray<Double> {
    val yHat = zerosLike(y)
    val sh = x.shape[0]
    for (i in 0 until sh) {
        val w = weight(x[i..i + 1].ravel(), x, tau)
        val theta = weightedLeastSquares(x, y, w)
        yHat[i] = x.dot(theta)[i]
    }
    return yHat
}

fun r2Score(y: KtNDArray<Double>, yHat: KtNDArray<Double>) =
    1.0 - sum((y - yHat) `**` 2) / `var`(y) / y.shape[0]

fun searchAccuracies(taus: KtNDArray<Double>, x: KtNDArray<Double>, y: KtNDArray<Double>): KtNDArray<Double> {
    val r = arrayListOf<Double>()
    for (tau in taus.flatIter()) {
        r.add(r2Score(y, weightedRegression(x, y, tau = tau)))
    }
    return array(r)
}

fun main() {
    val data = linspace<Double>(-3, 3, 1000)
    val y = sin(data `**` 3 / 3)
    data += Random.normal(scale = 0.1, size = *intArrayOf(1000))
    val x = hstack(ones<Double>(1000).reshape(1000, 1), data.reshape(1000, 1))
    val taus = linspace<Double>(0.01, 1, 10)

    val accuracies = searchAccuracies(taus, x, y)
    println(accuracies)
}

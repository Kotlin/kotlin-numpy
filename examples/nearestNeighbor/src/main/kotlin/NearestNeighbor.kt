import org.jetbrains.numkt.*
import org.jetbrains.numkt.core.KtNDArray
import org.jetbrains.numkt.core.ravel
import org.jetbrains.numkt.math.*
import org.jetbrains.numkt.random.Random

fun generateRandomPoints(size: Int = 10, min: Int = 0, max: Int = 1): KtNDArray<Double> =
    (max - min) * Random.randomSample(size, 2) + min

class NearestNeighbor(private val distance: Int = 0) {

    private lateinit var xTrain: KtNDArray<Double>
    private lateinit var yTrain: KtNDArray<Int>

    fun train(x: KtNDArray<Double>, y: KtNDArray<Int>) {
        xTrain = x
        yTrain = y
    }

    fun predict(x: KtNDArray<Double>): ArrayList<Int> {
        val predictions = ArrayList<Int>()
        for (x_i in x) {
            if (distance == 0) {
                val distances = sum(absolute(xTrain - x_i), axis = 1)
                val minIndex = argMin(distances)

                predictions.add(yTrain[minIndex].scalar!!)
            } else if (distance == 1) {
                val distances = sum(square(xTrain - x_i), axis = 1)
                val minIndex = argMin(distances)

                predictions.add(yTrain[minIndex].scalar!!)
            }
        }

        return predictions
    }
}

class Analysis(x: Array<KtNDArray<Double>>, distance: Int) {
    lateinit var xTest: KtNDArray<Double>
        private set
    lateinit var yTest: ArrayList<Int>
        private set

    private val nofClasses: Int = x.size
    private lateinit var range: Pair<Int, Int>

    private val classified: ArrayList<KtNDArray<Double>> = ArrayList()
    private val nn: NearestNeighbor

    init {
        val listY = ArrayList<KtNDArray<Long>>()
        for ((i, el) in x.indices.zip(x)) {
            listY.add(i * ones(el.shape[0]))
        }
        val xt = concatenate(*x, axis = 0)
        nn = NearestNeighbor(distance)
        nn.train(xt, array<Int>(listY).ravel())
    }

    fun prepareTestSamples(min: Int = 0, max: Int = 2, step: Double = 0.01) {
        range = Pair(min, max)

        // grid
        val grid = arrayOf(empty<Double>(201, 201), empty(201, 201))
        var s = 0.0
        for (j in 0..200) {
            for (k in 0..200) {
                grid[0][j, k] = s
            }
            s += step
        }
        for (j in 0..200) {
            s = 0.0
            for (k in 0..200) {
                grid[1][j, k] = s
                s += step
            }
        }

        xTest = vstack(grid[0].ravel(), grid[1].ravel()).t
    }

    fun analyse() {
        yTest = nn.predict(xTest)

        for (label in 0 until nofClasses) {
            val classI = ArrayList<KtNDArray<Double>>()
            for (i in 0 until yTest.size) {
                if (yTest[i] == label) {
                    classI.add(xTest[i..(i + 1)].ravel())
                }
            }
            classified.add(array(classI))
        }
    }
}

fun main() {
    val x1 = generateRandomPoints(50, 0, 1)
    val x2 = generateRandomPoints(50, 1, 2)

    var tempX = generateRandomPoints(50, 0, 1)
    var listX = arrayListOf<List<Double>>()
    for (tx in tempX) {
        listX.add(arrayListOf(tx[0].scalar!!, tx[1].scalar!! + 1))
    }
    val x3 = array<Double>(listX)

    tempX = generateRandomPoints(50, 1, 2)
    listX = arrayListOf()
    for (tx in tempX) {
        listX.add(arrayListOf(tx[0].scalar!!, tx[1].scalar!! - 1))
    }
    val x4 = array<Double>(listX)

    val c4 = Analysis(arrayOf(x1, x2, x3, x4), distance = 1)
    c4.prepareTestSamples()
    c4.analyse()

    // accuracy
    var accuracy = 0.0
    var i = 0
    for (x_i in c4.xTest) {
        val trueLabel: Int = if (x_i[0].scalar!! < 1 && x_i[1].scalar!! < 1) {
            0
        } else if (x_i[0].scalar!! > 1 && x_i[1].scalar!! > 1) {
            1
        } else if (x_i[0].scalar!! < 1 && x_i[1].scalar!! > 1) {
            2
        } else {
            3
        }
        if (trueLabel == c4.yTest[i++]) {
            accuracy += 1
        }
    }
    accuracy /= c4.xTest.shape[0]
    println(accuracy)
}
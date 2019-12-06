import org.jetbrains.numkt.array
import org.jetbrains.numkt.core.*
import org.jetbrains.numkt.empty
import org.jetbrains.numkt.linalg.dot
import org.jetbrains.numkt.loadtxt
import org.jetbrains.numkt.math.*
import org.jetbrains.numkt.random.Random
import org.jetbrains.numkt.statistics.`var`
import org.jetbrains.numkt.transpose

fun linear(inDim: Int, outDim: Int): Pair<KtNDArray<Double>, KtNDArray<Double>> {
    val w = Random.normal(scale = 1.0, size = *intArrayOf(inDim, outDim))
    val b = Random.normal(scale = 1.0, size = *intArrayOf(1, outDim))
    return Pair(w, b)
}

class MSELoss(val eps: Double = 1e-15) {
    private var target: KtNDArray<Double> = empty()
    private var output: KtNDArray<Double> = empty()

    private fun forward(output: KtNDArray<Double>, target: KtNDArray<Double>): Double {
        this.target = target.squeeze()
        this.output = output.squeeze()
        return ((this.target - this.output) `**` 2).mean()
    }

    fun backward() = this.output - this.target

    operator fun invoke(output: KtNDArray<Double>, target: KtNDArray<Double>) =
        forward(output, target)
}

class SGD(private val learningRate: Double = 0.001) {

    fun step(
        grads: Pair<KtNDArray<Double>, KtNDArray<Double>>,
        params: Pair<KtNDArray<Double>, KtNDArray<Double>>,
        learningRate: Double? = null
    ): Pair<KtNDArray<Double>, KtNDArray<Double>> {
        val lr = learningRate ?: this.learningRate
        val newParams = arrayListOf<KtNDArray<Double>>()
        for ((param, grad) in params.toList().zip(grads.toList())) {
            param -= lr * grad
            newParams.add(param)
        }
        return Pair(newParams[0], newParams[1])
    }
}

fun scale(x: KtNDArray<Double>): KtNDArray<Double> {
    val xCopy = x.copy()
    xCopy -= xCopy.mean(0)
    xCopy /= xCopy.std(axis = 0)
    return xCopy
}

class SGDLinearRegression(inDim: Int = 8, outDim: Int = 1) {
    var w: KtNDArray<Double>
    var b: KtNDArray<Double>
    private lateinit var data: KtNDArray<Double>
    private lateinit var lossFunction: MSELoss

    init {
        val lin = linear(inDim, outDim)
        w = lin.first
        b = lin.second
    }

    private fun forward(x: KtNDArray<Double>): KtNDArray<Double> {
        data = x
        return dot(x, w) + b
    }

    private fun gradients(): Pair<KtNDArray<Double>, KtNDArray<Double>> {
        val dz = lossFunction.backward()
        val dw = dot(transpose(data), dz).reshape(*w.shape)
        val db = array(arrayOf(dz.mean()))
        return Pair(dw, db)
    }

    fun backward(lossFunction: MSELoss): Pair<KtNDArray<Double>, KtNDArray<Double>> {
        this.lossFunction = lossFunction
        return gradients()
    }

    fun parameters(): Pair<KtNDArray<Double>, KtNDArray<Double>> {
        return Pair(w, b)
    }

    fun update(params: Pair<KtNDArray<Double>, KtNDArray<Double>>) {
        w = params.first
        b = params.second
    }

    operator fun invoke(x: KtNDArray<Double>) = forward(x)
}

fun evalScore(model: SGDLinearRegression, xVal: KtNDArray<Double>, yVal: KtNDArray<Double>): Double {
    val yHat = model(xVal).squeeze()
    val ytVal = yVal.squeeze()
    return 1.0 - ((ytVal - yHat) `**` 2).mean() / `var`(ytVal)
}

class DataLoader(
    private val data: KtNDArray<Double>,
    private val target: KtNDArray<Double>,
    private val batchSize: Int = 100
) {
    private var pos = 0
    val dataLoader = arrayListOf<Pair<KtNDArray<Double>, KtNDArray<Double>>>()
    fun itemBatch() {
        val m = data.shape[0]
        while (pos < m) {
            val xBatch = data[pos..(pos + batchSize)]
            val yBatch = target[pos..(pos + batchSize)]
            dataLoader.add(Pair(xBatch, yBatch))
            pos += batchSize
        }
    }
}

fun main() {
    val xTrain = loadtxt<Double>(
        "src/main/resources/x_train.txt",
        delimiter = ","
    )
    val xVal = loadtxt<Double>(
        "src/main/resources/x_val.txt",
        delimiter = ","
    )
    val yTrain = loadtxt<Double>(
        "src/main/resources/y_train.txt",
        delimiter = ","
    )
    val yVal = loadtxt<Double>(
        "src/main/resources/y_val.txt",
        delimiter = ","
    )

    val epochs = 50

    val dataloader = DataLoader(xTrain, yTrain)
    dataloader.itemBatch()
    val model = SGDLinearRegression()
    val lossFunction = MSELoss()
    val optimizer = SGD()

    val losses = arrayListOf<Double>()
    for (epoch in 1..epochs) {
        var lossSum = 0.0
        for (data in dataloader.dataLoader) {
            var (inputs, targets) = data
            inputs = scale(inputs)
            val outputs = model(inputs)
            val loss = lossFunction(outputs, targets)
            lossSum += loss
            val grads = model.backward(lossFunction)
            val params = model.parameters()
            val newParams = optimizer.step(grads, params)
            model.update(newParams)
        }
        val score = evalScore(
            model,
            scale(xVal),
            yVal
        )
        println("epoch: [$epoch/$epochs], loss: $lossSum, score: $score")
        losses.add(lossSum)
    }
}

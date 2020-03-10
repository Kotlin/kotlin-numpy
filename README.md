[![JetBrains incubator project](https://jb.gg/badges/incubator.svg)](https://confluence.jetbrains.com/display/ALL/JetBrains+on+GitHub)
[ ![Download](https://api.bintray.com/packages/kotlin/kotlin-numpy/kotlin-numpy/images/download.svg) ](https://bintray.com/kotlin/kotlin-numpy/kotlin-numpy/_latestVersion)
[![GitHub License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

# Kotlin bindings for NumPy

This project is a Kotlin library, which is a statically typed wrapper for the [NumPy](https://numpy.org/) library.

## Features

* Statically typed multidimensional arrays.
* Idiomatic API for users with NumPy experience.
* Random, math, linear algebra, and other useful functions from NumPy.
* Python allocates memory for arrays and frees memory when JVM GC collects unnecessary arrays.
* Direct access to array data using DirectBuffer. 
    * Increased performance working with array's data compared to python.
    
## Installation

In your Gradle build script:

1. Add the `kotlin-numpy` repository.
2. Add the `org.jetbrains:kotlin-numpy:0.1.0` implementation dependency.

Groovy build script (`build.gradle`):

```groovy
repositories {
    maven { url "https://kotlin.bintray.com/kotlin-numpy" }
}

dependencies {
    implementation 'org.jetbrains:kotlin-numpy:0.1.3'
}
```

Kotlin build script (`build.gradle.kts`):

```kotlin
repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-numpy")
}

dependencies {
    implementation("org.jetbrains:kotlin-numpy:0.1.3")
}
```
    
## Usage

Kotlin bindings for NumPy offer an API very similar to the original NumPy API. Consider the following programs:

Python:

```python
import numpy as np
a = np.arange(15).reshape(3, 5) # ndarray([[ 0,  1,  2,  3,  4],
                                #        [ 5,  6,  7,  8,  9],
                                #        [10, 11, 12, 13, 14]])
print(a.shape == (3, 5))        # True
print(a.ndim == 2)              # True
print(a.dtype.name)             # 'int64'

b = (np.arange(15) ** 2).reshape(3, 5)

c = a * b
print(c)
# [[   0    1    8   27   64]
#  [ 125  216  343  512  729]
#  [1000 1331 1728 2197 2744]]

d = c.transpose().dot(a)
print(d)
# [[10625 11750 12875 14000 15125]
#  [14390 15938 17486 19034 20582]
#  [18995 21074 23153 25232 27311]
#  [24530 27266 30002 32738 35474]
#  [31085 34622 38159 41696 45233]]
```


Kotlin:

```kotlin
import org.jetbrains.numkt.core.*
import org.jetbrains.numkt.math.*
import org.jetbrains.numkt.*

fun main() {
    val a = arange(15).reshape(3, 5) // KtNDArray<Int>([[ 0,  1,  2,  3,  4],
                                                     // [ 5,  6,  7,  8,  9],
                                                     // [10, 11, 12, 13, 14]]

    println(a.shape.contentEquals(intArrayOf(3, 5))) // true
    println(a.ndim == 2)                             // true
    println(a.dtype)                                 // class java.lang.Integer

    // create an array of ints, we square each element and the shape to (3, 5) 
    val b = (arange(15) `**` 2).reshape(3, 5)

    // c is the product of a and b, element-wise
    val c = a * b
    println(c)
    // Output:
    // [[   0    1    8   27   64]
    //  [ 125  216  343  512  729]
    //  [1000 1331 1728 2197 2744]]
    
    // d is the dot product of the transposed c and a
    val d = c.transpose().dot(a)
    println(d)
    // Output:
    // [[10625 11750 12875 14000 15125]
    //  [14390 15938 17486 19034 20582]
    //  [18995 21074 23153 25232 27311]
    //  [24530 27266 30002 32738 35474]
    //  [31085 34622 38159 41696 45233]]

}
```

#### Array creation

Simple ways to create arrays look like this:

```kotlin
    array(arrayOf(1, 2, 3)) // simple flat array: KtNDArray<Int>([1, 2, 3])

    array<Float>(listOf(listOf(15, 13), listOf(2, 31))) // KtNDArray<Float>([[15f, 13f], 
                                                                          // [ 2f, 31f])

    ones<Double>(3, 3, 3) // array of ones. Shape will be (3, 3, 3)

    linspace<Double>( 1, 3, 10 ) // array have 10 numbers from 1 to 3
```

#### Basic operations

Arithmetic operations are supported:

```kotlin
    val a = array(arrayOf(20, 30, 40, 50)) // [20, 30, 40, 50]
    val b = arange(4) // [0, 1, 2, 3]
    val c = a - b // [20 29 38 47]

    b `**` 2 // [0, 1, 4, 9]
    sin(a) * 10 // [ 9.12945251, -9.88031624, 7.4511316, -2.62374854]
```

Matrix operations:

```kotlin
    val matA = array<Long>(listOf(listOf(1, 1), listOf(0, 1))) // KtNDArray<Long>([[1, 1])
                                                                                // [0, 1]])
    
    val matB = array<Long>(listOf(listOf(2, 0), listOf(3, 4))) // KtNDArray<Long>([[2, 0]
                                                                                // [3, 4]])

    println(matA * matB)
    // elementwise product
    // [[2 0]
    //  [0 4]]

    println(matA `@` matB)
    // matrix product:
    // [[5 4]
    //  [3 4]]

    println(matA.dot(matB))
    // matrix product:
    // [[5 4]
    //  [3 4]]
```

Augmented assigment (or override assigment) operations modify an existing array instead of creating a new one.

>Note: When using augmented assignments, don't forget to import them explicitly (or import the entire package 
>`org.jetbrains.numkt.math.*`. Otherwise, kotlin tries to extend the operation. For example, the line `a += b`, 
>is extended to `a = a + b`.

```kotlin
    val a = ones<Int>(2, 3)
    val b = Random.random(2, 3)
    a *= 3

    b += a
```


#### Indexing, slicing, and iterating

Arrays in Kotlin bindings for NumPy use the traditional index to access the items. Also, there is an analogue of Python's `slice`.
To skip an index in slice, use `None`. For example, `[:8:2]` in Python is equivalent to `[None..8..2]` in kotlin.
Iteration occurs elementwise regardless of shape.

```kotlin
    val a = arange(10L) `**` 3 // KtNDArray<Long>([0, 1, 8, 27, 64, 125 216 343 512 729])

    //  a[2]
    println(a[2]) // 8

    println(a[2..5..1]) // equivalent a[2:5] in python. output: KtNDArray<Long>([8, 27, 64])

    // equivalent to a[0:6:2] = -1000 in python; from start to position 6, set every 2nd element to -1000
    a[0..6..2] = -1000
    println(a) // [-1000, 1, -1000, 27, -1000, 125, 216, 343, 512, 729]

    // reverse
    println(a[None..None..-1]) // [729, 512, 343, 216, 125, -1000, 27, -1000, 1, -1000]

    for (el in a.reshape(2, 5)) {
        print("${el.toDouble().pow(1.0 / 3.0)} ") // NaN 1.0 NaN 3.0 4.999999999999999 5.999999999999999 ...
    }
```

Remember that, when indexing, you must pass the number of indexes equal to the dimension of the `KtNDArray`
(| j<sub>1</sub>, j<sub>2</sub>, j<sub>3</sub>, ... | = ndim).
When slicing, this is not necessary.

```kotlin
    val x = array(arrayOf(1, 2, 3, 4, 5, 6)).apply { resize(2, 3, 1) }
    println(x.shape.joinToString())
//    2, 3, 1

    println(x[1..2])
//    [[[4]
//      [5]
//      [6]]]
      
    println(x[1, 2, 0])
//    6
```

There are three iterators:

##### [NDIterator](src/main/kotlin/org/jetbrains/numkt/core/KtNDArrayIterator.kt).
Calls a standard Python iterator. Always returns an view. If iteration occurs over a flat array, use the `scalar`
property to obtain the element. If the array is not a scalar, the `null` will be returned. To check, use methods
`isScalar` and `isNotScalar`.
```kotlin
val a = linspace<Double>(0, 10).reshape(2, 5, 5)

// Get ten one-dimensional arrays
for (ax1 in a) {
    for (ax2 in ax1) {
        println(ax2)
    }
}

// Sum of all elements
var sum = 0.0
for (ax1 in a) {
    for (ax2 in ax1) {
        for (el in ax2) {
            sum += el.scalar!!
        }
    }
}
```

##### [KtNDIter](src/main/kotlin/org/jetbrains/numkt/core/KtNDIter.kt).
This iterator is a mapping of the C array iterator API, like in python `np.nditer`.
It also displays items in the order they are in memory.
```kotlin
val a = arange(6).apply { resize(2, 3) }

// Square each element in the array
val iter = KtNDIter(a)
for (i in iter) {
    a[iter.multiIndex] = i * i
}

for (x in KtNDIter(a[None..None, 1..None..-1])) {
    print("$x ")
}
```

##### [FlatIterator](src/main/kotlin/org/jetbrains/numkt/core/KtNDArrayIterator.kt).
An iterator directly above the buffer. The fastest of all these iterators. Able to display view. Use method `flatIter`.

```kotlin
val a = linspace<Double>(0, 10)

// Displays all items
for (el in a.flatIter()) {
    print("$el ")
}
```


#### Stacking

```kotlin
    val a = floor(10 * Random.random(2, 2))
    val b = floor(10 * Random.random(2, 2))

    // stack arrays row wise
    val v = vstack(a, b)
    println(v.shape.joinToString())
    // 4, 2

    // stack arrays column wise
    val h = hstack(a, b)
    println(h.shape.joinToString())
    // 2, 4
```

    
## NumPy routine coverage

* Array creation
    * Ones and zeros - :white_check_mark:
    * From existing data - :white_check_mark:
    * Creating record arrays - :white_large_square:
    * Numeric character arrays - :white_large_square:
    * Numerical ranges - :white_check_mark:
    * Building matrices - :white_check_mark:
    * The Matrix class - :white_check_mark:
* Array manipulation routines
    * Basic operations - :white_check_mark:
    * Changing array shape - :white_check_mark:
    * Transpose-like operations - :white_check_mark:
    * Changing number of dimensions - :white_check_mark:
    * Changing kind of array - :white_check_mark:
    * Joining arrays - :white_check_mark:
    * Splitting arrays - :white_check_mark:
    * Tiling arrays - :white_check_mark:
    * Adding and removing elements - :white_check_mark:
    * rearranging elements - :white_check_mark:
* Binary operations
    * Elementwise bit operations - :white_check_mark:
    * Bit packing - :white_check_mark:
    * Output formatting - :white_check_mark:
* String operations - :white_large_square:
* C-Types Foreign Function Interface (`ctypeslib`) - :white_large_square:
* Datetime Support Functions - :white_large_square:
* Data type routines - :white_large_square:
* Optionally Scipy-accelerated routines - :white_large_square:
* Mathematical functions with automatic domain (`numpy.emath`) - :white_large_square: 
* Floating point error handling - :white_large_square:
* Discrete Fourier Transform (`numpy.fit`) - :white_large_square:
* Financial functions - :white_large_square:
* Functional programming - :white_large_square:
* NumPy-specific help functions - :white_large_square:
* Indexing routines
    * Generating index arrays - :white_large_square:
    * Indexing-like operations - :white_check_mark:
    * Inserting data into arrays - :white_large_square:
    * Iterating over arrays - :white_large_square:
* Input and output
    * NumPy binary files (NPY, NPZ) - :white_large_square:
    * Text files - :white_check_mark:
    * Raw binary files - :white_check_mark:
    * String formatting - :white_large_square:
    * Memory mapping files - :white_large_square:
    * Text formatting options - :white_large_square:
    * Base-n representations - :white_large_square:
    * Data sources - :white_large_square:
    * Binary format description - :white_large_square:
* Linear algebra (`numpy.linalg`)
    * Matrix and vector products - :white_check_mark:
    * Decompositions - :white_check_mark:
    * Matrix eigenvalues - :white_check_mark:
    * Norms and other numbers - :white_check_mark:
    * Solving equations and inverting matrices - :white_check_mark: without tensors
* Logic functions
    * Truth value testing - :white_check_mark:
    * Array contents - :white_check_mark:
    * Array type testing - :white_large_square:
    * Logic operations - :white_check_mark:
    * Comparison - :white_check_mark:
* Masked array operations - :white_large_square:
* Mathematical functions
    * Trigonometric functions - :white_check_mark:
    * Hyperbolic functions - :white_check_mark:
    * Rounding - :white_check_mark:
    * Sums, products, differences - :white_check_mark:
    * Exponents and logarithms - :white_check_mark:
    * Other special functions - :white_check_mark:
    * Floating point routines - :white_check_mark:
    * Rational routines - :white_check_mark:
    * Arithmetic operations - :white_check_mark:
    * Handling complex numbers - :white_large_square:
    * Miscellaneous - :white_check_mark:
* Matrix library (`numpy.matlib`) - :white_large_square:
* Miscellaneous routines - :white_large_square:
* Padding arrays - :white_large_square:
* Polynomials - :white_large_square:
* Random (`numpy.random`)
    * Simple random data - :white_check_mark:
    * Permutations - :white_check_mark:
    * Distributions - :white_check_mark:
    * Random generator - :white_large_square: 
* Set routines - :white_large_square:
* Sorting, searching and counting
    * Sorting - :white_check_mark:
    * Searching - :white_check_mark:
    * Counting - :white_check_mark:
* Statistics
    * Order statistics - :white_check_mark:
    * Averages and variances - :white_check_mark:
    * Correlating - :white_check_mark:
    * Histograms - :white_large_square:
* Test support - :white_large_square:
* Window functions - :white_large_square:

    
## How it works

### Foundation

Using Java Native Interface (JNI) and Python C Extensions, we attach the Python 
interpreter to the JVM process. There is a singleton [Interpreter](src/main/kotlin/org/jetbrains/numkt/Interpreter.kt) for this. 
Initialization of the Python interpreter occurs on the first call of any function. The interpreter will remain in the JVM
until the JVM exits. The [Interpreter](src/main/kotlin/org/jetbrains/numkt/Interpreter.kt) 
class contains external functions (as `callFunc`) through which NumPy functions are called. 
Let's have a more detailed look at the call to NumPy functions.

The following arguments are required to call NumPy functions:
* Array of call attributes - module names and function names in NumPy.
* Array of arguments - function arguments, equivalent to `*args` in Python.
Importantly, the arguments should be in the same order and in the same places as in Python.
If you skip an argument, pass `None` instead.
* Map of arguments - maps names of arguments to arguments, equivalent to `**kwargs` in Python.
* Return type - which class we expect to return. If the expected type is KtNDArray, then this is not needed.

To call a NumPy function, the above arguments are passed to the associated external kotlin function.
After that, the following code will appear in native code:
* By the array of call attributes we get callable `PyObject`. This is a related function from NumPy.
* The array of arguments is converted to a tuple of the corresponding Python objects.
* If the map of arguments is not empty, then it is converted into a dictionary of keyword arguments.
* We call a NumPy function with arguments passed to it - `PyObject_Call(FunctionObject, TupleArgs, DictKwargs)`,
in python it is `FunctionObject(TupleArgs, DictKwargs)`
* Convert the result in a Java object and return it.

Let's have a look at an example.

The diagonal method returns the specified diagonal, of the same type as the called `KtNDArray` object.

```kotlin
fun <T : Any> KtNDArray<T>.diagonal(offset: Int = 0, axis1: Int = 0, axis2: Int = 1): KtNDArray<T> =
    callFunc(nameMethod = arrayOf("ndarray", "diagonal"), args = arrayOf(this, offset, axis1, axis2))
```

We see that the first argument is an array of strings. 
Getting the final attribute will look like this: `numpy` -> `ndarray` -> `diagonal` (the NumPy module is used by default).
The next argument is an array of arguments: `this` (`KtNDArray` from which the diagonals are taken), `offset`, `axis1`, `axis2`.
In this case, `kwargs` are not used. We also expect the array to return, so we don’t pass anything to the return type.


### Objects

#### Type matching

When processing objects in native code, there is a conversion from Java objects to Python
objects, and when the result is returned, back from Python objects to Java objects.
The table below shows the conversion of objects of different types.

|    Kotlin -> Python    | Python/NumPy -> Kotlin |
|:----------------------:|:----------------------:|
|    `None` -> `None`    |     `None` -> `null`   |
|    `Char` -> `str`     |                        |
|   `String` -> `str`    |    `str` -> `String`   |
|  `Boolean` -> `bool`   |    `bool` -> `Boolean` |
|    `Byte` -> `int8`    |     `int8` -> `Byte`   |
|   `Short` -> `int16`   |    `int16` -> `Short`  |
|    `Int` -> `int32`    |     `int32` -> `Int`   |
|   `Long` -> `int64`    |     `int64` -> `Long`  |
|  `Float` -> `float32`  |   `float32` -> `Float` |
| `Double` -> `float64`  |   `float64` -> `Double`|
|   `Array` -> `tuple`   |    `tuple` -> `Array`  |
|    `List` -> `list`    |     `list` -> `List`   |
|      `Map` -> `dict`   |      `dict` -> `Map`   |
|`KtNDArray` -> `ndarray`|`ndarray` -> `KtNDArray`|
|    `Slice` -> `slice`  |                        |

#### What's inside `KtNDArray`

The main object type is [KtNDArray](src/main/kotlin/org/jetbrains/numkt/core/KtNDArray.kt).
Like `ndarray` in NumPy, it is a homogeneous multidimensional array. `KtNDArray` holds a pointer to
its corresponding `ndarray`. Using the pointer, we can perform operations on the array.

`KtNDArray` and `ndarray` operate on shared memory. Python allocates memory for the array,
and through `java.nio.DirectByteBuffer`, we get access to this memory.

`KtNDArray` provides access to some `ndarray` attributes, such as `shape`, `ndim`, `itemsize`,
`size`, `strides`, `dtype`. Additionally, `KtNDArray` has the field `data` of type `ByteBuffer`. 
This is the direct buffer.    

### Type safety

Kotlin is a statically typed programing language. This makes it possible to catch errors
at the compilation stage.

Lest's have a look at an example:

Python:
```python
import numpy as np

# ...

a = np.ones((3, 3), dtype=int) * 3
b = np.random.random((3, 3))

b *= a # success

a *= b # TypeError at runtime 
```

The same code written in Kotlin will notify us of an error during the compilation:


```kotlin
// ...

val a = ones<Int>(3, 3) * 3
val b = Random.random(3, 3)

b *= a // success

a *= b // compilation error
// Kotlin: Type mismatch: inferred type is KtNDArray<Double> but KtNDArray<Int> was expected
```

There are other types of errors that can be prevented during the compilation, for example:

* type mismatch of function arguments :

Python:

```python
a = np.array(['a', 'b', 'c'])
np.sin(a)

# TypeError: ufunc 'sin' not supported for the input types, 
# and the inputs could not be safely coerced to any supported types according to the casting rule ''safe''
```

Kotlin:

```kotlin
val a = array(arrayOf('a', 'b', 'c'))
sin(a)

// Kotlin: Type parameter bound for T in fun <T : Number> sin(x: KtNDArray<T>): KtNDArray<Double> 
// is not satisfied: inferred type Char is not a subtype of Number
```

* Method signature defined:

Python:

```python
a = np.array([0, 1], [1, 0])

# TypeError: data type not understood
```

Kotlin:

```kotlin
val a = array(listOf(0, 1), listOf(1, 0))

// Kotlin: None of the following functions can be called with the arguments supplied: ...
```

In Python, there are implicit type conversions that can be really tricky.
The following code will be seems to work fine, but implicitly converts `float` to `int`,
which the user may not expect. As a result, the output differs from the desired but no errors are seen.

Python:

```python
a = np.arange(15, dtype=np.int32)
b = np.linspace(0, 1, 15, dtype=np.float64)[::-1]
for i in range(15):
    a[i] = b[i]
print(a)

# [1 0 0 0 0 0 0 0 0 0 0 0 0 0 0]
```

In Kotlin, the compiler will tell the user that an explicit conversion is required here.

Kotlin:

```kotlin
val a = arange(15)
val b = linspace<Double>(0, 1, 15)[None..None..-1]
for (i in 0..14) {
    a[i] = b[i] // Error
}
```

## Requirements
To build and run the library, you will need:
To run library you need:
* Java 8 or above
* Python 3.5 or above
* NumPy 1.7 or above.

>Note: Make sure you use the correct Python environment.
>This is necessary to use the correct version of Python and NumPy.
>
>For the convenience of installing Python, NumPy and setting the environment,
>it's recommended to use [Anaconda](https://www.anaconda.com/).


## Building

To build the library, you will need [GCC](https://gcc.gnu.org/) or [Clang](https://clang.llvm.org/).

The build system of this project is Gradle.

First, you should build the native library: run `./gradlew ktnumpyReleaseSharedLibrary`. 
The library will appear in `./build/libs/ktnumpy/shared/release`. 

For the debug version, run: `./gradlew ktnumpyDebugSharedLibrary`.
Accordingly, the library will appear in `./build/libs/ktnumpy/shared/debug`.

After building the native library, run `./gradlew build`

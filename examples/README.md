# Usage samples Kotlin bindings for NumPy

This folder contains some usage examples of Kotlin bindings for NumPy: 

### nearestNeighbor

The nearest neighbor classifier compares a test sample with all training samples to predict a label (one of four classes).

### regression

Implementation of a local regression model with score R<sup>2</sup>.

### sgd

Implementation of a linear regression model using batch gradient descent. We build our model in a way with automatic differentiation.

We read pre-processed housing data for California. 
The characteristics contain coordinates (latitude, longitude), age of the house, number of rooms, number of bedrooms,
population in the area, number of households and average income.
Target value is the logarithmic cost of the house.
We are building a model, which will predict the house cost by its features.

### simpleNN

A simple neural network trained through backpropagation with three layers

## Launching samples

You can use `./gradlew run` to run all samples or `./gradlew :folder-name:run` to run a particular sample.

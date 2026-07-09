# k-NN Classifier from Scratch

A Java implementation of the **k-Nearest Neighbors (k-NN) classification algorithm built entirely from scratch without using any third-party Machine Learning libraries**.

The application reads training and test datasets from text files, dynamically determines the number of conditional attributes, performs k-NN classification, evaluates classifier accuracy, and allows users to classify new samples interactively.

Using the Iris dataset, the classifier achieved **96.67% accuracy, correctly classifying 29 out of 30 test samples**.

---

## Features

- k-Nearest Neighbors algorithm implemented entirely from scratch
- No third-party Machine Learning libraries
- Dynamic detection of the number of conditional attributes
- Training and test data loaded from external text files
- User-defined `k` parameter
- Euclidean distance calculation
- Nearest-neighbor selection
- Majority voting for classification
- Automatic evaluation of all test samples
- Accuracy calculation
- Interactive classification of new user-provided samples
- Support for datasets with varying numbers of numeric conditional attributes

---

## How the Algorithm Works

For each sample that needs to be classified:

1. Calculate the Euclidean distance between the sample and every observation in the training dataset.
2. Sort the training observations according to their distance from the sample.
3. Select the `k` nearest neighbors.
4. Determine the most frequent class among those neighbors using majority voting.
5. Assign the resulting class to the sample.

The Euclidean distance is calculated as:

```text
d(x, y) = √[(x₁-y₁)² + (x₂-y₂)² + ... + (xₙ-yₙ)²]
```

The implementation does not assume a fixed number of input features. The number of conditional attributes is determined dynamically from the provided dataset.

---

## Dataset

The project uses two input files:

```text
iris_training.txt
iris_test.txt
```

The program assumes that:

- The decision attribute is always the last attribute.
- All conditional attributes are numeric.
- The number of conditional attributes may vary and must not be hard-coded.

---

## Classification Results

During testing with the provided Iris dataset:

```text
Training samples: 120
Test samples: 30
Correctly classified samples: 29
Accuracy: 96.67%
```

Results may vary depending on the selected value of `k`.

---

## Interactive Classification

After evaluating the test dataset, the application allows the user to enter attribute values for a new sample.

The classifier then predicts its class using the same k-NN algorithm.

The process can be repeated until the user chooses to stop.

---

## Technologies

- Java
- Object-Oriented Programming
- File I/O
- Collections
- Algorithms
- Machine Learning fundamentals

---

## Project Requirements

This project was developed as part of the **Artificial Intelligence Tools (NAI)** course.

The implementation was required to:

- Read training and test data from external files.
- Dynamically determine the number of conditional attributes.
- Read the value of `k` from the user.
- Classify all samples in the test dataset.
- Display the number of correctly classified samples.
- Calculate and display classification accuracy.
- Allow interactive classification of new samples.
- Avoid all third-party Machine Learning libraries and tools.

---

## Learning Outcomes

Through this project, I strengthened my understanding of:

- k-Nearest Neighbors classification
- Supervised Machine Learning
- Euclidean distance
- Majority voting
- Model evaluation and classification accuracy
- Dynamic dataset processing
- Algorithm implementation from scratch
- Java file handling
- Object-Oriented Programming

---

## Future Improvements

- Visualize classification accuracy for different values of `k`
- Add automatic selection of the optimal `k`
- Implement additional distance metrics
- Add normalization and feature scaling
- Support CSV datasets
- Add cross-validation
- Improve handling of ties during majority voting

---

## Author

**Sena Elif Yorucu**

Computer Science Student  
Polish-Japanese Academy of Information Technology (PJATK)

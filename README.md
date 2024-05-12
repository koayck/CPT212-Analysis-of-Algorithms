# CPT212-Analysis-of-Algorithms

## Introduction

This is a algorithm analysis project for CPT212 - Design & Analysis of Algorithms course. This repository contains our implementation and analysis of two multiplication algorithms: the Simple Multiplication Algorithm and the Karatsuba Algorithm. We have implemented these algorithms in Java, inserted counters to count the primitive operations, and plotted graphs to compare their time complexities.

## Team Members

- Member 1: Koay Chun Keat
- Member 2: Jacky Chung Sze Yung

## Objectives

1. Manipulate data structures or algorithms in problem-solving and programming.
2. Perform complexity analysis of algorithms.

## Getting Started

Follow these steps to get a copy of the project up and running on your local machine for development and testing purposes:

### Prerequisites

You need to have Java and Maven installed on your machine. If you don't have Maven installed, you can still build and run the project using the Maven Wrapper script included in the project.

1.  **Clone the Repository:**
    Clone this repository to your local machine using the following command:

    `git clone https://github.com/koayck/CPT212-Analysis-of-Algorithms.git`

2.  **Navigate to the Project Directory:**

    `cd CPT212-Analysis-of-Algorithms`

3.  **Build the Project:**

    If you have Maven installed on your machine, you can build the project using the following command:

    `mvn clean package`

    If you don't have Maven installed, you can use the Maven Wrapper script to build the project:

    `./mvnw clean package`

4.  **Run the Project:**

    You can run the project using the following command:

    `java -jar target/cpt212-analysis-of-algorithms-1.0-SNAPSHOT.jar`

## Project Details

### Part 1: Simple Multiplication Algorithm

We have implemented the Simple Multiplication Algorithm as described in the assignment. The algorithm multiplies each digit in the multiplicand by each digit of the multiplier, keeps the partial product and carriers if there is any, and adds up all the properly shifted partial products and carriers.

### Part 2: Karatsuba Algorithm

We have also implemented the Karatsuba Algorithm, a fast multiplication algorithm that uses divide and conquer strategy. We have inserted counters at appropriate locations in the program to count the primitive operations executed by the Karatsuba algorithm.

## Results and Discussion
We have plotted graphs showing the number of operations versus the number of digits (n) for both algorithms. We have compared and discussed the results obtained in the experiment and the time complexity of the theoretical analysis. We have also compared the time complexity of the Simple Multiplication Algorithm versus the Karatsuba Algorithm based on the experiment results. 

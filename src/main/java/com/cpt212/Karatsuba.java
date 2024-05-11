package com.cpt212;

import java.math.BigInteger;

/**
 * Implements the Karatsuba algorithm for multiplication.
 * <p>
 * This class provides an implementation of the Karatsuba algorithm, which is a
 * fast multiplication algorithm. It uses divide and conquer to multiply two numbers.
 * <p>
 * The class also keeps track of the number of operations performed during the
 * multiplication process for analysis purposes.
 *
 * @author JackyChung2003
 * @version 1.0
 */
class Karatsuba implements IMultiplication {

  // Operation counters
  public long countAdditions = 0;
  public long countSubtractions = 0;
  public long countMultiplications = 0;
  public long countDivisions = 0;
  public long countComparisons = 0;
  public long countAssignments = 0;
  public long countMethodCalls = 0;

  public long countTotalOperations = 0; // For total primitive operations count in the program

  // Method 1
  // To multiply two integers using Karatsuba algorithm
  @Override
  public BigInteger mult(BigInteger x, BigInteger y) {
    countMethodCalls++; // Increment assignment count for each method call

    countComparisons += 3; // Increment comparison count for <, > and && operators
    // (unsure) because previous version no call this method
    countMethodCalls += 2; // Increment method call for both compareTo method

    // Checking only if input is within range
    if (x.compareTo(BigInteger.TEN) < 0 && y.compareTo(BigInteger.TEN) < 0) {
      countMultiplications++; // Increment multiplication count for x.multiply(y)
      countMethodCalls++; // Increment method call for return the value
      return x.multiply(y);
    }

    // Declaring variables in order to find length of both integer numbers x and y
    int noOneLength = numLength(x);
    int noTwoLength = numLength(y);
    countAssignments += 2; // Increment assignment count for noOneLength and noTwoLength

    // Finding maximum length from both numbers using math library max function
    int maxNumLength = Math.max(noOneLength, noTwoLength);
    countMethodCalls++; // Increment method call for Math.max method
    countAssignments++; // Increment assignment count for maxNumLength

    // Rounding up the divided Max length
    BigInteger halfMaxNumLength = BigInteger.valueOf(
      (maxNumLength / 2) + (maxNumLength % 2)
    );
    countMultiplications += 2; // Increment division count for maxNumLength / 2 and maxNumLength % 2
    countAdditions++; // Increment addition count for the sum
    countMethodCalls++; // Increment method call for BigInteger.valueOf method
    countAssignments++; // Increment assignment count for halfMaxNumLength

    // Multiplier
    BigInteger maxNumLengthTen = BigInteger.TEN.pow(
      halfMaxNumLength.intValue()
    );
    countMethodCalls += 2; // Increment method call for .intValue() and .pow() method
    countAssignments++; // Increment assignment count for maxNumLengthTen

    // Compute the expressions
    BigInteger a = x.divide(maxNumLengthTen);
    BigInteger b = x.mod(maxNumLengthTen);
    BigInteger c = y.divide(maxNumLengthTen);
    BigInteger d = y.mod(maxNumLengthTen);
    countMethodCalls += 4; // Increment method call for divide and mod methods
    countAssignments += 4; // Increment assignment count for a, b, c and d

    // Compute all mutilpying variables needed to get the multiplication
    BigInteger z0 = mult(a, c);
    BigInteger z1 = mult(a.add(b), c.add(d));
    BigInteger z2 = mult(b, d);
    countMethodCalls += 5; // Increment method call for mult(3) and add(2) methods
    countAssignments += 3; // Increment assignment count for z0, z1 and z2

    BigInteger result = z0
      .multiply(BigInteger.TEN.pow(halfMaxNumLength.intValue() * 2))
      .add(
        z1
          .subtract(z0)
          .subtract(z2)
          .multiply(BigInteger.TEN.pow(halfMaxNumLength.intValue()))
          .add(z2)
      );
    countMethodCalls += 10; // Increment method call for multiply(2), add(2), subtract(2), pow(2) and
    // intValue(2) methods
    countAssignments++; // Increment assignment count for result

    countMethodCalls++; // Increment method call for return the value
    
    // Returning the final count
    countTotalOperations =
      countAdditions +
      countMultiplications +
      countComparisons +
      countAssignments +
      countMethodCalls;

    return result;
  }

  // Method 2
  // To calculate length of the number
  public int numLength(BigInteger n) {
    countMethodCalls++; // Increment assignment count for each method call

    int noLen = 0;
    countAssignments++; // Increment assignment count for noLen

    while (n.compareTo(BigInteger.ZERO) > 0) {
      countComparisons++; // Increment comparison count for n > 0
      countMethodCalls++; // Increment method call for compareTo method

      noLen++; // Equal to noLen = noLen + 1
      countAdditions++; // Increment addition count for noLen++
      countAssignments++; // Increment assignment count for noLen++

      n = n.divide(BigInteger.TEN);
      countMethodCalls++; // Increment method call for divide method
      countAssignments++; // Increment assignment count for n
    }

    countMethodCalls++; // Increment method call for return the value
    // Returning length of number n
    return noLen;
  }

  // Method 3
  // To reset the operation counters
  public void resetCounters(){
    countAdditions = 0;
    countMultiplications = 0;
    countComparisons = 0;
    countAssignments = 0;
    countMethodCalls = 0;
    countTotalOperations = 0;
  }

  // Method 4
  // Getter for total operations count
  public long getTotalOperationsCount() {
    return countTotalOperations;
  }
}

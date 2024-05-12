package com.cpt212;

import java.math.BigInteger;

/**
* Implements the simple multiplication algorithm for multiplication.
* <p>
* This class provides an implementation of the simple multiplication algorithm.
* <p>
* The class also keeps track of the number of operations performed during the
* multiplication process for analysis purposes.
*
* @author koayck
* @version 1.0
*/
class SimpleMultiplication implements IMultiplication {

  // Operation counters
  public long countAdditions = 0;
  public long countSubtractions = 0;
  public long countMultiplications = 0;
  public long countDivisions = 0;
  public long countComparisons = 0;
  public long countAssignments = 0;
  public long countMethodCalls = 0;
  public long countElementReferences = 0;
  public long countReturn = 0;

  public long countTotalOperations = 0; // For total primitive operations count in the program

  // Method 1
  // To multiply two integers using simple multiplication
  public BigInteger mult(BigInteger num1, BigInteger num2) {
    countMethodCalls++;

    StringBuilder strNum1 = new StringBuilder(num1.toString());
    countMethodCalls++;
    countAssignments++;
    StringBuilder strNum2 = new StringBuilder(num2.toString());
    countMethodCalls++;
    countAssignments++;
    BigInteger result = BigInteger.ZERO;
    countAssignments++;

    System.out.println(strNum1 + " x " + strNum2);
    System.out.println("--------------------");

    countMethodCalls++;
    countSubtractions++;
    countAssignments++;
    countComparisons++;
    for (int i = strNum2.length() - 1; i >= 0; i--) {
      int n2 = strNum2.charAt(i) - '0';
      countMethodCalls++;
      countSubtractions++;
      countAssignments++;

      int carry = 0;
      countAssignments++;

      StringBuilder partialLine = new StringBuilder(); // string to store partial products
      countAssignments++;

      StringBuilder carryLine = new StringBuilder(); // string to store carriers
      countAssignments++;

      countMethodCalls++;
      countSubtractions++;
      countAssignments++;
      countComparisons++;
      for (int j = strNum1.length() - 1; j >= 0; j--) {
        int n1 = strNum1.charAt(j) - '0';
        countMethodCalls++;
        countSubtractions++;
        countAssignments++;

        BigInteger product = BigInteger
          .valueOf(n1)
          .multiply(BigInteger.valueOf(n2));
        countMethodCalls += 3; // 2 valueOf() and 1 multiply()
        countAssignments++; // Assign product to variable

        BigInteger[] divMod = product.divideAndRemainder(BigInteger.TEN);
        countMethodCalls++; // for divideAndRemainder
        countAssignments += 2; // for divMod[0] and divMod[1]
        
        carry = divMod[0].intValue(); // assign carry value
        countElementReferences++; // for divMod[0]
        countMethodCalls++; // for intValue
        countAssignments++; // Assign carry to variable
        
        partialLine.insert(0, divMod[1].toString());
        countElementReferences++; // for divMod[1]
        countMethodCalls += 2; // for insert and toString
        countAssignments++; // Assign divMod[1] to partialLine

        carryLine.insert(0, carry);
        countMethodCalls += 2; // for insert and toString
        countAssignments++; // Assign divMod[1] to partialLine

        countSubtractions++; // for j-1
        countAssignments++; // for assigning j-1 to j
        countComparisons++; // for loop to check j>=0
      }

      carryLine.append("0");
      countMethodCalls++;
      countAssignments++;

      countAssignments++;
      countMethodCalls++;
      countSubtractions++;
      countComparisons++;
      for (int k = 0; k < strNum2.length() - 1 - i; k++) {
        partialLine.append("0");
        countMethodCalls++;
        countAssignments++;

        carryLine.append("0");
        countMethodCalls++;
        countAssignments++;

        countSubtractions++; // for j-1
        countAssignments++; // for assigning j-1 to j
        countComparisons++; // for loop to check j>=0
      }

      System.out.println(
        partialLine + " partial products for (" + num1 + " x " + n2 + ")"
      );
      System.out.println(
        carryLine + " carriers   for (" + num1 + " x " + n2 + ")"
      );
      System.out.println("--------------------");

      result =
        result
          .add(new BigInteger(partialLine.toString()))
          .add(new BigInteger(carryLine.toString()));
      countMethodCalls += 4; // for 2 add() and 2 toString()
      countAssignments++;

      countSubtractions++; // for i--
      countAssignments++; // for assigning i-1 to i
      countComparisons++; // for loop to check i>=0
    }

    System.out.println("Product: " + result + "\n");

    countReturn++; // for return

    countTotalOperations =
      countAdditions +
      countSubtractions +
      countMultiplications +
      countDivisions +
      countComparisons +
      countAssignments +
      countElementReferences +
      countMethodCalls +
      countReturn;

    return result;
  }

  // Method 2
  // To reset the operation counters
  public void resetCounters(){
    countAdditions = 0;
    countSubtractions = 0;
    countMultiplications = 0;
    countDivisions = 0;
    countComparisons = 0;
    countAssignments = 0;
    countElementReferences = 0;
    countMethodCalls = 0;
    countReturn = 0;
    countTotalOperations = 0;
  }

  // Method 3
  // Getter for total operations count
  public long getTotalOperationsCount() {
    return countTotalOperations;
  }

  // Method 4
  // To calculate the value for cg(n) for big O analysis
  public long calculateCGn(int n) {
    return 28 * n * n;
  }
}

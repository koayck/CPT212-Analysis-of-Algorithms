/// Java Program to Implement Karatsuba Algorithm

// Importing Random class from java.util packahge
import java.io.FileWriter;
import java.io.IOException;
// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.scene.chart.LineChart;
// import javafx.scene.chart.NumberAxis;
// import javafx.scene.chart.XYChart;
// import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

// import javafx.application.Application;
// import javafx.scene.Scene;
// import javafx.scene.chart.LineChart;
// import javafx.scene.chart.NumberAxis;
// import javafx.scene.chart.XYChart;
// import javafx.stage.Stage;

// MAin class
class Karatsuba {

  // Operation counters
  static long countAdditions = 0;
  static long countMultiplications = 0;
  static long countComparisons = 0;
  static long countAssignments = 0;
  static long countMethodCalls = 0;

  static long countTotalOperations = 0; // For total primitive operations count in the program

  static ArrayList<Integer> digitsList = new ArrayList<>();
  static ArrayList<Long> operationsList = new ArrayList<>();

  // Main driver method
  public static long mult(long x, long y) {
    countMethodCalls++; // Incrementing method call counter

    // Checking only if input is within range
    if (x < 10 && y < 10) {
      countComparisons += 3; // For the if condition(two < and one &&), if equals to true, so it will be // adding 3 before the function returns

      countMultiplications++; // For the multiplication
      countMethodCalls++; // For returning the value
      // Multiplying the inputs entered
      return x * y;
    }

    countComparisons += 3; // For the if condition(two < and one &&), if false, so the adding 3 inside if
    // block will not be executed

    // Declaring variables in order to
    // Find length of both integer
    // numbers x and y
    int noOneLength = numLength(x);
    int noTwoLength = numLength(y);
    countAssignments += 2; // For assigning noOneLength and noTwoLength

    // Finding maximum length from both numbers
    // using math library max function
    int maxNumLength = Math.max(noOneLength, noTwoLength); // not sure how many in the Math.max()
    // checkback
    countComparisons += 2; // Two comparisons in Math.max (not sure)

    // Rounding up the divided Max length
    Integer halfMaxNumLength = (maxNumLength / 2) + (maxNumLength % 2);
    countAdditions++; // For the addition
    countMultiplications += 2; // For the division and modulus
    countAssignments++; // For halfMaxNumLength

    // Multiplier
    long maxNumLengthTen = (long) Math.pow(10, halfMaxNumLength); // not sure how many in the Math.pow()
    // checkback
    countMethodCalls++; // For Math.pow call
    countAssignments++; // For maxNumLengthTen

    // Compute the expressions
    long a = x / maxNumLengthTen;
    long b = x % maxNumLengthTen;
    long c = y / maxNumLengthTen;
    long d = y % maxNumLengthTen;
    countMultiplications += 4; // For the division and modulus
    countAssignments += 4; // For a, b, c, d

    // Compute all mutilpying variables
    // needed to get the multiplication
    long z0 = mult(a, c);
    long z1 = mult(a + b, c + d);
    long z2 = mult(b, d);
    countAdditions += 2; // Two additions in arguments of mult
    countAssignments += 3; // For z0, z1, z2

    long ans =
      (
        z0 *
        (long) Math.pow(10, halfMaxNumLength * 2) +
        ((z1 - z0 - z2) * (long) Math.pow(10, halfMaxNumLength) + z2)
      ); // not sure how many in this total

    countMultiplications += 3; // For the three multiplications
    countAdditions += 4; // For the 2 additions and 2 subtractions
    countMethodCalls += 2; // Another Math.pow call(Not sure)

    countAssignments++; // For the assignment

    // Returning the final count
    countTotalOperations =
      countAdditions +
      countMultiplications +
      countComparisons +
      countAssignments +
      countMethodCalls;

    countMethodCalls++; // For the return statement

    return ans;
  }

  // Method 1
  // To calculate length of the number
  public static int numLength(long n) {
    countMethodCalls++; // Incrementing method call counter
    int noLen = 0;
    countAssignments++; // For the assignment
    while (n > 0) {
      countComparisons++; // Comparison in the while equals to true condition

      noLen++; // ++ is an operator but not noLen = noLen + 1
      countAdditions++; // For the addition of noLen
      countAssignments++; // Assignment to noLen

      n /= 10;
      countMultiplications++; // division in the loop for n
      countAssignments++; // Assignment in the loop for n
    }

    countMethodCalls++; // For the return statement
    // Returning length of number n
    return noLen;
  }

  // // Method 3
  // // To write the results to a CSV file
  // public static void writeToCSV(int nDigits, long totalOperations) {
  // try {
  // FileWriter csvWriter = new FileWriter("data.csv", true);
  // csvWriter.append(nDigits + "," + totalOperations + "\n");
  // csvWriter.flush();
  // csvWriter.close();
  // } catch (IOException e) {
  // e.printStackTrace();
  // }
  // }

  // // Method 3
  // // To write the results to a CSV file
  // public static void writeToCSV(int nDigits, long totalOperations, int a, int
  // b) {
  // try {
  // FileWriter csvWriter = new FileWriter("data.csv", true);
  // csvWriter.append(nDigits + "," + totalOperations + a + "," + b + "," + "\n");
  // csvWriter.flush();
  // csvWriter.close();
  // } catch (IOException e) {
  // e.printStackTrace();
  // }
  // }

  // Method 3
  // To write the results to a CSV file
  public static void writeToCSV(
    int nDigits,
    long totalOperations,
    int x,
    int y
  ) {
    try {
      FileWriter csvWriter = new FileWriter("data.csv", true);
      csvWriter.append(
        nDigits + "," + totalOperations + "," + x + "," + y + "\n"
      );
      csvWriter.flush();
      csvWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // // Method 2
  // // Main driver function
  // public static void main(String[] args) {
  // // Showcasing karatsuba multiplication

  // try {
  // FileWriter csvWriter = new FileWriter("data.csv");
  // csvWriter.append("RandomNumber,TotalOperations\n");

  // // // Case 1: Big integer lengths
  // long expectedProduct = 1234 * 5678;
  // long actualProduct = mult(1234, 5678);

  // // Printing the expected and corresponding actual product
  // System.out.println("Expected 1 : " + expectedProduct);
  // System.out.println("Actual 1 : " + actualProduct + "\n\n");

  // assert (expectedProduct == actualProduct);

  // expectedProduct = 102 * 313;
  // actualProduct = mult(102, 313);

  // System.out.println("Expected 2 : " + expectedProduct);
  // System.out.println("Actual 2 : " + actualProduct + "\n\n");

  // assert (expectedProduct == actualProduct);

  // expectedProduct = 1345 * 63456;
  // actualProduct = mult(1345, 63456);

  // System.out.println("Expected 3 : " + expectedProduct);
  // System.out.println("Actual 3 : " + actualProduct + "\n\n");

  // assert (expectedProduct == actualProduct);

  // Integer x = null;
  // Integer y = null;
  // // Integer MAX_VALUE = 10000;
  // Integer MAX_VALUE = 100; // For testing purposes

  // // Boe creating an object of random class
  // // inside main() method
  // Random r = new Random();

  // for (int i = 0; i < MAX_VALUE; i++) {
  // x = (int) r.nextInt(MAX_VALUE);
  // y = (int) r.nextInt(MAX_VALUE);

  // // Reset counters
  // countAdditions = 0;
  // countMultiplications = 0;
  // countComparisons = 0;
  // countAssignments = 0;
  // countMethodCalls = 0;
  // countTotalOperations = 0;

  // expectedProduct = x * y;

  // // if (i == 9999) {
  // if (i == 99) {// For testing purposes

  // // Prove assertions catch the bad stuff.
  // expectedProduct = 1;
  // }
  // actualProduct = mult(x, y);

  // // Again printing the expected and
  // // corresponding actual product
  // // System.out.println("Expected: " + expectedProduct);
  // // System.out.println("Actual: " + actualProduct + "\n\n");

  // // Printing expected and actual products along with the count of operations
  // System.out.println("Test " + (i + 1) + ":");
  // System.out.println("Expected: " + expectedProduct);
  // System.out.println("Actual: " + actualProduct);
  // // System.out.println("Operations count:");
  // // System.out.println(" Additions: " + countAdditions);
  // // System.out.println(" Multiplications: " + countMultiplications);
  // // System.out.println(" Comparisons: " + countComparisons);
  // // System.out.println(" Assignments: " + countAssignments);
  // // System.out.println(" Method Calls: " + countMethodCalls + "\n");
  // System.out.println("Total Operations: " + countTotalOperations + "\n");

  // assert (expectedProduct == actualProduct);
  // }
  // csvWriter.flush();
  // csvWriter.close();
  // } catch (IOException e) {
  // e.printStackTrace();
  // }
  // }

  // Method 2
  // Main driver function
  public static void main(String[] args) {
    // Showcasing karatsuba multiplication

    try {
      FileWriter csvWriter = new FileWriter("data.csv");
      csvWriter.append("NumberOfDigits,TotalOperations\n");

      // Case 1: Big integer lengths
      long expectedProduct = 1234 * 5678;
      long actualProduct = mult(1234, 5678);

      // Printing the expected and corresponding actual product
      System.out.println("Expected 1 : " + expectedProduct);
      System.out.println("Actual 1 : " + actualProduct + "\n\n");

      assert (expectedProduct == actualProduct);

      expectedProduct = 102 * 313;
      actualProduct = mult(102, 313);

      System.out.println("Expected 2 : " + expectedProduct);
      System.out.println("Actual 2 : " + actualProduct + "\n\n");

      assert (expectedProduct == actualProduct);

      expectedProduct = 1345 * 63456;
      actualProduct = mult(1345, 63456);

      System.out.println("Expected 3 : " + expectedProduct);
      System.out.println("Actual 3 : " + actualProduct + "\n\n");

      assert (expectedProduct == actualProduct);

      Integer x = null;
      Integer y = null;
      String xString = null;
      String yString = null;

      // Integer MAX_VALUE = 10000;
      Integer MAX_VALUE = 10000; // For testing purposes

      // Boe creating an object of random class
      // inside main() method
      Random r = new Random(42);

      for (int i = 0; i < MAX_VALUE; i++) {
        x = (int) r.nextInt(MAX_VALUE);
        y = (int) r.nextInt(MAX_VALUE);
        xString = x.toString();
        yString = y.toString();

        while (xString.length() != yString.length()) {
          // // Generate random strings
          x = (int) r.nextInt(MAX_VALUE);
          y = (int) r.nextInt(MAX_VALUE);
          xString = x.toString();
          yString = y.toString();
          // x = Integer.toString(r.nextInt(MAX_VALUE));
          // y = Integer.toString(r.nextInt(MAX_VALUE));
        }

        // Reset counters
        countAdditions = 0;
        countMultiplications = 0;
        countComparisons = 0;
        countAssignments = 0;
        countMethodCalls = 0;
        countTotalOperations = 0;

        expectedProduct = x * y;

        // if (i == 9999) {
        if (i == 9999) { // For testing purposes
          // Prove assertions catch the bad stuff.
          expectedProduct = 1;
        }
        actualProduct = mult(x, y);

        // Again printing the expected and
        // corresponding actual product
        // System.out.println("Expected: " + expectedProduct);
        // System.out.println("Actual: " + actualProduct + "\n\n");

        // Printing expected and actual products along with the count of operations
        System.out.println("Test " + (i + 1) + ":");
        System.out.println("Expected: " + expectedProduct);
        System.out.println("Actual: " + actualProduct);
        System.out.println("Total Operations: " + countTotalOperations + "\n");

        // Write the results to the CSV file
        writeToCSV(String.valueOf(x).length(), countTotalOperations, x, y);
        // writeToCSV(numLength(x), countTotalOperations, x, y);
        // writeToCSV(numLength(x), countTotalOperations, x, y);

        assert (expectedProduct == actualProduct);
      }
      csvWriter.flush();
      csvWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

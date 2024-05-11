package com.cpt212;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

public class App {

  public static void main(String[] args) {
    // performMultiplication(new Karatsuba(), "data/k_data.csv");
    performMultiplication(new SimpleMultiplication(), "data/sm_data.csv");
  }

  public static void performMultiplication(
    IMultiplication multiplication,
    String fileName
  ) {
    FileWriter csvWriter = null;

    try {
      // create the folder if it does not exist
      File file = new File("data");
      if (!file.exists()) {
        file.mkdirs(); // This will create the directory if it doesn't exist
      }

      csvWriter = new FileWriter(fileName, false);
      csvWriter.append("n,multiplicand,multiplier,f(n),cg(n)\n");

      csvWriter.flush();
      csvWriter.close();

      BigInteger expectedProduct = null;
      BigInteger actualProduct = null;

      BigInteger x = null;
      BigInteger y = null;

      final int MAX_NUMBER_DIGIT = 10; // To set the maximum number of digits

      // Instatiating an object of random class
      Random r = new Random(1);

      // Loop to test the Karatsuba algorithm from 1-1000 digits
      for (int n = 1; n <= MAX_NUMBER_DIGIT; n++) {
        // Generating numbers with n decimal digits
        BigInteger lowerLimit = BigInteger.TEN.pow(n - 1); // Lower limit is 10^(n-1)
        BigInteger upperLimit = BigInteger.TEN.pow(n).subtract(BigInteger.ONE); // Upper limit is 10^n - 1

        // Generating 2 random number x and y between the lower and upper limits
        BigInteger range = upperLimit.subtract(lowerLimit).add(BigInteger.ONE);
        x = new BigInteger(range.bitLength() + 1, r).mod(range).add(lowerLimit);
        y = new BigInteger(range.bitLength() + 1, r).mod(range).add(lowerLimit);

        // Resetting the operation counters
        multiplication.resetCounters();

        // Calculating the expected and actual product, then later comparing them
        expectedProduct = x.multiply(y);
        actualProduct = multiplication.mult(x, y);

        // Print the results to the console for each test
        System.out.println("Test with " + n + " digits:" + "\n");
        System.out.println("Expected: " + expectedProduct + "\n");
        System.out.println("Actual: " + actualProduct + "\n");
        System.out.println(
          "Total Operations: " + multiplication.getTotalOperationsCount() + "\n"
        );

        // Write result to csv
        Utils.writeToCSV(
          fileName,
          n,
          x.intValue(),
          y.intValue(),
          multiplication.getTotalOperationsCount(),
          multiplication.calculateCGn(n)
        );

        // if expectedProduct != actualProduct, throw an error
        assert expectedProduct.equals(actualProduct);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      if (csvWriter != null) {
        try {
          csvWriter.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
  }
}

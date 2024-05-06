/// Java Program to Implement Karatsuba Algorithm

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

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
    public static BigInteger mult(BigInteger x, BigInteger y) {

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
        BigInteger halfMaxNumLength = BigInteger.valueOf((maxNumLength / 2) + (maxNumLength % 2));
        countMultiplications += 2; // Increment division count for maxNumLength / 2 and maxNumLength % 2
        countAdditions++; // Increment addition count for the sum
        countMethodCalls++; // Increment method call for BigInteger.valueOf method
        countAssignments++; // Increment assignment count for halfMaxNumLength

        // Multiplier
        BigInteger maxNumLengthTen = BigInteger.TEN.pow(halfMaxNumLength.intValue());
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

        BigInteger ans = z0.multiply(BigInteger.TEN.pow(halfMaxNumLength.intValue() * 2))
                .add(z1.subtract(z0).subtract(z2).multiply(BigInteger.TEN.pow(halfMaxNumLength.intValue()))
                        .add(z2));
        countMethodCalls += 10; // Increment method call for multiply(2), add(2), subtract(2), pow(2) and
                                // intValue(2) methods
        countAssignments++; // Increment assignment count for ans

        // Returning the final count
        countTotalOperations = countAdditions + countMultiplications + countComparisons + countAssignments
                + countMethodCalls;

        countMethodCalls++; // Increment method call for return the value

        return ans;
    }

    // Method 1
    // To calculate length of the number
    public static int numLength(BigInteger n) {
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

    // Method 2
    // To write the results to a CSV file
    public static void writeToCSV(int nDigits, long totalOperations, int x, int y) {
        try {
            FileWriter csvWriter = new FileWriter("data.csv", true);
            csvWriter.append(nDigits + "," + totalOperations + "," + x + "," + y + "\n");
            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method 3
    // Main driver function
    public static void main(String[] args) {
        FileWriter csvWriter = null;
        try {
            csvWriter = new FileWriter("data.csv", true); // true to append, false to overwrite
            csvWriter.append("NumberOfDigits,TotalOperations\n");

            BigInteger x, y;

            BigInteger expectedProduct, actualProduct;

            Random r = new Random(42);

            // Loop to test the Karatsuba algorithm from 1-1000 digits
            for (int digits = 1; digits <= 1000; digits++) {

                // Generating numbers with 'digits' decimal digits
                BigInteger minValue = BigInteger.TEN.pow(digits - 1);
                BigInteger maxValue = BigInteger.TEN.pow(digits).subtract(BigInteger.ONE);

                // Formula: min + (int)(Math.random() * ((max – min) + 1));
                x = new BigInteger(minValue.bitLength(), r).mod(maxValue.subtract(minValue).add(BigInteger.ONE))
                        .add(minValue);
                y = new BigInteger(minValue.bitLength(), r).mod(maxValue.subtract(minValue).add(BigInteger.ONE))
                        .add(minValue);

                // Resetting the operation counters
                countAdditions = 0;
                countMultiplications = 0;
                countComparisons = 0;
                countAssignments = 0;
                countMethodCalls = 0;
                countTotalOperations = 0;

                // Calculating the expected and actual product, then later comparing them
                expectedProduct = x.multiply(y);
                actualProduct = mult(x, y);

                // Print the results to the console for each test
                System.out.println("Test with " + digits + " digits:" + "\n");
                System.out.println("Expected: " + expectedProduct + "\n");
                System.out.println("Actual: " + actualProduct + "\n");
                System.out.println("Total Operations: " + countTotalOperations + "\n");

                writeToCSV(digits, countTotalOperations, x.intValue(), y.intValue());

                assert expectedProduct.equals(actualProduct);
            }
            csvWriter.flush();
            csvWriter.close();
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

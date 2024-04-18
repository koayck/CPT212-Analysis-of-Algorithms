/// Java Program to Implement Karatsuba Algorithm

// Importing Random class from java.util packahge
import java.util.Random;

// MAin class 
class Karatsuba {

    // Operation counters
    static long countAdditions = 0;
    static long countMultiplications = 0;
    static long countComparisons = 0;
    static long countAssignments = 0;
    static long countMethodCalls = 0;

    // Main driver method
    public static long mult(long x, long y) {

        countMethodCalls++; // Incrementing method call counter

        // Checking only if input is within range
        if (x < 10 && y < 10) {
            countComparisons += 3; // For the if condition(two < and one &&), if equals to true, so it will be
                                   // adding 3 before the function returns
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
        countComparisons += 2; // Two comparisons in Math.max (not sure)

        // Rounding up the divided Max length
        Integer halfMaxNumLength = (maxNumLength / 2) + (maxNumLength % 2);
        countAdditions++; // For the addition
        countMultiplications += 2; // For the division and modulus
        countAssignments++; // For halfMaxNumLength

        // Multiplier
        long maxNumLengthTen = (long) Math.pow(10, halfMaxNumLength); // not sure how many in the Math.pow()
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

        long ans = (z0 * (long) Math.pow(10, halfMaxNumLength * 2) +
                ((z1 - z0 - z2) * (long) Math.pow(10, halfMaxNumLength) + z2)); // not sure how many in this total

        countMultiplications += 3; // For the three multiplications
        countAdditions += 3; // For the three additions
        countMethodCalls++; // Another Math.pow call(Not sure)

        countMethodCalls++; // For the return statement
        return ans;

    }

    // Method 1
    // To calculate length of the number
    public static int numLength(long n) {
        countMethodCalls++; // Incrementing method call counter
        int noLen = 0;
        while (n > 0) {
            noLen++; // ++ is an operator but not noLen = noLen + 1
            countAdditions++; // For the addition of noLen
            n /= 10;
            countAssignments++; // Assignment in the loop for n
            countMultiplications++; // division in the loop for n
            countComparisons++; // Comparison in the while equals to true condition
        }

        countMethodCalls++; // For the return statement
        // Returning length of number n
        return noLen;
    }

    // Method 2
    // Main driver function
    public static void main(String[] args) {
        // Showcasing karatsuba multiplication

        // // Case 1: Big integer lengths
        long expectedProduct = 1234 * 5678;
        long actualProduct = mult(1234, 5678);

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

        Integer x = null;
        Integer y = null;
        // Integer MAX_VALUE = 10000;
        Integer MAX_VALUE = 10000; // For testing purposes

        // Boe creating an object of random class
        // inside main() method
        Random r = new Random();

        for (int i = 0; i < MAX_VALUE; i++) {
            x = (int) r.nextInt(MAX_VALUE);
            y = (int) r.nextInt(MAX_VALUE);

            // Reset counters
            countAdditions = 0;
            countMultiplications = 0;
            countComparisons = 0;
            countAssignments = 0;
            countMethodCalls = 0;

            expectedProduct = x * y;

            if (i == 9999) {
                // if (i == 4) {// For testing purposes

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
            System.out.println("Operations count:");
            System.out.println("  Additions: " + countAdditions);
            System.out.println("  Multiplications: " + countMultiplications);
            System.out.println("  Comparisons: " + countComparisons);
            System.out.println("  Assignments: " + countAssignments);
            System.out.println("  Method Calls: " + countMethodCalls + "\n");

            assert (expectedProduct == actualProduct);
        }
    }
}
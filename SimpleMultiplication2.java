import java.util.Random;
import java.util.Stack;

public class SimpleMultiplication2 {

  public static void main(String[] args) {
    // Clear the console
    try {
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    } catch (Exception e) {
      System.out.println(e);
    }

    for (int i = 1; i <= 10; i++) {
      int n = i; // number of digits, but the weakness is that it can only handle up to 10 digits
      Random rand = new Random();

      int lowerBound = (int) Math.pow(10, n - 1);
      int upperBound = (int) Math.pow(10, n) - 1;

      int multiplier = rand.nextInt(upperBound - lowerBound) + lowerBound;
      int multiplicand = rand.nextInt(upperBound - lowerBound) + lowerBound;
      System.out.println(
        "=============================================================="
      );
      System.out.println(n + " digits");
      System.out.println(
        "\nMultiplication result of " +
        multiplier +
        " x " +
        multiplicand +
        " = " +
        mult(multiplier, multiplicand) +
        "\n"
      );
    }
  }

  // To calculate length of the number
  public static int numLength(long n) {
    int noLen = 0;
    while (n > 0) {
      noLen++;
      n /= 10;
    }

    // Returning length of number n
    return noLen;
  }

  public static long mult(int multiplier, int multiplicand) {
    long result = 0;

    int counter = 0;

    // Perform multiplication digit by digit
    for (int i = numLength(multiplier) - 1; i >= 0; i--) {
      int m = multiplicand;
      multiplier %= 10;
      int partial = 0;
      int carrier = 0;
      for (int j = numLength(m) - 1; j >= 0; j--) {
        m %= 10;
        int product = m * multiplier;

        partial *= 10;
        carrier *= 10;
        if (product >= 10) {
          partial += product % 10;
          carrier += product / 10 * 10;
        } else {
          partial += product;
        }
        m /= 10;
      }

      System.out.println(
        "\nPartial products for " +
        multiplier +
        " x " +
        multiplicand_str.charAt(i)
      );
      // Pop stack_partial and append to a string
      while (!stack_partial.isEmpty()) {
        partial = String.valueOf(stack_partial.pop());
        sb.append(partial);
      }
      System.out.println("Partial Result: " + sb.toString());
      for (int k = 0; k < counter; k++) {
        sb.append("0");
      }
      result += Long.valueOf(sb.toString());

      sb.setLength(0); // Clear sb after printing

      System.out.println(
        "\nCarrier products for " +
        multiplier_str +
        " x " +
        multiplicand_str.charAt(i)
      );

      // Pop stack_carrier and append to a string
      while (!stack_carrier.isEmpty()) {
        carrier = String.valueOf(stack_carrier.pop());
        sb.append(carrier);
      }
      System.out.println("Carrier Result: " + sb.toString());
      for (int k = 0; k < counter + 1; k++) {
        sb.append("0");
      }
      result += Long.valueOf(sb.toString());

      sb.setLength(0); // Clear sb after printing

      // Clear the queues
      stack_partial.clear();
      stack_carrier.clear();
      counter++;
    }

    // Return the final result as a string
    return result;
  }
}

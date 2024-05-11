package com.cpt212;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class
 * <p>
 * This class provides utility methods for writing results to a CSV file.
 * <p>
 *
 * @author koayck
 * @version 1.0
 */
public class Utils {
  public static void writeToCSV(String fileName, int nDigits, long totalOperations, int x, int y) {
    try {
      FileWriter csvWriter = new FileWriter(fileName, true);
      csvWriter.append(
        nDigits + "," + totalOperations + "," + x + "," + y + "\n"
      );
      csvWriter.flush();
      csvWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

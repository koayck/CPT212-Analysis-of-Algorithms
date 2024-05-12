package com.cpt212;

import java.math.BigInteger;

/**
 * Interface for multiplication algorithms.
 * <p>
 * This interface provides a contract for multiplication algorithms to implement.
 * <p>
 *
 * @author koayck
 * @version 1.0
 */
public interface IMultiplication {
    BigInteger mult(BigInteger x, BigInteger y);
    void resetCounters();
    long getTotalOperationsCount();
    long calculateCGn(int n);
}
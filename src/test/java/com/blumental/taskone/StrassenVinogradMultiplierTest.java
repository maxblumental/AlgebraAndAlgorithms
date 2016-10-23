package com.blumental.taskone;

import Jama.Matrix;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class StrassenVinogradMultiplierTest {

    private MatrixMultiplier multiplier;
    private int N = 512;

    @Before
    public void setUp() {
        multiplier = new StrassenVinogradMultiplier();
    }

    @Test
    public void multiply() throws Exception {
        Matrix a = generateMatrix(N);
        Matrix b = generateMatrix(N);

        Matrix actual = multiplier.multiply(a, b);
        Matrix expected = a.times(b);

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                assertEquals(expected.get(i, j), actual.get(i, j), 0.01);
            }
        }
    }

    private Matrix generateMatrix(int n) {
        Matrix matrix = new Matrix(n, n);
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix.set(i, j, random.nextInt(100));
            }
        }
        return matrix;
    }
}
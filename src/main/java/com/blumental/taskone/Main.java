package com.blumental.taskone;

import Jama.Matrix;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Matrix[] matrices = readMatrices();
        Matrix matrix1 = matrices[0];
        Matrix matrix2 = matrices[1];

        StrassenVinogradMultiplier multiplier = new StrassenVinogradMultiplier();
        Matrix result = multiplier.multiply(matrix1, matrix2);

        Utils.printMatrix(result);
    }

    private static Matrix[] readMatrices() {
        MatrixReader matrixReader = new MatrixReaderImpl();
        try (Scanner scanner = new Scanner(System.in)) {
            int n = scanner.nextInt();

            scanner.useDelimiter("\\s");

            Matrix matrix1 = matrixReader.read(n, n, scanner);
            Matrix matrix2 = matrixReader.read(n, n, scanner);
            return new Matrix[]{matrix1, matrix2};
        }
    }
}

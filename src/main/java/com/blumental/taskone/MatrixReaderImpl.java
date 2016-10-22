package com.blumental.taskone;

import Jama.Matrix;

import java.util.Scanner;

public class MatrixReaderImpl implements MatrixReader {
    @Override
    public Matrix read(int n, int m, Scanner scanner) {

        Matrix matrix = new Matrix(n, m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (scanner.hasNext()) {
                    double a_ij = Double.parseDouble(scanner.next());
                    matrix.set(i, j, a_ij);
                }
            }
        }

        return matrix;
    }
}

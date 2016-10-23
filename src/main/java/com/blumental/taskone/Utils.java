package com.blumental.taskone;

import Jama.Matrix;

public class Utils {
    public static void printMatrix(Matrix matrix) {
        System.out.println("---");
        for (int i = 0; i < matrix.getRowDimension(); i++) {
            for (int j = 0; j < matrix.getColumnDimension(); j++) {
                System.out.printf("[%f] ", matrix.get(i, j));
            }
            System.out.println("\n");
        }
        System.out.println("---");
    }
}

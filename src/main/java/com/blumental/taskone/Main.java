package com.blumental.taskone;

import Jama.Matrix;

public class Main {

    public static void main(String[] args) {
        Matrix random = Matrix.random(3, 3);
        for (int i = 0; i < random.getRowDimension(); i++) {
            for (int j = 0; j < random.getColumnDimension(); j++) {
                System.out.printf("[%f] ", random.get(i, j));
            }
            System.out.println("\n");
        }
    }
}

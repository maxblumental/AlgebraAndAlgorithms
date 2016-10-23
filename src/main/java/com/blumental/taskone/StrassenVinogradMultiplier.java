package com.blumental.taskone;

import Jama.Matrix;

public class StrassenVinogradMultiplier implements MatrixMultiplier {

    /**
     * Matrices less than THRESHOLD * THRESHOLD
     * will be multiplied naively.
     */
    private final int THRESHOLD = 32;

    @Override
    public Matrix multiply(Matrix matrix1, Matrix matrix2) {

        validateInput(matrix1, matrix2);

        if (matrix1.getRowDimension() <= THRESHOLD) {
            return matrix1.times(matrix2);
        }

        return makeVinogradStrassenStep(matrix1, matrix2);
    }

    private Matrix makeVinogradStrassenStep(Matrix matrix1, Matrix matrix2) {
        Matrix[][] a = split(matrix1);
        Matrix[][] b = split(matrix2);
        System.out.println(matrix1.getRowDimension());

        Matrix s1 = a[1][0].plus(a[1][1]);
        Matrix s2 = s1.minus(a[0][0]);
        Matrix s3 = a[0][0].minus(a[1][0]);
        Matrix s4 = a[0][1].minus(s2);
        Matrix s5 = b[0][1].minus(b[0][0]);
        Matrix s6 = b[1][1].minus(s5);
        Matrix s7 = b[1][1].minus(b[0][1]);
        Matrix s8 = s6.minus(b[1][0]);

        Matrix p1 = multiply(s2, s6);
        Matrix p2 = multiply(a[0][0], b[0][0]);
        Matrix p3 = multiply(a[0][1], b[1][0]);
        Matrix p4 = multiply(s3, s7);
        Matrix p5 = multiply(s1, s5);
        Matrix p6 = multiply(s4, b[1][1]);
        Matrix p7 = multiply(a[1][1], s8);

        Matrix t1 = p1.plus(p2);
        Matrix t2 = t1.plus(p4);

        Matrix c_11 = p2.plus(p3);
        Matrix c_12 = t1.plus(p5).plus(p6);
        Matrix c_21 = t2.minus(p7);
        Matrix c_22 = t2.plus(p5);

        int n = matrix1.getRowDimension();
        Matrix result = new Matrix(n, n);
        result.setMatrix(0, n / 2 - 1, 0, n / 2 - 1, c_11);
        result.setMatrix(0, n / 2 - 1, n / 2, n - 1, c_12);
        result.setMatrix(n / 2, n - 1, 0, n / 2 - 1, c_21);
        result.setMatrix(n / 2, n - 1, n / 2, n - 1, c_22);

        return result;
    }

    private void validateInput(Matrix matrix1, Matrix matrix2) {
        assertSquare(matrix1);
        assertSquare(matrix2);
        assertSizeEquality(matrix1, matrix2);
    }

    private void assertSizeEquality(Matrix matrix1, Matrix matrix2) {
        assert matrix1.getRowDimension() == matrix2.getRowDimension()
                && matrix1.getColumnDimension() == matrix2.getColumnDimension();
    }

    private void assertSquare(Matrix matrix) {
        assert matrix.getRowDimension() == matrix.getColumnDimension();
    }

    private Matrix[][] split(Matrix matrix) {
        int n = matrix.getRowDimension();

        Matrix a_11 = matrix.getMatrix(0, n / 2 - 1, 0, n / 2 - 1);
        Matrix a_12 = matrix.getMatrix(0, n / 2 - 1, n / 2, n - 1);
        Matrix a_21 = matrix.getMatrix(n / 2, n - 1, 0, n / 2 - 1);
        Matrix a_22 = matrix.getMatrix(n / 2, n - 1, n / 2, n - 1);

        return new Matrix[][]{{a_11, a_12}, {a_21, a_22}};
    }
}

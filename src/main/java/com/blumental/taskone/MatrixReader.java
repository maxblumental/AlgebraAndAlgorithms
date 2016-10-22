package com.blumental.taskone;

import Jama.Matrix;

import java.util.Scanner;

public interface MatrixReader {
    Matrix read(int n, int m, Scanner scanner);
}

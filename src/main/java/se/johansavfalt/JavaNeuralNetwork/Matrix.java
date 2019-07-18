package se.johansavfalt.JavaNeuralNetwork;

import java.util.Arrays;
import java.util.List;

public class Matrix {

    private final int length;
    private final int width;
    private double[][] matrix;

    public Matrix(int length, int width){
        this.length = length;
        this.width = width;
        this.matrix = random(length, width);
    }

    // return a random m-by-n matrix with values between 0 and 1
    public static double[][] random(int m, int n) {
        double[][] a = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                a[i][j] = Math.random();
        return a;
    }

    // return n-by-n identity matrix I
    public static double[][] identity(int n) {
        double[][] a = new double[n][n];
        for (int i = 0; i < n; i++)
            a[i][i] = 1;
        return a;
    }


    // return B = A^T
    public double[][] transpose() {
        int m = matrix.length;
        int n = matrix[0].length;
        double[][] b = new double[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                b[j][i] = matrix[i][j];
        return b;
    }

    // return c = a + b
    public double[][] add(double[][] b) {
        int m = this.length;
        int n = this.width;
        double[][] c = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = matrix[i][j] + b[i][j];
        return c;
    }

    // return c = a - b
    public double[][] subtract(double[][] b) {
        int m = this.length;
        int n = this.width;
        double[][] c = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = this.matrix[i][j] - b[i][j];
        return c;
    }

    // return c = a * b
    public double[][] multiply(double[][] b) {
        int m1 = this.length;
        int n1 = this.width;
        int m2 = b.length;
        int n2 = b[0].length;
        if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
        double[][] c = new double[m1][n2];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    c[i][j] += this.matrix[i][k] * b[k][j];
        return c;
    }

    // matrix-vector multiplication (y = A * x)
    public double[] multiply(double[] x) {
        int m = this.length;
        int n = this.width;
        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y[i] += this.matrix[i][j] * x[j];
        return y;
    }

    public int getLength(){
        return this.length;
    }

    public int getWidth(){
        return this.width;
    }

    public double[][] getMatrix(){
        return this.matrix;
    }


    public static void main(String[] args) {
        Matrix matrix1 = new Matrix(2,2);
        Matrix matrix2 = new Matrix(2,2);
        matrix1.multiply(matrix2.getMatrix());
        System.out.println();



    }

}

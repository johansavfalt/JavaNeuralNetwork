package se.johansavfalt.JavaNeuralNetwork;

class MatrixOperations {

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

    // return x^T y
    public static double dot(double[] x, double[] y) {
        if (x.length != y.length) throw new RuntimeException("Illegal vector dimensions.");
        double sum = 0.0;
        for (int i = 0; i < x.length; i++)
            sum += x[i] * y[i];
        return sum;
    }

    // return B = A^T
    public static double[][] transpose(double[][] a) {
        int m = a.length;
        int n = a[0].length;
        double[][] b = new double[n][m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                b[j][i] = a[i][j];
        return b;
    }

    // return c = a + b
    public static double[][] add(double[][] a, double[][] b) {
        int m = a.length;
        int n = a[0].length;
        double[][] c = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j] + b[i][j];
        return c;
    }

    // return c = a - b
    public static double[][] subtract(double[][] a, double[][] b) {
        int m = a.length;
        int n = a[0].length;
        double[][] c = new double[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                c[i][j] = a[i][j] - b[i][j];
        return c;
    }

    // return c = a * b
    public static double[][] multiply(double[][] a, double[][] b) {
        int m1 = a.length;
        int n1 = a[0].length;
        int m2 = b.length;
        int n2 = b[0].length;
        if (n1 != m2) throw new RuntimeException("Illegal matrix dimensions.");
        double[][] c = new double[m1][n2];
        for (int i = 0; i < m1; i++)
            for (int j = 0; j < n2; j++)
                for (int k = 0; k < n1; k++)
                    c[i][j] += a[i][k] * b[k][j];
        return c;
    }

    // matrix-vector multiplication (y = A * x)
    public static double[] multiply(double[][] a, double[] x) {
        int m = a.length;
        int n = a[0].length;
        if (x.length != n) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[m];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                y[i] += a[i][j] * x[j];
        return y;
    }


    // vector-matrix multiplication (y = x^T A)
    public static double[] multiply(double[] x, double[][] a) {
        int m = a.length;
        int n = a[0].length;
        if (x.length != m) throw new RuntimeException("Illegal matrix dimensions.");
        double[] y = new double[n];
        for (int j = 0; j < n; j++)
            for (int i = 0; i < m; i++)
                y[j] += a[i][j] * x[i];
        return y;
    }

}

//need to wrap the "Matrix" in an object where i can detect dimensions of matrix
public class Matrix{

    private final int inputs;
    private final int units;
    private final double[][] matrix;

    public Matrix(int inputs, int units){
        this.inputs = inputs;
        this.units = units;
        this.matrix = new double[inputs][units];

    }

    public int getInputs(){ return this.inputs;}
    public int getunits(){ return this.units;}
    public double[][] getMatrix(){ return this.matrix;}




}



//class Matrix{
//
//	private final int inputs;
//	private final int units;
//	private final double[][] data;
//
//	public Matrix(int inputs, int units){
//		this.inputs = inputs;
//		this.units = units;
//		this.data = new double[inputs][units];
//
//	}
//
//	private void init_random_values(){
//		for (int i = 0; i < inputs ; i++) {
//			for (int j = 0; j < units ; j++) {
//				data[i][j] = Math.random();
//			}
//		}
//	}
//
//	public double[] matrixMultiplication(double[][] matrixB){
//		Matrix matrixA = this;
//
//		int matrixA_rows = matrixA.inputs;
//		int matrixA_cols = matrixA.units;
//
//		// TODO make a Matrix class?
//		int matrixB_rows = matrixB.length;
//		int matrixB_cols = matrixB[0].length;
//
//
//
//		double[] result = new double[matrixA_rows];
//
//
//		if (matrixA_cols != matrixB_rows) {
//			System.out.println("error wrong dimensions of the matrix");
//		} else {
//
//			for (int i = 0; i < matrixA_rows; i++) {
//				for (int j = 0; j < matrixB_cols; j++) {
//					for (int k = 0; k < matrixA_cols; k++) {
//						result[i] += matrixA.data[i][k] * matrixB[k][j];
//
//					}
//				}
//
//			}
//
//
//		}
//
//		return result;
//
//
//	}
//}
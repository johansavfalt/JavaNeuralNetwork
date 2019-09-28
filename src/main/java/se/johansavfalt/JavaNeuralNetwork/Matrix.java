package se.johansavfalt.JavaNeuralNetwork;

import java.util.function.Function;

final public class Matrix {
    private final int M;             // number of rows
    private final int N;             // number of columns
    public final double[][] data;   // M-by-N array

    public int getRows() {
        return M;
    }

    public int getColumns() {
        return N;
    }

    public double[][] getData() {
        return data;
    }

    public void setData(int m, int n, double value){
        this.data[m][n] = value;
    }

    // create M-by-N matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    // create matrix based on 2d array
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                this.data[i][j] = data[i][j];
    }

    // copy constructor
    private Matrix(Matrix A) { this(A.data); }



    // create and return a random M-by-N matrix with values between 0 and 1
    public static Matrix random(int M, int N) {
        Matrix A = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random();
        return A;
    }

    // create and return the N-by-N identity matrix
    public static Matrix identity(int N) {
        Matrix I = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    // swap rows i and j
    private void swap(int i, int j) {
        double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // create and return the transpose of the invoking matrix
    public Matrix transpose() {
        Matrix A = new Matrix(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    public void fillwith(int i){
        for (int j = 0; j < M; j++) {
            for (int k = 0; k < N; k++) {
                this.data[j][k] = i;
            }
        }
    }

    // return C = A + B
    public Matrix plus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) {
            B = getAdjustedMatrix(B, A.N);
        }
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }

    private Matrix getAdjustedMatrix(Matrix B,int n) {
        Matrix result = new Matrix(this.M, n);
        for (int i = 0; i < this.M; i++) {
            for (int j = 0; j < n; j++) {
                result.data[i][j] = B.data[0][j];
            }
        }
        return result;
    }

    // return C = A - B
    public Matrix minus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    public Matrix minusConstant(double constant){
        Matrix A = this;
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] - constant;
        return C;
    }

    // does A = B exactly?
    public boolean eq(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.data[i][j] != B.data[i][j]) return false;
        return true;
    }

    // return x^T y
//    public Matrix dot(Matrix B) {
//        Matrix A = this;
//        if (A.M != B.M) throw new RuntimeException("Illegal vector dimensions.");
//        double sum = 0.0;
//        for (int i = 0; i < A.M; i++)
//            sum += A.data[i] * B.data[i];
//        return sum;
//    }



    // return C = A * B
    public Matrix times(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;


    }


    private Matrix scalarmultiplication(Matrix B) {
        Matrix C = new Matrix(this.M, this.N);
        for (int i = 0; i < this.M; i++) {
            for (int j = 0; j < this.N; j++) {
                C.data[i][j] = this.data[i][j] * B.data[0][0];
            }
        }
        return C;
    }


    public Matrix applyFunction(Function<Double,Double> func){
        Matrix A = this;
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = func.apply(A.data[i][j]);
        return C;


    }


    // return x = A^-1 b, assuming A is square and has full rank
    public Matrix solve(Matrix rhs) {
        if (M != N || rhs.M != N || rhs.N != 1)
            throw new RuntimeException("Illegal matrix dimensions.");

        // create copies of the data
        Matrix A = new Matrix(this);
        Matrix b = new Matrix(rhs);

        // Gaussian elimination with partial pivoting
        for (int i = 0; i < N; i++) {

            // find pivot row and swap
            int max = i;
            for (int j = i + 1; j < N; j++)
                if (Math.abs(A.data[j][i]) > Math.abs(A.data[max][i]))
                    max = j;
            A.swap(i, max);
            b.swap(i, max);

            // singular
            if (A.data[i][i] == 0.0) throw new RuntimeException("Matrix is singular.");

            // pivot within b
            for (int j = i + 1; j < N; j++)
                b.data[j][0] -= b.data[i][0] * A.data[j][i] / A.data[i][i];

            // pivot within A
            for (int j = i + 1; j < N; j++) {
                double m = A.data[j][i] / A.data[i][i];
                for (int k = i+1; k < N; k++) {
                    A.data[j][k] -= A.data[i][k] * m;
                }
                A.data[j][i] = 0.0;
            }
        }

        // back substitution
        Matrix x = new Matrix(N, 1);
        for (int j = N - 1; j >= 0; j--) {
            double t = 0.0;
            for (int k = j + 1; k < N; k++)
                t += A.data[j][k] * x.data[k][0];
            x.data[j][0] = (b.data[j][0] - t) / A.data[j][j];
        }
        return x;

    }

    // print matrix to standard output
    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf("%9.4f", data[i][j]);
            System.out.println();
            
        }
        System.out.println();
        System.out.println("M: "+this.M+" N: "+this.N);
        System.out.println("---------------------------------------");
    }



    // test client
    public static void main(String[] args) {
//        double[][] d = { { 1, 2, 3 }, { 4, 5, 6 }, { 9, 1, 3} };

        double[][] da = { { 0, 1 }, { 1, 0 }, { 0, 0 }, { 1, 1 }};
        double[][] db = { { 5, 3 }, { 4, 2 }};
        double[][] dc = {{1},{1},{1},{1}};


        Matrix x = new Matrix(da);
        Matrix w = new Matrix(db);
        Matrix b = new Matrix(dc);



        x.show();
        w.show();
        x.times(w).show();

        b.show();
        x.times(w).plus(b).show();
        x.times(w).plus(b).transpose().show();


//        Matrix D = new Matrix(d);
//        D.show();
//
//
//        Matrix A = Matrix.random(5, 5);
//        A.show();
//
//
//        A.swap(1, 2);
//        A.show();
//
//
//        Matrix B = A.transpose();
//        B.show();
//
//
//        Matrix C = Matrix.identity(5);
//        C.show();
//
//
//        A.plus(B).show();
//
//
//        B.times(A).show();
//
//
//        // shouldn't be equal since AB != BA in general
//        System.out.println((A.times(B).eq(B.times(A))));
//
//
//        Matrix b = Matrix.random(5, 1);
//        b.show();
//
//
//        Matrix x = A.solve(b);
//        x.show();
//
//
//        A.times(x).show();

    }

    public Matrix sum() {
        double[][] result = {{0.0}};
        for (int i = 0; i < this.M; i++) {
            for (int j = 0; j < this.N; j++) {
                result[0][0] += this.data[i][j];
            }
        }
        return new Matrix(result);
    }

    public Matrix hadamanproduct(Matrix B) {
        if (this.M != B.M & this.N != B.N) throw new RuntimeException("Illegal matrix dimensions");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < this.M; i++) {
            for (int j = 0; j < this.N; j++) {
                C.data[i][j] = this.data[i][j] * B.data[i][j];
            }
        }

        return C;
    }

    public Matrix product(Matrix B) {
        if (this.M != B.M & this.N != B.N) throw new RuntimeException("Illegal matrix dimensions");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < this.M; i++) {
            for (int j = 0; j < this.N; j++) {
                C.data[i][j] = this.data[i][j] + B.data[i][j];
            }
        }

        return C;
    }


    public Matrix timesConstant(double constant) {
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < this.M; i++) {
            for (int j = 0; j < this.N; j++) {
                C.data[i][j] = this.data[i][j] * constant;
            }
        }

        return C;
    }
}

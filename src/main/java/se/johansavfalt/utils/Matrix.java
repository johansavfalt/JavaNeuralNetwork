package se.johansavfalt.utils;

/**
 * Class for handling all the matrix operations. generally all methods takes a matrix as parameter and returns
 * a matrix as a value. the class is nothing more than a wrapper around a double[][] data structure.
 */
final public class Matrix {
    private final int M;
    private final int N;
    public final double[][] data;

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

    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }

    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                this.data[i][j] = data[i][j];
    }



    public static Matrix random(int M, int N) {
        Matrix A = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random();
        return A;
    }

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

    public void fillwith(double i){
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


    public Matrix times(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) throw new RuntimeException("Illegal matrix dimensions.");

        Matrix C = new Matrix(A.M, B.N);
        for (int i = 0; i < A.M; i++)
            for (int j = 0; j < B.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;


    }


    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++)
                System.out.printf("%9.4f", data[i][j]);
            System.out.println();
            
        }
        System.out.println();
        System.out.println("M: "+this.M+" N: "+this.N);
        System.out.println("------------------------------------------");
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



    public Matrix max() {
        double[][] result = {{0.0}};
        for (int i = 0; i < this.M; i++) {
            for (int j = 0; j < this.N; j++) {
                if(i == 0){
                    result[0][0] = this.data[i][j];
                } else if (result[0][0] < this.data[i][j]){
                    result[0][0] = this.data[i][j];
                }
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

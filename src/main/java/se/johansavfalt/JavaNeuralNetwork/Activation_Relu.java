package se.johansavfalt.JavaNeuralNetwork;

import java.util.function.Function;

/**
 * Relu Activation class, implements Activationfunction interface
 */
public class Activation_Relu implements ActivationFunction {
    /**
     * Takes Matrix class and applies Relu on all elements
     * @param Z_Matrix
     * @return Matrix
     */
    @Override
    public Matrix activation(Matrix Z_Matrix) {
        return applyFunction(this::activate, Z_Matrix);
    }

    /**
     * Takes Matrix class and applies Relu derivative on all elements
     * @param Z_Matrix
     * @return Matrix
     */
    @Override
    public Matrix activation_derivative(Matrix Z_Matrix) {
        return applyFunction(this::activate_derivative,  Z_Matrix);
    }

    /**
     * Activation
     * @param x
     * @return
     */
    public double activate(double x) {
        return Math.max(x,0.0);
    }

    /**
     * Activation Derivative
     * @param x
     * @return
     */
    public double activate_derivative(double x) {
        if (x > 0.0){
            return 1.0;
        } else {
            return 0.0;
        }
    }

    /**
     * Applies funciton to all elements of a matrix
     * @param func
     * @param Z_Matrix
     * @return
     */
    public Matrix applyFunction(Function<Double,Double> func, Matrix Z_Matrix){
        int M = Z_Matrix.getRows();
        int N = Z_Matrix.getColumns();
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = func.apply(Z_Matrix.data[i][j]);
        return C;


    }

}


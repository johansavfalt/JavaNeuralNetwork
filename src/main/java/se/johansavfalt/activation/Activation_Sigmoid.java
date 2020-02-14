package se.johansavfalt.activation;

import java.util.function.Function;
import se.johansavfalt.utils.Matrix;

/**
 * Sigmoid activation class, implements Activationfunction interface
 */
public class Activation_Sigmoid implements ActivationFunction  {

    /**
     * takes matrix and applies activation function to all its elements
     * @param Z_Matrix
     * @return
     */
    @Override
    public Matrix activation(Matrix Z_Matrix) {
        return applyFunction(this::activate, Z_Matrix);
    }

    /**
     * takes matrix and applies activation derivatie to all its elements
     * @param Z_Matrix
     * @return
     */
    @Override
    public Matrix activation_derivative(Matrix Z_Matrix) {
        return applyFunction(this::activate_derivative,  Z_Matrix);
    }

    /**
     * activation value
     * @param doubleValue
     * @return
     */
    public double activate(double doubleValue){
        return 1 / (1 + Math.pow(Math.E, (-1 * doubleValue)));
    }

    /**
     * activation derivative
     * @param doubleValue
     * @return
     */
    public double activate_derivative(double doubleValue){
        double sigVal = activate(doubleValue);
        return sigVal * (1.0 - sigVal);

    }

    /**
     * Apply function to all the element in a matrix
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


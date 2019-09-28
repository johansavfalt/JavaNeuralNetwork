package se.johansavfalt.JavaNeuralNetwork;

import java.util.function.Function;

public class Activation_Sigmoid implements ActivationFunction  { //TODO this should implement the activationfunction interface


    @Override
    public Matrix activation(Matrix Z_Matrix) {
        return applyFunction(this::activate, Z_Matrix);
    }

    @Override
    public Matrix activation_derivative(Matrix Z_Matrix) {
        return applyFunction(this::activate_derivative,  Z_Matrix);
    }



    public double activate(double doubleValue){
        return 1 / (1 + Math.pow(Math.E, (-1 * doubleValue)));
    }

    public double activate_derivative(double doubleValue){
        double sigVal = activate(doubleValue);
        return sigVal * (1.0 - sigVal);

    }


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


package se.johansavfalt.JavaNeuralNetwork;

public class Activation_Softmax implements ActivationFunction {

    @Override
    public Matrix activation(Matrix Z_Matrix) {
        double max = Z_Matrix.max().getData()[0][0];
        Matrix exponentMatrix = Z_Matrix.minusConstant(max);
        double exponentSumMatrix = exponentMatrix.sum().getData()[0][0];
        Matrix result = new Matrix(Z_Matrix.getRows(), Z_Matrix.getColumns());
        for (int i = 0; i < result.getRows() ; i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                result.data[i][j] += exponentMatrix.data[i][j] / exponentSumMatrix;
            }
        }

        return result;
    }

    @Override
    public Matrix activation_derivative(Matrix Z_Matrix) {
        return null;
    }

    public double exponantiate(double doubleValue){
        return Math.exp(doubleValue);
    }
}

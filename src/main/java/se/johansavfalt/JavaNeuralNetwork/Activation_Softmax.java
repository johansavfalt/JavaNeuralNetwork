package se.johansavfalt.JavaNeuralNetwork;

public class Activation_Softmax implements ActivationFunction {

    @Override
    public Matrix activation(Matrix Z_Matrix) {
        Matrix exponentMatrix = Z_Matrix.applyFunction(this::exponantiate);
        Matrix result = new Matrix(Z_Matrix.getRows(), Z_Matrix.getColumns());
        double exponentSum = 0.0;
        for (int i = 0; i < exponentMatrix.getRows() ; i++) {
            for (int j = 0; j < exponentMatrix.getColumns(); j++) {
                exponentSum += exponentMatrix.data[i][j];
            }
        }

        for (int i = 0; i < result.getRows() ; i++) {
            for (int j = 0; j < result.getColumns(); j++) {
                result.data[i][j] += exponentMatrix.data[i][j] / exponentSum;
            }
        }
        // http://saitcelebi.com/tut/output/part2.html

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

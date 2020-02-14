package se.johansavfalt.activation;

import se.johansavfalt.utils.Matrix;

/**
 * Activation softmax implements the activationfunction interface
 */
public class Activation_Softmax implements ActivationFunction {
    /**
     * does activation softmax on all the elements of the vector / Matrix
     * @param Z_Matrix
     * @return
     */
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

    /**
     * does activation derivative of all the elements of the matrix
     * @param Z_Matrix
     * @return
     */
    @Override
    public Matrix activation_derivative(Matrix Z_Matrix) {
        Matrix result = new Matrix(Z_Matrix.getRows(), Z_Matrix.getColumns());

        for (int i = 0; i < Z_Matrix.getRows(); i++) {
            for (int j = 0; j < Z_Matrix.getColumns(); j++) {

                double propabilityI = Z_Matrix.data[i][j];

                for (int k = 0; k < Z_Matrix.getRows(); k++) {
                    for (int l = 0; l < Z_Matrix.getColumns(); l++) {
                        if(i==k && j==l){
                            result.getData()[k][l] = propabilityI * (1.0-propabilityI);
                        } else{
                            result.getData()[k][l] = (propabilityI * -1.0) * propabilityI;
                        }
                    }
                }
            }
        }

        return result;

    }

}

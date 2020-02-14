package se.johansavfalt.activation;

import se.johansavfalt.utils.Matrix;

/**
 * ActivationFunction interface, in order for the layers the easily switch between different activation functions
 * this abstraction was created.
 */
public interface ActivationFunction {

    public Matrix activation(Matrix Z_Matrix);

    public Matrix activation_derivative(Matrix Z_MAtrix);

}

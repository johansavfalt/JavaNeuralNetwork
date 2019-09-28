package se.johansavfalt.JavaNeuralNetwork;

public interface ActivationFunction {

    public Matrix activation(Matrix Z_Matrix);

    public Matrix activation_derivative(Matrix Z_MAtrix);

}

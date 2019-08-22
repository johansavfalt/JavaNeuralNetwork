package se.johansavfalt.JavaNeuralNetwork;

public interface ActivationFunction {

    public Matrix activation(Matrix x);

    public Matrix activation_derivative(Matrix x);

}

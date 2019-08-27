package se.johansavfalt.JavaNeuralNetwork;

public interface ActivationFunction {

    public double activation(double doubleValue);

    public double activation_derivative(double doubleValue);

}

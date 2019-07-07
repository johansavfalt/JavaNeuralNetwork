package se.johansavfalt.JavaNeuralNetwork;

public interface ActivationFunction {

    public double[][] activation(double[][] x);

    public double[][] activation_derivative(double[][] x);

}

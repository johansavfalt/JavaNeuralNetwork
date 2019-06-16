package se.johansavfalt.JavaNeuralNetwork;

public class Activation_Sigmoid implements ActivationFunction {


    @Override
    public double activation(double x){
        return (1 / (1 + Math.pow(Math.E, (-1 * x))));

    }

    @Override
    public double activation_derivative(double x){
        return activation(x) * (1 - activation(x));
    }


}

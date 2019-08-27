package se.johansavfalt.JavaNeuralNetwork;

public class Activation_Relu implements ActivationFunction {


    public double activation(double x) {
        return Math.max(x,0.0);
    }


    public double activation_derivative(double x) {
        if (x > 0.0){
            return 1.0;
        } else {
            return 0.0;
        }
    }

}


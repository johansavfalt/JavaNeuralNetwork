package se.johansavfalt.JavaNeuralNetwork;

public class Activation_Sigmoid implements ActivationFunction  { //TODO this should implement the activationfunction interface

    public double activation(double doubleValue){
        return 1 / (1 + Math.pow(Math.E, (-1 * doubleValue)));
    }

    public double activation_derivative(double doubleValue){
        double sigVal = activation(doubleValue);
        return sigVal * (1.0 - sigVal);

        //return Act * (1.0 - Act);

    }

    public static void main(String[] args) {
        Activation_Sigmoid cl1 = new Activation_Sigmoid();
//        double[][] double_ = {{1.0, 1.0}, {4.0, 5.0}, {4.0, 7.0}};
//        Matrix input = new Matrix(double_);
//        cl1.activation_derivative(input);
    }



}


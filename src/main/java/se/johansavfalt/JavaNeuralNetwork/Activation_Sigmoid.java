package se.johansavfalt.JavaNeuralNetwork;

import java.util.Arrays;

public class Activation_Sigmoid implements ActivationFunction  { //TODO this should implement the activationfunction interface
    
    public Matrix activation(Matrix x){
        x.applyFunction((Double doubleValue)->{
            return sigmoid(doubleValue);
        });
        return x;

    }

    public double sigmoid(Double doubleValue){
        return 1 / (1 + Math.pow(Math.E, (-1 * doubleValue)));
    }

    public Matrix activation_derivative(Matrix x){
        x.applyFunction((Double doubleValue) ->{
            double sigVal = sigmoid(doubleValue);
            return sigVal * (1.0 - sigVal);

        });


        //TODO : should be able to do this on double[][] ??
        //return Act * (1.0 - Act);
        return null;
    }

    public static void main(String[] args) {
        Activation_Sigmoid cl1 = new Activation_Sigmoid();
        double[][] double_ = {{1.0, 1.0}, {4.0, 5.0}, {4.0, 7.0}};
        Matrix input = new Matrix(double_);
        cl1.activation_derivative(input);
    }



}


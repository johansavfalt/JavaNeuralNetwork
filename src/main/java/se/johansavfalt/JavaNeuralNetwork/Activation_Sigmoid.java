package se.johansavfalt.JavaNeuralNetwork;

import java.util.Arrays;

public class Activation_Sigmoid implements ActivationFunction {
    
    public double[][] activation(double[][] x){
        double[][] result = null;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                result[i][j] = (1 / (1 + Math.pow(Math.E, (-1 * x[i][j]))));
            }
        }
        return result;

    }

    public double[][] activation_derivative(double[][] z){
        double[][] Act = activation(z);


        //TODO : should be able to do this on double[][] ??
        //return Act * (1.0 - Act);
        return null;
    }

    public static void main(String[] args) {
        Activation_Sigmoid cl1 = new Activation_Sigmoid();
        double[][] input = {{1.0, 1.0}, {4.0, 5.0}, {4.0, 7.0}};
        cl1.activation_derivative(input);
    }



}


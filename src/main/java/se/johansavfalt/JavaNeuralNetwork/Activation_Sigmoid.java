package se.johansavfalt.JavaNeuralNetwork;

import java.util.Arrays;

public class Activation_Sigmoid implements ActivationFunction  { //TODO this should implement the activationfunction interface
    
    public Matrix activation(Matrix x){
        Matrix result = new Matrix(x.getRows(), x.getColumns());
        for (int i = 0; i < x.getRows(); i++) {
            for (int j = 0; j < x.getColumns(); j++) {
                result.setData(i, j, (1 / (1 + Math.pow(Math.E, (-1 * x.getData()[i][j])))));
            }
        }
        return result;

    }

    public Matrix activation_derivative(Matrix x){
        Matrix Act = activation(x);
        //TODO does this work? times does not work
        Act.times(Act.minusConstant(1.0));

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


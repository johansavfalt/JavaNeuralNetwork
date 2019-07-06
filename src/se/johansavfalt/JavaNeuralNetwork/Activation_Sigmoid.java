package se.johansavfalt.JavaNeuralNetwork;

public class Activation_Sigmoid implements ActivationFunction {
    
    @Override
    public double[][] activation(double[][] x){
        double[][] result = null;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                result[i][j] = (1 / (1 + Math.pow(Math.E, (-1 * x[i][j]))));
            }
        }
        return result;

    }

    @Override
    public double[][] activation_derivative(double[][] x){
        double[][] Act = activation(x);
        //TODO : should be able to do this on double[][] ??
        //return Act * (1.0 - Act);
        return null;
    }

}


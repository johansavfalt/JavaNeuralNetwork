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

    public double[][] activation(double[][] x) {
        double[][] result = null;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                result[i][j] = activation(x[i][j]);
            }
        }
        return result;
    }

    public double[][] activation_derivative(double[][] x) {
        double[][] result = null;
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[i].length; j++) {
                result[i][j] = activation_derivative(x[i][j]);
            }
        }
        return result;
    }
}

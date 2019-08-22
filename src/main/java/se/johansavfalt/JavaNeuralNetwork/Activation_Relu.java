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

    public Matrix activation(Matrix x){
        Matrix result = new Matrix(x.getRows(), x.getColumns());
        for (int i = 0; i < x.getRows(); i++) {
            for (int j = 0; j < x.getColumns(); j++) {
                result.getData()[i][j] = activation(x.getData()[i][j]);
            }
        }
        return result;

    }



    public Matrix activation_derivative(Matrix x){
        Matrix result = new Matrix(x.getRows(), x.getColumns());
        for (int i = 0; i < x.getRows(); i++) {
            for (int j = 0; j < x.getColumns(); j++) {
                result.getData()[i][j] = activation(x.getData()[i][j]);
            }
        }
        return result;

    }

}

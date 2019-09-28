package se.johansavfalt.JavaNeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Training {


    private ArrayList<NeuralLayer> NeuralNetwork;
    private Matrix data;
    private double learningRate;
    private int epochs;
    private Matrix deltaLoss;
    private Matrix Y ;


    public Training(ArrayList<NeuralLayer> NeuralNetwork, Matrix data, double learningRate, int epochs, Matrix Y){
        this.NeuralNetwork = NeuralNetwork;
        this.data = data;
        this.learningRate = learningRate;
        this.epochs = epochs;
        this.Y = Y;

    }

    public void train(){
        for (int i = 0; i < epochs - 1; i++) {
            Matrix forwardPass = this.forwardPopagation(data);
            deltaLoss = compute_cross_entropy_loss(forwardPass, this.Y, true);
            this.backwardPropagation(deltaLoss);
            this.updateParameters();

            if(i % 1 == 0){
                this.forwardPopagation(data);
                System.out.println(i);
                this.compute_cross_entropy_loss(this.forwardPopagation(data),this.Y, false).show();
            }

        }
    }

    private void updateParameters() {
        for (NeuralLayer layer : NeuralNetwork) {
            layer.updateParameters(this.learningRate);
        }
    }

    private Matrix backwardPropagation(Matrix deltaLoss) {

        for (int i = NeuralNetwork.size()-1; i >=0 ; i--) {
            Matrix layerInput = deltaLoss;
            deltaLoss = NeuralNetwork.get(i).layer_backward_propagation(layerInput);

        }
        return deltaLoss;
    }

    private Matrix forwardPopagation(Matrix data) {
        for (NeuralLayer layer : NeuralNetwork) {
            Matrix layerInput = data;
            data = layer.layer_forward_propagation(layerInput);
        }
        return data;
    }


    private static Matrix compute_cross_entropy_loss(Matrix data, Matrix test, boolean derivative) {
        if (derivative){
            return data.minus(test);
        } else {
            return cross_entropy_loss(data, test);
        }

    }

    private static Matrix cross_entropy_loss(Matrix predictDistribution, Matrix trueDistribution) {
        double error = 0.0;
        int M = predictDistribution.getRows();
        List<Double> batchResult = new ArrayList<>();


        for (int i = 0; i < M; i++) {
            double singleTrue = trueDistribution.getData()[i][0];
            double singlePred = predictDistribution.getData()[i][0];

            if (singleTrue == 1){
                error = Math.log(singlePred)* -1.0;
            } else {
                error =  Math.log(1.0 - singlePred) * -1.0;
            }
            batchResult.add(error);


        }
        Matrix result = new Matrix(1,1);
        double doublevalue = (1.0/M * batchResult.stream().mapToDouble(f -> f.doubleValue()).sum());
        result.setData(0,0, doublevalue);
        return result;
    }

    public static void main(String[] args){

        ArrayList<NeuralLayer> NeuralNetwork = new ArrayList<NeuralLayer>();
        NeuralNetwork.add(new NeuralLayer(2,4, new Activation_Relu()));
        NeuralNetwork.add(new NeuralLayer(4,4, new Activation_Relu()));
        NeuralNetwork.add(new NeuralLayer(4,2, new Activation_Relu()));
        NeuralNetwork.add(new NeuralLayer(2,1, new Activation_Sigmoid()));


        Matrix data = new Matrix(new double[][]{{0, 1}, {1, 0}, {0, 0}, {1, 1}});
        Matrix test = new Matrix(new double[][]{{1}, {1}, {0}, {0}});
        double learningRate = 0.01;
        int epochs = 50;


        Training training = new Training(NeuralNetwork, data, learningRate, epochs, test);
        training.train();




    }

    public void oldMain(){
        List<NeuralLayer> NeuralNetwork = new ArrayList<NeuralLayer>();
        NeuralNetwork.add(new NeuralLayer(2,4, new Activation_Relu()));
        NeuralNetwork.add(new NeuralLayer(4,4, new Activation_Relu()));
        NeuralNetwork.add(new NeuralLayer(4,2, new Activation_Relu()));
        NeuralNetwork.add(new NeuralLayer(2,1, new Activation_Sigmoid()));


        Matrix data = new Matrix(new double[][]{{0, 1}, {1, 0}, {0, 0}, {1, 1}});
        Matrix test = new Matrix(new double[][]{{1}, {1}, {0}, {0}});

        for (NeuralLayer layer : NeuralNetwork) {
            Matrix layerInput = data;
            data = layer.layer_forward_propagation(layerInput);
        }

        Matrix deltaLoss = compute_cross_entropy_loss(data, test, true);


        for (int i = NeuralNetwork.size()-1; i >=0 ; i--) {
            Matrix layerInput = deltaLoss;
            deltaLoss = NeuralNetwork.get(i).layer_backward_propagation(layerInput);

        }

        for (NeuralLayer layer :
                NeuralNetwork) {
            layer.updateParameters(0.01);
        }
    }

}

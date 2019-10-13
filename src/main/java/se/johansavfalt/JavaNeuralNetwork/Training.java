package se.johansavfalt.JavaNeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Training {


    private final Data trainingData;
    private ArrayList<NeuralLayer> NeuralNetwork;
    private Matrix data;
    private double learningRate;
    private int epochs;
    private Matrix deltaLoss;
    private Matrix Y ;


    public Training(ArrayList<NeuralLayer> NeuralNetwork, Matrix data, double learningRate, int epochs, Matrix Y,
    Data trainingData){
        this.NeuralNetwork = NeuralNetwork;
        this.data = data;
        this.learningRate = learningRate;
        this.epochs = epochs;
        this.Y = Y;
        this.trainingData = trainingData;

    }

    public void train(){
        for (int i = 0; i < epochs - 1; i++) {
            this.trainingData.reshuffledata();
            Matrix forwardPass = this.forwardPopagation(this.trainingData.getTestdata());
            deltaLoss = compute_cross_entropy_loss(forwardPass, this.trainingData.getTest(), true);
            this.backwardPropagation(deltaLoss);
            this.updateParameters();

            if(i % 10000 == 0){
                System.out.println(i);
                compute_cross_entropy_loss(this.forwardPopagation(this.trainingData.getTestdata()),this.trainingData.getTest()
                        , false).show();
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


}

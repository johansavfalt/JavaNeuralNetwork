package se.johansavfalt;


import java.util.ArrayList;
import java.util.List;

import se.johansavfalt.activation.Activation_Relu;
import se.johansavfalt.activation.Activation_Sigmoid;
import se.johansavfalt.utils.Matrix;
import se.johansavfalt.utils.Data;

public class Prediction {

    public static List<Matrix> getTrainihgData(){
        List<Matrix> listData = new ArrayList<>();
        listData.add(new Matrix(new double[][]{{0, 1}}));
        listData.add(new Matrix(new double[][]{{1, 0}}));
        listData.add(new Matrix(new double[][]{{0, 0}}));
        listData.add(new Matrix(new double[][]{{1, 1}}));
        return listData;

    }

    public static List<Matrix> getTestData(){
        List<Matrix> listDataTest = new ArrayList<>();
        listDataTest.add(new Matrix(new double[][]{{1}}));
        listDataTest.add(new Matrix(new double[][]{{1}}));
        listDataTest.add(new Matrix(new double[][]{{0}}));
        listDataTest.add(new Matrix(new double[][]{{0}}));
        return listDataTest;

    }
    public static void main(String[] args) {

        // create the trainingdata
        Data trainingData = new Data(Prediction.getTrainihgData(),Prediction.getTestData());


        // Create NeuralNetwork structure, basically neurallayer object in a ArrayList
        ArrayList<NeuralLayer> NeuralNetwork = new ArrayList<NeuralLayer>();
        NeuralNetwork.add(new NeuralLayer(2,4, new Activation_Relu()));
        NeuralNetwork.add(new NeuralLayer(4,4, new Activation_Relu()));
        NeuralNetwork.add(new NeuralLayer(4,4, new Activation_Relu()));
        NeuralNetwork.add(new NeuralLayer(4,1, new Activation_Sigmoid()));


        // Hyperparameters
        double learningRate = 0.01;
        int epochs = 30;
        int printResult = 1;

        // Create TrainingObject
        Training training = new Training(NeuralNetwork, learningRate, epochs, trainingData, printResult);

        // train model
        training.train();

    }

}

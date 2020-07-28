package se.johansavfalt;


import java.util.ArrayList;
import java.util.List;
import se.johansavfalt.activation.Activation_Sigmoid;
import se.johansavfalt.utils.Matrix;
import se.johansavfalt.utils.Data;

public class Prediction {

    public static void main(String[] args) {

        //TODO: need to try this out
        NeuralNetwork neuralNetwork = new NeuralNetwork(4, 2, 1, 2, 4,
                new Activation_Sigmoid(), new Activation_Sigmoid());

        // Create NeuralNetwork structure, basically neurallayer object in a ArrayList
        ArrayList<NeuralLayer> NeuralNetwork = new ArrayList<NeuralLayer>();
        NeuralNetwork.add(new NeuralLayer(2,4, new Activation_Sigmoid()));
        NeuralNetwork.add(new NeuralLayer(4,4, new Activation_Sigmoid()));
        NeuralNetwork.add(new NeuralLayer(4,4, new Activation_Sigmoid()));
        NeuralNetwork.add(new NeuralLayer(4,1, new Activation_Sigmoid()));

        // Set data, "data" trainingdata and "test" is testdata
//        Matrix data = new Matrix(new double[][]{{0, 1}, {1, 0}, {0, 0}, {1, 1}});
//        Matrix test = new Matrix(new double[][]{{1}, {1}, {0}, {0}});

        List<Matrix> listData = new ArrayList<>();
        List<Matrix> listDataTest = new ArrayList<>();
        listData.add(new Matrix(new double[][]{{0, 1}}));
        listData.add(new Matrix(new double[][]{{1, 0}}));
        listData.add(new Matrix(new double[][]{{0, 0}}));
        listData.add(new Matrix(new double[][]{{1, 1}}));

        listDataTest.add(new Matrix(new double[][]{{1}}));
        listDataTest.add(new Matrix(new double[][]{{1}}));
        listDataTest.add(new Matrix(new double[][]{{0}}));
        listDataTest.add(new Matrix(new double[][]{{0}}));




        Data trainingData = new Data(listData, listDataTest);

        // Hyperparameters
        double learningRate = 0.0001;
        int epochs = 100000;
        int printResult = 1;

        // Create TrainingObject
        Training training = new Training(NeuralNetwork, learningRate, epochs, trainingData, printResult);

        // train model
        training.train();

    }

}
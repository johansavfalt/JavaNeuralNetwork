package se.johansavfalt.JavaNeuralNetwork;


import java.util.ArrayList;

public class Prediction {

    public static void main(String[] args) {

        // Create NeuralNetwork structure, basically neurallayer object in a ArrayList
        ArrayList<NeuralLayer> NeuralNetwork = new ArrayList<NeuralLayer>();
        NeuralNetwork.add(new NeuralLayer(2,4, new Activation_Sigmoid()));
        NeuralNetwork.add(new NeuralLayer(4,4, new Activation_Sigmoid()));
        NeuralNetwork.add(new NeuralLayer(4,2, new Activation_Sigmoid()));
        NeuralNetwork.add(new NeuralLayer(2,1, new Activation_Sigmoid()));

        // Set data, "data" trainingdata and "test" is testdata
        Matrix data = new Matrix(new double[][]{{0, 1}, {1, 0}, {0, 0}, {1, 1}});
        Matrix test = new Matrix(new double[][]{{1}, {1}, {0}, {0}});
        Data trainingData = new Data(data, test);

        // Hyperparameters
        double learningRate = 0.001;
        int epochs = 50000;

        // Create TrainingObject
        Training training = new Training(NeuralNetwork, data, learningRate, epochs, test, trainingData);

        // train model
        training.train();

    }

}

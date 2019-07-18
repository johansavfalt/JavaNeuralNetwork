package se.johansavfalt.JavaNeuralNetwork;

import java.util.ArrayList;
import java.util.List;

public class Prediction {

    public static void main(String[] args){

        List<NeuralLayer> NeuralNetwork = new ArrayList<NeuralLayer>();
        NeuralNetwork.add(new NeuralLayer(2,4, new Activation_Relu()));
        NeuralNetwork.add(new NeuralLayer(4,4, new Activation_Relu()));
        NeuralNetwork.add(new NeuralLayer(4,2, new Activation_Relu()));
        NeuralNetwork.add(new NeuralLayer(2,4, new Activation_Relu()));


    }
}

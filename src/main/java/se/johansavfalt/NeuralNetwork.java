package se.johansavfalt;

import java.util.ArrayList;
import java.util.stream.IntStream;
import se.johansavfalt.activation.ActivationFunction;


public class NeuralNetwork{

    int hiddenLayers;
    int nodesInHiddenLayers;
    int layers;
    int inputNodes;
    int outPutNodes;
    ActivationFunction hiddenActivation;
    ActivationFunction outActivation;

    ArrayList<NeuralLayer> NeuralLayers;

    public NeuralNetwork(int layers,
            int inputNodes,
            int outPutNodes,
            int hiddenLayers,
            int nodesInHiddenLayers,
            ActivationFunction hiddenActivation,
            ActivationFunction outActivation){

        this.layers = layers;
        this.inputNodes = inputNodes;
        this.outPutNodes = outPutNodes;
        this.hiddenLayers = hiddenLayers;
        this.nodesInHiddenLayers = nodesInHiddenLayers;
        this.hiddenActivation = hiddenActivation;
        this.outActivation = outActivation;
    }

    public void createNetwork(){
        IntStream.range(0, hiddenLayers - 1).forEach(i ->{
            if(i < 1){
                addNeuralLayer(this.inputNodes,
                        this.nodesInHiddenLayers, this.hiddenActivation);
            } else if (i > 1 && i < layers-1){
                addNeuralLayer(this.nodesInHiddenLayers,
                       this.nodesInHiddenLayers, this.hiddenActivation);
            } else if (i > 1 && i < layers){
                addNeuralLayer(this.nodesInHiddenLayers,
                        this.outPutNodes, this.outActivation);
            }

        });

    }

    private void addNeuralLayer(int inputNodes, int outputNodes,
        ActivationFunction activation){
        this.NeuralLayers.add(new NeuralLayer(inputNodes, outputNodes, activation));

    }

}

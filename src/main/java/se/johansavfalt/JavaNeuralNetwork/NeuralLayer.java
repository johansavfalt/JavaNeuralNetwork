package se.johansavfalt.JavaNeuralNetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import se.johansavfalt.JavaNeuralNetwork.Activation_Sigmoid;
import se.johansavfalt.JavaNeuralNetwork.Activation_Relu;
import se.johansavfalt.JavaNeuralNetwork.Matrix;


public class NeuralLayer {

	private final int inputs;
	private final int units;
	private Matrix weights;
	private Matrix bias;
	private Matrix Activation_prev;
	private Matrix Activation_curr;
	private Matrix Z_curr;
	private ActivationFunction activation;


	public NeuralLayer(int inputs, int units, ActivationFunction activation){
		this.inputs = inputs;
		this.units = units;
		this.activation = activation;
		this.weights = Matrix.random(inputs, units);
		this.bias = new Matrix(1 , units);
		this.bias.fillwith(1);
	};


	private Matrix layer_forward_propagation(Matrix Activation_prev){
		this.Activation_prev = Activation_prev;
		this.Z_curr = this.Activation_prev.times(this.weights).plus(this.bias);
		this.Activation_curr = this.Z_curr.applyFunction(activation::activation);
		return this.Activation_curr;

	}
	
	
	
	public static void main(String[] args) {

		List<NeuralLayer> NeuralNetwork = new ArrayList<NeuralLayer>();
		NeuralNetwork.add(new NeuralLayer(2,4, new Activation_Relu()));
		NeuralNetwork.add(new NeuralLayer(4,4, new Activation_Relu()));
		NeuralNetwork.add(new NeuralLayer(4,2, new Activation_Relu()));
		NeuralNetwork.add(new NeuralLayer(2,1, new Activation_Sigmoid()));


		Matrix data = new Matrix(new double[][]{{0, 1}, {1, 0}, {0, 0}, {1, 1}});


		for (NeuralLayer layer : NeuralNetwork) {
			Matrix layerInput = data;
			Matrix layerOutput = layer.layer_forward_propagation(layerInput);
			data = layerOutput;
		}




		for (int i = NeuralNetwork.size()-1; i >=0 ; i--) {
			Matrix layerInput = data;
			Matrix layerOutput = NeuralNetwork.get(i).layer_forward_propagation(layerInput);
			data = layerOutput;

		}

		
	}

}

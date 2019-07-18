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
	private double[][] Activation_prev;
	private double[][] Activation_curr;
	private double[][] Z_curr;


	private ActivationFunction activation;


	public NeuralLayer(int inputs, int units, ActivationFunction activation){
		this.inputs = inputs;
		this.units = units;
		this.activation = activation;
		this.weights = new Matrix(this.inputs, this.units);
		this.bias = new Matrix(1, units);
	};


	private double[][] layer_forward_propagation(double[][] Activation_prev){
		this.Activation_prev = Activation_prev;
		// TODO should redo the matrixclass from https://causeyourestuck.io/2017/06/25/neural-network-scratch-practice/
		this.Z_curr = this.bias.add(this.weights.multiply(this.Activation_prev));
		this.Activation_curr = this.activation.activation(this.Z_curr);
		return this.Activation_curr;

	}
	
	public static void main(String[] args) {

		List<NeuralLayer> NeuralNetwork = new ArrayList<NeuralLayer>();
		NeuralNetwork.add(new NeuralLayer(2,4, new Activation_Relu()));
		NeuralNetwork.add(new NeuralLayer(4,4, new Activation_Relu()));
		NeuralNetwork.add(new NeuralLayer(4,2, new Activation_Sigmoid()));
		NeuralNetwork.add(new NeuralLayer(2,1, new Activation_Sigmoid()));

		double[][] data = {{1, 2}, {4, 5}};

		NeuralNetwork.get(0).layer_forward_propagation(data);


//		double[][] weights = {{1, 2, 3}, {4, 5, 6}};
//		double[][] input = {{1}, {4}, {4}};
//
//		double[] bias = {1, 1};

		
	}

}

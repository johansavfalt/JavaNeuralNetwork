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
	private double[][] weights;
	private double[][] Activation_prev;
	private double[][] Activation_curr;
	private double[][] Z_curr;
	private double[][] bias;

	private ActivationFunction activation;


	public NeuralLayer(int inputs, int units, ActivationFunction activation){
		this.inputs = inputs;
		this.units = units;
		this.activation = activation;
	};

	private void init_weight_matrix(){
		this.weights = Matrix.random(this.inputs,this.units);
	};

	private void init_bias_matrix(){
		this.bias = Matrix.random(1,this.units);

	};

	public void init_layer(){
		init_weight_matrix();
		init_bias_matrix();
	}

	private double[][] layer_forward_propagation(double[][] Activation_prev){
		this.Activation_prev = Activation_prev;
		this.Z_curr = Matrix.add(Matrix.multiply(this.weights, this.Activation_prev), this.bias);
		//TODO: this.activation_curr could be both double and double[][] use generic type?
		// probably have to box double into Double
		// maby should always be double[][]? with [[1.0]] for single double?
		this.Activation_curr = activation.activation(this.Z_curr);
		return this.Activation_curr;

	}
	
	public static void main(String[] args) {

		List<NeuralLayer> NeuralNetwork = new ArrayList<NeuralLayer>();
		NeuralNetwork.add(new NeuralLayer(2,4, new Activation_Relu()));
		NeuralNetwork.add(new NeuralLayer(4,4, new Activation_Relu()));
		NeuralNetwork.add(new NeuralLayer(4,2, new Activation_Sigmoid()));
		NeuralNetwork.add(new NeuralLayer(2,1, new Activation_Sigmoid()));

		for (NeuralLayer layer: NeuralNetwork) {
			layer.init_layer();

		}


		double[][] weights = {{1, 2, 3}, {4, 5, 6}};
		double[][] input = {{1}, {4}, {4}};
		
		double[] bias = {1, 1};

		
	}

}

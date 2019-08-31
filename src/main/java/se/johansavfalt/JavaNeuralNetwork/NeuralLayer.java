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
		this.bias = new Matrix(units,1 );
		this.bias.fillwith(1);
	};


	private Matrix layer_forward_propagation(Matrix Activation_prev){
		this.Activation_prev = Activation_prev;
		System.out.println("activationprev");
		this.Activation_prev.show();
		System.out.println("wights");
		this.weights.show();
		System.out.println("activationprev * weights");
		this.Activation_prev.times(this.weights).show();
		System.out.println("bias");
		this.bias.show();
		Matrix result = this.Activation_prev.times(this.weights);
		System.out.println("activation * weights + bias");
		result.plus(this.bias).show();

		this.Z_curr = this.Activation_prev.times(this.weights).plus(this.bias).transpose();
		this.Activation_curr = this.Z_curr.applyFunction(activation::activation);

		return this.Activation_curr;

	}
	
	public static void main(String[] args) {

		// TODO : something is wrong with the bias dimensions
		List<NeuralLayer> NeuralNetwork = new ArrayList<NeuralLayer>();
		NeuralNetwork.add(new NeuralLayer(2,4, new Activation_Relu())); // bias 4,x
		NeuralNetwork.add(new NeuralLayer(4,4, new Activation_Relu())); // bias 4,x
		NeuralNetwork.add(new NeuralLayer(4,2, new Activation_Relu())); // bias 2,x this becomes wrong
		NeuralNetwork.add(new NeuralLayer(2,1, new Activation_Sigmoid())); // bias 1, x


		Matrix data = new Matrix(new double[][]{{0, 1}, {1, 0}, {0, 0}, {1, 1}});


		for (NeuralLayer layer : NeuralNetwork) {
			Matrix layerInput = data;
			Matrix layerOutput = layer.layer_forward_propagation(layerInput);
			data = layerOutput;
		}

		
	}

}

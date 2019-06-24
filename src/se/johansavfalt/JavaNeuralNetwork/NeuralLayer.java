package se.johansavfalt.JavaNeuralNetwork;

import java.util.Arrays;
import se.johansavfalt.JavaNeuralNetwork.Activation_Sigmoid;
import se.johansavfalt.JavaNeuralNetwork.Activation_Relu;
import se.johansavfalt.JavaNeuralNetwork.Matrix;


public class NeuralLayer {

	private final int inputs;
	private final int units;
	private double[][] weights;
	private double[][] Activation_prev;
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

	private void layer_forward_propagation(double[][] Activation_prev){
		this.Activation_prev = Activation_prev;
		this.Z_curr = Matrix.add(Matrix.multiply(this.weights, this.Activation_prev),this.bias);
		//this.Activation_curr = activation.activation(this.Z_curr);

	}
	
	public static void main(String[] args) {

		NeuralLayer nl1 = new NeuralLayer(2,4, new Activation_Relu());
		NeuralLayer nl2 = new NeuralLayer(4,4, new Activation_Relu());
		NeuralLayer nl3 = new NeuralLayer(4,2, new Activation_Sigmoid());
		NeuralLayer nl4 = new NeuralLayer(2,1, new Activation_Sigmoid());


		double[][] weights = {{1, 2, 3}, {4, 5, 6}};
		double[][] input = {{1}, {4}, {4}};
		
		double[] bias = {1, 1};

//		HelpFunctions hf = null;
//
//		double[] step1 =  hf.matrixMultiplication(weights, input);
//		double[] step2  = hf.addBias(step1, bias);
//		double[] result  = hf.sigmoid(step2);
//
//
//
//		for (int i = 0; i < result.length; i++) {
//				System.out.println(result[i]);
//
//		}
		
	}

}

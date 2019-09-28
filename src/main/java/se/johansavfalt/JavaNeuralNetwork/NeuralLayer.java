package se.johansavfalt.JavaNeuralNetwork;

import java.util.ArrayList;
import java.util.List;


public class NeuralLayer {

	private final int inputs;
	private final int units;
	private Matrix weights;
	private Matrix bias;
	private Matrix Activation_prev;
	private Matrix Activation_curr;
	private Matrix Z_curr;
	private ActivationFunction activation;
	private Matrix deltaWeights;
	private double deltaBias;
	private Matrix deltaCurr;
	private Matrix deltaCurr1;
	private Matrix deltaCurr2;


	public NeuralLayer(int inputs, int units, ActivationFunction activation){
		this.inputs = inputs;
		this.units = units;
		this.activation = activation;
		this.weights = Matrix.random(inputs, units);
		this.bias = new Matrix(1 , units);
		this.bias.fillwith(1);
	};


	public Matrix layer_forward_propagation(Matrix Activation_prev){
		this.Activation_prev = Activation_prev;
		this.Z_curr = this.Activation_prev.times(this.weights).plus(this.bias);
		this.Activation_curr = this.activation.activation(Z_curr);
		//this.Activation_curr = this.Z_curr.applyFunction(activation::activation);
		return this.Activation_curr;

	}


	public Matrix layer_backward_propagation(Matrix delta_Aprev) {
		this.deltaWeights = this.Activation_prev.transpose().times(delta_Aprev);
		for (int i = 0; i < delta_Aprev.getColumns(); i++) {
			this.deltaBias += delta_Aprev.getData()[0][i];
		}
		this.deltaCurr = delta_Aprev.times(this.weights.transpose());
		this.deltaCurr1 = this.deltaCurr.hadamanproduct(this.activation.activation_derivative(this.Activation_prev));
		//this.deltaCurr1 = this.deltaCurr.hadamanproduct(this.Activation_prev.applyFunction(activation::activation_derivative));
		return this.deltaCurr1;
	}



	public static void main(String[] args) {

		
	}

	public void updateParameters(double learningRate) {
		this.weights = this.weights.product(this.deltaWeights.timesConstant(-learningRate));
		this.bias = this.bias.timesConstant(this.deltaBias);

	}




}

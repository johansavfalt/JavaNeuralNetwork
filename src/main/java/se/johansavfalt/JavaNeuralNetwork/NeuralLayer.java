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


	private Matrix layer_forward_propagation(Matrix Activation_prev){
		this.Activation_prev = Activation_prev;
		this.Z_curr = this.Activation_prev.times(this.weights).plus(this.bias);
		this.Activation_curr = this.Z_curr.applyFunction(activation::activation);
		return this.Activation_curr;

	}

	private Matrix layer_backward_propagation(Matrix delta_Aprev) {
		//

		this.Activation_prev.transpose().show();
		delta_Aprev.show();
		this.bias.show();
		this.deltaWeights = this.Activation_prev.transpose().times(delta_Aprev);
		for (int i = 0; i < this.bias.getRows(); i++) {
			//this.deltaBias[][] += this.bias.getData()[0][i];
		}
		System.out.println(this.deltaBias);
		this.deltaCurr = delta_Aprev.times(this.weights.transpose()).times(this.Activation_prev.applyFunction(activation::activation).transpose());
		return this.deltaCurr;
	}
	
	public static void main(String[] args) {

		List<NeuralLayer> NeuralNetwork = new ArrayList<NeuralLayer>();
		NeuralNetwork.add(new NeuralLayer(2,4, new Activation_Relu()));
		NeuralNetwork.add(new NeuralLayer(4,4, new Activation_Relu()));
		NeuralNetwork.add(new NeuralLayer(4,2, new Activation_Relu()));
		NeuralNetwork.add(new NeuralLayer(2,1, new Activation_Sigmoid()));


		Matrix data = new Matrix(new double[][]{{0, 1}, {1, 0}, {0, 0}, {1, 1}});
		Matrix test = new Matrix(new double[][]{{1}, {1}, {0}, {0}});

		for (NeuralLayer layer : NeuralNetwork) {
			Matrix layerInput = data;
			data = layer.layer_forward_propagation(layerInput);
		}

		Matrix deltaLoss = compute_cross_entropy_loss(data, test, true);


		for (int i = NeuralNetwork.size()-1; i >=0 ; i--) {
			Matrix layerInput = deltaLoss;
			deltaLoss = NeuralNetwork.get(i).layer_backward_propagation(layerInput);

		}

		
	}

	private static Matrix compute_cross_entropy_loss(Matrix data, Matrix test, boolean derivative) {
		if (derivative){
			return data.minus(test);
		} else {
			return cross_entropy_loss(data, test);
		}

	}

	private static Matrix cross_entropy_loss(Matrix predictDistribution, Matrix trueDistribution) {
		double error = 0.0;
		int M = predictDistribution.getRows();
		List<Double> batchResult = new ArrayList<>();


		for (int i = 0; i < M; i++) {
			double singleTrue = trueDistribution.getData()[i][0];
			double singlePred = predictDistribution.getData()[i][0];

			if (singleTrue == 1){
				error = Math.log(singlePred)* -1.0;
			} else {
				error =  Math.log(1.0 - singlePred) * -1.0;
			}
			batchResult.add(error);


		}
		double doubleResult = (1/M * batchResult.stream().mapToDouble(f -> f.doubleValue()).sum());
		Matrix result = new Matrix(1,1);
		result.setData(1,1, doubleResult);
		return result;


	}

}

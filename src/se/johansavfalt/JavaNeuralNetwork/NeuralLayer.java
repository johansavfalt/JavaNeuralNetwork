package se.johansavfalt.JavaNeuralNetwork;

import java.util.Arrays;
import se.johansavfalt.JavaNeuralNetwork.Activation_Sigmoid;
import se.johansavfalt.JavaNeuralNetwork.Activation_Relu;


class HelpFunctions {

	//TODO should move all this into a matrix class
	
	public static double[] matrixMultiplication(double[][] matrixA, double[][] matrixB){

		
		int matrixA_rows = matrixA.length;
		int matrixA_cols = matrixA[0].length;
		int matrixB_rows = matrixB.length;
		int matrixB_cols = matrixB[0].length;
		
		
		
		double[] result = new double[matrixA_rows]; 
		
		
		if (matrixA_cols != matrixB_rows) {
			System.out.println("error wrong dimensions of the matrix");
		} else {
			
			for (int i = 0; i < matrixA_rows; i++) {
				for (int j = 0; j < matrixB_cols; j++) {
					for (int k = 0; k < matrixA_cols; k++) {
						result[i] += matrixA[i][k] * matrixB[k][j];
						
					}
				}
				
			}
			
			
		}
		
		return result;
		

	}

	public static double[] addBias(double[] matrixA, double[] matrixB){
				
		for (int i = 0; i < matrixA.length; i++) {
				matrixA[i] += matrixB[i];
				
			}
			
		
				
		return matrixA;
		
	}
	
	public static double[] sigmoid(double[] resultZ) {
		double[] activation = Arrays.copyOf(resultZ, resultZ.length);
		
		for (int i = 0; i < activation.length; i++) {
			activation[i] = 1 / (1 + Math.exp(-resultZ[i]));
		}
		
		return activation;
		
		
	}
}

class Matrix{

	private final int inputs;
	private final int units;
	private final double[][] data;

	public Matrix(int inputs, int units){
		this.inputs = inputs;
		this.units = units;
		this.data = new double[inputs][units];

	}

	private void init_random_values(){
		for (int i = 0; i < inputs ; i++) {
			for (int j = 0; j < units ; j++) {
				data[i][j] = Math.random();
			}
		}
	}

	private double[] matrixMultiplication(double[][] matrixB){
		Matrix matrixA = this;

		int matrixA_rows = matrixA.inputs;
		int matrixA_cols = matrixA.units;

		// TODO make a Matrix class?
		int matrixB_rows = matrixB.length;
		int matrixB_cols = matrixB[0].length;



		double[] result = new double[matrixA_rows];


		if (matrixA_cols != matrixB_rows) {
			System.out.println("error wrong dimensions of the matrix");
		} else {

			for (int i = 0; i < matrixA_rows; i++) {
				for (int j = 0; j < matrixB_cols; j++) {
					for (int k = 0; k < matrixA_cols; k++) {
						result[i] += matrixA.data[i][k] * matrixB[k][j];

					}
				}

			}


		}

		return result;


	}
}

public class NeuralLayer {

	private final int inputs;
	private final int units;
	Matrix weights;
	private ActivationFunction activation;

	public NeuralLayer(int inputs, int units, ActivationFunction activation){
		this.inputs = inputs;
		this.units = units;
		this.activation = activation;
		System.out.println(this.activation.getClass().getSimpleName());
	};

	private void init_weight_matrix(){
		this.weights =  new Matrix(this.inputs, this.units);

	};

	private void init_bias_matrix(){
		this.weights =  new Matrix(this.inputs, this.units);

	};

	private double sigmoid(double x){
		return (1/( 1 + Math.pow(Math.E,(-1*x))));

	}

	private void init_activation_function(){
		if(this.activation.equals("sigmoid")){

		};

	};

	private void feedForward(double[][] weights, double[][] input, double[] bias){



	}
	
	public static void main(String[] args) {

		NeuralLayer nl = new NeuralLayer(1,1, new Activation_Sigmoid());

		double[][] weights = {{1, 2, 3}, {4, 5, 6}};
		double[][] input = {{1}, {4}, {4}};
		
		double[] bias = {1, 1};

		HelpFunctions hf = null;

		double[] step1 =  hf.matrixMultiplication(weights, input);
		double[] step2  = hf.addBias(step1, bias);
		double[] result  = hf.sigmoid(step2);


		
		for (int i = 0; i < result.length; i++) {
				System.out.println(result[i]);
			
		}
		
	}

}

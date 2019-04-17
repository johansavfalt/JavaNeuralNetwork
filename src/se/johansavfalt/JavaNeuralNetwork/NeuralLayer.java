package se.johansavfalt.JavaNeuralNetwork;

import java.util.Arrays;

class HelpFunctions {
	
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




public class NeuralLayer {
	
	public static void main(String[] args) {

		double[][] weights = {{1, 2, 3}, {4, 5, 6}};
		double[][] input = {{1}, {4}, {4}};
		
		double[] bias = {1, 1};
		
		double[] result = HelpFunctions.sigmoid(
				HelpFunctions.addBias(
						HelpFunctions.matrixMultiplication(weights, input),bias));
		
		for (int i = 0; i < result.length; i++) {
				System.out.println(result[i]);
			
		}
		
	}

}

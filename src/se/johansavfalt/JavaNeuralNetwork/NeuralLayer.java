package se.johansavfalt.JavaNeuralNetwork;



public class NeuralLayer {

	
	public static double[][] matrixMultiplication(double[][] matrixA, double[][] matrixB){
		
		int matrixA_rows = matrixA.length;
		int matrixA_cols = matrixA[0].length;
		int matrixB_rows = matrixB.length;
		int matrixB_cols = matrixB[0].length;
		
		
		
		double[][] result = new double[matrixA_rows][matrixB_cols]; 
		
		
		if (matrixA_cols != matrixB_rows) {
			System.out.println("error wrong dimensions of the matrix");
		} else {
			
			for (int i = 0; i < matrixA_rows; i++) {
				for (int j = 0; j < matrixB_cols; j++) {
					for (int k = 0; k < matrixA_cols; k++) {
						result[i][j] += matrixA[i][k] * matrixB[k][j];
						
					}
				}
				
			}
			
			
		}
		
		return result;
		

	}
	
	public static double[][] addBias(double[][] matrixA, double[] matrixB){
		
		for (int i = 0; i < matrixA.length; i++) {
			for (int j = 0; j < matrixA[i].length; j++) {
			
				matrixA[i][j] += matrixB[i];
				
			}
			
		}
		
		return matrixA;
		
	}
	
	public static void main(String[] args) {

		double[][] matrixA = {{1, 2, 3}, {4, 5, 6}};
		double[][] matrixB = {{1}, {4}, {4}};
		

		double[][] matrixE = {{1, 1}, {1, 1}};
		double[] matrixF = {1, 1};
		
		double[][] matrixg = addBias(matrixE, matrixF);
		
		
		double[][] matrixC = matrixMultiplication(matrixA, matrixB);
		
		for (int i = 0; i < matrixC.length; i++) {
			for (int j = 0; j < matrixC[i].length; j++) {
				System.out.println(matrixC[i][j]);
			}
		}
		
	}

}

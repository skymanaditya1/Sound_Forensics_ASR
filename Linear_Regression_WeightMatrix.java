package com.aditya.sound.hmm;

import java.util.Scanner;

/**
 * Class to implement the linear regression algorithm.
 * Reads the feature_matrix as input (number_of_observations X number_of_features) dimension 
 * Reads the array result_array (number_of_observations X 1) dimension
 * @author Aditya
 * 
 * We have to find the optimal weights. The Optimal Weights can be calculated using the formula 
 * W = inverse(transpose(X), X) * transpose(X) * y
 *
 */

public class Linear_Regression_WeightMatrix {

	public static int number_observations;
	public static int number_features;
	public static float[][] feature_matrix;
	public static float[][] result_array;
	
	public static void main(String[] args){
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of observations : ");
		number_observations = in.nextInt();
		System.out.println("Enter the number of features : ");
		number_features = in.nextInt();
		System.out.println("Enter the feature matrix : ");
		feature_matrix = new float[number_observations][number_features];
		for(int i=0; i<feature_matrix.length; i++){
			for(int j=0; j<feature_matrix[i].length; j++){
				feature_matrix[i][j] = in.nextFloat();
			}
		}
		
		result_array = new float[number_observations][1];
		System.out.println("Enter the result matrix : ");
		for(int i=0; i<result_array.length; i++){
			result_array[i][0] = in.nextFloat();
		}
		/**
		 * First step is to take the transpose of the feature_matrix
		 * 
		 */
		
		// display(transpose(feature_matrix));
		
		/**
		 * Second step is to multiply the matrices transpose(X) and X
		 * 
		 */
		
		 // display(multiply_matrices(transpose(feature_matrix), feature_matrix));
		
		/**
		 * Third step is to take the inverse of the multiplication of the two matrices.
		 * 
		 */
		
		// display(inverse_matrix(multiply_matrices(transpose(feature_matrix), feature_matrix)));
		
		/**
		 * Fourth step is multiplying the matrix obtained from inverse_matrix with the transpose of X
		 * After finding out the inverse, the next step is to calculate the optimal weights.
		 */
		// multiply_matrices(multiply_matrices(inverse_matrix(multiply_matrices(transpose(feature_matrix), feature_matrix)), transpose(feature_matrix)), );
		
		/**
		 * Fifth step is multiplying the result of the previous four steps with the result array
		 * The resulting array gives the most optimal weights. 
		 */
		System.out.println("The weight matrix is : ");
		display(multiply_matrices(multiply_matrices(inverse_matrix(multiply_matrices(transpose(feature_matrix), feature_matrix)), transpose(feature_matrix)), result_array));
	}

	/**
	 * Method for computing the inverse of a matrix.
	 * Inverse can only be computed for a square matrix
	 * @param multiply_matrices -> the matrix to calculate the inverse
	 * 1. If it is a 1D matrix, return the matrix divided by its determinant
	 * 2. If it is a 2D matrix, use the formula for calculating the inverse
	 * 3. If it is a nD matrix, use the Gauss Jordan Elimination for computing the inverse.
	 */
	private static float[][] inverse_matrix(float[][] multiply_matrices) {
		// TODO Auto-generated method stub
		// Dimension of the square matrix
		int dimension = multiply_matrices.length;
		float[][] inverse_mat = new float[dimension][dimension];
		
		if(dimension == 1){
			inverse_mat[0][0] = 1 / multiply_matrices[0][0];
		}
		
		// Swap the values at multiply_matrices[0][0] and multiply_matrices[1][1].
		else if(dimension == 2){
			
			float temp_determinant = two_find_determinant(multiply_matrices, dimension);
			
			float temp = multiply_matrices[0][0];
			multiply_matrices[0][0] = multiply_matrices[1][1];
			multiply_matrices[1][1] = temp;
			multiply_matrices[0][1] = -1 * multiply_matrices[0][1];
			multiply_matrices[1][0] = -1 * multiply_matrices[1][0];
			
			for(int i=0; i<multiply_matrices.length; i++){
				for(int j=0; j<multiply_matrices[i].length; j++){
					inverse_mat[i][j] = multiply_matrices[i][j] / temp_determinant;
				}
			}
		}
		
		// Find the inverse of the matrix for dimensions greater than 2, using determinant and cofactor approach 
		// Matrix -> NxN matrix, to find the inverse of a matrix, find out the minor matrix, then find out the cofactor matrix
		// after that find out the adjoint matrix. The inverse will be adjoint matrix divided by the determinant of the matrix.
		else{
			// Calculate the determinant of NxN matrix
			float temp_determinant = find_determinant(multiply_matrices, multiply_matrices.length);
			
			// Find out the minor matrix
			float[][] temp_minor = new float[dimension][dimension];
			for(int i=0; i<temp_minor.length; i++){
				for(int j=0; j<temp_minor[i].length; j++){
					// temp_minor[i][j] = find_determinant(find_minor(multiply_matrices, multiply_matrices.length, i, j), multiply_matrices.length);
					temp_minor[i][j] = find_determinant((find_minor_matrix(multiply_matrices, i, j)), temp_minor.length-1); 
				}
			}
			// System.out.println("Displaying the temp minor matrix ");
			// display(temp_minor);
			// System.out.println("Stopped displaying the temp minor matrix ");
			
			float[][] temp_cofactor = new float[dimension][dimension];
			int product = 1;
			for(int i=0; i<temp_minor.length; i++){
				for(int j=0; j<temp_minor[i].length; j++){
					// if(temp_minor[i][j] == 0.0 || temp_minor[i][j] == -0.0){
						// temp_cofactor[i][j] = (float) 0.0;
					// }
					// else{
						temp_cofactor[i][j] = temp_minor[i][j] * product;
						product = product * -1;
					// }
				}
			}
			// display(temp_cofactor);
			
			// After calculating the cofactor matrix, swap the entries about the main diagonal
			// float[][] swap_diagonal = new float[dimension][dimension];
			
			for(int i=0; i<temp_cofactor.length; i++){
				for(int j=0; j<temp_cofactor[i].length; j++){
					if (i < j){
						float temp = temp_cofactor[i][j];
						temp_cofactor[i][j] = temp_cofactor[j][i];
						temp_cofactor[j][i] = temp;
					}
					else{
						// System.out.println("* Do nothing *");
					}
				}
			}
			
			// The inverse of the matrix will be all the elements of the temp_cofactor divided by the determinant of the matrix
			for(int i=0; i<inverse_mat.length; i++){
				for(int j=0; j<inverse_mat[i].length; j++){
					inverse_mat[i][j] = temp_cofactor[i][j] / temp_determinant;
				}
			}
			
			for(int i=0; i<inverse_mat.length; i++){
				for(int j=0; j<inverse_mat[i].length; j++){
					if ( inverse_mat[i][j] == -0.0){
						inverse_mat[i][j] = (float)0;
					}
					else{
						// Do nothing
					}
				}
			}
			
		}
		return inverse_mat;
	}
	
	/**
	 * Method that returns the matrix without the ROWth row and the COLth column
	 * @param matrix
	 * @param length
	 * @param row
	 * @param col
	 * @return
	 */
	private static float[][] find_minor_matrix(float[][] matrix, int row, int col){
		
		float[][] minor = new float[matrix.length-1][matrix.length-1];
		int var_i = 0;
		int var_j = 0;
		for(int i=0; i<matrix.length; i++){
			for(int j=0; j<matrix[i].length; j++){
				if(i != row && j != col){
					minor[var_i][var_j] = matrix[i][j];
					var_j ++;
				}
				else{
					// System.out.println("Do nothing");
				}
			}
			
			if(var_j == minor.length){
				var_i++;
			}
			var_j = 0;
		}
		return minor;
	}
	
	/**
	 * Calculates the determinant of NxN matrix
	 * @param matrix1 - to calculate the determinant of matrix1
	 * @param length - length of the row/column about which to calculate the determinant
	 * @return - float determinant of the matrix
	 */
	private static float find_determinant(float[][] matrix1, int length) {
		// TODO Auto-generated method stub
		float determinant = 0;
		int product = 1;
		if(length==2){
			determinant = two_find_determinant(matrix1, length);
		}
		else{
			for(int i=0; i<length; i++){
				determinant += matrix1[0][i] * product * find_determinant(find_minor(matrix1, length, 0, i), length-1);
				product = product * -1;
			}
		}
		return determinant;
	}

	/**
	 * Finds the minor of the matrix in question. Used for calculating the determinant of the matrix.
	 * @param matrix1
	 * @param length
	 * @param i
	 * @param i2
	 * @return
	 */
	private static float[][] find_minor(float[][] matrix1, int length, int i,
			int i2) {
		// TODO Auto-generated method stub
		/**
		 * Call the method to find the determinant of the 2X2 matrix
		 */
		float b[][] = new float[matrix1.length-1][matrix1.length-1];
		int var_i = 0;
		int var_j = 0;
		for(int row_i=0; row_i<length; row_i++){
			for(int col_j=0; col_j<length; col_j++){
				if( row_i != i && col_j != i2){
					b[var_i][var_j] = matrix1[row_i][col_j];
					var_j ++;
				}
				else{
					//System.out.println("Did nothing ");
				}
			}
			if(var_j == b.length){
				var_i++;
			}
			var_j = 0;
		}
		return b;
	}
	
	/**
	 * Finds the determinant of the 2X2 matrix
	 * @param a
	 * @param n
	 * @return
	 */
	
	private static float two_find_determinant(float[][] a, int n) {
		// TODO Auto-generated method stub
		float determinant_matrix = a[0][0] * a[1][1] - a[0][1] * a[1][0];
		return determinant_matrix;
	}

	/**
	 * Code for multiplying two matrices with number of columns of first matrix equal to the number of rows of the second matrix
	 * @param transpose -> the first matrix to multiply 
	 * @param feature_matrix2 -> the second matrix to multiply
	 * The result matrix will have number of rows equal to the number of rows of the first matrix and the number of columnd equal
	 * to the number of columns equal to the second matrix.
	 */
	private static float[][] multiply_matrices(float[][] transpose,
			float[][] feature_matrix2) {
		// TODO Auto-generated method stub
		float[][] result_matrix = new float[transpose.length][feature_matrix2[0].length];
		int const_number = transpose[0].length; // Same as feature_matrix2.length. 
		for(int i=0; i<result_matrix.length; i++){
			for(int j=0; j<result_matrix[i].length; j++){
				result_matrix[i][j] = 0;
				for(int k=0; k<const_number; k++){
					result_matrix[i][j] += transpose[i][k] * feature_matrix2[k][j];
				}
			}
		}
		
		return result_matrix;
		
	}

	/**
	 * Method for returning the transpose of the feature matrix
	 * @param feature_matrix2 -> matrix to transpose
	 */
	private static float[][] transpose(float[][] feature_matrix2) {
		// TODO Auto-generated method stub
		float[][] transpose_matrix = new float[number_features][number_observations];
		for(int i=0; i<feature_matrix2.length; i++){
			for(int j=0; j<feature_matrix2[i].length; j++){
				transpose_matrix[j][i] = feature_matrix2[i][j];
			}
		}
		
		return transpose_matrix;
	}

	/**
	 * Method to display the NxM float matrix
	 * @param feature_matrix2 -> takes the float_matrix as input
	 */
	private static void display(float[][] feature_matrix2) {
		// TODO Auto-generated method stub
		for(int i=0; i<feature_matrix2.length; i++){
			for(int j=0; j<feature_matrix2[i].length; j++){
				System.out.print(feature_matrix2[i][j]+" ");
			}
			System.out.println();
		}
	}
}

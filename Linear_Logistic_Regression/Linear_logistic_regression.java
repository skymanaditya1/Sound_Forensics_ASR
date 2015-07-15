package com.aditya.sound.hmm;

import java.util.Scanner;

/**
 * Class for calculating the optimal weight values for a feature matrix and the corresponding result array. 
 * @author Aditya
 *
 */

public class Linear_logistic_regression {

	public static int number_observations;
	public static int number_features;
	public static float[][] feature_matrix;
	public static float[] result_array;
	public static int determinant_matrix;
	public static float[] solutions_matrix;
	public static int[] constant;
	
	public static void main(String[] args){
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of observations : ");
		number_observations = in.nextInt();
		System.out.println("Enter the number of features : ");
		number_features = in.nextInt();
		feature_matrix = new float[number_observations][number_features];
		System.out.println("Enter the feature matrix : ");
		for(int i=0; i<feature_matrix.length; i++){
			for(int j=0; j<feature_matrix[i].length; j++){
				feature_matrix[i][j] = in.nextFloat();
			}
		}
		result_array = new float[number_observations];
		System.out.println("Enter the result array : ");
		for(int i=0; i<result_array.length; i++){
			result_array[i] = in.nextFloat();
		}
		
		/**
		 * First task if finding the transpose of the matrix
		 */
		//find_transpose();
		
		/**
		 * Second task would be multiplying two matrices, in our case the matrix with its transpose
		 */ 
		//mutilpy_matrix(find_transpose(feature_matrix), feature_matrix);
		
		
		/**
		 * Third task would be finding the inverse of the matrix transpose(X) * X
		 */
		find_inverse(multiply_matrix(find_transpose(feature_matrix), feature_matrix));
		
		/**
		 * Fourth task would be to multiply it with the transpose of X
		 */
		
		/**
		 * Fifth task would be to multiply it with the result matrix
		 */
	}

	/**
	 * This method multiplies two matrices and return the resultant matrix.
	 * To multiply two matrices, the number of columns of the first matrix should be equivalent to the
	 * number of rows of the second matrix. 
	 * The resulting matrix will be of the dimensions number_of_rows_matrix1 * number_of_columns_matrix2
	 * @param find_transpose
	 * @param feature_matrix2
	 */
	private static float[][] multiply_matrix(float[][] find_transpose,
			float[][] feature_matrix2) {
		// TODO Auto-generated method stub
		// The resulting matrix will be a square matrix to be passed to find_inverse() function. 
		float[][] result_matrix = new float[find_transpose.length][feature_matrix2[0].length];
		int constant_var = find_transpose[0].length; // Equal to the number of columns in the first matrix, number of rows in second matrix
		for(int i=0; i<result_matrix.length; i++){
			for(int j=0; j<result_matrix[i].length; j++){
				result_matrix[i][j] = 0;
				for(int k=0; k<constant_var; k++){
					result_matrix[i][j] += find_transpose[i][k] * feature_matrix2[k][j];
				}
			}
		}
		
		return result_matrix;
		
	}

	/**
	 * Method for finding the inverse of the matrix.
	 * Given a matrix it finds the inverse matrix - inverse can only be found for a square matrix.  
	 * Use the Gauss_jordan elimination method for finding the inverse of the matrix
	 * We have to find the determinant of the matrix and also find the 
	 */
	private static float[][] find_inverse(float[][] matrix1) {
		// TODO Auto-generated method stub
		// Find the inverse of the matrix1 -> matrix1 is the given square matrix
		float[][] inverse_matrix = new float[matrix1.length][matrix1[0].length];
		
		// If the square matrix has dimension 1 return 1 / A[0][0]
		if( matrix1.length == 1){
			inverse_matrix[0][0] = 1 / matrix1[0][0];
		}

		// If the square matrix has dimension 2, use the direct formula for computing the inverse 
		float temp_determinant = find_determinant(matrix1, matrix1.length);
		// Swap the elements at matrix1[0][0] and matrix1[1][1] and reverse the signs of elements at matrix1[0][1] and matrix[1][0]
		float temp_swap = matrix1[0][0];
		matrix1[0][0] = matrix1[1][1];
		matrix1[1][1] = temp_swap;
		
		matrix1[0][1] = -1 * matrix1[0][1];
		matrix1[1][0] = -1 * matrix1[1][0];
		
		// Divide all the elements in the matrix by the determinant of the matrix
		for(int i=0; i<matrix1.length; i++){
			for(int j=0; j<matrix1[i].length; j++){
				matrix1[i][j] /= temp_determinant;
			}
		}
		
		// If the square matrix has dimension n, where n>2, find the inverse using the Gauss Jordan Elimination technique
		
		
		return inverse_matrix;
	}

	private static float find_determinant(float[][] matrix1, int length) {
		// TODO Auto-generated method stub
		float determinant = 0;
		int product = 1;
		if(length==2){
			determinant = two_find_determinant(matrix1, length);
		}
		for(int i=0; i<length; i++){
			determinant += matrix1[0][i] * product * find_determinant(find_minor(matrix1, length, 0, i), length-1);
			product = product * -1;
		}
		return determinant;
	}

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

	private static float two_find_determinant(float[][] matrix1, int length) {
		// TODO Auto-generated method stub
		float determinant_matrix = matrix1[0][0] * matrix1[1][1] - matrix1[0][1] * matrix1[1][0];
		return determinant_matrix;
	}

	private static float[][] find_transpose(float[][] f_matrix) {
		// TODO Auto-generated method stub
		float[][] feature_matrix1 = new float[number_observations][number_features];
		// Transpose the matrix feature_matrix into the feature_matrix1 and read it back to the feature_matrix
		for(int i=0; i<f_matrix.length; i++){
			for(int j=0; j<f_matrix[i].length; j++){
				feature_matrix1[i][j] = f_matrix[j][i];
			}
		}
		/**for(int i=0; i<feature_matrix.length; i++){
			for(int j=0; j<feature_matrix[i].length; j++){
				feature_matrix[i][j] = feature_matrix1[i][j];
			}
		}*/
		
		// System.out.println("The transpose of the matrix is : ");
		// display(feature_matrix);
		return feature_matrix1;
	}

	/**
	private static void display(float[][] feature_matrix2) {
		// TODO Auto-generated method stub
		for(int i=0; i<feature_matrix2.length; i++){
			for(int j=0; j<feature_matrix2[i].length; j++){
				System.out.print(feature_matrix2[i][j]+" ");
			}
			System.out.println();
		}
	}*/
	
	/**
	 * Method for displaying the solutions array
	 * @param solutions
	 *
	private static void display_solutions(float[] solutions) {
		// TODO Auto-generated method stub
		for(int i=0; i<solutions.length; i++)
			System.out.print(solutions[i]+" ");
	}*/

	/**
	 * Finds the determinant of the 2X2 matrix
	 * @param a
	 * @param n
	 * @return
	 *
	private static int two_find_determinant(int[][] a, int n) {
		// TODO Auto-generated method stub
		int determinant_matrix = a[0][0] * a[1][1] - a[0][1] * a[1][0];
		return determinant_matrix;
	}*/
	
	
	/**
	 * Method for finding the determinant of the matrix
	 * @param a -> the matrix
	 * @param n -> the size of the square matrix
	 * @return -> determinant of the matrix
	 *
	private static int find_determinant(int[][] a, int n) {
		// TODO Auto-generated method stub1
		int determinant = 0;
		int product = 1;
		if(n==2){
			determinant = two_find_determinant(a, n);
		}
		for(int i=0; i<n; i++){
			determinant += a[0][i] * product * find_determinant(find_minor(a, n, 0, i), n-1);
			product = product * -1;
		}
		return determinant;
	}
	*/
	
	/**
	 * 
	 * @param a -> the matrix
	 * @param n -> the size of the input matrix
	 * @param i -> the row to neglect in the resulting matrix 
	 * @param i2 -> the column to neglect in the resulting matrix
	 * @return -> returns the n-1Xn-1 matrix
	 *
	private static int[][] find_minor(int[][] a, int n, int i, int i2) {
		// TODO Auto-generated method stub
		// Call the method to find the determinant of the 2X2 matrix
		int b[][] = new int[a.length-1][a.length-1];
		int var_i = 0;
		int var_j = 0;
		for(int row_i=0; row_i<n; row_i++){
			for(int col_j=0; col_j<n; col_j++){
				if( row_i != i && col_j != i2){
					b[var_i][var_j] = a[row_i][col_j];
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
	*/
	
	
	/**
	 * 
	 * @param coeff -> the original coefficient matrix
	 * @param constant -> the array of constant values
	 * @param determinant_matrix -> the determinant of the matrix
	 *
	private static float[] find_solutions_cramers_rule(int[][] coeff, int[] constant, int determinant_matrix){
		for(int i=0; i<coeff.length; i++){
			for(int j=0; j<coeff[i].length; j++){
				int temp = coeff[j][i];
				coeff[j][i] = constant[j];
				constant[j] = temp;
			}
			int temp_determinant = find_determinant(coeff, coeff.length);
			solutions_matrix[i] = temp_determinant/determinant_matrix;
			
			// Restoring the original contents of the coeff matrix
			
			for(int j=0; j<coeff[i].length; j++){
				int temp = coeff[j][i];
				coeff[j][i] = constant[j];
				constant[j] = temp;
			}
		}
		
		return solutions_matrix;
	}
	*/
}

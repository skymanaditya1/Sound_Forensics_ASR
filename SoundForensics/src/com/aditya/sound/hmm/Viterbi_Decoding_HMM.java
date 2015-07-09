package com.aditya.sound.hmm;

import java.util.Scanner;

/**
 * This class computes the Viterbi path probability, and determines the most probable hidden state sequence 
 * for a sequence of observations. It also determines the max probability of the observation sequence.
 * @author Aditya
 *
 */

public class Viterbi_Decoding_HMM {

	public static int number_states;
	public static float[][] transition_matrix;
	public static int observation_length;
	public static float[][] observation_prob;
	public static float most_probable;
	public static int[] probable_states;
	// public static float[][] v_prob;
	
	public static void main(String[] args){
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of states : ");
		number_states = in.nextInt();
		System.out.println("Enter the state transitional probability matrix : ");
		transition_matrix = new float[number_states+1][number_states];
		for(int i=0; i<transition_matrix.length; i++){
			for(int j=0; j<transition_matrix[i].length; j++){
				System.out.println("Enter the transition probability from state "+i+" to state "+(j+1));
				transition_matrix[i][j] = in.nextFloat();
			}
		}
		display(transition_matrix);
		System.out.println("Enter the length of the observation sequence : ");
		observation_length = in.nextInt();
		System.out.println("Enter the state observation likelihood : ");
		observation_prob = new float[observation_length][number_states];
		for(int i=0; i<observation_prob.length; i++){
			for(int j=0; j<observation_prob[i].length; j++){
				System.out.println("Enter the observation prob of obs "+i+" given state "+j);
				observation_prob[i][j] = in.nextFloat();
			}
		}
		display(observation_prob);
		probable_states = new int[observation_length+1];
		
		float[][] v_prob = new float[observation_length][number_states];
		
		most_probable = viterbi_algorithm(probable_states, v_prob);
		// most_probable = viterbi_algorithm(probable_states);
		System.out.println("The probability of the most probable sequence is : "+most_probable);
		
	}
	
	/**
	 * Method for computing the most probable sequence of states for the given observation sequence, 
	 * and the max probability
	 * @param probable_states2 ->stores the most probable sequence of states.
	 * @param v_prob -> stores the viterbi path probability for each state
	 * @return -> the max probability for the most probable sequence of states. 
	 */
	private static float viterbi_algorithm(int[] probable_states2, float[][] v_prob) {
		// TODO Auto-generated method stub
		
		float temp_prob = 0;
		int temp_state = 0; // Holds the state with max temp probability, initialized with the start state
		float temp_max = 0; // Holds the max prob temporarily
		int count = 0; // Counter for iteration through the probable_states2 array
		
		probable_states2[count++] = temp_state; // Initialize with the start state as the most probable state
		
		/**
		 * 1. Initialization
		 */
		for(int i=0; i<number_states; i++){
			v_prob[0][i] = transition_matrix[0][i] * observation_prob[0][i];
			if(v_prob[0][i] > temp_max){
				temp_max = v_prob[0][i];
				temp_state = (i+1); // Since we follow a 0 based indexing
			}
		}
		
		// Write the most probable state to the probable_states2 array
		probable_states2[count++] = temp_state;
		
		// Clear the temp_max variable
		temp_max = 0;
		
		/**
		 * 2. Recursion
		 */
		float temp_max1 = 0; // Checks for the max viterbi prob in the same states (i.e states with the same length of the timestep t).
		
		for(int i=1; i<observation_length; i++){
			
			temp_max1 = 0;
			
			for(int j=0; j<number_states; j++){
				
				temp_max = 0;
				
				for(int k=0; k<number_states; k++){
					float temp_viterbi = v_prob[i-1][k] * transition_matrix[k+1][j] * observation_prob[i][j];
					if ( temp_viterbi > temp_max){
						temp_max = temp_viterbi;
						// temp_state = j;
					}
					v_prob[i][j] = temp_max;
				}
				
				if ( v_prob[i][j] > temp_max1){
					temp_max1 = v_prob[i][j];
					temp_state = (j+1); // Since we follow a 0 based indexing
				}
			}
			probable_states2[count++] = temp_state;
		}
		
		/**
		 * 3. Termination 
		 */
		// Finds the max probability of the final state and returns it
		for(int i=0; i<number_states; i++){
			if ( v_prob[observation_length-1][i] > temp_prob){
				temp_prob = v_prob[observation_length-1][i];
			}
		}
		
		System.out.println("The most probable hidden state sequence for the given observation sequence is : ");
		for(int i=0; i<probable_states2.length; i++){
			System.out.print(probable_states2[i]+" ");
		}
		System.out.println();
		
		System.out.println("The Viterbi path probabilities are : ");
		display(v_prob);
		
		return temp_prob;
	}

	public static void display(float[][] transition_matrix2){
		for(int i=0; i<transition_matrix2.length; i++){
			for(int j=0; j<transition_matrix2[i].length; j++){
				System.out.print(transition_matrix2[i][j]+" ");
			}
			System.out.println();
		}
	}
}

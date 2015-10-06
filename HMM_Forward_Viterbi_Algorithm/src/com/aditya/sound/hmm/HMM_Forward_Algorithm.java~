package com.aditya.sound.hmm;

import java.util.Scanner;

/**
 * This class implements the Forward Algorithm of the Hidden Markov Model
 * @author Aditya
 * The user has to give three inputs
 * i. The number of states in the automaton and the transition probabilities for each transition
 * ii. the observation sequence.
 * iii. state observation likelihood, of the observation symbol Ot given the state j
 * It then calculates the forward_path_probability by taking a sum of the forward_path_probability
 * of all the final states.  
 */

public class HMM_Forward_Algorithm {

	public static int number_states;
	public static float[][] transition_matrix;
	public static int observation_length;
	public static float[][] observation_prob;
	public static float[][] forward_prob;
	public static float forward_probability;
	
	public static void main(String[] args){
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of states : ");
		number_states = in.nextInt();
		/**
		 * number_states will be 1 more than the number of states entered by the user,
		 * including the start state
		 */
		transition_matrix = new float[number_states+1][number_states];
		System.out.println("Enter the transition matrix : ");
		for(int i=0; i<transition_matrix.length; i++){
			for(int j=0; j<transition_matrix[i].length; j++){
				//if(j==0)
					//transition_matrix[i][j] = 0;
				//else{
					System.out.println("Enter the transition probability from "+i+" state to "+(j+1)+" state : ");
					transition_matrix[i][j] = in.nextFloat();
				//}
			}
		}
		display(transition_matrix);
		System.out.println("Enter the number of observations in the observation sequence : ");
		observation_length = in.nextInt();
		observation_prob = new float[observation_length][number_states];
		System.out.println("Enter the observation likelihood probabilities : ");
		for(int i=0; i<observation_prob.length; i++){
			for(int j=0; j<observation_prob[i].length; j++){
				System.out.println("Enter the state observation likelihood for obs. "+i+" given state "+j+" : ");
				observation_prob[i][j] = in.nextFloat();
			}
		}
		
		display(observation_prob);
		System.out.println("Calculating the forward probabilities : ");
		forward_prob = new float[observation_length][number_states];
		
		/**
		 * Calculating the forward_prob involves three basic steps:
		 * 1. Initialization
		 * 2. Recursion
		 * 3. Termination
		 */
		
		forward_probability = calculate_forward_prob(forward_prob);
		System.out.println("The forward probability of the automaton with the given observation "
				+ "sequence is : "+forward_probability);
	}

	/**
	 * Method for calculating the forward probabilities
	 * @param forward_prob2
	 */
	private static float calculate_forward_prob(float[][] forward_prob2) {
		// TODO Auto-generated method stub
		
		float forward_prob_number = 0;
		
		// 1. Initialization
		for(int i=0; i<number_states; i++){
			forward_prob2[0][i] = transition_matrix[0][i] * observation_prob[0][i];
		}
		
		// 2. Recursion
		// For each time step
		for(int i=1; i<observation_length; i++){
			// For each state s in S
			for(int j=0; j<number_states; j++){
				// For all the states in the previous forward_path
				forward_prob2[i][j] = 0;
				for(int k=0; k<number_states; k++){
					forward_prob2[i][j] += forward_prob2[i-1][k] * transition_matrix[k+1][j]
							* observation_prob[i][j]; 
				}
			}
		}
		
		// 3. Termination
		// Calculating the forward_prob by summing up the forward_prob of all the final states
		for(int i=0; i<number_states; i++){
			forward_prob_number += forward_prob2[observation_length-1][i];
		}
		
		System.out.println("The forward_probability matrix is : ");
		display(forward_prob2);
		return forward_prob_number;
	}

	/**
	 * Displays the transition matrix
	 * @param transition_matrix2
	 */
	private static void display(float[][] transition_matrix2) {
		// TODO Auto-generated method stub
		for(int i=0; i<transition_matrix2.length; i++){
			for(int j=0; j<transition_matrix2[i].length; j++){
				System.out.print(transition_matrix2[i][j]+" ");
			}
			System.out.println();
		}
	}
}

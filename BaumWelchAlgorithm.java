package com.aditya.sound.hmm;

import java.util.Scanner;

public class BaumWelchAlgorithm {

	public static int number_mfcc;
	public static int number_sounds;
	public static float[][] transition_Probabilities;
	public static String[] sound_samples;
	
	public static void main(String[] args){
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of sounds in the corpus : ");
		number_sounds = in.nextInt();
		System.out.println("Enter the sound samples in the corpus : ");
		sound_samples = new String[number_sounds];
		in.nextLine();
		for(int i=0; i<number_sounds; i++){
			sound_samples[i] = in.nextLine();
		}
		System.out.println("Enter the number of MFCC's (observations) for each sound sample : ");
		number_mfcc = in.nextInt();
		System.out.println("Enter the transition probability matrix : ");
		transition_Probabilities = new float[number_sounds][number_mfcc];
		for(int i=0; i<transition_Probabilities.length; i++){
			for(int j=0; j<transition_Probabilities[i].length; j++){
				transition_Probabilities[i][j] = in.nextFloat();
			}
		}
		// Method for displaying the 2D float matrix
		display(transition_Probabilities);
		
		//Method for displaying the 1D string array
		display1(sound_samples);
	}

	/**
	 * Method for displaying the 1D array of Strings
	 * @param sound_samples2
	 */
	private static void display1(String[] sound_samples2) {
		// TODO Auto-generated method stub
		for(int i=0; i<sound_samples2.length; i++){
			System.out.print(sound_samples2[i]+" ");
		}
		System.out.println();
	}

	/**
	 * Method for displaying the 2D float matrix
	 * @param transition_Probabilities2
	 */
	private static void display(float[][] transition_Probabilities2) {
		// TODO Auto-generated method stub
		for(int i=0; i<transition_Probabilities2.length; i++){
			for(int j=0; j<transition_Probabilities2[i].length; j++){
				System.out.print(transition_Probabilities2[i][j]+" ");
			}
			System.out.println();
		}
	}
}

package com.classification.algorithms;

import java.util.Random;
import java.util.Scanner;

public class PrintsRandomValue {

	private static int number_points;
	private static int[] points;
	private static int number_clusters;
	private static int[] number_centroid;
	private static float[][] centroid_points;
	
	
	public static void main(String[] args){
		// Random rand = new Random();
		// System.out.println(rand.nextInt(10));
		Random rand = new Random();
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of points in the cluster");
		number_points = in.nextInt();
		points = new int[number_points];
		System.out.println("Enter the points ");
		for(int i=0; i<points.length; i++){
			points[i] = in.nextInt();
		}
		System.out.println("Enter the number of clusters ");
		number_clusters = in.nextInt();
		number_centroid = new int[number_clusters];
		// Taking random values for the centroidal points
		// find out the maximum values among the points
		int max_greatest = find_max(points);
		
		// find out the random values for the centroids
		for(int i=0; i<number_clusters; i++){
			number_centroid[i] = rand.nextInt(max_greatest);
		}
		
		centroid_points = new float[number_points][number_clusters];
		// Find out the distance of each point from each centroid
		for(int i=0; i<number_points; i++){
			for(int j=0; j<number_clusters; j++){
				centroid_points[i][j] = points[i] - number_centroid[j];
			}
		}
		
		// print out the centroid_points
		// System.out.println(centroid_points);
		display(centroid_points);
		
		in.close();
	}


	// Display the centroid_points
	private static void display(float[][] centroid_points2) {
		// TODO Auto-generated method stub
		for(int i=0; i<centroid_points2.length; i++){
			for(int j=0; j<centroid_points2[i].length; j++){
				System.out.println(centroid_points2[i][j]+" ");
			}
			System.out.println();
		}
	}


	// finds out the greatest value among all the points
	private static int find_max(int[] points2) {
		// TODO Auto-generated method stub
		int max = points2[0];
		for(int i=1; i<points2.length; i++){
			if (points2[i] > max)
				max = points2[i];
		}
		return max;
	}
}

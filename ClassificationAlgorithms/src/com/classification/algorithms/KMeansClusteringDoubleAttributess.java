package com.classification.algorithms;

import java.util.Random;
import java.util.Scanner;

public class KMeansClusteringDoubleAttributess{

	private static int number_points;
	private static int number_attributes;
	private static float[][] points_attributes;
	private static int number_clusters;
	private static Random random;
	private static float[][] cluster_points;
	// private static int[] number_of_points; 
	private static float[][] cluster_distances;
	
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		random = new Random();
		
		// number of points in the cluster
		System.out.println("Enter the number of points ");
		number_points = in.nextInt();
		
		// accept the attributes for each point
		System.out.println("Enter the number of attributes for each "
				+ "point ");
		number_attributes = in.nextInt();
		
		// 2D array for accepting the inputs
		points_attributes = new float[number_points][number_attributes];
		
		System.out.println("Enter the points and their attribute ");
		
		for(int i=0; i<number_points; i++){
			for(int j=0; j<number_attributes; j++){
				points_attributes[i][j] = in.nextFloat();
			}
		}
		
		// find the max value of all the points
		int max_value = (int) find_greatest_point(points_attributes);
		
		// number of clusters accepted by K-means
		System.out.println("Enter the number of clusters ");
		number_clusters = in.nextInt();
		
		// find out the random value for all the points in the cluster
		cluster_points = new float[number_clusters][number_attributes];
		
		
		// for each cluster calculate the distance (euclidean) 
		// from the points to centroid of the cluster 
		for(int i=0; i<cluster_points.length; i++){
			for(int j=0; j<cluster_points[i].length; j++){
				cluster_points[i][j] = random.nextInt(max_value);
			}
		}
		
		display_cluster_values(cluster_points);
		
		// initialize the number_of_points with the number of cluster
		// number_of_points = new int[number_clusters];
		
		// calculate the cluster distances using the 
		// k means clustering algorithm
		// find out the number-of-points in each cluster
		// calculate the cluster-distances for each point from each cluster
		cluster_distances = new float[number_points][number_clusters];
		
		calculate_cluster_distances(points_attributes, cluster_points, 
				cluster_distances);
		
		in.close();
	}


	/**
	 * Method for calculating the k - means clustering distances
	 * @param points_attributes2
	 * @param cluster_points2
	 */
	private static void calculate_cluster_distances(
			float[][] points_attributes2, float[][] cluster_points2, 
			float[][] cluster_distances2) {
		// TODO Auto-generated method stub
		for(int i=0; i<cluster_distances2.length; i++){
			for(int j=0; j<cluster_distances2[i].length; j++){
				cluster_distances2[i][j] = find_euclidean_distance(points_attributes2, i, 
						cluster_points2, j);
			}
			
		}
		
		/**
		 * Also for each of the points in the cluster_distances,
		 * find out the minimum value for each of the points, and 
		 * increment the value of the index in the number_of_points
		 */
		
		System.out.println("--- Prints out cluster distances ---");
		display_cluster_values(cluster_distances2);
		
		/**
		 * Finds the number of words in each cluster
		 */
		find_words_each_cluster(cluster_distances2);
		
	}


	/**
	 * Finds out the number of words in every cluster
	 * @param cluster_distances2
	 * @param number_of_points2
	 */
	private static void find_words_each_cluster(float[][] cluster_distances2) {
		float[] temp_centroidal_values = new float[number_clusters];
		int[] temp_cluster_points = new int[number_clusters];
		// TODO Auto-generated method stub
		for(int i=0; i<cluster_distances2.length; i++){
			float min_value = cluster_distances2[i][0];
			int min_index = i;
			for(int j=1; j<number_clusters; j++){
				if(cluster_distances2[i][j] < min_value){
					min_value = cluster_distances2[i][j];
					min_index = j;
				}
			}
			
			temp_centroidal_values[min_index] += min_value;
			temp_cluster_points[min_index] += 1;
			
		}
		
		System.out.println(" ---Displays the number of points in each cluster--- ");
		display_linear_array(temp_cluster_points);
	}
	
	/**
	 * Displays the number of points in each cluster
	 * @param number_of_points2
	 */


	private static void display_linear_array(int[] number_of_points2) {
		// TODO Auto-generated method stub
		for(int i=0; i<number_of_points2.length; i++){
			System.out.println(number_of_points2[i]+" ");
		}
		System.out.println();
	}


	/**
	 * Method for finding the euclidean distance 
	 * between two given points
	 * @param points_attributes2
	 * @param i
	 * @param cluster_points2
	 * @param j
	 * @return
	 */
	private static float find_euclidean_distance(float[][] points_attributes2,
			int i, float[][] cluster_points2, int j) {
		// TODO Auto-generated method stub
		float euclidean_distance;
		float squared_sum_distance = 0; 
		for(int count=0; count<number_attributes; count++){
			squared_sum_distance += Math.pow((points_attributes2[i][count] - cluster_points2[j][count]), 2);
		}
		euclidean_distance = (float) Math.sqrt(squared_sum_distance);
		return euclidean_distance;
	}


	/**
	 * Displays the cluster_points random values
	 * @param cluster_points2
	 */
	private static void display_cluster_values(float[][] cluster_points2) {
		// TODO Auto-generated method stub
		for(int i=0; i<cluster_points2.length; i++){
			for(int j=0; j<cluster_points2[i].length; j++){
				System.out.print(cluster_points2[i][j]+" ");
			}
			System.out.println();
		}
		
	}


	
	/**
	 * Calculates the root mean squared distance between the points
	 * and returns the maximum value
	 * @param points_attributes2
	 * @return
	 */
	private static float find_greatest_point(float[][] points_attributes2) {
		// TODO Auto-generated method stub
		float max_value = 0;
		
		for(int i=0; i<points_attributes2.length; i++){
			
			float squared_sum = 0;
			float root_squared_sum;
			
			for(int j=0; j<points_attributes2[i].length; j++){
				squared_sum += Math.pow(points_attributes2[i][j], 2);
			}
			
			root_squared_sum = (float) Math.sqrt(squared_sum);
			if( root_squared_sum > max_value)
				max_value = root_squared_sum;
			
		}
		return max_value;
	}
}

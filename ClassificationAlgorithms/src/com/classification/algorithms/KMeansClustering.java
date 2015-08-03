package com.classification.algorithms;

import java.util.Random;
import java.util.Scanner;

/**
 * K-Means is a clustering algorithm, 
 * Takes the set of points x1, ..., xn as input
 * Also takes the number of clusters K as input
 * Place centroids c1, ..., ck at random locations
 * For each point xi find the nearest centroid Cj, 
 * arg min D(xi, Cj), using the Euclidean distance.
 * distance (e.g Euclidian) between instance xi and cluster cj
 * assign the point xj to the cluster j.
 * For each cluster j = 1 ... K, new centroid cj = mean
 * of all points, assigned to cluster j in previous step
 * @author aditya
 *
 */

public class KMeansClustering {

	static int number_points;
	static int[] point_values;
	static int number_clusters;
	static float[] centroidal_values;
	static int max_point_value;
	static Random rand;
	static float[][] cluster_distances;
	static int[] cluster_points; // maintains the number of points in each cluster
	
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		rand = new Random();
		System.out.println("Enter number of points ");
		number_points = in.nextInt();
		point_values = new int[number_points];
		System.out.println("Enter the 1D coordinates of points");
		for(int i=0; i<number_points; i++)
			point_values[i] = in.nextInt();
		
		// function to find the maximum point values among all the points
		max_point_value = find_max_point(point_values);
		
		System.out.println("Enter the number of clusters");
		number_clusters = in.nextInt();
		centroidal_values = new float[number_clusters];
		
		// assign random values to the centroids
		for(int i=0; i<number_clusters; i++){
			centroidal_values[i] = rand.nextInt(max_point_value);
		}
		
		// prints out the centroidal values
		print_linear(centroidal_values);
		
		// finds out the distance(euclidean) between the points and the centroidal values
		cluster_distances = new float[number_points][number_clusters];
		
		for(int i=0; i<number_points; i++){
			for(int j=0; j<number_clusters; j++){
				cluster_distances[i][j] = Math.abs(point_values[i] - centroidal_values[j]);
			}
		}
		
		// prints out the cluster_distances
		print_cluster(cluster_distances);
		
		// each row is a point that contains values for all the clusters.
		// the minimum value is the cluster, to which the point belongs
		// find out the means of all the points in all the clusters, and accordingly change
		// the centroidal values.
		float[] temp_centroidal_values = new float[number_clusters];
		cluster_points = new int[number_clusters];
		
		for(int i=0; i<cluster_distances.length; i++){
			float min = cluster_distances[i][0];
			int min_value = 0;
			for(int j=1; j<cluster_distances[i].length; j++){
				if(cluster_distances[i][j] < min){
					min = cluster_distances[i][j];
					min_value = j;
				}
				temp_centroidal_values[min_value] += min;
				cluster_points[min_value] += 1;
			}
		}
		// print out the cluster_points
		print_linear_integers(cluster_points);
		
		for(int i=0; i<centroidal_values.length; i++){
			centroidal_values[i] = temp_centroidal_values[i] / cluster_points[i];
		}
		// prints out the centroidal_values
		print_linear(centroidal_values);
		
		in.close();
	}

	
	/**
	 * Prints out the integer values for the cluster
	 * @param cluster_points2
	 */
	private static void print_linear_integers(int[] cluster_points2) {
		// TODO Auto-generated method stub
		System.out.println("---Cluster Points ---");
		for(int i=0; i<cluster_points2.length; i++)
			System.out.print(cluster_points2[i]+" ");
		System.out.println();
	}


	/**
	 * Prints out the 2D cluster distances
	 * @param cluster_distances2
	 */
	private static void print_cluster(float[][] cluster_distances2) {
		// TODO Auto-generated method stub
		for(int i=0; i<cluster_distances2.length; i++){
			for(int j=0; j<cluster_distances2[i].length; j++){
				System.out.print(cluster_distances2[i][j]+" ");
			}
			System.out.println();
		}
	}

	/**
	 * Method for printing the 1D array integer values
	 * @param centroidal_values2
	 */
	private static void print_linear(float[] centroidal_values2) {
		// TODO Auto-generated method stub
		for(int i=0; i<centroidal_values2.length; i++)
			System.out.print(centroidal_values2[i]+" ");
		System.out.println();
	}

	private static int find_max_point(int[] point_values2) {
		// TODO Auto-generated method stub
		int max = point_values2[0];
		for(int i=0; i<point_values2.length; i++)
			if(point_values2[i] > max)
				max = point_values2[i];
		return max;
	}
}

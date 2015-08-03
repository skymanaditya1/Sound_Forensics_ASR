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

public class KMeansAlgorithm {

	static int number_points;
	static int[] points;
	static int number_clusters;
	static int[] centroids;
	static Random rand;
	static float[][] point_centroid_cluster;
	
	public static void main(String[] args){

		rand = new Random();
		Scanner in = new Scanner(System.in);
		System.out.println("Enter the number of points");
		number_points = in.nextInt();
		System.out.println("Enter the points ");
		// Assuming each point has only one attribute
		points = new int[number_points];
		for(int i=0; i<points.length; i++)
			points[i] = in.nextInt();
		// Find out the maximum value of the point in the dataset
		int max = find_max(points);
		System.out.println("Enter the number of clusters");
		// Assigning random values to the k centroids
		number_clusters = in.nextInt();
		centroids = new int[number_clusters];
		// For each point we have to calculate which cluster it 
		// belongs to, by using the euclidean distance
		for(int i=0; i<centroids.length; i++){
			centroids[i] = rand.nextInt(max); 
		}
		
		point_centroid_cluster = new float[number_clusters][number_points];
		
		// for each point calculate the distance from each cluster
		for(int i=0; i<number_clusters; i++){
			for(int j=0; j<number_points; j++){
				// find out the euclidean distance between
				// the ith centroid and jth point
				point_centroid_cluster[i][j] = find_distance(centroids[i], points[j]);
			}
		}
		
		display(point_centroid_cluster);
		
		in.close();
	}

	// DIsplay the point_centroid_cluster
	private static void display(float[][] point_centroid_cluster2) {
		// TODO Auto-generated method stub
		for(int i=0; i<point_centroid_cluster.length; i++){
			for(int j=0; j<point_centroid_cluster[i].length; j++){
				System.out.println(point_centroid_cluster[i][j]+" ");
			}
			System.out.println();
		}
	}

	// Finds out the distance between the cluster centroid
	// and the point
	private static float find_distance(int i, int j) {
		// TODO Auto-generated method stub
		
		float val = (float) Math.sqrt(Math.pow((i-j), 2));
		
		return val;
	}

	// finds out the maximum value of the point
	private static int find_max(int[] points2) {

		// TODO Auto-generated method stub
		int max  = points2[0];
		for(int i=1; i<points2.length; i++){
			if(points2[i] > max){
				max = points2[i];
			}
		}
		return max;
	}
}
 
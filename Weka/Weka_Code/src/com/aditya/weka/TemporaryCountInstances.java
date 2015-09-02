package com.aditya.weka;

import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Java Program for counting the number of instances in an arff file
 * @author aditya
 *
 */

public class TemporaryCountInstances {
	
	public static final String file_name = "/home/aditya/CCBD_Sound_Internship/Sound1/Airplane/1.arff";
	
	public static void main(String[] args) throws Exception{
		DataSource source = new DataSource(file_name);
		Instances dataset = source.getDataSet();
		
		// Get the number of Instances in the given file
		int num_Instances = dataset.numInstances();
		
		System.out.println("The number of Instances in the given file is "+num_Instances);
		
	}
}

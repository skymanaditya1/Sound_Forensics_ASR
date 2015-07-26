package com.aditya.weka;

import java.io.PrintWriter;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Displays the value of the class Attribute for each Instance
 * Also saves the result to a text file
 * @author aditya
 *
 */

public class DispClassAttribute {

	public static String absolute_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		// store the data set in an instance object
		DataSource source = new DataSource(absolute_path+"/weather.numeric.arff");
		Instances dataset = source.getDataSet();
		
		if(dataset.classIndex() == -1){
			dataset.setClassIndex(dataset.numAttributes() - 1);
		}
		// Saving the contents to a file, overrides the file_path if present
		PrintWriter writer = new PrintWriter(absolute_path+"/class_values_weather.txt", "UTF-8");
		
		// get the value of class attribute for each instance
		int num_Instances = dataset.numInstances();
		for(int i=0; i<num_Instances; i++){
			Instance instance = dataset.instance(i);
			double class_Value = instance.classValue();
			System.out.println(instance.classAttribute().value((int)class_Value));
			writer.println(""+instance.classAttribute().value((int)class_Value));
		}
		writer.close();
	}
}

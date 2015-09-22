package com.aditya.weka;

import java.io.PrintWriter;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * This class demonstrates the use of the weka API 
 * in Windows
 * @author Aditya
 *
 */

public class WekaTestingWindowsCode {

	private static String path_name = "G:/CCBD SoundForensics/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		DataSource source = new DataSource(path_name+"/weather.numeric.arff");
		Instances dataset = source.getDataSet();
		
		dataset.setClassIndex(dataset.numAttributes() - 1);
		
		PrintWriter writer = new PrintWriter(path_name+"/weather.numeric.class.txt", "UTF-8");
		
		// print out the class values
		int num_instances = dataset.numInstances();
		for(int i=0; i<num_instances; i++){
			Instance instance = dataset.instance(i);
			double class_value = instance.classValue();
			System.out.println(instance.classAttribute().value((int)class_value));
			writer.println(instance.classAttribute().value((int)class_value));
		}
		
		writer.close();
		System.out.println("Class values written successfully");
	}
}

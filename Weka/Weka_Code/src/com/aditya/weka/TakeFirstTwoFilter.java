package com.aditya.weka;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 * Removes all the attributes except for the first two attributes
 * @author aditya
 *
 */

public class TakeFirstTwoFilter {

	// your path here
	public static String abs_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka"; 
	
	public static void main(String[] args) throws Exception{
		// load the data set in an instances object
		DataSource source = new DataSource(abs_path+"/iris.arff");
		Instances dataset = source.getDataSet();
		
		Remove remove = new Remove();
		String[] opts = {"-R", "3-5"}; // {"-R", "1-2", "-V"} inverts
		remove.setOptions(opts);
		remove.setInputFormat(dataset);
		
		Instances newData = Filter.useFilter(dataset, remove);
		
		// save it in an arff file
		ArffSaver arff_saver = new ArffSaver();
		arff_saver.setInstances(newData);
		arff_saver.setFile(new File(abs_path+"/iris_two_attributes.arff"));
		arff_saver.writeBatch();
		
		// commit success message
		System.out.println("Attributes removed successfully");
	}
}

package com.aditya.weka;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 * Written on linux
 * removes a range of attributes from a dataset using remove
 * @author aditya
 *
 */

public class AttributeFiler {

	public static void main(String[] args) throws Exception{
		
		// load dataset and instances
		DataSource source = new DataSource("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/iris.arff");
		Instances instance = source.getDataSet();
		
		// Set the options and the dataset for the filter
		String[] opts = new String[]{"-R", "1"};
		Remove remove = new Remove();
		remove.setOptions(opts);
		remove.setInputFormat(instance);
		Instances newData = Filter.useFilter(instance, remove);
		
		// save newData to arff file
		ArffSaver arff_saver = new ArffSaver();
		arff_saver.setInstances(newData);
		arff_saver.setFile(new File("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/iris_remove_filter.arff"));
		arff_saver.writeBatch();
		
		// print success message
		System.out.println("Attribute removed successfully");
	}
}

package com.aditya.weka;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;

public class DiscretizeAttributes {

	public static void main(String[] args) throws Exception{
		// load the data set in an instance object
		DataSource source = new DataSource("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/iris.arff");
		Instances dataset = source.getDataSet();
		
		Discretize discretize = new Discretize();
		
		// create a string array of options, B -> interval, R -> range of attributes
		String[] opts = {"-B", "5", "-R", "1-2"};
		discretize.setOptions(opts);
		discretize.setInputFormat(dataset);
		
		Instances newData = Filter.useFilter(dataset, discretize);
		
		// save it into arff file
		ArffSaver arff_saver = new ArffSaver();
		arff_saver.setInstances(newData);
		arff_saver.setFile(new File("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/iris_discretize.arff"));
		arff_saver.writeBatch();
		
		// display the success message
		System.out.println("Attributes discretized successfully");
	}
}

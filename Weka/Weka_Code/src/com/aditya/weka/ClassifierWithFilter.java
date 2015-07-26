package com.aditya.weka;

import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.Remove;

/**
 * Builds a classifier with filter
 * @author aditya
 *
 */

public class ClassifierWithFilter {
	
	public static String abs_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		// load the dataset in an instance object
		DataSource source = new DataSource(abs_path+"/iris.arff");
		Instances dataset = source.getDataSet();
		
		// set the class index to the last attribute if not set
		if(dataset.classIndex() == -1)
			dataset.setClassIndex(dataset.numAttributes() - 1);
		
		// classifier J48
		J48 tree = new J48();
		
		// filter 
		Remove filter = new Remove();
		String[] opts = {"-R", "1"};
		filter.setOptions(opts);
		
		// filtered classifier object
		FilteredClassifier f_c = new FilteredClassifier();
		f_c.setClassifier(tree);
		f_c.setFilter(filter);
		f_c.buildClassifier(dataset);
		
		System.out.println(f_c.graph());
	}
}

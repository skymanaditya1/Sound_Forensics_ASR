package com.aditya.weka;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Save and load a model using the j48 classifier
 * on the iris dataset
 * @author aditya
 *
 */

public class LoadSaveModelj48 {

	public static String abs_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		// load the dataset for training
		DataSource source = new DataSource(abs_path+"/iris.arff");
		Instances traindataset = source.getDataSet();
		
		// set the class index to the last attribute
		traindataset.setClassIndex(traindataset.numAttributes() - 1);
		
		// build the classifier
		J48 tree = new J48();
		tree.buildClassifier(traindataset);
		
		weka.core.SerializationHelper.write(abs_path+"/j48_model_iris.model", tree);
		
		// print success message
		System.out.println("Success");
	}
}

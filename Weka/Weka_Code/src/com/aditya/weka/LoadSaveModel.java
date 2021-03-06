package com.aditya.weka;

import weka.classifiers.functions.SMOreg;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * uses the SerializationHelper class available in the 
 * weka.core package for loading and saving a model.
 * this class uses the SMOreg for classification and 
 * uses the iris_numeric for training a model and 
 * uses the iris_numeric_unknown for testing the model/
 * @author aditya
 *
 */

public class LoadSaveModel {

	public static String abs_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		// load the dataset for training the model
		DataSource source = new DataSource(abs_path+"/iris_numeric.arff");
		Instances traindata = source.getDataSet();
		
		// set the class index to the last attribute
		traindata.setClassIndex(traindata.numAttributes() - 1);
		
		// build the classifier
		SMOreg smo = new SMOreg();
		smo.buildClassifier(traindata);

		// print the model
		System.out.println(smo);
		
		// save the model
		weka.core.SerializationHelper.write(abs_path+"/smo_model_iris.model", smo);

		// load the model for testing the data
		DataSource source1 = new DataSource(abs_path+"/iris_numeric_unknown.arff");
		Instances testdata = source1.getDataSet();
		
		// set the class index to the last attribute
		testdata.setClassIndex(testdata.numAttributes() - 1);
		
		// load the trained model for testing
		SMOreg smo_trained = (SMOreg) weka.core.SerializationHelper.read(abs_path+"/smo_model_iris.model");
		
		// predict the class value for the first instance using the model
		// get the double value for the first instance
		Instance instance = testdata.instance(0);
		double original_value = instance.classValue();
		
		double predSmo = smo_trained.classifyInstance(instance);
		
		System.out.println(original_value+" "+predSmo);
	}
}

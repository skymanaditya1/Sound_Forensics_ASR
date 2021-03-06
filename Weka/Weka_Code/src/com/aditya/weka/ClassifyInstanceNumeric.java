package com.aditya.weka;

import weka.classifiers.functions.SMOreg;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * classifies the instances if the class value is numeric
 * using SMOreg (Sequential Minimal Optimization) regression.
 * Uses the iris_numeric data set for training and the unknown iris
 * dataset for testing the classifier.
 * @author aditya
 *
 */

public class ClassifyInstanceNumeric {

	public static String abs_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		// load the dataset for training
		DataSource source = new DataSource(abs_path+"/iris_numeric.arff");
		Instances traindata = source.getDataSet();
		
		// set the classindex to the last attribute
		traindata.setClassIndex(traindata.numAttributes() - 1);
		
		// load the dataset for testing
		DataSource source1 = new DataSource(abs_path+"/iris_numeric_unknown.arff");
		Instances testdata = source1.getDataSet();
		
		// set the classindex to the last attribute
		testdata.setClassIndex(testdata.numAttributes() - 1);
		
		// build the classifier
		SMOreg smo = new SMOreg();
		smo.buildClassifier(traindata);
		
		// observed value and predicted value
		System.out.println("Observed , Predicted");
		
		// predict the class value for individual instances
		for(int i=0; i<testdata.numInstances(); i++){
			// create an instance object for each individual instance
			Instance instance = testdata.instance(i);
			
			double observedValue = instance.classValue();
			
			// predict the value using the smo classifier
			double predictedValue = smo.classifyInstance(instance);
			
			System.out.println(observedValue+" "+predictedValue);
		}
	}
}

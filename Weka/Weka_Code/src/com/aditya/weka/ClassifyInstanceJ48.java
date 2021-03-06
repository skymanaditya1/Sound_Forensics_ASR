package com.aditya.weka;

import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Classifies the individual instances
 * using the J48 model for classification. 
 * @author aditya
 *
 */

public class ClassifyInstanceJ48 {

	public static String abs_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		// load the dataset for training
		DataSource source = new DataSource(abs_path+"/weather.numeric.arff");
		Instances traindata = source.getDataSet();
		
		// set the class index to the last attribute
		traindata.setClassIndex(traindata.numAttributes() - 1);
		
		// print the class value with the class index
		for(int i=0; i<traindata.numClasses(); i++){
			String classValue = traindata.classAttribute().value(i);
			System.out.println("Class index "+" value "+classValue);
		}
		
		// build the classifier
		J48 tree = new J48();
		tree.buildClassifier(traindata);
		
		// build the data set for testing
		DataSource source1 = new DataSource(abs_path+"/weather.numeric.unknown.arff");
		Instances testdata = source1.getDataSet();
		
		// set the class index to the last attribute
		testdata.setClassIndex(testdata.numAttributes() - 1);
		
		// original and predicted
		System.out.println("original, predicted");
		
		// predict the class value for all the instances
		for(int i=0; i<testdata.numInstances(); i++){
			Instance instance = testdata.instance(i);
			
			// get the original class value for individual instances
			double class_index = instance.classValue();
			String originalClass = testdata.classAttribute().value((int)class_index);
			
			double predIndex = tree.classifyInstance(instance);
			String predClass = testdata.classAttribute().value((int)predIndex);
			
			System.out.println(originalClass+" "+predClass);
		}
	}
}

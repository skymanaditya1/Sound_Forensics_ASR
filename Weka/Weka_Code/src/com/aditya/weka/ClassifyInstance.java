package com.aditya.weka;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Uses the Naive bayes classifier to predict 
 * the class value for each instance in an unknown dataset
 * @author aditya
 *
 */

public class ClassifyInstance {

	public static String abs_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		// load the dataset for training
		DataSource source = new DataSource(abs_path+"/iris.arff");
		Instances traindata = source.getDataSet();
		
		// set the class index to the last attribute
		traindata.setClassIndex(traindata.numAttributes() - 1);
		
		// for each classindex attribute print the class index and value
		for(int i=0; i<traindata.numClasses(); i++){
			System.out.println("Class index "+i+" value "+traindata.classAttribute().value(i));
		}
		
		// build the classifier
		NaiveBayes nb = new NaiveBayes();
		nb.buildClassifier(traindata);
		
		// load the dataset for testing
		DataSource source1 = new DataSource(abs_path+"/iris_unknown.arff");
		Instances testdata = source1.getDataSet();
		
		// set the class index to the last attribute
		testdata.setClassIndex(testdata.numAttributes() - 1);
		
		// Original Value Predicted Value
		System.out.println("Original Value , Predicted Value");
		
		// predict the class value for each instance using nb classifier
		for(int i=0; i<testdata.numInstances(); i++){
			Instance instance = testdata.instance(i);
			
			// get the class attribute for each individual instance
			double classI = instance.classValue();
			String classValueOriginal = instance.classAttribute().value((int)classI);
			
			// predict the class value for individual class instance using nb
			double predValue = nb.classifyInstance(instance);
			String classPredicted = instance.classAttribute().value((int)predValue);
			
			System.out.println(classValueOriginal+" "+classPredicted);
		}
	}
}

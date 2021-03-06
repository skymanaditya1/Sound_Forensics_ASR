package com.aditya.weka;

import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.SMO;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Class to demonstrate the capability of the classifiers
 * NaiveBayes, SMO and J48 tree.
 * @author aditya
 *
 */

public class Classification {

	public static String abs_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		// load the data set in an instance object
		DataSource source = new DataSource(abs_path+"/iris.arff");
		Instances dataset = source.getDataSet();
		
		if( dataset.classIndex() == -1 ){
			dataset.setClassIndex(dataset.numAttributes() - 1);
		}
		
		NaiveBayes naive_b = new NaiveBayes();
		naive_b.buildClassifier(dataset);
		System.out.println(naive_b.getCapabilities()); // prints out the capabilities of the classifier
		
		SMO svm = new SMO();
		svm.buildClassifier(dataset);
		System.out.println(svm.getCapabilities());
		
		// set the options for the tree, pruning confidence
		String[] options = {"-C", "0.11", "-M", "3"};

		J48 tree = new J48();
		tree.setOptions(options);
		tree.buildClassifier(dataset);
		System.out.println(tree.getCapabilities());
		System.out.println(tree.graph());
	}
}

package com.aditya.weka;

import java.io.PrintWriter;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Class evaluates using the naive bayes model
 * @author aditya
 *
 */

public class EvaluateNaiveBayes {

	public static String abs_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		// load the training dataset
		DataSource source_train = new DataSource(abs_path+"/iris_train.arff");
		Instances dataset_train = source_train.getDataSet();
		
		// set the class index to the last attribute
		dataset_train.setClassIndex(dataset_train.numAttributes() - 1);
		
		// load the test dataset
		DataSource source_test = new DataSource(abs_path+"/iris_test.arff");
		Instances dataset_test = source_test.getDataSet();
		
		dataset_test.setClassIndex(dataset_test.numAttributes() - 1);
		
		// build the classifier with training data
		NaiveBayes naive = new NaiveBayes();
		naive.buildClassifier(dataset_train);
		
		// build the evaluation model using training data
		Evaluation evaluation = new Evaluation(dataset_train);
		
		// evaluate the model using the test data and classifier
		evaluation.evaluateModel(naive, dataset_test);
		
		// print the summary of evaluation
		System.out.println(evaluation.toSummaryString("Evaluation results\n", false));
		
		// print out the confusion matrix
		System.out.println(evaluation.toMatrixString());
		
		// write the results to a file
		PrintWriter writer = new PrintWriter(abs_path+"/evaluate_model_naive_bayes_iris.txt", "UTF-8");
		writer.println(evaluation.toSummaryString());
		writer.println(evaluation.toMatrixString());
		
		writer.close();
	}
}

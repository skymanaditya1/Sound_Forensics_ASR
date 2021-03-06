package com.aditya.weka;

import java.io.PrintWriter;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Class for evaluating a model given a classifier 
 * and the training and test dataset as input
 * Evaluates using the J48 model
 * @author aditya
 *
 */

public class Evaluatej48 {

	public static String abs_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		// load the training dataset in an instance object
		DataSource train_source = new DataSource(abs_path+"/iris_train.arff");
		Instances train_dataset = train_source.getDataSet();
		
		// set the class index to the last attribute
		if(train_dataset.classIndex() == -1)
			train_dataset.setClassIndex(train_dataset.numAttributes() - 1);
		
		// load the test dataset in an instance object
		DataSource test_source = new DataSource(abs_path+"/iris_test.arff");
		Instances test_dataset = test_source.getDataSet();
		
		// set the class index to the last attribute
		if(test_dataset.classIndex() == -1)
			test_dataset.setClassIndex(test_dataset.numAttributes() - 1);
		
		// build the classifier with train_dataset
		J48 tree = new J48();
		tree.buildClassifier(test_dataset);
		
		// build evaluation model with training data
		Evaluation evaluation = new Evaluation(train_dataset);
		
		// evaluate the model with the classifier and test_dataset
		evaluation.evaluateModel(tree, test_dataset);
		
		// print summary
		System.out.println(evaluation.toSummaryString("Evaluation results: \n", false));
		
		// prints out the confusion matrix
		System.out.println(evaluation.toMatrixString("The confusion matrix is : \n"));
		
		// Store the results in a file
		PrintWriter writer = new PrintWriter(abs_path+"/evaluate_model_j48_iris", "UTF-8");
		writer.println(evaluation.toSummaryString("Evaluation results:\n", false));
		writer.println(evaluation.toMatrixString());
		writer.close();
	}
}

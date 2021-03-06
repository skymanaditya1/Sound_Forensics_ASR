package com.aditya.weka;

import java.io.PrintWriter;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * evaluate the model for folds using the 
 * J48 tree classifier using the iris dataset
 * @author aditya
 *
 */

public class FoldsJ48 {

	public static String abs_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		// load the training dataset
		DataSource source = new DataSource(abs_path+"/iris.arff");
		Instances dataset = source.getDataSet();
		
		// set the class index to the last attribute
		dataset.setClassIndex(dataset.numAttributes() - 1);
		
		// build the classifier
		J48 tree = new J48();
		
		// parameters for the cross validation
		int seed = 1;
		int folds = 10;
		
		Random rand = new Random(seed);
		
		// randomize the dataset
		Instances randData = new Instances(dataset);
		randData.randomize(rand);
		
		// stratify the dataset if the class attribute is nominal
		if(randData.classAttribute().isNominal())
			randData.stratify(folds);
		
		// store the evaluation results in a file
		PrintWriter writer = new PrintWriter(abs_path+"/cross_validate_j48_iris.txt", "UTF-8");
		writer.println("Evaluation statistics for J48 classifier : ");
		
		// perform cross validation for each fold
		for(int i=0; i<folds; i++){
			// build the evaluation model
			Evaluation evaluation = new Evaluation(randData);
			
			// build the training data and the test dataset
			Instances train = randData.trainCV(folds, i);
			Instances test = randData.testCV(folds, i);
			
			// build the classifier
			tree.buildClassifier(train);
			
			// evaluate the model for each fold
			evaluation.evaluateModel(tree, test);
			
			// print the evaluation results
			writer.println("Statistics for fold "+(i+1)+"/"+folds);
			writer.println(evaluation.toSummaryString()); // prints summary
			writer.println(evaluation.toMatrixString()); // prints confusion matrix
			
			System.out.println("Statistics for fold "+(i+1)+"/"+folds);
			System.out.println(evaluation.toSummaryString());
			System.out.println(evaluation.toMatrixString());
		}
		
		writer.close();
	}
}

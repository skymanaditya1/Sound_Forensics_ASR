package com.aditya.weka;

import java.io.PrintWriter;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.SMO;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * evaluates the model with SMO (Sequential Minimal Optimization) 
 * as the classifier and the iris dataset for testing and training.
 * also implements some of the methods available with the evaluate
 * class. Also performs cross validation.
 * the cross validate model performs cross validation for a classifier 
 * on a set of instances
 * @author aditya
 *
 */

public class EvaluateSMO {

	public static String abs_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		// load the dataset for training
		DataSource train_source = new DataSource(abs_path+"/iris_train.arff");
		Instances train_dataset = train_source.getDataSet();
		
		// set the class index to the last attribute
		train_dataset.setClassIndex(train_dataset.numAttributes() - 1);
		
		// load the dataset for testing
		DataSource test_source = new DataSource(abs_path+"/iris_test.arff");
		Instances test_dataset = test_source.getDataSet();
		
		// set the class index to the last attribute
		test_dataset.setClassIndex(test_dataset.numAttributes() - 1);
		
		// build the SMO classifier 
		SMO svm = new SMO();
		svm.buildClassifier(train_dataset);
		
		// load the evaluation model with the training dataset
		Evaluation evaluation = new Evaluation(train_dataset);
		
		// cross evaluation model parameters
		Random rand = new Random(1);
		int folds = 10;
		
		// cross validate the model with SMO classifier and test dataset
		// evaluation.evaluateModel(svm, test_dataset);
		evaluation.crossValidateModel(svm, test_dataset, folds, rand);
		
		// print the results
		System.out.println(evaluation.toSummaryString("Evaluation results :\n", false));
		
		// print out the confusion matrix
		System.out.println(evaluation.toMatrixString());
		
		// useful functions with the evaluation class
		System.out.println("pctCorrect "+evaluation.pctCorrect());
		System.out.println("pctIncorrect "+evaluation.pctIncorrect());
		System.out.println("unclassified "+evaluation.pctUnclassified());
		System.out.println("numInstances "+evaluation.numInstances());
		System.out.println("relAbsError "+evaluation.relativeAbsoluteError());
		System.out.println("rootMeanSquare "+evaluation.rootMeanSquaredError());
		System.out.println("kappa stats "+evaluation.kappa());
		System.out.println("Incorrect "+evaluation.incorrect());
		System.out.println("Correct "+evaluation.correct());
		System.out.println("Error rate "+evaluation.errorRate());
		System.out.println("Area under the ROC curve");
		System.out.println("Area under Iris-setosa class "+evaluation.areaUnderROC(0));
		System.out.println("Area under Iris-versicolor class "+evaluation.areaUnderROC(1));
		System.out.println("Area under Iris-virginica class "+evaluation.areaUnderROC(2));
		System.out.println("Recall of Iris-setosa class "+evaluation.recall(0));
		System.out.println("Precision of Iris-setosa class "+evaluation.precision(0));
		
		// save the results in a file
		PrintWriter writer = new PrintWriter(abs_path+"/cross_evaluate_model_smo_iris.txt", "UTF-8");
		writer.println(evaluation.toSummaryString("Evaluation statistics \n", false));
		writer.println(evaluation.toMatrixString());
		writer.close();
	}
}

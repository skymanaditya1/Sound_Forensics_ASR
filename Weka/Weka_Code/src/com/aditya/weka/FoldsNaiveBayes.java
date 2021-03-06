package com.aditya.weka;

import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * model to train and test the dataset instances
 * by using folds for the naive bayes classifier
 * @author aditya
 *
 */

public class FoldsNaiveBayes {

	public static String abs_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		// load the training dataset 
		DataSource source = new DataSource(abs_path+"/iris.arff");
		Instances training_dataset = source.getDataSet();
		
		// set the class index to the last attribute
		training_dataset.setClassIndex(training_dataset.numAttributes() - 1);
		
		// build the classifier
		NaiveBayes nb = new NaiveBayes();
		
		int seed = 1;
		int folds = 15;
		
		Random rand = new Random(seed);
		
		// randomize the dataset
		Instances randData = new Instances(training_dataset);
		randData.randomize(rand);
		
		// stratify the dataset if the class attribute is nominal 
		if(randData.classAttribute().isNominal())
			randData.stratify(folds);
		
		// evaluate the model for each of the folds
		for(int i=0; i<folds; i++){
			Evaluation evaluation = new Evaluation(randData);
			
			// build the training and test data for each fold
			Instances test = randData.testCV(folds, i);
			Instances train = randData.trainCV(folds, i);
			
			// build the nb classifier
			nb.buildClassifier(train);
			
			// evaluate the model
			evaluation.evaluateModel(nb, test);
			
			// output the evaluation results
			System.out.println(evaluation.toSummaryString("Evaluation Statisitics\n", false));
			
			// print out the confusion matrix for each fold
			System.out.println(evaluation.toMatrixString("Confusion matrix for fold "+(i+1)+"/"+folds));
		}
	}
}

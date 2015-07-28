package com.aditya.weka;

import weka.classifiers.Classifier;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.meta.AdaBoostM1;
import weka.classifiers.meta.Bagging;
import weka.classifiers.meta.Stacking;
import weka.classifiers.meta.Vote;
import weka.classifiers.trees.DecisionStump;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.RandomForest;
import weka.classifiers.trees.RandomTree;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Class for combining models, includes methods
 * such as Bagging, Boosting, Voting and Stacking
 * @author aditya
 *
 */

public class CombineModels {

	public static String file_name = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/weather.nominal.arff";
	
	public static void main(String[] args) throws Exception{
		DataSource source = new DataSource(file_name);
		Instances traindataset = source.getDataSet();
		
		traindataset.setClassIndex(traindataset.numAttributes() - 1);
		
		// Boosting a weak classifier using AdaBoostM1
		// only boosts nominal class classifiers
		AdaBoostM1 m1 = new AdaBoostM1();
		m1.setClassifier(new DecisionStump()); // set the base classifier
		m1.setNumIterations(20);
		m1.buildClassifier(traindataset);
		
		// Bagging to reduce the variance of a classifier
		Bagging bagging = new Bagging();
		bagging.setClassifier(new RandomTree()); // set the base classifier
		bagging.setNumIterations(25);
		bagging.buildClassifier(traindataset);
		
		// Stacking to combine several models
		Stacking stack = new Stacking();
		stack.setMetaClassifier(new J48()); // needs one meta model
		Classifier[] classifier = {
				new J48(), 
				new NaiveBayes(),
				new RandomForest()
		};
		stack.setClassifiers(classifier);
		stack.buildClassifier(traindataset);
		
		// Voting used for combining classifiers
		Vote vote = new Vote();
		vote.setClassifiers(classifier); // needs one or more classifier
		vote.buildClassifier(traindataset);
		
		System.out.println(m1.toString());
		System.out.println(bagging.toString());
		System.out.println(stack.toString());
		System.out.println(vote.toString());
	}
}

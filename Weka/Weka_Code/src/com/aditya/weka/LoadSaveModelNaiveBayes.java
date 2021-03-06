package com.aditya.weka;

import java.io.PrintWriter;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * loads and saves the model using the NaiveBayes
 * for classification on the iris_numeric.arff dataset
 * @author aditya
 *
 */

public class LoadSaveModelNaiveBayes {

	public static String abs_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		DataSource source = new DataSource(abs_path+"/iris.arff");
		Instances traindataset = source.getDataSet();
		
		traindataset.setClassIndex(traindataset.numAttributes() - 1);
		
		NaiveBayes nb = new NaiveBayes();
		nb.buildClassifier(traindataset);
		
		// save the model
		weka.core.SerializationHelper.write(abs_path+"/nb_iris_model.model", nb);
		
		DataSource source1 = new DataSource(abs_path+"/iris_nb_model_test.arff");
		Instances testdataset = source1.getDataSet();
		
		testdataset.setClassIndex(testdataset.numAttributes() - 1);
		
		// load the model
		NaiveBayes nb_model = (NaiveBayes) weka.core.SerializationHelper.read(abs_path+"/nb_iris_model.model");
		
		System.out.println("Original Value, Predicted Value");

		PrintWriter writer = new PrintWriter(abs_path+"/model_building_nb.txt", "UTF-8");
		writer.println("Model statistics");
		
		// use the model for classification
		for(int i=0; i<testdataset.numInstances(); i++){
			Instance instance = testdataset.instance(i);
			
			double class_index = instance.classValue();
			String original_value = instance.classAttribute().value((int)class_index);
			
			// predict the value using the model
			double pred_model = nb_model.classifyInstance(instance);
			String pred_value = instance.classAttribute().value((int)pred_model);
			
			System.out.println(original_value+" "+pred_value);
			
			// save the details to a file
			writer.println(original_value+" "+pred_value);
		}
		
		writer.println(nb_model);
		System.out.println(nb_model);
		
		writer.close();
	}
}

package com.aditya.weka;

import java.io.PrintWriter;

import weka.classifiers.functions.LinearRegression;
import weka.classifiers.functions.SMOreg;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Implements Regression models, LinearRegression and SMOreg
 * @author aditya
 *
 */

public class Regression {

	public static String abs_path = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka";
	
	public static void main(String[] args) throws Exception{
		// load the dataset in an instance object
		/**DataSource source = new DataSource(abs_path+"/new.arff");
		Instances dataset = source.getDataSet();
		
		Remove remove = new Remove();
		String[] options = {"-R", "5"};
		remove.setOptions(options);
		remove.setInputFormat(dataset);
		Instances newData = Filter.useFilter(dataset, remove);
		
		//save it in arff file
		ArffSaver arff_saver = new ArffSaver();
		arff_saver.setInstances(newData);
		arff_saver.setFile(new File(abs_path+"/iris_numeric.arff"));
		arff_saver.writeBatch();*/
		
		DataSource source = new DataSource(abs_path+"/iris_numeric.arff");
		Instances dataset = source.getDataSet();
		
		// set the class instance to the last attribute
		if(dataset.classIndex() == -1)
			dataset.setClassIndex(dataset.numAttributes() - 1);
		
		// builds linear regression model
		LinearRegression regression = new LinearRegression();
		regression.buildClassifier(dataset);
		
		System.out.println(regression);
		
		// build the SMOreg model
		SMOreg reg = new SMOreg();
		reg.buildClassifier(dataset);
		
		System.out.println(reg);
		
		// save the results of comparison of linear regression and smoreg models
		PrintWriter writer = new PrintWriter(abs_path+"/comp_linear_smo_regression_iris.txt", "UTF-8");
		writer.println("Comparison between the linear regression and smo regression models "
				+ "on iris numeric only attributes dataset");
		writer.println(regression);
		writer.println(reg);
		
		writer.close();
		
		// print success message
		System.out.println("Comparison file created successfully");
	}
}

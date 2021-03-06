package com.aditya.weka;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;

/**
 * Written in Linux
 * Code converts a CSV (Comma Separated Values) file to ARFF (Attribute Relation File Format) file.
 * @author aditya
 *
 */

public class CSV2Arff {

	public static void main(String[] args) throws Exception{
		
		// load CSV file
		CSVLoader csv_loader = new CSVLoader();
		csv_loader.setSource(new File("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/burden_estimate.csv"));
		Instances instance = csv_loader.getDataSet(); // get Instance object
		
		// save to arff file
		ArffSaver arff_saver = new ArffSaver();
		arff_saver.setInstances(instance);
		arff_saver.setFile(new File("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/burden_estimate_converted.arff"));
		arff_saver.writeBatch();
		
		// display success message
		System.out.println("CSV to ARFF successful");
	}
}

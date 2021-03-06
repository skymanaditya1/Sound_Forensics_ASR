package com.aditya.weka;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.CSVSaver;

/**
 * Written in Linux
 * Class demonstrates the reading of ARFF (Attribute Relation File Format) file 
 * and saving it in CSV (Comma Separated Values) file format.
 * @author aditya
 *
 */

public class Arff2CSV {

	public static void main(String[] args) throws Exception{
		// read arff file
		ArffLoader arff_loader = new ArffLoader();
		arff_loader.setSource(new File("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/iris.arff"));
		Instances instance = arff_loader.getDataSet(); // get instance object
		
		// save to csv file
		CSVSaver csv_saver = new CSVSaver();
		csv_saver.setInstances(instance);
		csv_saver.setFile(new File("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/iris_converted.csv"));
		csv_saver.writeBatch();
		
		// print success message
		System.out.println("Arff to Csv written successfully");
	}
}

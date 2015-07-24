package com.aditya.weka;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import weka.core.Instances;
import weka.core.converters.ArffSaver;

/**
 * Written on Linux environment
 * Class demonstrates loading and saving of arff (attribute relation file format) file by weka
 * @author aditya
 *
 */

public class LoadSaveData {

	public static void main(String[] args) throws Exception{
		FileReader file_reader = new FileReader("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/iris.arff");
		BufferedReader buffer_reader = new BufferedReader(file_reader);
		Instances instance = new Instances(buffer_reader);
		System.out.println(instance.toSummaryString());
		ArffSaver saver = new ArffSaver();
		saver.setInstances(instance);
		File file = new File("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/new.arff");
		saver.setFile(file);
		saver.writeBatch();
	}
}

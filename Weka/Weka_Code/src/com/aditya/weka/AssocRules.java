package com.aditya.weka;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Association Rules used for finding correlations, 
 * associations amd patterns
 * @author aditya
 *
 */

public class AssocRules {

	public static String filename = "/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/weather.nominal.arff";
	
	public static void main(String[] args) throws Exception{
		DataSource source = new DataSource(filename); 
		Instances traindataset = source.getDataSet();
		
		Apriori apriori = new Apriori();
		apriori.buildAssociations(traindataset);
		
		System.out.println(apriori);
	}
}

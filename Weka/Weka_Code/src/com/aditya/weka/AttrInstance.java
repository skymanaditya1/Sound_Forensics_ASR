package com.aditya.weka;

import weka.core.AttributeStats;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.experiment.Stats;

/**
 * Written in Linux
 * Class demonstrates the use of Attribute, 
 * AttributeStats and Instances class in Weka
 * @author aditya
 */

public class AttrInstance {

	public static void main(String[] args) throws Exception{
		// get the dataset in an instance object
		DataSource source = new DataSource("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/weather.numeric.arff");
		Instances dataset = source.getDataSet();
		
		// if the class index is not defined set it to the last attribute
		if( dataset.classIndex() == -1){
			dataset.setClassIndex(dataset.numAttributes() - 1);
		}
		
		int numAttributes = dataset.numAttributes() - 1;
		for(int i=0; i<numAttributes; i++){
			// check if the attribute is nominal
			if(dataset.attribute(i).isNominal()){
				System.out.println("The "+i+"th Attribute is Nominal");
				int num_values = dataset.attribute(i).numValues();
				System.out.println("The "+i+"th Attribute has "+num_values+" values");
			}
			
			AttributeStats attr_stats = dataset.attributeStats(i);
			
			// get a stats attribute from attr_stats
			if(dataset.attribute(i).isNumeric()){
				Stats stats = attr_stats.numericStats;
				System.out.println("The "+i+"th attribute is Numeric");
				System.out.println("The max numeric value is "+stats.max+" "
						+ "and min numeric value is "+stats.min+" and std dev is "
								+ stats.stdDev);
			}
			
			int distinct_count = attr_stats.distinctCount;
			int missing_count = attr_stats.missingCount;
			int total_count = attr_stats.totalCount;
			
			System.out.println("The "+i+"th attribute has "+distinct_count+" "
					+ "distinct count, "+missing_count+" missing count and "+
					total_count+" total count.");
			
		}
		
		// get information about the instances
		int num_Inst = dataset.numInstances();
		for(int i=0; i<num_Inst; i++){
			Instance instance = dataset.instance(i);
			
			// check if the missing_attribute is missing for the ith instance
			int missing_attribute = 0;
			if(instance.isMissing(missing_attribute)){
				System.out.println("The "+missing_attribute+" "
						+ "is missing from the "+i+"th instance");
			}
			
			// check if the class is missing in the ith instance
			if(instance.classIsMissing()){
				System.out.println("The attribute class is missing from "+
						i+ "th instance");
			}
			
			// if we want to access the class values for each of the instance
			int class_value = (int)instance.classValue();
			System.out.println(instance.classAttribute().value(class_value));
		}
	}
}

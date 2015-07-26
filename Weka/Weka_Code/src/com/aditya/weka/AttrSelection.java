package com.aditya.weka;

import java.io.File;

import weka.attributeSelection.CfsSubsetEval;
import weka.attributeSelection.GreedyStepwise;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.supervised.attribute.AttributeSelection;

/**
 * Written in Linux
 * Applies AttributeSelection using the evaluator and search 
 * Removes irrelevant attributes which 
 * decreases the efficiency of algorithms in weka
 * @author aditya
 */

public class AttrSelection {

	public static void main(String[] args) throws Exception{
		// load the data set instance
		DataSource source = new DataSource("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/iris.arff");
		Instances dataset = source.getDataSet();
		
		AttributeSelection filter = new AttributeSelection();
		
		// set the search and evaluator objects for the algorithm attribute selection
		CfsSubsetEval eval = new CfsSubsetEval();
		GreedyStepwise search = new GreedyStepwise();
		
		// enable the search backwards
		search.setSearchBackwards(true);
		
		// set the filter to use the evaluator and search algorithms
		filter.setEvaluator(eval);
		filter.setSearch(search);
		filter.setInputFormat(dataset);
		
		Instances newData = Filter.useFilter(dataset, filter);
		
		// save it to arff file
		ArffSaver arff_saver = new ArffSaver();
		arff_saver.setInstances(newData);
		arff_saver.setFile(new File("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/iris_attributeselection.arff"));
		arff_saver.writeBatch();
		
		// display the success message
		System.out.println("Attribute selection applied successfully");
	}
}

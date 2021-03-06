package com.aditya.weka;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.NonSparseToSparse;

/**
 * Written in Linux
 * Class for converting NonSparseToSparse Format
 * @author aditya
 *
 */

public class Sparse {

	public static void main(String[] args) throws Exception{
		// get the dataset instance
		DataSource source = new DataSource("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/iris.arff");
		Instances dataset = source.getDataSet();
		
		// create an object of NonSparseToSparse class
		NonSparseToSparse sp = new NonSparseToSparse();
		sp.setInputFormat(dataset);
		
		// store the result of the filter in newData
		Instances newData = Filter.useFilter(dataset, sp);
		
		// save into the arff file
		ArffSaver arff_saver = new ArffSaver();
		arff_saver.setInstances(newData);
		arff_saver.setFile(new File("/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Weka/iris_sparse.arff"));
		arff_saver.writeBatch();
		
		// display the success message
		System.out.println("Non Sparse File Converted to Sparse Successfully");
	}
}

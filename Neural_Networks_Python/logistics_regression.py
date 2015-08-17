'''

Code for implementing the logistic regression using numpy.
The program takes the feature_matrix as input of dimensions
number_observations * number_features and the result_matrix
of dimensions number_observations * 1
The most optimal value of weights is found using the formula,
inverse(transpose(feature_matrix) * feature_matrix) * transpose(feature_matrix)
* result_matrix
This method of finding the most optimal weights is called as the normal equation
method.

'''

import numpy as np

file_path = '/home/aditya/CCBD_Sound_Internship/Sound_Forensics_ASR_Git/Neural_Networks_Python/logistics_regression_input.txt'

number_observations = 4
number_features = 4

feature_matrix = [[3,1,5,7],
		  [4,2,7,1],
		  [9,4,1,8],
		  [3,1,5,3]]

result_matrix = [3,4,1,6]

weights = np.linalg.inv(np.transpose(feature_matrix).dot(feature_matrix)).dot(np.transpose(feature_matrix)).dot(result_matrix)

print(weights)

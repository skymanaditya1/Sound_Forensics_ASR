Let me walk you through my repo:

1. The first folder contains K means algorithm as a classification algorithm, I have implemented the code myself for classification on data values a single attribute (in 1 Dimension) and with multiple attributes (in n Dimension). Since I have written the code from scratch I cannot guarantee on its correctness, since I haven't really deployed the algorithm in real time environments. 

2. Second is HMM_Forward_Viterbi_Algorithm which is my own implementation of the Hidden Markov Model's Forward Algorithm (for finding out the forward path probabilities) and Viterbi Algorithm for finding out the best sequence of hidden states given an observation sequence. 

3. Third is MFCC_ARFF. I till now talked about classification, but never talked about what property of sound I am trying to classify. I am using the Mel Frequency Cepstral Coefficients (MFCC) of sound files and converting them into arff file (which is accepted as input by weka - a popular machine learning software) to train a model (I get a .model file as output). 
Inside the folder you can find arff_mfcc_generation.py which is the python code for generating mfcc attributes of a given sound sample and storing it into arff (attribute relation file format) files. Also present are folders containing the arff files (of mfcc's) of some common sounds. 

4. Fourth is I have tried to extract the MFCC Features using Matlab as the software. I follow the general procedure of extracting the mfcc's from a given sound file (I never really wrote the entire code). You can find how to generate mfcc of a sound file from this link -> http://practicalcryptography.com/miscellaneous/machine-learning/guide-mel-frequency-cepstral-coefficients-mfccs/
and the book chapter 7 of Martin Jurafsky. 

5. Fifth is Neural_Networks_Python. Tried to implement the backpropagation algorithm with feed forward propagation algorithm in python. (The code is incomplete). 

6. Sixth is Parts of Speech Tags, in this case I used the already implemented Stanford Parts of Speech Tags algorithms, which accepts a file containing English sentences as input and gives as output a file containing the English sentences with words attached to their corresponding parts of speech tag delimited using '_'.

7. Seventh is Weka - This folder contains some of the standard arff files provided for training and testing codes written using the weka api. It also contains some .model files whose working is context dependent (restricted to my pc). It also contains a folder Weka_Code which contains java classes that use the Weka api to implement a variety of tasks. Please look at the individual class for further documentation. 

8. BaumWelchAlgorithm.java is a java class (a part of Hidden Markov Model) that is used for finding the most optimal transition probability matrix and emission probability matrix (The code is incomplete).

9. Linear_Regression_WeightMatrix.java finds out the most optimal set of weights, given the training set (input) and output. 

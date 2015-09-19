#
# Imports
#

import numpy as np

class BackPropagationNetwork:
	''' A back-propagation network'''
	
	#
	# Class members
	#
	layerCount = 0 # Number of layers in the network
	shape = None
	weights = [] # Each set of weights is a matrix (numpy array), called theta
	
	#
	# Class methods
	#
	
	def __init__(self, layerSize): # layerSize can be a list or tuple
		''' Initialize the network'''
		
		#
		# Layer info
		#
		self.layerCount = len(layerSize) - 1 # Not counting the input layer
		self.shape = layerSize
		
		#
		# Input / Output data from the last Run
		#
		
		self._layerInput = []
		self._layerOutput = []
		
		# Create the weight arrays
		for (l1, l2) in zip(layerSize[:-1], layerSize[1:]):
			self.weights.append(np.random.normal(scale=0.01, size=(l2, l1+1)))
		
		#
		# Run method
		#
		
	def Run(self, input):
		''' Run the network based on the input data 
		The input for each training example becomes the input to the activation values of the first layer'''
		lnCases = input.shape[0]
			
		# Clear out the previous intermediate value lists
			
		self._layerInput = []
		self._layerOuput = []
			
		# Run it!
		for index in range(self.layerCount):
			# Determine the layer input
			if index == 0:
				layerInput = self.weights[0].dot(np.vstack([input.T, np.ones([1, lnCases])]))
			else:
				layerInput = self.weights[index].dot(np.vstack([self._layerOutput[-1], np.ones([1, lnCases])]))
					
			self._layerInput.append(layerInput)
			self._layerOutput.append(self.sgm(layerInput))
				
		return self._layerOutput[-1].T
			
	#
	# Transfer functions
	#
	def sgm(self, x, Derivative=False):
		if not Derivative:
			return 1 / (1+np.exp(-x)) # If not derivative
		else:
			out = self.sgm(x)
			return out*(1-out) # return self.sgm(x) * (1-self.sgm(x))
				
	
#
# If run as a script, create a test object
#

if __name__ == "__main__":
	bpn = BackPropagationNetwork((2,2,1)) # number of units in the input layer -> 2, number of units in the first hidden layer (i.e layer 2) -> 2, number of units in the output layer (i.e layer 3) -> 1
	print(bpn.layerCount)
	print(bpn.shape)
	print(bpn.weights)
	
	lvInput = np.array([[0,0], [1, 1], [-1, 0.5]])
	lvOutput = bpn.Run(lvInput)
	
	print("input: {0}\nOutput: {1}".format(lvInput, lvOutput))
		
		


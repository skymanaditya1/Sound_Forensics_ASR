import numpy as np

class BackPropagationNetwork:
	''' Class members'''
	
	layerCount = 0
	weights = []
	shape = None
	
	def __init__(self, layerSize):
		self.layerCount = len(layerSize)
		self.shape = layerSize
		
		self._layerInput = []
		self._layerOutput = []
		
		# Create a matrix of weights which is a mapping of functions from layer j to layer j+1
		for (l1, l2) in zip(layerSize[:-1], layerSize[1:]):
			self.weights.append(np.random.normal(scale=0.1, size=(l2, l1+1)))
			
		
# Create a class object, if run as a script
if __name__ == "__main__":
 	bpn = BackPropagationNetwork((2, 2, 1))
 	
 	print(bpn.layerCount)
 	print(bpn.weights)
 	print(bpn.shape)
		

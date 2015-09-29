import numpy as np

class NeuralNetwork(object):

	# 2 units in the input layer, 3 units in the hidden layer and 1 unit in the output layer
	def __init__(self):
		self.inputLayerSize = 2
		self.hiddenLayerSize = 3
		self.outputLayerSize = 1
		
		self.W1 = np.random.randn(self.inputLayerSize, self.hiddenLayerSize)
		self.W2 = np.random.randn(self.hiddenLayerSize, self.outputLayerSize)
	
	def sigmoid(self, x):
		# returns the sigmoid of a value
		return 1 / (1 + np.exp(-x))
		
	def forward_propagation(self, X):
		# Accepts training examples as input
		
		# Calculates the activation values of units in each layer
		z2 = np.dot(X, self.W1) 
		a2 = self.sigmoid(z2) # Calculates the activation of units in layer 2
		z3 = np.dot(a2, self.W2)
		yHat = self.sigmoid(z3) # calculates the activation of the units in the output layer
		
		return yHat
		
if __name__ == "__main__":
	# Training examples
	nn = NeuralNetwork()
	
	inp = np.array([[3,5],
						 [5,1],
						 [10,2]])
	a3 = nn.forward_propagation(inp)
	print("The predicted values are",a3)
								

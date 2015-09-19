import numpy as np

''' Python class for evaluating the sigmoidal function '''

class SigmoidalClass:
	# Class members
	
	value = 0
	
	def __init__(self, x):
		self.value = x
		
	def sigmoidal_function(self, x):
		return 1 / (1+np.exp(-x))
		

# If run as a script, create a test object

if __name__ == "__main__":

	x = int(input("Enter the value of x : "))
	sg = SigmoidalClass(x)
	
	print(sg.sigmoidal_function(x))

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
	
	


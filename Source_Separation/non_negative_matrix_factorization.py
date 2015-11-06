import numpy as np

error_threshold = 0.001

def matrix_factorization(P, Q, R, K, steps = 20000, alpha = 0.0002, beta = 0.02):
	Q = Q.T
	for step in range(steps):
		for i in range(len(R)):
			for j in range(len(R[i])):
				if R[i][j] > 0:
					eij = R[i][j] - np.dot(P[i,:], Q[:,j])
					for k in range(K):
						P[i][k] = P[i][k] + alpha * (2 * eij * Q[k][j] - beta * P[i][k])
						Q[k][j] = Q[k][j] + alpha * (2 * eij * P[i][k] - beta * Q[k][j])

		eR = np.dot(P, Q)
		# Modify the value of the error
		e = 0
		for i in range(len(R)):
			for j in range(len(R[i])):
				if R[i][j] > 0:
					e = e + pow(R[i][j] - np.dot(P[i,:], Q[:,j]), 2)
					for k in range(K):
						e = e + (beta / 2) * (pow(P[i][k], 2) + pow(Q[k][j], 2))	

		# if the error is less than 0.001, then break
		# also print the step number at which the error diminished
		if e < error_threshold:
			print("Error diminished at step", step)
			break

	return P, Q.T

def display(R):
	for i in range(len(R)):
		for j in range(len(R[i])):
			print(R[i][j], end=" ")
		print(end="\n")

if __name__ == "__main__":
	R = [
	[5,3,0,1],
	[4,0,0,1],
	[1,1,0,5],
	[1,0,0,4],
	[0,1,5,4],
	]

	num_rows = len(R)
	num_cols = len(R[0])
	# Enter the dimensions of the matrix
	
	"""num_rows = int(input("Enter the no of rows in the matrix : "))
	num_cols = int(input("Enter the no of cols in the matrix : "))

	print("Enter the contents of the matrix ")
	for i in range(num_rows):
		R.append([])
	for i in range(len(R)):
		for j in range(num_cols):
			R[i].append(int(input()))
	"""

	# Convert the matrix R into a numpy array matrix
	R = np.array(R)
	
	# Take the number of features as input
	K = int(input("Enter the number of features : "))

	# Prepare the matrices P and Q
	# P is of dimension num_rows X K
	# Q is of dimension num_cols X K
	# Randomize the matrices P and Q
	P = np.random.randn(num_rows, K)
	Q = np.random.randn(num_cols, K)

	# Enter the number of steps / iteration
	"""steps = int(input("Enter the number of epochs : "))
	"""

	# matrix_factorization returns the modified matrices P and Q
	# alpha is the learning rate
	# beta is the regularization factor
	
	print("Factorizing the matrix, Please Wait ...")
	nP, nQ = matrix_factorization(P, Q, R, K)

	print("Results")
	# Also print the contents of the resultant modified matrix
	nR = np.dot(nP, nQ.T)

	print("Matrix P:\n", nP)
	print("Matrix Q:\n", nQ)
	print("Original matrix R:\n", R)
	print("Predicted matrix R:\n", nR)

	# Print out the difference between the two matrices
	R_difference = np.subtract(nR, R)

	print("Error matrix:\n", R_difference)

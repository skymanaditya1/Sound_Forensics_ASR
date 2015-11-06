import numpy as np

"""
@INPUT:
	R 	  : a matrix to be factorized, dimension N x M
	P 	  : an initial matrix of dimension N x K
	Q 	  : an initial matrix of dimension M x K
	K 	  : the number of latent features
	steps : the maximum number of steps to perform the optimization
	alpha : the learning rate
	beat  : the regularization parameter
@OUTPUT
	P     : the final matrix
	Q     : the final matrix
"""
def matrix_factorization(R, P, Q, K, steps=10000, alpha=0.0002, beta=0.02):
	"""R is the matrix to be factorized, 
	P and Q are the final matrices such that their product approximates R,
	K is the number of latent features,
	steps is the number of steps to run the iteration for
	alpha is the learning rate
	beta is the regularization factor
	"""
	Q = Q.T # Obtains the transpose of the matrix Q
	for step in range(steps):
		for i in range(len(R)):
			for j in range(len(R[i])):
				eij = R[i][j] - np.dot(P[i,:], Q[:,j])
				for k in range(K):
					P[i][k] = P[i][k] + alpha * (2 * eij * Q[k][j] - beta * P[i][k])
					Q[k][j] = Q[k][j] + alpha * (2 * eij * P[i][k] - beta * Q[k][j])

		eR = np.dot(P, Q)
		e = 0
		for i in range(len(R)):
			for j in range(len(R[i])):
				if R[i][j] > 0:
					e = e + pow(R[i][j] - np.dot(P[i,:], Q[:,j]), 2)
					for k in range(K):
						e = e + (beta / 2) * (pow(P[i][k], 2) + pow(Q[k][j], 2))

		if e < 0.001:
			break

	return P, Q.T				

num_rows = 5
num_cols = 4

if __name__ == "__main__":
	R = [
	[5,4,0,1],
	[4,0,0,1],
	[1,1,0,5],
	[1,0,0,4],
	[0,1,5,4],
	]
	

	"""R = []
	num_rows = int(input("Enter the dimension of the matrix : "))
	num_cols = int(input())

	for i in range(num_rows):
		R.append([])

	print("Enter the contents of the matrix : ")
	for i in range(len(R)):
		for j in range(num_cols):
			R[i].append(int(input()))"""

	print("Factorizing the matrix R, Please wait...")
	R = np.array(R)

	N = len(R)
	M = len(R[0])
	K = 2

	P = np.random.randn(N, K)
	Q = np.random.randn(M, K)

	nP, nQ = matrix_factorization(R, P, Q, K)
	nR = np.dot(nP, nQ.T)

	print("Matrix P:\n", nP)
	print("Matrix Q:\n", nQ)
	print("Original Matrix R:\n", R)
	print("Matrix R:\n", nR)
	
	print("The error difference between the actual matrix and the predicted matrix")
	resultant_matrix = np.subtract(R, nR)
	print("The resultant matrix :\n",resultant_matrix)

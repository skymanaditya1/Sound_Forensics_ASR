"""Source separation using NMF with sparseness criteria
"""
	
num_rows = 2
num_cols = 2

def display(li):
	for i in range(len(li)):
		for j in range(len(li[i])):
			print(li[i][j], end=" ")
		print(end="\n")
	
if __name__ == "__main__":
	li = []
	num_rows = int(input("Enter the dimensions of the matrix : "))
	num_cols = int(input())
	print("Enter the contents of the matrix")
	for i in range(num_rows):
		li.append([])
	for i in range(len(li)):
		for j in range(num_cols):
			li[i].append(int(input()))
	display(li)

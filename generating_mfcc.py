import os
import re
import subprocess

def getNumInstances( fileName ):
	num = 0
	s = "java -cp /usr/share/java/weka.jar weka.core.Instances "+fileName
	out = subprocess.check_output(s,shell=True) 
	#get number of instances from the output
	out_str = out.decode('utf-8')
	m = re.search(r'(Num Instances:)?(\d+).*',out_str)
	if( m ):
		print(m.group(0))
		num = int(m.group(2))
	else:
		print("ELSE : Couldn't get number of instances")
		print(out_str)
	return num

def populateNomFile(inst, whistleornot):
	whistleornot = "cat"
	os.system("touch "+WRITE_DIR+"/nominal.arff")
	print(inst, whistleornot)
	f = open(WRITE_DIR+"/nominal.arff", "w")
	f.write("@relation (null)\n@attribute whistleornot {yes/no}\n@data\n")
	for i in range(0, inst):
		f.write(whistleornot+"\n")

if __name__ == "__main__":
	OPENSMILE_DIR = "/home/aditya/CCBD_Sound_Internship/openSMILE-2.1.0/bin/linux_x64_standalone_static"
	READ_DIR = input("Enter the directory to read : ")
	WRITE_DIR = READ_DIR+"_output"
	os.system("mkdir "+WRITE_DIR)
	whistleornot = input("Is it whistle or not : ")
	i = 1
	for dir_path, dir_name, files in os.walk(READ_DIR):
		for file_name in files:
			abs_path = dir_path+"/"+file_name
			string = OPENSMILE_DIR+"/SMILExtract -C "+OPENSMILE_DIR+"/MFCC12_E_D_A.conf -I "+abs_path+" -O "+WRITE_DIR+"/arff"+str(i)+".arff"
			print(string)
			os.system(string)
			if(os.stat(WRITE_DIR+"/arff"+str(i)+".arff").st_size==0):
				continue
			if(i == 1):
				print(i)
				pass
			elif(i == 2):
				'''merge the contents of the arff1.arff and arff2.arff files'''
				merge_str = "java -cp /usr/share/java/weka.jar weka.core.Instances merge "+WRITE_DIR+"/arff1.arff "+WRITE_DIR+"/arff2.arff > "+WRITE_DIR+"/final.arff"
				os.system(merge_str)
			else:
				'''merge the contents of the arff file with the final.arff file and put the contents in the finale.arff file.'''
				merge_str = "java -cp /usr/share/java/weka.jar weka.core.Instances merge "+WRITE_DIR+"/arff"+str(i)+".arff "+WRITE_DIR+"/final.arff > "+WRITE_DIR+"/finale.arff"
				os.system(merge_str)
				'''read back the contents of the finale.arff file to the final.arff file'''
				os.system("cat "+WRITE_DIR+"/finale.arff > "+WRITE_DIR+"/final.arff")
				print(i)
			i += 1
	'''Merge the resultant final.arff file with the nominal.arff file containing the nominal attribute'''
	inst = getNumInstances(WRITE_DIR+"/final.arff")
	populateNomFile(inst, whistleornot)
	merge_str = "java -cp /usr/share/java/weka.jar weka.core.Instances merge "+WRITE_DIR+"/final.arff "+WRITE_DIR+"/nominal.arff > "+WRITE_DIR+"/mergefile.arff"
	os.system(merge_str)
	os.system("cat "+WRITE_DIR+"/margefile.arff > "+WRITE_DIR+"/final.arff")
	
	
	

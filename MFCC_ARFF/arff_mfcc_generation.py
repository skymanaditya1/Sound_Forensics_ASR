import os
import subprocess
import re

'''method for obtaining the number of instances in a given arff file
'''
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
			
'''method for populating the nominal file.
'''
def populateNomFile(nominal_attr, nominal_value, inst):
	os.system("touch "+WRITE_DIR+"/nominal.arff")
	print(nominal_attr, nominal_value, inst)
	f = open(WRITE_DIR+"/nominal.arff", "w")
	f.write('@relation (null)\n@attribute '+nominal_attr+' {yes,no}\n@data\n')
	for i in range(0, inst):
		f.write(nominal_value+"\n")

if __name__ == "__main__":
	OPENSMILE_DIR="/home/aditya/CCBD_Sound_Internship/openSMILE-2.1.0/bin/linux_x64_standalone_static"
	READ_DIR=input("Enter the read directory : ")
	
	nominal_attr = input("Enter the nominal attribute : ")
	nominal_value = input("Enter the nominal attribute value : ")
	
	WRITE_DIR=READ_DIR+"_Output"
	os.system("mkdir "+WRITE_DIR)
	i = 1
	for dir_path, dir_name, files in os.walk(READ_DIR):
		for file_name in files:
			abspath = dir_path+"/"+file_name
			string = OPENSMILE_DIR+"/SMILExtract -C "+OPENSMILE_DIR+"/MFCC12_E_D_A.conf -I "+READ_DIR+"/"+file_name+" -O "+WRITE_DIR+"/arff"+str(i)+".arff"
			print(string)
			os.system(string)
			inst = getNumInstances(WRITE_DIR+"/arff"+str(i)+".arff")	
			print("The number of instances in the file "+WRITE_DIR+"/arff"+str(i)+".arff is "+str(inst))
			populateNomFile(nominal_attr, nominal_value, inst)
			merge_str = "java -cp /usr/share/java/weka.jar weka.core.Instances merge "+WRITE_DIR+"/arff"+str(i)+".arff "+WRITE_DIR+"/nominal.arff > "+WRITE_DIR+"/merge_file.arff"
			os.system(merge_str)
			os.system("cat "+WRITE_DIR+"/merge_file.arff > "+WRITE_DIR+"/arff"+str(i)+".arff")
			if(os.stat(WRITE_DIR+"/arff"+str(i)+".arff").st_size==0):
				continue
			if (i==1):
				os.system("cat "+WRITE_DIR+"/arff"+str(i)+".arff > "+WRITE_DIR+"/final.arff")
			else:
				os.system("java -cp /usr/share/java/weka.jar weka.core.Instances append "+WRITE_DIR+"/arff"+str(i)+".arff "+WRITE_DIR+"/final.arff > "+WRITE_DIR+"/final_temp.arff")
				os.system("cat "+WRITE_DIR+"/final_temp.arff > "+WRITE_DIR+"/final.arff")
				print(i)
			i += 1

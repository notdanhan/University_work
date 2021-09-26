from random import uniform
myfile = open("randomnos.txt","w")
myfile.write("varA : .float ")
for i in range(0,49):
	myfile.write("%s,"%(uniform(0,100)))
myfile.write("%s\nvarB : .float "%(uniform(0,100)))
for i in range(0,49):
	myfile.write("%s,"%uniform(0,100))
myfile.write("%s\nvarC : .float "%(uniform(0,100)))
for i in range(0,49):
	myfile.write("0,")
myfile.write("0")
print("Done")
myfile.close()

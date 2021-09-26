#include <stdio.h>

int main() {
	//Declare the file pointer
	FILE * fptr;
	//Open the file as write only
	fptr = fopen("test.txt","w");
	/*
		fopen Options
		w : Write only, whether it exists or not (overwrites already existing files)
		r : Read only, file must exist
		a : Append data, adds data to end of file or makes new file if it does not exist
		r+ : opens existing file for reading and writing
		w+ : creates file for reading and writing
		a+ : creates file for reading and appending
	 */
	//Write to a file
	fprintf(fptr, "Hello World!\n");
	fputs("Poo Poo", fptr);
	// fputs does not append a new line without explict calls contrary to puts
	// Close the file
	fclose(fptr);
}
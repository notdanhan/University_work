#include <stdio.h>
#include "string.h"
#include "stdlib.h"

typedef struct result
{
	char firstName[50];
	char lastName[50];
	long id;
	char english, french, maths, philosophy;
} result;

// array of pointers to 'result' structures - assuming that there is 100 or less records in the data file
result * results[100];

// number of records read from the file
int numResults = 0;

// read file and populate the results
// you will need to use malloc to allocate a new 'result' structure for each record read from the file
// the *result pointer returned by malloc will be stored in the next member of the array *results[]
int readFile(char *fileName);

// set all the pointers in *results[] to NULL before reading in the file
void initialiseResults();

// function to print an individual member of the *results[] array
void printResult(result *aResult);

int main()
{
	char fileName[] = "results.txt";
	int i=0;

	
	void initialiseResults();
	
	if (!readFile(fileName)) 
	{
		printf( "File could not be opened !!\n" );
		return 0;
	} 
	printf("\nFamily Guy Funny Moments Volume 2\n");
	while (results[i] != NULL)
	{
		printResult(results[i]);
		i++;
	}

	return 0;
}

void printResult(result *aResult)
{
	// PUT YOUR CODE HERE
	printf("%s\t%s\t%d\t%c\t%c\t%c\t%c\n",aResult->firstName,aResult->lastName,aResult->id,aResult->english,aResult->maths,aResult->french,aResult->philosophy);
}

void initialiseResults()
{
	for (int i = 0; i< 100; i++) {
		result temp;
		results[i] = &temp;
	}
}

int readFile(char *fileName)
{
	// PUT YOUR CODE HERE
	FILE * fptr;
	fptr = fopen(fileName,"r");
	printf("name jeff");
	char line[200];
	fgets(line,200,fptr);
	do {
		fgets(line,200,fptr);
		sscanf(line,"%d %s %s %c %c %c %c",&results[numResults]->id,results[numResults]->firstName,&results[numResults]->english,&results[numResults]->maths,&results[numResults]->french,&results[numResults]->philosophy);
		numResults++;
	}while(!feof(fptr));
	fclose(fptr);
	return 1;
}


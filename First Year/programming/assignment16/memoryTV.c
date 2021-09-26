#include <stdio.h>
#include <stdlib.h>

typedef struct result {
	char firstName[50];
	char lastName[50];
	long id;
	char english, french, maths, philosophy;
} result;

int numResults = 0;

result * generateresults();
int readFile(char * filename, result * results);
void printResult(result * aResult);
void nullify(result *);

int main() {
	int i = 0;
	result * results = generateresults();
	char filename[] = "results.txt";
	if(!readFile(filename,results)) {
		return 0;
	}
	else while ((results + i)->id != -1) {	//COULD NOT ASSIGN A NULL TO THE FINAL ITEM DUE TO STACK SMASHING ALSO AVOIDS SEGFAULT
			printResult((results + i));
			i++;
	}
	free(results);
	return 0;
}

result * generateresults() {
	FILE * fptr;					//THIS READS A FILE AND COUNTS LINES THEN DYNAMICALLY ALLOCATES MEMORY
	fptr = fopen("results.txt","r");
	int counter = 0;
	char jeff[200];
	while(!feof(fptr)) {
		fgets(jeff,200,fptr);
		++counter;
	}
	fclose(fptr);
	result * temp = (result *) malloc(counter * sizeof(result));
	for (int i = 0; i < counter; i++) {
		nullify(temp + i);
	}
	return temp;
}

int readFile(char * filename, result * results) {
	FILE * fptr;
	fptr = fopen(filename,"r");
	char * read = (char *) malloc(200 * sizeof(char));
	fgets(read,200,fptr); //SKIPS FIRST LINE
	free(read); //NO WASTAGE
	do {
		fscanf(fptr," %d %s %s %c %c %c %c",&results[numResults].id,results[numResults].firstName,results[numResults].lastName,&results[numResults].english,&results[numResults].maths,&results[numResults].french,&results[numResults].philosophy);
		++numResults;
	} while (!feof(fptr));
	fclose(fptr);
	results[numResults].id = -1; //DONE TO AVOID STACK SMASHING AS I CANNOT ASSIGN NULL
	return(1);
}

void printResult(result *aResult) {
	printf("%15s\t%15s\t%d\t%c\t%c\t%c\t%c\n",aResult->firstName,aResult->lastName,aResult->id,aResult->english,aResult->maths,aResult->french,aResult->philosophy);
}

void nullify(result * val) {
	val = NULL;
}
#include <stdio.h>
#include "string.h"
#include "stdlib.h"

typedef struct
{
	int day, month, year;
} date;

typedef struct 
{
	char firstName[50];
	char lastName[50];
	char email[50];
	date dob;
} person;

char months[12][12] = {"January","February","March","April","May","June","July","August","September","October","November","December"};
int numContacts = 0;
person people[10];  
int readFile(char *fileName);
int getAge(date dob);
char *getMonth(int month);

void printPerson(person p1);

int  main()
{
	char fileName[] = "data.txt";
	int i=0;

	if (!readFile(fileName)) 
	{
		printf( "File could not be opened !!\n" );
		return 0;
	} 
	
	
}

// PRINTS OUT A PERSON DATA
// USES getAGE AND getMonth
void printPerson(person p1)
{
	// INSERT YOUR CODE HERE
	printf("First Name: %s\nLast Name: %s\nEmail: %s\nDOB: %s %d, %d\n Age: %d\n",p1.firstName,p1.lastName,p1.email,getMonth(p1.dob.month),p1.dob.day,p1.dob.year,getAge(p1.dob));
}


int readFile(char *fileName)
{
	char line[400];
	FILE *fptr = fopen(fileName, "r");
	char *token;
	char delim[6]=",";  
	int n=0,i=0;
	person p1;
	date d1;

	if (fptr == NULL)
	{
		printf("Error opening file ! \n");
		return 0;
	}

	// use fgets to skip first line
	fgets (line, 400, fptr);

	//first data line
	while (!feof(fptr))
	{ 
		fgets (line,200,fptr);
		// INSERT CODE HERE
		// USE STRTOK TO PARSE DATA AND POPULATE person STRUCTURE p1
		int field_pos = 0;
		char *field = strtok(line, ",");
        while (field) {
            if (field_pos == 0) {
                strcpy(p1.firstName,field);
            }
            if (field_pos == 1) {
                strcpy(p1.lastName,field);
            }
            if (field_pos == 2) {
                strcpy(p1.email,field);
            }
            if (field_pos == 3) {
                p1.dob.day = atoi(strtok(field,"/"));
				field = strtok(NULL,"/");
				p1.dob.month = atoi(field);
				field = strtok(NULL,"/");
				p1.dob.year = atoi(field);
				break;
            }
            field = strtok(NULL, ",");
            field_pos++;
        }

		// PRINT OUT PERSON p1
		printPerson(p1);
		
		// ADD p1 to ARRAY OF person STRUCTURES
		people[n] = p1;
		
		// INCREMENT NUM READ IN
		n++;
		
	} 
	numContacts = n;

	fclose (fptr);

	return 1;
}

// RETURNS AGE - DON'T WORRY ABOUT ACCURACY - NEAREST YEAR WILL DO

int getAge(date dob)
{
	int age;

	age = 2019 - dob.year;

	return age;

}

// RETURNS STRING TO USE IN PRINTING OUT THE MONTH
char *getMonth(int month)
{
	return months[month-1];

}
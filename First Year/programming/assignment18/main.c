#include <stdio.h>
#include <stdarg.h>
#include "string.h"
#include "stdlib.h"
#include "time.h"

// enum corresponding to whether the user entered /w or /r
typedef enum {write, read} mode;

// struct to hold a date
typedef struct
{
	int day, month, year;
} date;

// struct to hold a log entry (date and text)
typedef struct
{
	date d;
	char text[100];
} entry;

// ways to call access Log
// accessLog (2, filepath, enum mode = read)   /* read everything from the log file */
// accessLog (3, filepath, enum mode = read, struct date = datetoread)   /* read entries from a specific date */
// accessLog (3, filepath, enum mode = write, struct entry = entry)   /* write an entry */
// no more - it's just an exercise!!
void accessLog(int i, ...);

// functions I use to read / write log entries
void writeToLog(char *filePath, entry e);
// this function can be called as follows:
// readFromLog(1, filePath)   /* read everything */
// readFromLog(2, filePath, date) /* read entries for a specific date */
void readFromLog(int i, ...);

// utility functions
// return true if dates the same
int compareDates(date d1, date d2);
// convert a string e.g. "12/10/2019" to a date structure
date stringToDate(char* string);

// ways to call main (i.e. run the program.exe)
// prog.exe c:\\log.txt /r
// prog.exe c:\\log.txt /r 10/12/2019
// prog.exe c:\\log.txt /w 12/2/2020 "this is a new entry"
void main(int argc, char* argv[])
{
	mode accessMode;
	char* filePath;
	date d;

	// test - checking inputs are ok
	/*
	for (int i = 0; i < argc; i++)
	{
		printf ("%d %s\n", i, argv[i]);
	}
	*/

	filePath = argv[1];

	if (argc == 3)
	{
		accessLog(2, filePath, read);
	}

	if (argc > 3) {
		if (!strcmp(argv[2],"/w")){
			accessMode = write;
		}
		else if(!strcmp(argv[2],"/r")) {
			accessMode = read;
		}
	}
	else {
		accessMode = read;
	}

	if ((argc == 4) && (accessMode == read))
	{
		d = stringToDate (argv[3]);
		accessLog(3, filePath, read, d);
	}

	if ((argc == 5) && (accessMode == write))
	{
		d = stringToDate (argv[3]);
		entry e;
		e.d = d;
		strcpy (e.text, argv[4]);
		accessLog (3, filePath, write, e);
	}


}

void accessLog(int i, ...)
{
	char* filePath;
	mode accessMode;
	entry entryToWrite;
	date dateToRead;
	va_list ap;

	va_start(ap, i);

	// ENTER CODE TO GET THE filePath FROM THE va_list
	// ...
	filePath = va_arg(ap,char *);
	
	// ENTER CODE TO GET THE accessMode FROM THE va_list
	// ...
	accessMode = va_arg(ap,mode);
	
	if ((i==2) && (accessMode == read)) 
	{
		readFromLog(1, filePath);
	}

	if ((i==3) && (accessMode == read)) 
	{
		// ENTER CODE TO GET THE dateToRead FROM THE va_list
		// ...
		dateToRead = va_arg(ap,date);
		readFromLog(2, filePath, dateToRead);
	}

	if ((i==3) && (accessMode == write))
	{
		// ENTER CODE TO GET THE entryToWrite FROM THE va_list
		// ...		
		entryToWrite = va_arg(ap,entry);
		writeToLog(filePath, entryToWrite);
	}

	va_end(ap);

}

void writeToLog(char *filePath, entry e)
{
	FILE *fptr;

	fptr = fopen(filePath, "a");
	fprintf(fptr, "%d/%d/%d %s\n", e.d.day, e.d.month, e.d.year, e.text);
	fclose(fptr);

	
}

void readFromLog(int i, ...)
{
	FILE *fptr;
	char* filePath;
	date dateToRead;
	char line[300];
	va_list ap;
	date logDate;
	char *token;
	char logText [200];

	va_start(ap, i);

	// ENTER CODE TO GET THE filePath FROM THE va_list
	// ...
	filePath = va_arg(ap,char*);
	
	
	if (i == 2)
	{
		// ENTER CODE TO GET THE dateToRead FROM THE va_list
		// ...
		dateToRead = va_arg(ap,date);
	}

	fptr = fopen(filePath, "r");
	printf("\n\nLog Data \n\n");
	do
	{
		fgets(line, 200, fptr);
		if(feof(fptr)) break;
		if (strlen(line) < 10) return;
		
		// ENTER CODE TO USE STRTOK TO PARSE THE logDate FROM THE line
		// ...
		sscanf(line,"%d%*[/]%d%*[/]%d%*[ ]%[^\n]",&logDate.day,&logDate.month,&logDate.year,logText);
		
		// ENTER CODE TO USE STRTOK TO PARSE THE logText FROM THE line
		// ...


		strcpy (logText, logText);


		if (i == 2)
		{
			if (compareDates (dateToRead, logDate))
			{
				printf("%d/%d/%d %s\n", logDate.day, logDate.month, logDate.year, logText);
			}
		}
		else
		{
			printf("%d/%d/%d %s\n", logDate.day, logDate.month, logDate.year, logText);
		}
	} while(!feof(fptr));
	printf("\n\n");
}

int compareDates(date d1, date d2)
{
	if ((d1.day == d2.day) && (d1.month == d2.month) && (d1.year == d2.year))
	{
		return 1;
	}
	else
	{
		return 0;
	}
} 

date stringToDate(char* string)
{
	date d1;
	char* token;

	token = strtok(string, "/");
	d1.day = atoi(token);
	token = strtok(NULL, "/");
	d1.month = atoi(token);
	token = strtok(NULL, "/");
	d1.year = atoi(token);
	
	return d1;
}
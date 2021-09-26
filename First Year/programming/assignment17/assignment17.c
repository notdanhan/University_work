#include <stdio.h>
#include <string.h>
#include <stdlib.h>

struct result
{
	int id;
	char *firstName;
	char *lastName;
	char english, maths, french, philosophy;
	struct result *next;
} ;


struct result *  readFile(char *fileName);
void printResult(struct result *theResult);
struct result * findResult(struct result *first, int id);

int main()
{
	char fileName[] = "results.txt";
	int i=0;

	struct result *first;
	struct result *foundResult;
	if ((first = readFile(fileName)) == NULL) 
	{
		printf( "File could not be opened !!\n" );
		return 0;
	} 
	
	printf("Looking for ID 105 ......\n");
	foundResult = findResult(first, 105);
	if (foundResult != NULL)
	{
		printResult(foundResult);
	}

	printf("\n\n");

	printf("Looking for ID 135 ......\n");
	foundResult = findResult(first, 135);
	if (foundResult != NULL)
	{
		printResult(foundResult);
	}

	return 0;
}

void printResult(struct result *theResult)
{
	// INSERT CODE HERE
	printf("\nID:\t\t%d\n",theResult->id);
	printf("First Name:\t%s\n",theResult->firstName);
	printf("Last Name:\t%s\n",theResult->lastName);
	printf("English:\t%c\n",theResult->english);
	printf("Maths:\t\t%c\n",theResult->maths);
	printf("French:\t\t%c\n",theResult->french);
	printf("Philosophy:\t%c\n",theResult->philosophy);
}


struct result * readFile(char *fileName)
{
	char line[400];

	FILE *fptr = fopen(fileName, "r");
	char *token;
	char delim[6]="\t";  
	struct result *first = NULL;
	struct result *last = NULL;
	struct result *current = NULL;

	if (fptr == NULL)
	{
		printf("Error opening file ! \n");
		return 0;
	}
	// use fgets to skip first line
	fgets (line, 200, fptr);

	while (!feof(fptr))
	{ 
		//next data line
		fgets (line,200,fptr);

		if (first == NULL)
		{
			first = (struct result *) malloc(sizeof(struct result));
			current = first;
			
		}
		else
		{
			// INSERT CODE HERE
			current->next = (struct result *) malloc(sizeof(struct result));
			current = current->next;
		}

		if ((token = strtok (line,delim)) != NULL)
		{
			current->id = atoi(token);
		}

		if ((token = strtok (NULL,delim)) != NULL)
		{
			// INSERT CODE HERE
			current->firstName = strdup(token);
		}

		if ((token = strtok (NULL,delim)) != NULL) 
		{
			// INSERT CODE HERE
			current->lastName = strdup(token);
		}

		if ((token = strtok (NULL,delim)) != NULL) 
		{
			// INSERT CODE HERE
			current->english = *token;
		}

		if ((token = strtok (NULL,delim)) != NULL)
		{
			// INSERT CODE HERE
			current->maths = *token;
		}

		if ((token = strtok (NULL,delim)) != NULL) 
		{
			// INSERT CODE HERE
			current->french = *token;
		}

		if ((token = strtok (NULL,delim)) != NULL) 
		{
			// INSERT CODE HERE
			current->philosophy = *token;
		}


	} 
     
	fclose (fptr);

	return first;
}


struct result * findResult(struct result *first, int id)
{
	struct result *current = first;

	while(current != NULL)
	{
		// INSERT CODE HERE
		if (current->id == id) {
			return current;
		}
		current = current->next;
	}

	return NULL;
}
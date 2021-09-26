#include <stdio.h>
#include "string.h"
#include "stdlib.h"

typedef struct 
{
	char firstName[50];
	char lastName[50];
	char companyName[50];
	char address[50];
	char city[50];
	char county[50];
	char state[50];
	char zip[50];
	char phone1[50];
	char phone2[50];
	char email[50];
	char web[50];
} contact;

char *strlwr(char *str) {
  unsigned char *p = (unsigned char *)str;

  while (*p) {
	  if(*p >= 65 && *p <= 90)
     *p = *p + 32;
      p++;
  }

  return str;
}

int numContacts = 0;
contact contacts[501];  

int readFile(char *fileName);
int findContact(char *string);
void printContact(contact theContact);


void main()
{
	char string[20];
	char fileName[] = "us-500.csv";
	int i=0;
	int option;


	if (!readFile(fileName)) 
	{
		printf( "File could not be opened !!\n" );
		return;
	} 

	puts("Enter name you are looking for: ");

	scanf("%[^\n]",string);

	findContact(string);
				
}

int findContact(char *string)
{
	int i,j=0;
	char * temp = strlwr(string);
	for (i=0;i<numContacts;i++)
	{
		if(strstr(strlwr(contacts[i].firstName),temp)!=NULL || strstr(strlwr(contacts[i].lastName),temp)) {
			j++;
			printf("Result %d\n",j);
			printContact(contacts[i]);
			printf("\n");
		}
	}

	return j;
}

void printContact( contact theContact)
{
	printf("First Name: %s\n",theContact.firstName);
	printf("Last Name: %s\n",theContact.lastName);
	printf("Company Name: %s\n",theContact.companyName);
	printf("Address: %s\n",theContact.address);
	printf("City: %s\n",theContact.city);
	printf("County: %s\n",theContact.county);
	printf("State: %s\n",theContact.state);
	printf("Zip: %s\n",theContact.zip);
	printf("Phone #1: %s\n",theContact.phone1);
	printf("Phone #2: %s\n",theContact.phone2);
	printf("e-mail: %s\n",theContact.email);
	printf("Web: %s\n",theContact.web);

}


int readFile(char *fileName)
{
	char line[200];
	FILE *fptr = fopen(fileName, "r");
	char *token;
	char delim[6]=",";  
	int n=0,i=0;
	contact newContact;

	if (fptr == NULL)
	{
		printf("Error opening file ! \n");
		return 0;
	}

	// use fgets to skip first line
	fgets (line, 200, fptr);

	//first data line
	
	do
	{ 	
		fgets(line,200,fptr);
		if (strchr(line,'\"') != NULL) {
			sscanf(line,"%[^,]%*[,]%[^,]%*[,\"]%[^\"]%*[\",]%[^,]%*[,]%[^,]%*[,]%[^,]%*[,]%[^,]%*[,]%[^,]%*[,]%[^,]%*[,]%[^,]%*[,]%[^,]%*[,]%s",newContact.firstName,newContact.lastName,newContact.companyName,newContact.address,newContact.city,newContact.county,newContact.state,newContact.zip,newContact.phone1,newContact.phone2,newContact.email,newContact.web);
		}
		else {
			sscanf(line,"%[^,]%*[,]%[^,]%*[,]%[^,]%*[,]%[^,]%*[,]%[^,]%*[,]%[^,]%*[,]%[^,]%*[,]%[^,]%*[,]%[^,]%*[,]%[^,]%*[,]%[^,]%*[,]%s",newContact.firstName,newContact.lastName,newContact.companyName,newContact.address,newContact.city,newContact.county,newContact.state,newContact.zip,newContact.phone1,newContact.phone2,newContact.email,newContact.web);
		}
		
		contacts[numContacts] = newContact;
		numContacts++;
		if (feof(fptr)) {
			break; //Stops printing last result twice
		}
		printContact(newContact);
		

	} while (!feof(fptr));
     
	fclose (fptr);

	return 1;
}


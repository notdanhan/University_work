#include <stdio.h>
#include <stdlib.h>
#include <string.h>

struct date{
	int day, month, year;
};

struct account {
	int id;
	char name[100];
	double balance;
	struct date DateOfBirth;
	struct date lastTransactionDate;
};  

int main() {
	struct account accounts[10];
	int i = 0;
	FILE * fptr =fopen("sample.txt","r");
	char line[100];
	char * token; 
	fgets(line,100,fptr);
	while(!feof(fptr)) {
		fgets(line, 100, fptr);
		token = strtok(line,"\t");
		accounts[i].id = atol(token);
		token = strtok(NULL,"\t");
		strcpy(accounts[i].name,token);
		token = strtok(NULL,"\t");
		sscanf(token,"%d/%d/%d",&accounts[i].DateOfBirth.day,&accounts[i].DateOfBirth.month,&accounts[i].DateOfBirth.year);
		token = strtok(NULL,"\t");
		accounts[i].balance = atof(token);
		token = strtok(NULL,"\t");
		sscanf(token,"%d-%d-%d",&accounts[i].lastTransactionDate.day,&accounts[i].lastTransactionDate.month,&accounts[i].lastTransactionDate.year);
		i++;
	}
	fclose(fptr);
}
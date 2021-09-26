#include <stdio.h>
#include <stdlib.h>

struct date {
	int day, month, year;
};

struct student {
	int studentID;
	char firstName[20];
	char lastName[20];
	char courseID[10];
	int year;
	struct date registrationDate;
	struct date dateOfBirth;
	int averageGrade;
};

int main() {
	int i = 0; //i counts max index + 1
	struct student students[100]; //Make an array of structures
	FILE * fptr;
	fptr = fopen("students.txt","r+"); //r+ indicates reading and writing without clearing file
	do {
		fscanf(fptr, "%d %s %s %s %d %d %d %d %d %d %d", &students[i].studentID, &students[i].firstName, &students[i].lastName, &students[i].courseID, &students[i].year, &students[i].registrationDate.day, &students[i].registrationDate.month, &students[i].registrationDate.year, &students[i].dateOfBirth.day, &students[i].dateOfBirth.month, &students[i].dateOfBirth.year);
		students[i].averageGrade = rand() % 100; //Generate random number
		i++; 
	} while(!feof(fptr));
	rewind(fptr); //Put File Pointer back to start of the file
	for (int a = 0; a < i; a++) {
		if (students[a].studentID == 0) break;
		fprintf(fptr, "%d\t%s\t%s\t%s\t%d\t%d\t%d\t%d\t%d\t%d\t%d\t%d\n", students[a].studentID, students[a].firstName, students[a].lastName, students[a].courseID, students[a].year, students[a].registrationDate.day, students[a].registrationDate.month, students[a].registrationDate.year, students[a].dateOfBirth.day, students[a].dateOfBirth.month, students[a].dateOfBirth.year, students[a].averageGrade);
	}
	fclose(fptr);
}
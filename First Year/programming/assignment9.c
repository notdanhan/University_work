#include <stdio.h>
#include <string.h>
#include <stdlib.h>

struct date
{
	int day, month, year;
};

struct student
{
	char firstName[50];
	char surname[50];
	int ID;
	struct date DOB;
};


int addStudent(struct student students[], int numStudents);
void printStudents(struct student students[], int numStudents);
void sortByID(struct student students[], int numStudents);
void sortBySurname(struct student students[], int numStudents);

int main()
{

	struct student students[100] = { 
		{"Rolando","Gamble", 17560001, {1,1,2000}},
		{"Kyra","Eaton", 17973061, {11,10,2016}},
		{"Chandler","Humphrey", 17427400, {6,12,1999}},
		{"Makayla","Huang", 17127058, {2,2,2000}},
		{"Jazmyn","Terrell", 17746780, {29,6,2000}},
		{"Erica","Glass", 17058753, {13,8,2000}},
		{"Alyvia","Blair", 17599020, {11,9,2000}}, 
		{"Talia","Chen", 17619205, {17,10,2000}},
		{"Rebekah","Hopkins", 17912255, {25,11,2000}},
		{"Jocelynn","Ware", 17899236, {27,4,1999}}
	};

	int numStudents = 10; //NOTE: Represents maximun index accessed

	// use addStudent to add a new student and it returns the updated number of students
	// obviously, numStudents is the number of structures in the array (students[]) that contain data
	numStudents = addStudent (students, numStudents);

	// sort by ID number and print out list
	sortByID (students, numStudents);
	printf ("\n\n%d Students, Sorted by ID: \n", numStudents);
	printStudents (students, numStudents);

	// sort by surname and print out list
	sortBySurname (students, numStudents);
	printf ("\n\n%d Students, Sorted by Surname: \n", numStudents);
	printStudents (students, numStudents);

	return 0;
}

int addStudent(struct student students[], int numStudents)
{
	char first[50], last[50];
	int id, dd, mm, yyyy;
	//NUM STUDENTS IS MAXIMUM INDEX VALUE!!!!!

	// Ask user for data for new student and add to the array of students
	// your code here
	printf("Add a new Student\nEnter data as follows:\nfname sname id dd mm yyyy\n");
	fscanf(stdin, "%s %s %d %d %d %d", first, last, &id, &dd, &mm, &yyyy);
	strcpy(students[numStudents].firstName, first);
	strcpy(students[numStudents].surname, last);
	students[numStudents].ID = id;
	struct date dob;
	dob.day = dd;
	dob.month = mm;
	dob.year = yyyy;
	students[numStudents].DOB = dob;

	numStudents++;

	return numStudents;
}

void printStudents(struct student students[], int numStudents)
{
	int i = 0;
	printf ("%20s%20s%15s\t\t%10s\n", "First Name", "Surname", "ID Number", "DOB");
	// write a for loop to print out each student structure
	// your code here
	while (i<numStudents) {
		printf ("%20s%20s\t%d\t\t%d/%d/%d\n", students[i].firstName, students[i].surname, students[i].ID, students[i].DOB.day,students[i].DOB.month,students[i].DOB.year);
		i++;
	}
}

void sortByID(struct student students[], int numStudents)
{
	struct student temp;
	int i,j;

	// use the Bubble sort algorithm to sort the students by ID
	// your code here
	for (i = 0; i < (numStudents - 2);i++) {
		for(j = 0; j < (numStudents - 2);j++) {
			if(students[j].ID > students[j+1].ID) {
				temp = students[j+1];
				students[j+1] = students[j];
				students[j] = temp;
			}
		}
	}
}

void sortBySurname(struct student students[], int numStudents)
{
	struct student temp;
	int i,j;

	// use the Bubble sort algorithm to sort the students by Surname
	// your code here
	for (i = 0; i < (numStudents - 2);i++) {
		for(j = 0; j<(numStudents - 2);j++) {
			if (strcmp(students[j].surname,students[j+1].surname) > 0) {
				temp = students[j+1];
				students[j+1] = students[j];
				students[j] = temp;
			}
		}
	}
}

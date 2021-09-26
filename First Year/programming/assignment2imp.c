#include <stdio.h>
#include <string.h>

int main() {
	int marks[5];
	char subs[5][8];
	int big, big_val = 0;
	strcpy(subs[0], "English");
	strcpy(subs[1], "French");
	strcpy(subs[2], "Maths");
	strcpy(subs[3], "Irish");
	strcpy(subs[4], "Science");
	for (int i = 0; i < 5; i++ ) {
		printf("Enter %s Mark: ", subs[i]);
		scanf("%d", &marks[i]);
		big = (marks[i] > big) ? marks[i] : big;
		big_val = (marks[i] > big) ? i : big_val;
	}
	float average = (float)((marks[0] + marks[1] + marks[2] + marks[3] + marks[4]) / 5.0f);
	printf("\nAverage Mark = %0.2f \n\nFails:", average);
	for (int i = 0; i < 5; i++ ) {
		(marks[i] < 40) ? printf(" %s,",subs[i]) : printf("");
	}
	printf("\nPasses:");
	for (int i = 0; i < 5; i++) {
		(marks[i] >= 40) ? printf(" %s,", subs[i]):printf("");
	}
	printf("\nHighest Mark = %d in %s\n",marks[big_val],subs[big_val]);
}
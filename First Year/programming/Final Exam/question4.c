#include<stdio.h>
#include<string.h>

typedef enum { OPEN, ASSIGNED,  FIXED, CLOSED } status;
typedef struct {
	char developerName[40];
	status bugStatus;
} bug;

bug openBug() {
	bug bugnew;
	bugnew.bugStatus = OPEN;
	return bugnew; 
}

void assignBug(bug *abug, char* developerName) {
	strcpy(abug->developerName,developerName);
	abug->bugStatus = ASSIGNED;
}

void fixBug(bug  * abug) {
	if (abug->bugStatus == ASSIGNED) {
		abug->bugStatus = FIXED;
	}
	else {
		printf("Cannot FIX bug if not ASSIGNED!\n");
	}
}

void closeBug(bug * abug) {
	abug->bugStatus = CLOSED;
}

void printBugStatus(bug abug) {
	switch(abug.bugStatus) {
		case OPEN:
			printf("Bug is OPEN\n");
			break;
		case ASSIGNED:
			printf("Bug is ASSIGNED, assigned to %s\n",abug.developerName);
			break;
		case FIXED:
			printf("Bug is FIXED\n");
			break;
		case CLOSED:
			printf("Bug is CLOSED\n");
			break;
		default:
			printf("You should never see this!\n");
			break;
	}
}

int main() {
	bug bug1 = openBug();
	printBugStatus(bug1);
	assignBug(&bug1, "Alex");
	printBugStatus(bug1);
	fixBug(&bug1);
	printBugStatus(bug1);
	closeBug(&bug1);
	printBugStatus(bug1);
	fixBug(&bug1);
	printBugStatus(bug1);
}
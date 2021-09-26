#include <stdio.h>
#include <string.h>

int main() {
	FILE * fptr;
	fptr = fopen("accounts.txt","w");
	float balance;
	int account_num;
	char fname[50];
	char lname[50];
	int running = 0;
	char verify[2];
	do {
		printf("Enter firstname lastname: ");
		fscanf(stdin,"%s %s",fname, lname);
		printf("Enter account number: ");
		fscanf(stdin, "%d", &account_num);
		printf("Enter Balance: ");
		fscanf(stdin,"%f",&balance);
		fprintf(fptr,"%s\t%s\t%d\t%0.2f\n", fname, lname, account_num, balance);
		printf("Enter another account y/n: ");
		fscanf(stdin, " %s", verify);
		if (strcmp(verify, "y") == 0 || strcmp(verify, "Y") == 0) {
			running = 1;
		}
		else {
			running = 0;
		}
	} while(running);
	fclose(fptr);
	fptr = fopen("accounts.txt","r");
	printf("Contents of file:\n ");
	fscanf(fptr, "%s %s %d %f", fname, lname, &account_num, &balance);
	while(!feof(fptr)) {
		printf(" %s\t%s\t%d\t%0.2f\n", fname, lname, account_num, balance);
		fscanf(fptr, "%s %s %d %f", fname, lname, &account_num, &balance);
	}
	fclose(fptr);
}
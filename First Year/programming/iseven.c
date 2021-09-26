#include <stdio.h>

int ackerman(int,int);
int is_odd(int);
int is_even(long);

int main() {
	long test_number;
	printf("Check if a number is even: ");
	scanf("%d",&test_number);
	test_number = is_even(test_number);
	if(test_number) {
		printf("YEs");
		return 1;
	}
	printf("No");
	return 0;
}

int ackerman(int m, int n) {
	if (m == 0) {
		return (n+1);
	}
	if (m > 0 && n == 0) {
		return ackerman((m - 1),1);
	}
	return ackerman((m-1),ackerman(m,n-1));
}

int is_odd(int a) {
	if (a % 2 != 0) {
		return 1;
	}
	else {
		return 0;
	}
}

int is_even(long num) {
	int tally;
	for (int i = 1; i <=num;i++) {
		tally += (is_odd(ackerman(3,i)));
	}
	return is_odd(tally);
}
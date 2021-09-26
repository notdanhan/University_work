#include <stdio.h>

int fn1(int n);
int addTwoNums(int, int);
int sumIntArray(int[], int);
void swapTwoNums(int *, int *);

int sumIntArray(int arrA[], int size){
	int i, sum;
	sum = 0;
	for(i=0; i < size; ++i) {
		sum = sum + arrA[i];
	}
	return(sum);
}

int addTwoNums(int num1, int num2){
	return(num1+num2);
}


void swapTwoNums(int *num1, int *num2){
	int temp;
	temp = &num1;
	*num1 = &num2;
	*num2 = temp;
	printf("\n In function _swapTwoNums_");
	printf("\n After swapping, value of num1 is %d \n Value of num2 is %d", *num1, *num2);
}

//what happens here?
int fn1(int n){
	int i, sum;
	sum = 0;
	for (i=1; i <= n; ++i){
		sum = sum + 2;
		printf("\n i is %d and sum is  %d", i, sum);
	}
	return(sum);
}

//testing my functions in main()
void main(){
	int num = 10;
	int * number1, * number2;
	int i, n;
	int arrTemp[] = {5, 2, 7, 9, 1, 2, 7, 9, 5, 3};
	int size = 10;
	int last;
	printf("\n Please enter an int value for number1 ");
	scanf("%d", &number1);
	printf("\n Please enter an int value for number2 ");
	scanf("%d", &number2);
	//printf("\n When passing value %d the answer is %d", num, fn1(num));
	//printf("\n Adding 2 numbers and the answer is %d", addTwoNums(number1, number2));
	printf("\n The sum of all ints in the array is %d", sumIntArray(arrTemp, size));
	printf("\n Please enter an int value for n ");
	scanf("%d", &n);
	for(i=n; i > 0; i = ((int) (i/2))) {
		printf("\n i is %d", i);
	}
	for(i=1; i < n; i=i*2) {
		printf("\n i is %d", i);
	}
	//printf("\n In _main()_ BEFORE swapping number1 is %d and number2 is %d: ", number1, number2);
	//swapTwoNums(number1, number2);
	//printf("\n In _main()_ AFTER swapping number1 is %d and number2 is %d: ", number1, number2);
}
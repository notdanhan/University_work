#include<time.h>

double averageWordLength(char *words[], int numWord) {
	double total_length = 0;
	for (int i = 0; i < numWord; i++) {
		int l = 0;
		while (words[i][l]!='\0') {
			total_length++;
			l++;
		}
	}
	return total_length/numWord;
}

char *  returnRandomString(char*  words[], int   numWords) {
	time_t t;
	srand(time(&t));
	return words[rand() % numWords];
}

int * squares (int *arr, int len) {
	int * square = (int *) malloc(len * sizeof(int));
	for (int i = 0; i < len; i++) {
		*(square + i) = *(arr + i) * *(arr + i);
	}
	return square;
}
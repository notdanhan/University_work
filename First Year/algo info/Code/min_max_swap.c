#include <stdio.h>
#include <stdlib.h>

int main() {
	int size;
	int temp;
	int min = 9999999;
	int min_index = -1;
	int floor = 0 ;
	printf("how many intergers? ");
	scanf("%d",&size);
	int * nums_arr = (int *) malloc(size * sizeof(int));
	for (int i = 0; i < size; i++) {
		scanf(" %d", &temp);
		*(nums_arr + i) = temp;
	}
	for (int i = 0; i < size; ++i) {
		for (int a = 0; a < size - floor; a++) {
			if (*(nums_arr + a + floor) < min) {
				min = *(nums_arr + a + floor);
				min_index = a+floor;
			}
		}
		temp = *(nums_arr + floor);
		*(nums_arr + floor) = *(nums_arr + min_index);
		*(nums_arr + min_index) = temp;
		
		min = 9999999;
		floor++;
	}
	for (int i = 0; i<size;i++) {
		printf("%d ",*(nums_arr + i));
	}
}
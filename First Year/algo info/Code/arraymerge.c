// merge_arrays.cpp : Merge two sorted arrays in to one new array maintaing sorted order with no duplicates
//
#include <stdio.h>

int main(void) {
	//sample data
	int arrA[5] = {2, 4, 12, 14, 24};
	int arrB[7] = {1, 12, 17, 19, 29, 44, 49};
	int sizeA = 5;
	int sizeB = 7;

	int arrC[100];
	
	int i, j, k;
	int sizeC;
   	sizeC = sizeA + sizeB;
	i = j = k = 0;
	
	while (i < sizeA && j < sizeB){  // keeping going until get to end of arrA or arrB

	    if (arrA[i] < arrB[j]){   //add element from arrA
            arrC[k] = arrA[i];
            ++i;
	    }
	    else if (arrB[j] < arrA[i]){  //add element from arrB
            arrC[k] = arrB[j];
            ++j;
	    }
	    else {  //same value in both arrays
			arrC[k] = arrA[i];
            ++i;
			++j;
	    }
		++k;
	} //end while
    //reached the end of one of the arrays at this point
	if (i == sizeA){		// all of A written to arrC already
        while(j < sizeB){
            arrC[k] = arrB[j];
            ++j;
            ++k;
	    }
	} 
    else {	//all of A written to arrC already
        while (i < sizeA){
            arrC[k] = arrA[i];
            ++i;
            ++k;
	    }
	}
    sizeC = k;
	printf("\n Printing the merged array of size %d ", sizeC);
	for(k = 0; k < sizeC; ++k) {
        printf("\n %d", arrC[k]);
	}
}
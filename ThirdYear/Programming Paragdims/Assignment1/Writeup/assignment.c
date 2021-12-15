#include <stdio.h>
#include <stdlib.h>
/* Question 1 Code
  By Daniel Hannon(19484286)
*/
int main(int arg, char* argc[]){
  printf("Hello assignment1.\n");
  //Create required data types and fill it with dummy data
  int myInt = 5;
  int * pInt = &myInt;
  long myLong = 12;
  double * pDouble = (double *) malloc(sizeof(double));
  *pDouble = 1.2323423;
  char ** dpChar = (char **) malloc(sizeof(char *));
  *(dpChar) = (char*) malloc(sizeof(char));
  **dpChar = 'a';
  //Print the data
  printf("Size of Types:\nInt: %ld\nInt *: %ld\nLong: %ld\nDouble *: %ld\nChar **: %ld\n",sizeof(myInt),sizeof(pInt),sizeof(myLong),sizeof(pDouble),sizeof(dpChar));
  //For some reason when I try to free the pointers it SegFaults so I did not do that
  return 0;
}

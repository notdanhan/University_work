#include <stdio.h>
#include <stdlib.h>
#include "genericLinkedList.h"

void runTests(){
  printf("Tests running...\n");

  printf("\nCreate String element\n");
  genericListElement * l = createGEl("Interesting test string",30,&printGenericString);
  genericTraverse(l);
  printf("\nCreate Integer Array Element\n");
  //Make it a pointer so free does not cause a segfault
  int * numArray = (int *) malloc(5 * sizeof(int));
  for(int i = 0; i < 5; i++) {
    *(numArray+i) = i;
  }
  genericInsertAfter(l,numArray,5*sizeof(int),&printGenericInt);
  free(numArray);
  genericTraverse(l);
  printf("\nDeleting Integer array element\n");
  genericDeleteAfter(l);
  genericTraverse(l);
  printf("\nPushing Float and calculating linked list size\n");
  float testNum = 123.456;
  float * ptestNum =  &testNum;
  genericPush(&l,ptestNum,sizeof(float),&printGenericFloat);
  printf("Length: %d\n",genericLength(l));
  genericTraverse(l);
  printf("\nPushing some random rubbish so I can verify pop/dequeue work\n");
  genericPush(&l,"I have no creativity",30,&printGenericString);
  int test1234 = 123456;
  float testfloat1234 = 456.789;
  genericPush(&l,&test1234,1*sizeof(int),&printGenericInt);
  genericPush(&l,&testfloat1234,sizeof(float),&printGenericFloat);
  genericTraverse(l);

  printf("\nTesting Pop\n");
  genericListElement * temp = genericPop(&l);
  free(temp);
  genericTraverse(l);
  printf("\nTesting Dequeue\n");
  genericListElement * temp2 = genericDequeue(l);
  free(temp2);
  genericTraverse(l);
  printf("\nTests complete.\n");
}

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "genericLinkedList.h"

typedef struct genericListElementStruct {
  void * data;
  size_t size;
  fptr printFunc;
  struct genericListElementStruct * next;
} genericListElement;

genericListElement* createGEl(void * data, size_t size, void(printFunc)(void*,size_t)) {
  genericListElement * gel = (genericListElement *) malloc(sizeof(genericListElement));
  //Copy element in and stuff, I use memcpy for obvious reasons
  gel->data = malloc(size);
  memcpy(gel->data,data,size);
  gel->size = size;
  //Prevent junk data here :)
  gel->next = NULL;
  gel->printFunc = printFunc;
  return gel;
}

void genericTraverse(genericListElement * start) {
  genericListElement * curr = start;
  while(curr != NULL) {
    curr->printFunc(curr->data,curr->size);
    curr = curr->next;
  }
}

//inserts a new element after the given el
genericListElement * genericInsertAfter(genericListElement * after, void* data, size_t size, void(printFunc)(void*,size_t)) {
  genericListElement * new = createGEl(data,size,printFunc);
  genericListElement * temp = after->next;
  after->next = new;
  new->next = temp;
  return new;
}

void genericDeleteAfter(genericListElement * after) {
  if(after->next != NULL) {
    genericListElement * temp = after->next;
    after->next = temp->next;
    free(temp->data);
    free(temp);
  }
}

int genericLength(genericListElement * start) {
  int count = 0;
  genericListElement * curr = start;
  while(curr!= NULL) {
    count++;
    curr = curr->next;
  }
  return count;
}

void genericPush(genericListElement ** list, void* data, size_t size, void(printFunc)(void*,size_t)) {
  genericListElement * new = createGEl(data,size,printFunc);
  new->next = (*list);
  *list = new;
}

genericListElement * genericPop(genericListElement ** list) {
  genericListElement * temp = *(list);
  *list = (*list)->next;
  temp->next = NULL;
  return temp;
}

void genericEnqueue(genericListElement ** list, void * data, size_t size, void(printFunc)(void *,size_t)) {
  genericListElement * new = createGEl(data,size,printFunc);
  new->next = *(list);
  *(list) = new;
}

genericListElement* genericDequeue(genericListElement * list) {
  genericListElement * temp = list;
  if(list == NULL) {
    return NULL;
  }else if(list->next == NULL) {
    return list;
  } else {
    while(temp->next->next != NULL) {
      temp = temp->next;
    }
    genericListElement * temp2 = temp->next;
    temp->next = NULL;
    return temp2;
  }
}

void printGenericString(void * data,size_t size) {
  char * stringData = (char *) data;
  int strLength = size/sizeof(char);
  for(int i = 0; i<strLength; i++) {
    //Special String specific end case
    if(*(stringData + i) == '\0') {
      break;
    }
    printf("%c",*(stringData + i));
  }
  printf("\n");
}

void printGenericInt(void * data,size_t size) {
  int* intData = (int *) data;
  int intLen = size/sizeof(int);
  for(int i = 0; i < intLen; i++) {
    printf("%d, ",*(intData + i));
  }
  printf("\n");
}

void printGenericFloat(void * data,size_t size) {
  float* floatData = (float *) data;
  int floatLen = size/sizeof(float);
  for(int i = 0; i < floatLen; i++) {
    printf("%f, ",*(floatData+i));
  }
  printf("\n");
}

void printGenericDouble(void * data,size_t size) {
  double* doubleData = (double *) data;
  int doubleLen = size/sizeof(double);
  for(int i = 0; i < doubleLen; i++) {
    printf("%lf, ",*(doubleData+i));
  }
  printf("\n");
}

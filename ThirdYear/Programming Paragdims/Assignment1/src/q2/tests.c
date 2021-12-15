#include <stdio.h>
#include <stdlib.h>
#include "tests.h"
#include "linkedList.h"

void runTests(){
  printf("Tests running...\n");
  listElement* l = createEl("Test String (1).", 30);
  //printf("%s\n%p\n", l->data, l->next);
  //Test create and traverse
  traverse(l);
  printf("\n");

  //Test insert after
  listElement* l2 = insertAfter(l, "another string (2)", 30);
  insertAfter(l2, "a final string (3)", 30);
  traverse(l);
  printf("\n");

  // Test delete after
  deleteAfter(l);
  traverse(l);
  printf("\n");

  //Test linkedlist Length
  printf("Length: %d\n\n",length(l));
  printf("Testing Push:\n");
  push(&l,"I was Pushed onto the linked list",50);
  traverse(l);
  printf("\nTesting Pop:\n");
  listElement * popped = pop(&l);
  free(popped);
  traverse(l);
  printf("\nTesting Enqueue:\n");
  enqueue(&l,"Hi",5);
  traverse(l);
  printf("\nTesting Dequeue:\n");
  listElement * last = dequeue(l);
  free(last);
  traverse(l);
  printf("\nTests complete.\n");
}

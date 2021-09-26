#include <stdlib.h>
#include <stdio.h>
#include <string.h>

typedef struct todo todo;

typedef struct todo {
	char item[100];
	todo* next;
}  todo;
void addItem(char *item);
void printTodoItems();
todo * first = NULL;
int main(){ 
	addItem ("Do Washing");
	addItem ("Buy House");
	addItem ("Eat dinner");  
	printTodoItems ();
}

void addItem(char * item) {
	if (first == NULL) {
		first = (todo *) malloc(sizeof(todo));
		first->next = NULL;
		strcpy(first->item,item);
		return;
	}
	todo * current = first;
	while (current->next != NULL) {
		current = current->next;
	}
	current->next = (todo *) malloc(sizeof(todo));
	current = current->next;
	strcpy(current->item,item);
	current->next = NULL;
}

void printTodoItems() {
	todo * current = first;
	while(current != NULL) {
		printf("%s\n",current->item);
		current = current->next;
	}
}
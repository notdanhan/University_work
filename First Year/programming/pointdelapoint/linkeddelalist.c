#include <stdio.h>
#include <stdlib.h>
typedef struct network network;

typedef struct network {
	int value;
	network * next;
} network;

void chaindelete(network *,network *);
void printarray(network *);

int main() {
	network * database = (network *) malloc(6 * sizeof(network));
	network * first = database;
	for (int i = 0; i < 5; i++) {
		(database + i) ->value = i;
		(database + i)->next = &database[i+1];
	}
	(database + 5) ->next = NULL;
	(database + 5) -> value = 5;
	//DELETE 4th VALUE
	chaindelete((database + 3),first);
	printarray(first);
}

void chaindelete(network * node, network * first) {
	network * curr = first;
	while(curr != NULL) {
		if(curr->next == node) {
			curr->next = node->next; //Removes it kind of
			node = NULL;
			free(node);
			break;
		}
		curr = curr->next;
	}
	return;
}

void printarray(network * first) {
	network * curr = first;
	while(curr != NULL) {
		printf("%d ",curr->value);
		curr = curr->next;
	}
}
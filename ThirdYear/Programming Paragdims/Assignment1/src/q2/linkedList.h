#ifndef CT331_ASSIGNMENT_LINKED_LIST
#define CT331_ASSIGNMENT_LINKED_LIST

typedef struct listElementStruct listElement;

//Creates a new linked list element with given content of size
//Returns a pointer to the element
listElement* createEl(char* data, size_t size);

//Prints out each element in the list
void traverse(listElement* start);

//Inserts a new element after the given el
//Returns the pointer to the new element
listElement* insertAfter(listElement* after, char* data, size_t size);

//Delete the element after the given el
void deleteAfter(listElement* after);

//Get LinkedList Length
int length(listElement * list);

//Push Element to head of array
void push(listElement ** list, char* data, size_t size);

//Pop Element from head of array
listElement* pop(listElement ** list);

//Queue Specific Methods
//Enqueue element to head of queue
void enqueue(listElement** list, char* data, size_t size);

//remove element from tail of queue
listElement* dequeue(listElement* list);
#endif

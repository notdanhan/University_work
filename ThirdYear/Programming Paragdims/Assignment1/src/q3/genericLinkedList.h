#ifndef CT331_ASSIGNEMT_LINKED_LIST_GENERIC
#define CT331_ASSIGNEMT_LINKED_LIST_GENERIC
typedef void(*fptr)(void *,size_t);
typedef struct genericListElementStruct genericListElement;
//Create a genericListElement with a function pointer
genericListElement* createGEl(void* data, size_t size, void(printFunc)(void*,size_t));

//Traverse and print each element in the list
void genericTraverse(genericListElement* start);

//inserts a new element after the given el
//returns a pointer to the new element
genericListElement* genericInsertAfter(genericListElement* after, void* data, size_t size, void(printFunc)(void*,size_t));

//Delete Element after the given element
void genericDeleteAfter(genericListElement* after);

//Get Generic List Length
int genericLength(genericListElement* list);

//Push Element to head of the array
void genericPush(genericListElement** list, void* data, size_t size, void(printFunc)(void*,size_t));

//Pop Element from head of array
genericListElement* genericPop(genericListElement ** list);

//Queue Specific functions
//Enqueue
void genericEnqueue(genericListElement** list, void * data, size_t size, void(printFunc)(void*,size_t));

//Dequeue
genericListElement* genericDequeue(genericListElement * list);


//Print Methods
void printGenericString(void * data, size_t size);

void printGenericInt(void*, size_t);

void printGenericFloat(void*, size_t);

void printGenericDouble(void*, size_t);

#endif

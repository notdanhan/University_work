int length(listElement * list) {
  int count = 0;
  listElement * temp = list;
  while(temp != NULL) {
    count++;
    temp=temp->next;
  }
  return count;
}

void push(listElement ** list, char* data, size_t size) {
  listElement* elem = createEl(data,size);
  elem->next = *list;
  *list = elem;
}

listElement* pop(listElement** list) {
  listElement* temp = *list;
  //reassign the start of the linked list to the second element
  *list = (*list)->next;
  //Remove reference to the start of the linked list
  temp->next = NULL;
  return temp;
}

void enqueue(listElement** list, char* data, size_t size) {
  //Does the same thing as push
  push(list,data,size);
}

listElement * dequeue(listElement* list) {
  //Create a Temp variable for traversal
  listElement * temp = list;
  //Queues with only One element
  if(temp->next == NULL) {
    list = NULL;
    return temp;
  }

  while(temp->next->next != NULL) {
    temp = temp->next;
  }

  listElement * temp2 = temp->next;
  temp->next = NULL;
  return temp2;
}
#include <stdio.h>
#include <string.h>

int main() {
	//Finds and replaces word in a string
	char instring[300];
	char find[20];
	char replace[20];
	printf("Type a sentence: ");
	scanf("%s\n",instring);
	printf("What word do you want to find? ");
	scanf("%s\n",find);
	printf("What would you like to replace it with? ");
	scanf("%s\n",replace);
	char *waddr[] = strstr(instring,find);
	if (waddr != NULL) {
		
	}
}
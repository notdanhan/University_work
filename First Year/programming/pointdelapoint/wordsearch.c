#include <stdio.h>
#include <string.h>

int main() {
	char chec[] = "dog";
	char atac[] = "cat";
	char *addr = strstr(chec,atac);
	if (addr == NULL) {
		printf("It aint there N'wah\n");
	}
}
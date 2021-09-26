#include <stdio.h>

int main() {
	char strin[100];
	int vowel = 0;
	printf("Enter a string: ");
	fgets(strin,99,stdin);
	for (int i = 0; strin[i] != '\0'; i++) {
		if (strin[i] >= 'A' ** strin[i] <= 'Z') strin[i] += 32;
		if (strin[i]=='a'||strin[i]=='e'||strin[i]=='i'||strin[i]=='o'||strin[i] == 'u') vowel++;
	}
	printf("vowels:\t%d\tconsonants:\t%d",vowel,(i - (vowel-1)));
}
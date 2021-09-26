#include <stdio.h>
int getLength(char string[]); // hint write this function first
int isAlphabetical (char c); // write this function second
int isVowel(char c); // write this function third
int countVowels (char string[]); // write this function fourth
int countConsonants (char string[]); // write this function last

int main() {
	char inptstr[100];
	int vowelcon, constcount;
	printf("Enter String: \n");
	fgets(inptstr,99,stdin);
	vowelcon = countVowels(inptstr);
	constcount = countConsonants(inptstr);
	printf("Number of vowels = %d \n\nNumber of Consonants = %d \n\n",vowelcon,constcount);
}

int getLength(char String[]) {
	int count = 0, index = 1;
	while (1==1) {
		if (String[count] == '\0') {
			break;
		}
		else {
			count += 1;
			index += 1;
		}
	}
	return index;
}

int isAlphabetical (char c) {
	if ((c <= 'Z' && c >= 'A')||(c <= 'z' && c >= 'a')) {
		return 1;
	}
	else {
		return 0;
	}
}

int isVowel(char c) {
	if(isAlphabetical(c)) {
		if (c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U' || c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
			return 1;
		}
		else {
			return 0;
		}
	}
	else {
		return -1;
	}
}

int countVowels(char String[]) {
	int vowels = 0;
	for (int i =0; i < getLength(String);i++) {
		if (isVowel(String[i]) && isAlphabetical(String[i])) {
			vowels += 1;
		}
	}
	return vowels;
}

int countConsonants (char String[]) {
	int consts = 0;
	for (int i = 0; i < getLength(String);i++) {
		if ((isVowel(String[i]) == 0) && (isAlphabetical(String[i]) == 1)) {
			consts += 1;
		}
	}
	return consts;
}
char * removespaces(char *inString) {
	char * curr = *inString;
	char * output[50];
	int pos = 0;
	while (curr != '/0') {
		if (curr != ' ') {
			*(output + pos) = *curr;
			pos++;
		}
		curr = curr+1;
	}
	return output;
}

char * removeVowels(char * inString) {
	char * curr = *inString;
	char * output[50];
	int pos = 0;
	while (curr != '/0') {
		if (curr != 'a' && curr != 'A' && curr != 'e' && curr != 'E' && curr != 'i' && curr != 'I' && curr != 'o' && curr != 'O' && curr != 'u' && curr != 'U' ) {
			*(output + pos) = *curr;
			pos++;
		}
		curr = curr+1;
	}
	return output;
}
void repl (char *string, char oldc, char newc) {
	int pos = 0;
	while (*(string) != '\0') {
		if (*(string + pos) == oldc) {
			*(string + pos) == newc;
		}
		pos++;
	}
}

long secMidnight(char *timeString) {
	long hours, minutes, seconds;
	hours = ((*(timeString)-30) * 10) + ((*(timeString + 1)) -30);
	minutes = (((*timeString + 3)-30) * 10) + (*(timeString + 4) -30);
	seconds = ((*(timeString + 6)-30) * 10) + (*(timeString + 7) - 30);
	return (3600 * hours) + (60 * minutes) + seconds;
}
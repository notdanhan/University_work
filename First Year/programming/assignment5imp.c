#include <stdio.h>
#include <string.h>
double calculateArea(double height, double width); /*Unnessesary functions that are here to prove you can do functions */
double calculatePaint(double area, int numCoats, double m2PerLitre);
int main() {
	double area = 0.0, width, height; /*Use of doubles unnessesary memory wastage, should use floats*/
	int coats = 0, phase = 0;
	char obj[2][8];
	char con1[2];
	strcpy(obj[0],"Wall");
	strcpy(obj[1],"Window");
	while (phase < 2) {
		printf("Enter %s Height(m): ",obj[phase]); 
		scanf(" %lf",&height);
		printf("Enter %s Width(m): ",obj[phase]);
		scanf(" %lf",&width); /*Works in two phases 0 walls, 1 windows */
		(phase == 0) ? area += calculateArea(height,width) : (area -= calculateArea(height,width));
		printf("More %s s (y/n): ",obj[phase]);
		scanf(" %s",con1); /* Had to use a string due to weird gcc behaviour, does not work in VStudio*/
		if (!strcmp(con1,"n")) {
			if (phase == 0) {
				printf("\nTotal Wall Area = %0.2lf\n",area);
				printf("How many doors (Double doors count as 2) ");
				scanf("%d",&coats);
				area = area - (coats * 2); /*Assuming a door incl. edging occupies 2m² */
				printf("\nTotal Wall Area minus doors  = %0.2lf\n\n",area);
				printf("Are there windows in the room (y/n):");
				scanf("%s",&con1);
				if (strcmp(con1,"n")) phase++; 
				else break ; /* could not use single line if/else syntax due to nature of break */
			}
			else if (phase == 1) {
				printf("\nTotal Wall Area minus windows  = %0.2lf\n\n",area);
				break;
			}
		}
	} /*Get the rest lol*/
	printf("What is the coverage in m2 per litre of your paint? ");
	scanf(" %lf",&width);
	printf("\nHow many coats do you want? ");
	scanf(" %d",&coats);
	area = calculatePaint(area,coats,width);
	coats = (int) (area + 0.5f); /*Rounds to nearest whole via typecasting and sneaky addition, Even though you'd need to always round up for paint this is what they wanted*/
	printf("You will need %0.2lf litres of paint or %d to the nearest litre of paint \n",area,coats);
} /*GCC does not ask you to return anything for int main() */
double calculateArea(double height, double width) {
	return width * height; /*This would take less time to type out each time it's used considering it's only used twice*/
}
double calculatePaint(double area, int numCoats, double m2PerLitre) {
	return ((area / m2PerLitre)*((float)numCoats)); /*Single use function (Useless in the grand scheme of things)*/
}
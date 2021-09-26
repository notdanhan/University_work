#include <stdio.h>
#include <string.h>

double calculateArea(double height, double width);

double calculatePaint(double area, int numCoats, double m2PerLitre);

double lesswindows(double area, double width, double height);

double lessdoors(double area);

int main() {
	double area, width, height;
	int coats, phase;
	char obj[2][8];
	char con1[2];
	strcpy(obj[0],"Wall");
	strcpy(obj[1],"Window");
	phase  = 0;
	area = 0.0;
	coats = 0;
	while (phase < 2) {
		printf("Enter "); 
		printf(obj[(phase)]); 
		printf(" Height (m): ");
		scanf(" %lf",&height);

		printf("Enter ");
		printf(obj[(phase)]);
		printf(" Width (m): ");
		scanf(" %lf",&width);

		if (phase == 0) { /*Walls*/
			area += calculateArea(height,width);
		}
		if (phase == 1) { /*Windows*/
			area = area - calculateArea(height,width);
		}

		printf("More ");
		printf(obj[(phase)]);
		printf("s (y/n): ");
		scanf(" %s",con1);
		if (!strcmp(con1,"n")) {
			if (phase == 0) {
				printf("\nTotal Wall Area = %0.2lf\n",area);
				area = lessdoors(area);
				printf("\nTotal Wall Area minus doors  = %0.2lf\n\n",area);
				printf("Are there windows in the room (y/n):");
				scanf("%s",&con1);
				if (!strcmp(con1,"n")) {
					break;
				}
				else {
					phase++;
				}
			}
			else if (phase == 1) {
				printf("\nTotal Wall Area minus windows  = %0.2lf\n\n",area);
				break;
			}
		}
	}
	/*Get the rest lol*/
	printf("What is the coverage in m2 per litre of your paint? ");
	scanf(" %lf",&width);
	printf("\nHow many coats do you want? ");
	scanf(" %d",&coats);
	area = calculatePaint(area,coats,width);
	coats = (int) (area + 0.5f);
	printf("You will need %0.2lf litres of paint or %d to the nearest litre of paint \n",area,coats);
}

double calculateArea(double height, double width) {
	return width * height;
}

double calculatePaint(double area, int numCoats, double m2PerLitre) {
	return ((area / m2PerLitre)*((float)numCoats));
}

double lessdoors(double area) {
	int doors;
	printf("How many doors (Double doors count as 2) ");
	scanf("%d",&doors);
	doors = doors * 2;
	return (area - doors);
}
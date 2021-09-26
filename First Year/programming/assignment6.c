#include <stdio.h>
#include <math.h>
#include <strings.h>

#define PI 3.14159265358979

int main() {
	int i = 0;
	double angle;
	double angle_rads;
	char inpt[30];
	char rpt = 'y';
	while(1==1) {
		printf("Enter a function using degrees eg. 'sin 45'\n");
		scanf("%s %lf",inpt,&angle);
		angle_rads = (angle/360) * (PI * 2.0); /*Deg rads conversion */
		while(inpt[i]) {
			inpt[i] = toupper(inpt[i]);
			i++;
		}
		i = 0;
		if(!strcmp(inpt,"SIN")) {
			angle_rads = sin(angle_rads);
		}
		else if (!strcmp(inpt,"COS")) {
			angle_rads = cos(angle_rads);
		}
		else if (!strcmp(inpt,"TAN")){
			angle_rads = tan(angle_rads);
		}
		else {
			printf("Function is not defined \n");
			continue;
		}
		printf("%s(%lf)\t=\t%0.5lf\n",inpt,angle,angle_rads);
		printf("Would you like to perform another calculation? ");
		scanf(" %c",&rpt);
		if (toupper(rpt) == 'N') {
			break;
		}
	}
}
#include <stdio.h>

#define PI 3.14159265358979323846

float get_rect(){
	float w, h;
	printf("Input Base Length: ");
	scanf("%f",&w);
	printf("Input Height: ");
	scanf("%f",&h);
	return w * h;
}

int main() {
	int shape_choice;
	float area,num1,num2;
	puts("Area Calculator\nEnter Choice:\n1 <Triangle>\n2 <Square>\n3 <Rectangle>\n4 <Parallelogram>\n5 <Trapezoid>\n6 <Circle>\n7 <Ellipse>\n8 <Sector>");
	printf("Choice: ");
	scanf("%d",&shape_choice);
	switch (shape_choice) {
		case 1:
			printf("Input Base Length: ");
			scanf("%f",&area);
			printf("Input Height: ");
			scanf("%f",&num1);
			area = (area * 0.5f) * num1; 
			break;
		case 2:
			printf("Input Side Length: ");
			scanf("%f",&area);
			area = area * area;
			break;
		case 3:
			area = get_rect();
			break;
		case 4:
			area = get_rect();
			break;
		case 5:
			printf("Input bottom side length: ");
			scanf("%f", &area);
			printf("Input Top Side Length: ");
			scanf("%f", &num1);
			printf("Input Vertical Height: ");
			scanf("%f",&num2);
			area = ((num1 + area)/2) * num2;
			break;
		case 6:
			printf("Input Radius Length: ");
			scanf("%f",&area);
			area = PI * (area * area);
			break;
		case 7:
			printf("Input Major Radius: ");
			scanf("%f",&area);
			printf("Input Minor Radius: ");
			scanf("%f",&num1);
			area = area * num1 * PI;
			break;
		case 8:
			printf("Input Sector Radius: ");
			scanf("%f",&area);
			printf("Input Sector Angle (In radians): ");
			scanf("%f",&num1);
			area = ((area * area) * num1 * 0.5f);
			break;
		default:
			printf("Not in Index");
			area = 0.0f;
	}
	printf("Area = %0.2f",area);
}
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct date {
	int day;
	int month;
	int year;
} date;

typedef struct sale {
	date sale_date;
	char region[50];
	char rep[50];
	char itemName[50];
	int units;
	float unit_cost;
	float total_cost;
} sale;

int main() {
	sale sales_data[43];
	char line[200];
	char region[20];
	char item[20];
	int totalSales = 0;
	float totalRevenue = 0;
	FILE * fptr = fopen("SampleData.txt","r");
	fgets(line,200,fptr);
	int i = 0;
	while(!feof(fptr)) {
		fscanf(fptr,"%d/%d/%d %s %s %s %d %f %f",&sales_data[i].sale_date.day,&sales_data[i].sale_date.month,&sales_data[i].sale_date.year, sales_data[i].region,sales_data[i].rep,sales_data[i].itemName,&sales_data[i].units,&sales_data[i].unit_cost,&sales_data[i].total_cost);
		i++;
	}

	printf("Enter a region and an Item separated by a space: ");
	scanf("%s %s",region,item);
	for(i = 0; i<43;i++) {
		if(!strcmp(sales_data[i].region,region) && !strcmp(sales_data[i].itemName,item)) {
			totalSales += sales_data[i].units;
			totalRevenue += sales_data[i].total_cost;
		}
	}
	printf("Total units sold for %s in region %s: %d with Total value %.2f\n",item,region,totalSales,totalRevenue);
	fclose(fptr);
}

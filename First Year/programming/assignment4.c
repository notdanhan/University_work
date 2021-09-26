#include <stdio.h>
#include <string.h>

int main() {
    char inpt[3][21];
    strcpy(inpt[0], "9rWLK0gFoHZqNIj3DMkR");
    strcpy(inpt[1], "q405IHl0sOx5BrmDTeoh");
    strcpy(inpt[2], "95EkdriUzNFQTP5vuhR3");
    for (int i = 0; i <= 2; i++) {
        int nums,upps,downs;
        nums = 0;
        upps = 0;
        downs = 0;
        printf("String #%d",i);
        printf("\nOriginal String: %s\n",inpt[i]);
        for (int x = 0; x <= 20;x++) {
            if (inpt[i][x]== '\0') {
                break;
            }
            if (inpt[i][x] >= 'A' && inpt[i][x] <= 'Z') {
                inpt[i][x] = inpt[i][x] + 32;
                upps++;
            }
            else if (inpt[i][x] >= 'a' && inpt[i][x] <= 'z') {
                inpt[i][x] = inpt[i][x] - 32;
                downs++;
            }
            else if (inpt[i][x] >= '0' && inpt[i][x] <= '9') {
                inpt[i][x] = '*';
                nums++;
            }
        }
        printf("New String:\t %s",inpt[i]);
        printf("\n%d digits to spaces; %d lower to upper; %d upper to lower\n",nums,downs,upps);
    }
}

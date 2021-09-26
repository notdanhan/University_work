#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <unistd.h>
#include <sys/wait.h>

int inpipe[2], outpipe[2];  // These are global variables
unsigned char was_updated = 0;
void Adder(void);  // Computes the sum of 4 numbers in "inpipe"
                    // Puts answer into "outpipe"

int SubProc(int a, int b, int c, int d);
           // Puts four numbers to be summed into "inpipe"
           // Reads answer from "outpipe".

int pipeSum(int a,int b, int c, int d) {
  int ParentsPID , ans= 0;
  pipe(inpipe);
  pipe(outpipe);

  ParentsPID = getpid(); // now I'll always know who I am

  fork(); // Make 1st subprocess
  if (getpid() == ParentsPID)
    fork(); // Make a second subprocess

  if ( getpid() == ParentsPID )
  {
    Adder();  // Add for the 1st subproc
    Adder();  // Add for the 2nd subproc
  }
  else
  {
    ans = SubProc(a,b,c,d);
    printf("SubProc (%d): %d+%d+%d+%d= %d\n", getpid(), a, b, c, d, ans);
		exit(0);// subprocess should exit after printing the answer
  }
	printf("%d",ans);
  return(ans);
}

int main() {
  int a,b,c,d,result;
  int successes = 0;
  int fails = 0;
  time_t t;
  //Seed the random number generator
  srand(time(&t));
  //Run 100 tests
  for(int i = 0; i < 2; i++) {
    a = rand() % 1024;
    b = rand() % 1024;
    c = rand() % 1024;
    d = rand() % 1024;
    result = a + b + c + d;
		int pipeResult = pipeSum(a,b,c,d);
    if(result == pipeResult) {
      successes++;
    } else {
      fails++;
    }
	}
	printf("Test Results, %d Passes %d fails\n",successes,fails);
	return 0;
}

void Adder(void ) {
  int i, number, sum=0;

  for (i=0; i<4; i++)
  {
    read(inpipe[0], &number, sizeof(int));
    sum += number;
  }
  write(outpipe[1], &sum, sizeof(int));
}

int SubProc(int a, int b, int c, int d) {
  int ans;
  printf("SubProc (%d) writes four numbers to the pipe()\n",
	 getpid());
  write(inpipe[1], &a, sizeof(int));
  write(inpipe[1], &b, sizeof(int));
  write(inpipe[1], &c, sizeof(int));
  write(inpipe[1], &d, sizeof(int));

  printf("SubProc (%d) reads the answer from a pipe()\n",
	getpid());
  read(outpipe[0], &ans, sizeof(int));
  return(ans);
}

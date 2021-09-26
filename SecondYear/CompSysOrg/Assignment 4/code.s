.data

.balign 4

Size : .word 6

Fib : .word 0,0,0,0,0,0

 

.text

.global main

main:

	PUSH {lr}          /*save return address from main on stack*/
	LDR r0, addr_Size          /* r0 has the Address of Size */
	LDR r0, [r0]        /* r0 has the value of Size */
	PUSH {r0}         /*push size value on stack as 1st parameter*/
	LDR r0, addr_Fib           /*r0 has Address of Fib array*/
	PUSH {r0}         /*push address of Fib array on stack as 2nd parameter*/
	BL generate_fib_series            /*Call function to generated fib series in Fib array*/

	/* write the remaining code or instructions here .. */
	POP {lr} /*Load return address and put it into lr*/
	bx lr	/*Terminate the program*/

 

addr_Size : .word Size

addr_Fib : .word Fib

 

generate_fib_series:
	/*write the definition of function here…*/
	POP {r4} /*Load address of Array into r4*/
	POP {r3} /*Load size of Array into r3*/
	STR #1, [r4] /*Stores 1 at Fib[0]*/
	ADD r4, r4, #4 /*Moves Fib pointer 4 bytes*/
	SUB r3, r3, #1 /*Takes one from the counter*/
	MOV r1, #1 /* populates r1 with 1*/
	MOV r2, #0 /*Populates r2 with 0*/
	B fib
fib:
	ADD r0, r1, r2	/*Adds Fib[i-1] and Fib[i-2]*/
	MOV r2, r1		/*Copies r1 into r2*/
	MOV r1, r0		/*Copies r0 into r1*/
	STR r0, [r4]	/*Stores r0 into Fib[i] */
	ADD r4, r4, #4	/*Moves Fib pointer 4 bytes*/
	SUB r3, r3, #1	/*Takes one away from the counter*/
	CMP r3, #0		/*If else/ if num>0 return to fib else exit loop*/
	BGT fib
	BEQ exit
exit:
	BX lr /*Return to main*/
.data

.balign 4
N : .word 6
Fib : .word 0,0,0,0,0,0

.text

.global main

main:
	LDR r1, addr_N	/*r1 has the address of N*/
	LDR r1, [r1]	/*r1 has the value of N*/
	LDR r2, addr_Fib /*r2 has the Address of array Fib*/
	MOV r0, #1
	STR r0, [r2] /*Store 1 on Fib[0]*/
	ADD r2, r2, #4 /*r2 moved to next element Address by 4 bytes*/
	STR r0, [r2] /*Store 1 on Fib[1]*/
	SUB r1, #2 /*Subtract 2 from N*/
	MOV r4, #1 /*Load 1 into r4*/
	MOV r5, #1 /*Load 1 into r5*/
loop:
	SUB r1, #1	
	ADD r0, r4, r5
	ADD r2, r2, #4
	LDR r0 [r2]
	MOV r5, r4
	MOV r4, r0
	CMP r1, #0
	BLE exit
	BGT loop

exit:
	BX lr

addr_N : .word N
addr_Fib : .word Fib
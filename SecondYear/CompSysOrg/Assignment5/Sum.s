.data
.balign 4
array: .skip 400
.text
.global main
main:
	mov r3, #41 /* change this value   */
outer_loop:     /* of r3 to measure    */
	cmp r3, #0  /* different cpu cycles*/
	beq end
	ldr r1, address_of_array
	mov r2, #0
inner_loop:
	cmp r2, #100
	beq break_inner_loop
	mov r4, r2
	and r4, r4, #1
	cmp r4, #0
	bne odd_index	   				/*************/
	add r5, r2, r2, LSL #2  /*these lines*/
	b next_index            /*           */
odd_index:		   					/*need       */
	mov r5, r2, LSL #2     	/*           */
	add r5, r5, #1         	/*predication*/
next_index:              	/*************/
	str r5, [r1]
	add r1, r1, #4
	add r2, r2, #1
	b inner_loop
break_inner_loop:
	sub r3, r3, #1
	b outer_loop
end:
 	bx lr

address_of_array : .word array

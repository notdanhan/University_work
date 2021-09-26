.data

.balign 4
ans: .asciz "answer is %f\n"

.balign 4

varA : .float 3.0
varB : .float 4.5
varC : .float 0.0

.text
.global main
main:
	push {lr}	/*Add lr to the stack - allows branching*/
	ldr r1, address_of_varA	/*Load */
	vldr s0, [r1]
	ldr r1, address_of_varB
	vldr s1, [r1]
	vcmp.f32 s0, s1
	vmrs APSR_nzcv, FPSCR /*copying nzcv bits of fpscr to cpsr in order to take conditional jump*/
	blt add_numbers
 	vsub.f32 s3, s0, s1
	b end
add_numbers:
	vadd.f32 s3, s0, s1
end:
	ldr r1, address_of_varC
	vstr s3, [r1]

	vcvt.f64.f32	d3, s3 /*convert it into double precision if you want to print it*/
	vmov	r1, r2, d3     /*move converted value bits in r1 and r2*/
	ldr r0, address_of_ans
	bl printf

	pop {lr}
	bx lr

address_of_varA: .word varA
address_of_varB: .word varB
address_of_varC: .word varC
address_of_ans: .word ans

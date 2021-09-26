fruits = []
while True:
	fruit = input("Enter a fruit ")
	if (fruit == "end"):
		break
	else:
		fruits.append(fruit)

print("You entered %s fruits!"%(len(fruits)))
for fruit in fruits:
	print(fruit)

print("Watch me whip")
print(fruits[::-1])
print("now watch me nae nae")
fruits.sort()
print(fruits)
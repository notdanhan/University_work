total = 0
def tally(): #Gets Tallies lmao
    global total
    total += int(input("Enter a number: "))
while True:
    rango = int(input("How many numbers would you like to add: "))
    if (rango <= 5 and rango >= 1):
        for i in range(rango):
            tally()
        break
print("total is %d"%(total))
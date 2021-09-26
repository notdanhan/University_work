from random import randint
num = randint(1,5)
win = False
for i in range(3):
    guess = int(input("Guess a number between one and five "))
    if (guess == num):
        win = True
        break

if (win):
    print("Youre winner")
else:
    print("Loser hahaha")
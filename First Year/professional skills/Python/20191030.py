def powof(num1,num2):
    return (num1 ** num2)

def arrayofnums():
    nums= input("Enter base and exponent separated by space: ").split()
    nums[0] = int(nums[0])
    nums[1] = int(nums[1])
    return nums

def main():
    nums = arrayofnums()
    rev = input("String goes here: ")
    rev = rev[::-1]
    print("%s" % rev)
    print("%s"%(powof(nums[0],nums[1])))
    nums = arrayofnums()
    if (nums[0] > nums[1]):
        print("%s > %s"% (nums[0],nums[1]))
    elif (nums[1] > nums[0]):
        print("%s < %s"% (nums[0],nums[1]))
    else:
        print("%s = %s"% (nums[0],nums[1]))

    day = input("Day of the week: ")
    day = day.lower()
    if (day == "monday"):
        print("It is Tuesday")
    elif (day == "tuesday"):
        print("Happy Saturday!")
    elif (day == "wednesday"):
        print("I hate mondays")
    elif(day == "thursday"):
        print("Happy Friday")
    elif(day == "friday"):
        print("It is Sunday")
    elif (day == "saturday"):
        print("It is Wednesday")
    elif (day == "sunday"):
        print("Happy Friday!!!")
main()
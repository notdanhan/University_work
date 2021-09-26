import random
import datetime
locations = ["Galway","Roscommon","Cork","Ballybunion","Bunclody","Wexford","Kildare","Tullamore","Athlone","Athboy","Ballybofey","Castlebar","New Ross","Wexford Town","Ballinasloe","Dundalk","Dublin","Castlerea","Boyle","Kill","Athenry","Moyvane","Tarbert","Foynes","Limerick","Letterfrack","Oughterard","Lanesboro","Longford","Maynooth","Derry","Lisdoonvarna","Poundtown","Birr","Tuam","Ballyjamesduff","Garryowen","Leixlip"]
data = open("dummydata.txt","w")
destinations = int(input("How much data do you want to generate: "))
for i in range(0,destinations):
	#Dates are between 1/1/2021 00:00 and 1/1/2022 00:00
	date1 = random.randint(1609459200,1640995200)
	#Make Sure the arrival time is always in the future
	date2 = random.randint((date1+1800),(date1 + 28800))
	d1 = datetime.datetime.fromtimestamp(date1)
	d2 = datetime.datetime.fromtimestamp(date2)
	data.write("journeys.add(new Trip(%d,%.2ff,\"%s\",\"%s\",\"%s\",\"%s\",\"%s\",\"%s\"));\n" % (random.randint(0,10000),random.uniform(5,30),locations[random.randint(0,len(locations) -1 )],locations[random.randint(0,len(locations) - 1)],d1.strftime("%d/%m/%y"),d1.strftime("%H:%M"),d2.strftime("%d/%m/%y"),d2.strftime("%H:%M")))
data.close()

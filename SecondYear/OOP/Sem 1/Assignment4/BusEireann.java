public class BusEireann extends Company {
	public BusEireann() {
		super();
		companyName = "BusEireann";
		journeys.add(new Trip(5947,44.19f,"Foynes","Maynooth","18/6/2020","11:2","16/2/2020","22:23"));
		journeys.add(new Trip(3414,58.52f,"Moyvane","Athenry","9/10/2020","22:3","22/1/2020","4:3"));
		journeys.add(new Trip(8540,22.05f,"Derry","Foynes","6/4/2020","15:29","2/10/2020","20:20"));
		journeys.add(new Trip(3231,19.37f,"Tarbert","Ballyjamesduff","23/9/2020","15:45","4/1/2020","18:41"));
		journeys.add(new Trip(2650,26.58f,"Moyvane","Longford","3/2/2020","16:10","24/12/2020","0:49"));
		journeys.add(new Trip(2018,83.17f,"Moyvane","Castlerea","22/5/2020","8:50","5/5/2020","20:35"));
		journeys.add(new Trip(1694,28.76f,"Poundtown","Wexford","13/6/2020","7:49","7/6/2020","14:16"));
		journeys.add(new Trip(1195,12.27f,"Letterfrack","Tuam","18/1/2020","21:58","28/2/2020","20:17"));
		journeys.add(new Trip(9283,7.43f,"Foynes","Tarbert","24/4/2020","12:38","1/7/2020","21:51"));
		journeys.add(new Trip(913,91.41f,"Roscommon","Foynes","19/8/2020","1:21","18/6/2020","4:31"));
		journeys.add(new Trip(1202,33.15f,"Longford","Maynooth","3/4/2020","17:53","14/5/2020","19:7"));
	}
}

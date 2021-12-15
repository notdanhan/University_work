package com.ct326.nuigalway.ie;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;

public class ObjectSerializationTest {
	public static void main(String[] args) {
		ArrayList<Celebrity> celebrityArrayList = new ArrayList<>();
		//Create some celebrities
		//Java allows for UTF-16 so I can use fadas
		Celebrity temp = new Celebrity(0,"John Sample", "Garda AgeCard Model", LocalDate.of(1989,01,27));
		temp.addAward(new Award("Most Attractive Male","An Garda Síocana",LocalDate.of(2007,1,1)));
		temp.addAward(new Award("Least Likely to forget their age card","An Garda Síocana", LocalDate.of(2011,8,24)));
		temp.addAward(new Award("Purple Heart","HRH Queen Elizabeth II",LocalDate.of(2020,7,16)));
		celebrityArrayList.add(temp);
		temp = new Celebrity(1,"Seosaimhín Eilionór Williams-Fitzpatrick","PSC model",LocalDate.of(1980,1,1));
		temp.addAward(new Award("Most Likely to be investigated for GDPR Violations","Irish Data Commissioner",LocalDate.of(2018,02,22)));
		temp.addAward(new Award("Most Likely to be used as model for the PSC","Department of Welfare & Social Protection",LocalDate.of(2015,1,1)));
		celebrityArrayList.add(temp);
		temp = new Celebrity(2,"Benjamin Reichwald","Musician",LocalDate.of(1994,4,4));
		temp.addAward(new Award("Number 1 in Reality","year0001",LocalDate.of(2003,1,1)));
		temp.addAward(new Award("Most Iced Out","Forbes",LocalDate.of(2020,3,3)));
		celebrityArrayList.add(temp);
		temp = new Celebrity(3,"Hans Peter Geerdes","Musician",LocalDate.of(1964,3,16));
		temp.addAward(new Award("Most Likely to rewrite a song for the irish market","The Nation of Ireland",LocalDate.of(1994,04,21)));
		celebrityArrayList.add(temp);
		temp = new Celebrity(4,"Enya Patricia Brennan","Musician",LocalDate.of(1961,5,17));
		temp.addAward(new Award("Best Song","Critics Choice Movie",LocalDate.of(2002,1,3)));
		temp.addAward(new Award("Best New Age Album","Grammy",LocalDate.of(1997,1,2)));
		temp.addAward(new Award("Best New Age Album","Grammy",LocalDate.of(2002,5,2)));
		celebrityArrayList.add(temp);

		try {
			FileOutputStream out = new FileOutputStream("celebrities");
			ObjectOutputStream s = new ObjectOutputStream(out);
			for(Celebrity celeb : celebrityArrayList) {
				s.writeObject(celeb);
			}
			s.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

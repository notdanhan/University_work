package com.ct326.nuigalway.ie;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Celebrity Class
 * @author Daniel Hannon (19484286)
 */
//Ignore method unused warnings
@SuppressWarnings("unused")
public class Celebrity implements Serializable {
	private int id;
	private String celebrityName;
	private String profession;
	private LocalDate dateOfBirth;
	private transient List<Award> awards; //Marked Transient as it is not serializable as per assignment spec

	/**
	 * Constructor
	 * @param id - Celebrity ID number
	 * @param celebrityName - Celebrity Name
	 * @param profession - Celebrity Profession
	 * @param dateOfBirth - Date of Birth
	 */
	public Celebrity(int id, String celebrityName, String profession, LocalDate dateOfBirth) {
		this.id = id;
		this.celebrityName = celebrityName;
		this.profession = profession;
		this.dateOfBirth = dateOfBirth;
		this.awards = new ArrayList<>();
	}

	//Getters and setters (Not used currently but could be in a future assignment IDK)
	public String getCelebrityName() {
		return celebrityName;
	}

	public int getId() {
		return id;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public String getProfession() {
		return profession;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public void setCelebrityName(String celebrityName) {
		this.celebrityName = celebrityName;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setProfession(String profession) {
		this.profession = profession;
	}

	public void addAward(Award award) {
		this.awards.add(award);
	}

	public void removeAward(Award award) {
		this.awards.remove(award);
	}

	/**
	 * Write Object, it writes awards to a text file called awards.txt
	 * @param s the ObjectOutputStream
	 * @throws IOException failed to write object
	 */
	@Serial
	private void writeObject(ObjectOutputStream s) throws IOException {
		s.defaultWriteObject();
		PrintWriter out = null;
		try {
			//Open awards.csv, add the contents of the arraylist to the end of the file
			out = new PrintWriter(new FileOutputStream("awards.csv",true));
			for(Award award: this.awards){
				out.println(String.format("%d,%s,%s,%s",this.id,award.getAwardName(),award.getAwardOrganization(),award.getAwardDate().toString()));
			}
		} catch(IOException err) {
			err.printStackTrace();
		} finally {
			if(out != null) {
				out.close();
			}
		}
	}

	/**
	 * Read Object, Reads awards from awards.txt
	 * @param s the ObjectInputStream
	 * @throws IOException failed to read object from the input stream
	 * @throws ClassNotFoundException failed to find the class
	 *
	 */
	@Serial
	private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
		s.defaultReadObject();
		this.awards = new ArrayList<>();
		Award temp;
		BufferedReader in = null;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		try {
			//Read in the awards
			in = new BufferedReader(new FileReader("awards.csv"));
			String line;
			while((line=in.readLine())!=null) {
				//I Tried using Scanner here, but it would not work, so instead I used StringTokenizer
				StringTokenizer tokenizer = new StringTokenizer(line,",");
				//Check if the ID matches, if yes continue parsing, if no go to the next line
				try {
					if(Integer.parseInt(tokenizer.nextToken()) == this.id) {
						temp = new Award(tokenizer.nextToken(), tokenizer.nextToken(), LocalDate.parse(tokenizer.nextToken(), formatter));
						awards.add(temp);
					}
				} catch(Exception err) {
					//This is to deal with any junk data within the csv, print it as a formality
					System.out.println("Could not parse record: " + line);
				}
			}
		} catch(IOException err) {
			err.printStackTrace();
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch(IOException err){
				err.printStackTrace();
			}
		}
	}

	@Override
	public String toString() {
		String temp = String.format("Name: %s\nProfession: %s\nDate of Birth: %s\nAwards: \n",this.celebrityName,this.profession,this.dateOfBirth.toString());
		for(Award award:awards) {
			temp = String.format("%s%s\n\n",temp,award.toString());
		}
		return temp;
	}
}

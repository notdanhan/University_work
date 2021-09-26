
public class Canary extends Bird
{

	String name; // the name of this Canary

	/**
	* Constructor for objects of class Canary
	*/
	public Canary(String name)
	{
		super(); // call the constructor of the superclass Bird
		this.name = name;
		this.colour = "yellow"; // this overrides the value inherited from Bird
	}
	public String getName() {
		return this.name;
	}
	/**
	 * Sing method overrides the sing method
	 * inherited from superclass Bird
	 */
	@Override // good programming practice to use @Override to denote overridden methods
	public void sing(){
		System.out.println("tweet tweet tweet");
	}

	/**
	 * toString method returns a String representation of the bird
	 * What superclass has Canary inherited this method from? 
	 */
	@Override
	public String toString(){
		String strng ="";
		strng+= "Canary; ";
		strng+= "name: ";
		strng+= this.name;
		strng+= "; ";
		strng+= "colour: ";
		strng+= this.colour;
		strng+= " has Feathers; ";
		strng+= this.hasFeathers;
		strng+= "; Has wings: ";
		strng+= this.hasWings;
		strng+= "; Flies: ";
		strng+= this.flies;
		strng+= "; breathes: ";
		strng+= this.breathes;
		strng+= "; has skin: ";
		strng+= this.hasSkin;
		strng+= "\n";
		return strng;
	}


	/**
	 * equals method defines how equality is defined between 
	 * the instances of the Canary class
	 * param Object
	 * return true or false depending on whether the input object is 
	 * equal to this Canary object
	 */

	@Override
	public boolean equals(Object obj){
		try {
			obj = (Canary)obj; /*Gets rid of passing a string in*/
			return obj.toString().equals(this.toString()); /*Converts to strings and compares both strings*/
		} catch(Exception e) {
			return false;
		}
	}
}

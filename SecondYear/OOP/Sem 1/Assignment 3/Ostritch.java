public class Ostritch extends Bird{
	boolean isTall;
	boolean hasLongThinLegs;
	String name;
	
	Ostritch(String name) {
		super();
		this.name = name;
		this.isTall = true;
		this.hasLongThinLegs = true;
		this.flies = false;
	}

	@Override
	public String toString() {
		String output = "";
		output += "Ostritch; ";
		output += "Name: ";
		output += this.name;
		output += "; Color: ";
		output += this.colour;
		output += "; Is Tall: ";
		output += this.isTall;
		output += "; Has Long Thin Legs: ";
		output += this.hasLongThinLegs;
		output += "; Flies: ";
		output += this.flies;
		output += "; Has Feathers: ";
		output += this.hasFeathers;
		output += "; has wings: ";
		output += this.hasWings;
		output += "; Breathes: ";
		output += this.breathes;
		output += "; Has Skin: ";
		output += this.hasSkin;
		output += "\n";

		return output;
	}

	@Override
	public boolean equals(Object obj) {
		try {
			obj = (Ostritch)obj;
			return this.toString().equals(obj.toString());
		} catch(Exception e) {
			return false;
		}
	}

	public boolean isTall() {
		return this.isTall;
	}

	public boolean hasLongThinLegs() {
		return this.hasLongThinLegs;
	}

	public String getName() {
		return this.name;
	}
}

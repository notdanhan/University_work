public class Shark extends Fish {
	boolean canBite;
	boolean isDangerous;
	String name;
	
	public Shark(String name) {
		super();
		this.name = name;
		canBite = true;
		isDangerous = true;
	}

	public boolean canBite() {
		return this.canBite;
	}

	public boolean isDangerous() {
		return this.isDangerous;
	}

	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		String output = "Shark";
		output += " Name: " + this.name;
		output += "; Color: " + this.colour;
		output += "; Can Bite: " + this.canBite;
		output += "; Is Dangerous: " + this.isDangerous;
		output += "; Has Gills: " + this.hasGills;
		output += "; Has Fins: " + this.hasFins;
		output += "; Can Swim: " + this.canSwim;
		output += "; Breathes: " + this.breathes;
		output += "\n";
		return output;
	}

	@Override
	public boolean equals(Object o) {
		//Type casted so I could check it
		/*The way this works is simple, it checks if canBite exists, and catches ClassCastException*/
		try {
			Shark shark = (Shark)o;
			if(shark.canBite()) return shark.toString().equals(this.toString());
		} catch(Exception e) {
			return false;
		}
		return false;
	}
}

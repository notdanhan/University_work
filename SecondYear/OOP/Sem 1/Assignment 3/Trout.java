public class Trout extends Fish{
	boolean hasSpikes;
	boolean isEdible;
	boolean swimsUpstreamToLayEggs;
	String name;
	
	public Trout(String name) {
		this.name = name;
		this.hasSpikes = true;
		this.colour = "Brown";
		this.isEdible = true;
		this.swimsUpstreamToLayEggs = true;
	}

	public boolean hasSpikes() {
		return this.hasSpikes;
	}

	public boolean isEdible() {
		return this.isEdible;
	}

	public boolean swimsUpstreamToLayEggs() {
		return this.swimsUpstreamToLayEggs;
	}

	@Override
	public String toString() {
		String output = "Trout";
		output += " Name: " + this.name;
		output += "; Color: " + this.colour;
		output += "; Has Spikes: " + this.hasSpikes;
		output += "; Is Edible: " + this.isEdible;
		output += "; Swims Upstream to lay eggs: " + this.swimsUpstreamToLayEggs;
		output += "; Has Gills: " + this.hasGills;
		output += "; Has Fins: " + this.hasFins;
		output += "; Can Swim: " + this.canSwim;
		output += "; Breathes: " + this.breathes;
		output += "\n";
		return output;
	}

	@Override
	public boolean equals(Object o) {
		try {
			o = (Trout)o;
			return this.toString().equals(o.toString());
		} catch(Exception e) {
			return false;
		}
		
	}
}

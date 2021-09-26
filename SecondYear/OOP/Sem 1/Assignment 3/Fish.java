public class Fish extends Animal {
	boolean hasFins;
	boolean canSwim;
	boolean hasGills;
	public Fish() {
		this.hasFins = true;
		this.canSwim = true;
		this.hasGills = true;
	}

	@Override
	public void move(int distance) {
		System.out.printf("I swim %s meters\n",distance);
	}
	
	public boolean hasFins() {
		return this.hasFins;
	}

	public boolean canSwim() {
		return this.canSwim;
	}

	public boolean hasGills() {
		return this.hasGills;
	}
}

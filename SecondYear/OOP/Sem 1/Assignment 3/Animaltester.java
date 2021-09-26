public class Animaltester {
	public Animaltester() {
		//Constructor that does nothing
	}

	public static void main(String args[]) {
		Animaltester tests = new Animaltester();
		System.out.println("Running test 1");
		tests.test1();
		System.out.println("\nRunning test 2");
		tests.test2();
	}

	public void test1() {
		Animal[] animals = new Animal[4];
		animals[0] = new Canary("Jeff");
		animals[1] = new Ostritch("Paul");
		animals[2] = new Shark("Crikey");
		animals[3] = new Trout("Steve");
		//Loops through and prints everything toString() method call is redundant
		for(int i =0; i < animals.length; i++) {
			System.out.print(animals[i]);
		}

	}

	public void test2() {
		Animal[] animals = new Animal[4];
		animals[0] = new Canary("Jeff");
		animals[1] = new Ostritch("Paul");
		animals[2] = new Shark("Crikey");
		animals[3] = new Trout("Steve");
		System.out.println("Checking for equivalent animals");
		for(int i = 0; i < animals.length; i++) {
			System.out.print("Animal " + i +" ");
			for (int j = 0; j < animals.length; j++) {
				//Prints the values of the equals methods()
				System.out.print(" " + animals[i].equals(animals[j]));
			}
			System.out.print("\n");
		}
		/*Proof of ostritch*/
		Ostritch jeff = new Ostritch("Jeff");
		jeff.move(10);
	}
}

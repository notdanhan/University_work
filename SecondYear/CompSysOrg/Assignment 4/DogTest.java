public class DogTest {

	public DogTest() {

	}
	public void foo(Dog dog) {
		dog.name = "Martin Gore";
	}
	public static void main(String args[]) {
		Dog mydog = new Dog("Dave Gahan");
		DogTest test = new DogTest();
		System.out.println(mydog.name);
		mydog.bark();
		test.foo(mydog);
		System.out.println(mydog.name);
	}
}

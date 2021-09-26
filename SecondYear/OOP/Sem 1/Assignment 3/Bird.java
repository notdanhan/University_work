
public class Bird extends Animal
{
    //instance variables (fields)
    boolean hasFeathers;
    boolean hasWings;
    boolean flies;

    /**
     * Constructor for objects of class Bird
     */
    public Bird()
    {
        super(); //calls the constructor of its superclass  - Animal
        colour = "black"; //overrides the value of colour inherited from Animal
        hasFeathers = true; //all the subclasses of Bird inherit this property and value
        hasWings = true; //all the subclasses of Bird inherit this property and value
        flies = true; //all the subclasses of Bird inherit this property and value
    }

    /**
     * move method overrides the move method
     * inherited from superclass Animal
     */
    @Override // good programming practice to use @Override to denote overridden methods
    public void move(int distance){
		if(flies) {
			System.out.printf("I fly %d metres \n", distance);
		} else {
            System.out.println("I am a bird, but cannot fly");
            System.out.printf("I run %d metres\n",distance);
		}
		//Not all birds fly. What can we do here to check for the ability to fly
		//and print an appropriate message based on whether or not the bird can fly?
    }
    
    /**
     * sing method that all birds have
     */
    public void sing(){
        System.out.println("tra la la");
    }
    
    /**
     * 'getter' method for the hasWings field
     */
    public boolean hasWings(){
        return hasWings;
    }
    
    /**
     * 'getter' method for the hasFeathers field
     */
    public boolean hasFeathers(){
        return hasFeathers;
    }
}

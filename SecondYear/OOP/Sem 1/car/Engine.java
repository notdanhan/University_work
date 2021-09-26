
/**
 * Write a description of class Engine here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Engine
{
    // instance variables - replace the example below with your own
    private int fuel;
    private int kpg;

    /**
     * Constructor for objects of class Engine
     */
    public Engine(int kpg)
    {
        // initialise instance variables
        this.kpg = kpg;
        fuel =0;
    }

    public void setFuel(int fuel){
        this.fuel =fuel;
    }
    
    public int getFuel(){
         return fuel;
    }
    
    /**
     * go method of the engine calculates the amount of fuel needed to go
     * the distance required. It updates the fuel variable based on this calculation.
     * It returns false if the updated fuel calculation is less than zero. 
     * This is a rough and ready way to determine if the fuel level can accomodate the distance required.
     * Can you do better ? For example, if there was fuel for 5 km, but the distance variable was 10km
     * perhaps this method should return the distance that could be travelled, rather
     * than returning false.
     *
     * @param  distance  : the distance required to travel 
     * @return  true or false based on whether it is possible or not
     */
    public boolean go(int distance)
    {
        fuel = fuel - distance/kpg; // integer division problem here. Can you spot it?
        if(fuel >=0){
            return true;
        }
        
        return false;
    }
    
    /**
     * getRange method of the engine calculates the distance that this engine can accommodate based
     * the remaining fuel available
     *
     * 
     * @return the distance the Engine can accommodate based on the remaining fuel
     */
    public int getRange()
    {
        //TODO
        return -1;
    }
    
}


/**
 * Abstract class Tower_Console - write a description of the class here
 *
 * @author Daniel Hannon 19484286
 * @version Final
 */
public abstract class Tower_Console extends Console
{
    int generation;
    boolean hasDiskDrive;
    public Tower_Console(int generation, boolean hasDiskDrive, String name, String description, String color, float price) {
        super(name, description, color, price);
        this.generation = generation;
        this.hasDiskDrive = hasDiskDrive;
    }
    
    public int getGeneration() {
        return this.generation;
    }
    
    public boolean getHasDiskDrive() {
        return this.hasDiskDrive;
    }
}

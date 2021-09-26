
/**
 * Write a description of class Playstation here.
 *
 * @author Daniel Hannon 19484286
 * @version Final
 */
public class Playstation extends Tower_Console implements Status{
    // instance variables - replace the example below with your ow
    private String edition;
    static boolean isAvailable;
    /**
     * Constructor for objects of class Playstation
     */
    public Playstation(String edition, int generation, boolean hasDiskDrive, String description, String color, float price, boolean isAvailable)
    {
        // initialise instance variables
        super(generation, hasDiskDrive, "Playstation", description, color, price);
        this.edition = edition;
        this.isAvailable = isAvailable;
    }
    
    public int getNumber() {
        //They will always be the same
        return this.generation;
    }
    
    public String getEdition() {
        return this.edition;
    }
    
    public boolean getAvailability() {
        return this.isAvailable;
    }
    
    public void setAvailability(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    
    @Override
    public String toString() {
        String output = "";
        output += "Name:\t\tSony Playstation " + this.generation + "("+this.edition + " Edition)\n";
        output += "Description:\t\t" + this.description + "\n";
        output += "Disk Drive:\t\t";
        if(this.hasDiskDrive) {
            output+="Yes\n";
        } else {
            output+="No\n";
        }
        output+="Color:\t\t" + this.color + "\n";
        output+="Price:\t\t" + (Math.round(this.price * 100.0f)/100.0f) +"\n";
        output+="Available:\t\t";
        if(this.isAvailable) {
            output+="Yes\n";
        } else {
            output+="No\n";
        }
        return output;
    }
}

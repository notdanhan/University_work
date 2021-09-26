
/**
 * Write a description of class Switch here.
 *
 * @author Daniel Hannon 19484286
 * @version Final
 */
public class Switch extends Handheld_Console implements Status
{
    private String model;
    private static boolean isAvailable;
    /**
     * Constructor for objects of class Switch
     */
    public Switch(String model, float batteryLife, float weight, String description, String color, float price, boolean isAvailable) {
        super(batteryLife, weight, "Nintendo Switch", description, color, price);
        this.model = model;
        this.isAvailable = isAvailable;
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
        output += "Name:\t\tNintendo Switch (Model: "+this.model + ")\n";
        output += "Description:\t\t" + this.description + "\n";
        output += "Battery Life:\t\t" + (Math.round(this.batteryLifeHours * 100.0f)/100.0f) +" hours\n";
        output += "Weight:\t\t" + (Math.round(this.weightGrams * 100.0f)/100.0f) + " grams\n";
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

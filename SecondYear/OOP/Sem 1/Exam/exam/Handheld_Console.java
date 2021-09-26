
/**
 * Abstract class Handheld_Console - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public abstract class Handheld_Console extends Console
{
    // instance variables - replace the example below with your own
    float batteryLifeHours;
    float weightGrams;
    
    public Handheld_Console(float batteryLife, float weight, String name, String description, String color, float price) {
        super(name, description, color, price);
        this.batteryLifeHours = batteryLife;
        this.weightGrams = weight;
    }
    
    public float getBatteryLifeHours() {
        return this.batteryLifeHours;
    }
    
    public float getWeightGrams() {
        return this.weightGrams;
    }
}

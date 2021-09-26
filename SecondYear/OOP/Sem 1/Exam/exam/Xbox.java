
/**
 * Write a description of class Xbox here.
 *
 * @author Daniel Hannon 19484286
 * @version Final
 */
public class Xbox extends Tower_Console implements Status
{
    private String series;
    private static boolean isAvailable;
    
    public Xbox(String series, int generation, boolean hasDiskDrive, String description, String color, float price, boolean isAvailable) {
        super(generation, hasDiskDrive, "Xbox", description, color, price);
        this.series = series;
        this.isAvailable = isAvailable;
    }
    
    public String getSeries(){
        return this.series;
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
        output += "Name:\t\tMicrosoft Xbox Series " + series + "(Generation: "+ this.generation +")\n";
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

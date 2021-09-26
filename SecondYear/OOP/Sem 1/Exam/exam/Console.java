
/**
 * Abstract class Console - write a description of the class here
 *
 * @author Daniel Hannon 19484286
 * @version Final
 */
public abstract class Console {
    // instance variables - replace the example below with your own
    String name;
    String description;
    String color;
    float price;
    
    public Console(String name, String description, String color, float price) {
        this.name = name;
        this.description = description;
        this.color = color;
        this.price = price;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getColor() {
        return this.color;
    }
    
    public float getCost() {
        return this.price;
    }
}

import java.util.ArrayList;
/**
 * Write a description of class SmythsToyStore here.
 *
 * @author Daniel Hannon
 * @version Final
 */
public class SmythsToyStore {
    public static void main(String[] args) {
        Console myPlaystation = new Playstation("Digital",5,false,"Next Gen Console From Sony","White",400.00f,false);
        Console myXbox = new Xbox("X",5,true,"Next Gen Console From Microsoft", "Black", 599.99f, false);
        Console mySwitch = new Switch("Lite",9.0f,299.371f,"Next Gen Console from Nintendo", "Red", 339.99f, false);
        
        int xboxCount = 0;
        int playstationCount = 0;
        int switchCount = 0;
        ArrayList<Console> myList = new ArrayList<Console>();
        for(int i = 0; i < 50; i++) {
            myList.add(myPlaystation);
            myList.add(myXbox);
            myList.add(mySwitch);
        }
        
        for(int j = 0; j < 150; j++) {
            Console temp = myList.get(j);
            if (temp instanceof Playstation) {
                playstationCount++;
                if(playstationCount == 1) {
                    Playstation mytemp = (Playstation) temp;
                    mytemp.setAvailability(true);
                }
            } else if (temp instanceof Xbox) {
                xboxCount++;
                if(xboxCount == 1) {
                    Xbox tempxbox = (Xbox)temp;
                    tempxbox.setAvailability(true);
                    
                }
            } else if (temp instanceof Switch) {
                switchCount++;
                if(switchCount == 1) {
                    Switch tempswitch = (Switch)temp;
                    tempswitch.setAvailability(true);
                }
            }
        }
        
        System.out.println("Total Ordered Consoles: " + (xboxCount + switchCount + playstationCount));
        System.out.println("Total Order Cost: €" + (((float)xboxCount * myXbox.getCost()) +((float) switchCount * mySwitch.getCost()) + ((float)playstationCount * myPlaystation.getCost()))+"\n\n");
        System.out.println("Xbox Consoles Ordered: " + xboxCount);
        System.out.println("________XBOX________");
        System.out.println(myXbox);
        System.out.println("____________________");
        System.out.println("Playstation Consoles Ordered: " + playstationCount);
        System.out.println("________Playstation________");
        System.out.println(myPlaystation);
        System.out.println("____________________");
        System.out.println("Switch Consoles Ordered: " + switchCount);
        System.out.println("________Switch________");
        System.out.println(mySwitch);
        System.out.println("____________________");
        
    }
}

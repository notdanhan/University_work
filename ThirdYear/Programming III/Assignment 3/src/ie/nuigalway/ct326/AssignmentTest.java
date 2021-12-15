package ie.nuigalway.ct326;
/**
 * AssignmentTest
 * @Author Daniel Hannon (19484286)
 */

import java.io.*;
import java.util.Scanner;

public class AssignmentTest {
    public static void main(String[] args) {
        //define these here so they can be closed in finally blocks
        BufferedReader in = null;
        PrintWriter out = null;
        try {
            in = new BufferedReader(new FileReader("ass3.csv"));
            String myString = "";
            while ((myString = in.readLine()) != null) {
                //Initalize scanner with delimiter ','
                Scanner scanner = new Scanner(myString);
                scanner.useDelimiter(",");
                String landType = scanner.next();
                String region = scanner.next();
                float landValue = 0;
                //Check for number to avoid parsing errors and stuff
                if(scanner.hasNextFloat()) {
                    landValue = scanner.nextFloat();
                }
                scanner.close();
                //Worst Case no Match, Best Case State
                //Iterate through every region until found, then stop
                for(Region iRegion : Region.values()) {
                    if(region.equals(iRegion.getRegionName())) {
                        try {
                            //Append to the region file if it exists (w+ flag)
                            //Otherwise, create it
                            out = new PrintWriter(new FileWriter(iRegion.getRegionName()+".txt",true));
                            out.println(String.format("%s %.6f",landType,landValue));

                        } catch (FileNotFoundException err) {
                            err.printStackTrace();
                        } catch (IOException err) {
                            err.printStackTrace();
                        } finally{
                            if(out!= null) {
                                try {
                                    out.close();
                                } catch (Exception error) {
                                    error.printStackTrace();
                                }
                            }
                        }
                        //Region was matched so no need to iterate further
                        break;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(in != null) {
                    in.close();
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package ie.nuigalway.cs.ct326;

/**
 * Points Calculator Implementation as a class
 * @Author Daniel Hannon 194848286
 */
public class SilverPointsCalculator implements PointsCalculator{
    @Override
    public int calculatePoints(int somePoints) {
        //Silver Customers get 1.15x points
        return somePoints + ((int)((float)somePoints * 0.15f));
    }
}

package ie.nuigalway.cs.ct326;

/**
 * An interface for classes concerned with calculating loyalty points.
 * @author Adrian Clear
 *
 */
public interface PointsCalculator {
	
	/**
	 * Calculate the points due to a customer, including any tier bonus to be applied.
	 * @param somePoints an int representing the amount of points to add for the customer before any tier bonus has been applied.
	 * @return the number of points owed to the customer after any tier bonus has been applied.
	 */
	public abstract int calculatePoints(int somePoints);
}

package uk.co.mrrobinsmith.planetsim.base;

/**
 * DoubleSimParameter extends DoubleParameter to implement a DoubleParameter
 * with minimum and maximum values.
 * 
 * @author Robin Smith
 * @version 1 (03/11/2010)
 */

public class BoundDoubleParameter extends DoubleParameter
{
	private double minValue;
	private double maxValue;
	private static final double MIN_DELTA = 0.0001;
	
	public BoundDoubleParameter(String name, double value, double minValue,
	                            double maxValue, boolean isAdjustable)
	{
		super(name, value, isAdjustable);
		this.minValue = minValue;
		this.maxValue = maxValue;
	}
		
	/**
	 * Sets the value of the DoubleSetupParameter.
	 * @param value the new value.
	 */
	public void setValue(double newValue)
	{
		if ((newValue >= minValue) && (newValue <= maxValue)) {
			value = newValue;
		}
		else if (newValue > maxValue) {
			value = maxValue;
			
		}
		else if (newValue < minValue) {
			value = minValue;
		}
	}
	
	/**
	 * Sets the value of this DoubleSimParameter using a String containing the
	 * new value.
	 * @param a String containing the new value.
	 */
	public void setValue(String s)
	{
		double newValue = Double.parseDouble(s.trim());
		if ((newValue >= minValue) && (newValue <= maxValue)) {
			value = newValue;
		}
		else if (newValue > maxValue) {
			value = maxValue;
			
		}
		else if (newValue < minValue) {
			value = minValue;
		}
	}
	
	/**
	 * Increases the value of the DoubleSimParameter by two.
	 */
	public void incValue()
	{
		double newValue = 0.0;
		if (value < MIN_DELTA && value >= 0.0) {
			newValue = MIN_DELTA;
		}
		else if (value > (-1.0 * MIN_DELTA) && value < 0.0) {
			newValue = 0.0;
		}
		else if (value >= MIN_DELTA) {
			newValue = value * 2.0;
		}
		else if (value < (-1.0 * MIN_DELTA)) {
			newValue = value / 2.0;
		}
		if (newValue < maxValue) {
			value = newValue;
		}
		else {
			value = maxValue;
		}
	}
	
	/**
	 * Decreases the value of the DoubleSimParameter by two.
	 */
	public void decValue()
	{
		double newValue = 0.0;
		if (value > (-1.0 * MIN_DELTA) && value <= 0.0) {
			newValue = -1.0 * MIN_DELTA;
		}
		else if (value <= MIN_DELTA && value > 0.0) {
			newValue = 0.0;
		}
		else if (value <= (-1.0 * MIN_DELTA)) {
			newValue = value * 2.0;
		}
		else if (value > MIN_DELTA) {
			newValue = value / 2.0;
		}
		if (newValue > minValue) {
			value = newValue;
		}
		else {
			value = minValue;
		}
	}
	
}

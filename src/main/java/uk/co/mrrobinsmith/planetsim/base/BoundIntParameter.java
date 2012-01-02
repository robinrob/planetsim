package uk.co.mrrobinsmith.planetsim.base;

/**
 * IntSimParameter extends Parameter to implement functionality to store an
 * integer parameter.
 * 
 * @author Robin Smith
 * @version 1 (03/11/2010)
 */

public class BoundIntParameter extends IntParameter
{
	private int minValue;
	private int maxValue;
	private int defaultValue;
	
	/**
	 * Creates an IntSimParameter given a name, value, minimum value, maximum
	 * value, and a boolean value to specify whether or not the IntSimParameter
	 * is adjustable during the simulation using it.
	 * @param name name for the parameter.
	 * @param value the value.
	 * @param minValue the minimum value.
	 * @param maxValue the maximum value.
	 * @param isAdjustable true or false.
	 */
	public BoundIntParameter(String name, int value, int minValue, int maxValue,
	                         boolean isAdjustable)
	{
		super(name, value, isAdjustable);
		this.minValue = minValue;
		this.maxValue = maxValue;
		defaultValue = value;
	}
	
	/**
	 * Sets the value of this IntSimParameter given a new value.
	 * @param value the new value.
	 */
	public void setValue(int value)
	{
		if (value >= minValue && value <= maxValue) {
			this.value = value;
		}
	}
	
	/**
	 * Sets the value of this IntSimParameter to its initial value.
	 */
	public void setDefault()
	{
		value = defaultValue;
	}
	
	/**
	 * Increments the value of this IntSimParameter.
	 */
	public void incValue()
	{
		int newValue = ++value;
		if (newValue < maxValue) {
			value = newValue;
		}
		else {
			value = maxValue;
		}
	}

	/**
	 * Decrements the value of this IntSimParameter.
	 */
	public void decValue()
	{
		int newValue = --value;
		if (newValue > minValue) {
			value = newValue;
		}
		else {
			value = minValue;
		}
	}
	
}

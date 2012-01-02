package uk.co.mrrobinsmith.planetsim.base;

/**
 * IntParameter extends Parameter to implement functionality to store an
 * integer parameter.
 * 
 * @author Robin Smith
 * @version 1 (03/11/2010)
 */

public class IntParameter extends Parameter
{
	protected int value;
	protected int defaultValue;
	
	/**
	 * Creates an IntParameter given a name, a value and a boolean value to
	 * indicate whether or not the parameter is adjustable during running of
	 * the simulation using it.
	 * @param name the name for the parameter.
	 * @param value the initial value.
	 * @param isAdjustable true or false.
	 */
	public IntParameter(String name, int value, boolean isAdjustable)
	{
		super(name, isAdjustable);
		this.value = value;
		TYPE = Parameter.INT;
		defaultValue = value;
	}
	
	/**
	 * Gets the value of this IntParameter.
	 * @return the value.
	 */
	public int getValue()
	{
		return value;
	}
	
	/**
	 * Gets the value of this IntParameter as a String.
	 * @return the String.
	 */
	public String getValueString()
	{
		return "" + value;
	}
	
	/**
	 * Sets the value of this IntParameter given a new value.
	 * @param value the new value.
	 */
	public void setValue(int value)
	{
		this.value = value;
	}
	
	/**
	 * Sets the value of this IntParameter using a String containing the new
	 * value.
	 * @param value a String containing the new value.
	 */
	public void setValue(String s)
	{
		this.value = Integer.parseInt(s.trim());
	}
	
	/**
	 * Sets the value of this IntParameter to its initial value.
	 */
	public void setDefault()
	{
		value = defaultValue;
	}
	
	/**
	 * Increments the value of this IntParameter.
	 */
	public void incValue()
	{
		value++;
	}
	
	/**
	 * Decrements the value of this IntParameter.
	 */
	public void decValue()
	{
		value--;
	}
	
}

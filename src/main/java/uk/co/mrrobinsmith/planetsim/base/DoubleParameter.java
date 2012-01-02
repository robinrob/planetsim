package uk.co.mrrobinsmith.planetsim.base;

/**
 * DoubleParameter extends Parameter to implement functionality to store a
 * parameter whose value is a double.
 * 
 * @author Robin Smith
 * @version 1 (03/11/2010)
 */

public class DoubleParameter extends Parameter
{
	protected double value;
	private double defaultValue;
	
	/**
	 * Creates a DoubleParameter given a name, value and a boolean value to
	 * indicate whether or not the parameter is adjustable during running of
	 * the simulation using it.
	 * @param name the name for the parameter.
	 * @param value the value.
	 * @param isAdjustable true or false.
	 */
	public DoubleParameter(String name, double value, boolean isAdjustable)
	{
		super(name, isAdjustable);
		this.value = value;
		TYPE = Parameter.DOUBLE;
		defaultValue = value;
	}
	
	/**
	 * Gets the value of this DoubleParameter.
	 * @return the value.
	 */
	public double getValue()
	{
		return value;
	}
	
	/**
	 * Gets the value of this DoubleParameter as a String.
	 * @return the String.
	 */
	public String getValueString()
	{
		return "" + value;
	}
	
	/**
	 * Sets the value of this DoubleParameter.
	 * @param value the new value.
	 */
	public void setValue(double value)
	{
		this.value = value;
	}
	
	/**
	 * Sets the value of this DoubleParameter using a String containing the new
	 * value.
	 * @param a String containing the new value.
	 */
	public void setValue(String s)
	{
		this.value = Double.parseDouble(s.trim());
	}
	
	/**
	 * Sets the value of this DoubleParameter to its initial value.
	 */
	public void setDefault()
	{
		value = defaultValue;
	}
	
	/**
	 * Multiplies the value of this DoubleParameter by two.
	 */
	public void incValue()
	{
		value *= 2;
	}
	
	/**
	 * Divides the value of this DoubleParameter by two.
	 */
	public void decValue()
	{
		value /= 2;
	}
	
}

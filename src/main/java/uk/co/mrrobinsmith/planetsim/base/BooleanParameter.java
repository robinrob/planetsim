package uk.co.mrrobinsmith.planetsim.base;

public class BooleanParameter extends Parameter
{
	protected boolean value;
	protected boolean defaultValue;
	
	/**
	 * Creates a BooleanParameter given a name, a value and a boolean value to
	 * indicate whether or not the parameter is adjustable during running of
	 * the simulation using it.
	 * @param name the name for the parameter.
	 * @param value the initial value.
	 * @param isAdjustable true or false.
	 */
	public BooleanParameter(String name, boolean value, boolean isAdjustable)
	{
		super(name, isAdjustable);
		this.value = value;
		defaultValue = value;
	}
	
	/**
	 * Gets the value of this BooleanParameter.
	 * @return the boolean value.
	 */
	public boolean getValue()
	{
		return value;
	}
	
	/**
	 * Gets the value of this BooleanParameter as a String.
	 */
	public String getValueString()
	{
		return "" + value;
	}
	
	/**
	 * Sets the value of this BooleanParameter to a new value.
	 * @param value the new value.
	 */
	public void setValue(boolean value)
	{
		this.value = value;
	}
	
	/**
	 * Sets the value of this BooleanParameter using a String containing the
	 * new value.
	 * @param s a String containing the new value.
	 */
	public void setValue(String s)
	{
		value = Boolean.parseBoolean(s.trim());
	}
	
	/**
	 * Sets the value of this BooleanParameter to its initial value.
	 */
	public void setDefault()
	{
		value = defaultValue;
	}
	
	/**
	 * Changes the value of this BooleanParameter to its opposite value.
	 */
	public void incValue()
	{
		if (value) {
			value = false;
		}
		else {
			value = true;
		}
	}
	
	/**
	 * Changes the value of this BooleanParameter to its opposite value.
	 */
	public void decValue()
	{
		if (!value) {
			value = true;
		}
		else {
			value = false;
		}
	}

}

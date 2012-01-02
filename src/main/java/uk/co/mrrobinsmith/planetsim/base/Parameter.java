package uk.co.mrrobinsmith.planetsim.base;

/**
 * Parameter is a superclass which describes the basic properties of a 
 * parameter used in a simulation: the parameter's name and whether or not it
 * is adjustable during the running of the simulation it is used in. Known
 * subclasses: IntParameter, DoubleParameter, ColorParameter, IntSimParameter,
 * DoubleSimParameter, ColorSimParameter, IntDataParameter, DoubleDataParameter.
 * 
 * @author Robin Smith
 * @version 1 (03/11/2010)
 */

public abstract class Parameter
{
	public static final int INT = 0;
	public static final int DOUBLE = 1;
	public static final int COLOR = 2;
	public static final int BOOLEAN = 2;
	
	protected String name;
	protected boolean isAdjustable;
	/* A String which indicates type of parameter, e.g. double, int etc. */
	protected int TYPE;
	
	/**
	 * Constructor for Parameter.
	 * @param name name of the parameter
	 * @param isAdjustable whether or not the parameter is adjustable while the
	 * simulation using it is running
	 */
	public Parameter(String name, boolean isAdjustable)
	{
		this.name = name;
		this.isAdjustable = isAdjustable;
	}
	
	/**
	 * Gets the name of the Parameter.
	 * @return the name.
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Gets the type of the Parameter as an int.
	 * @return the type.
	 */
	public int getType()
	{
		return TYPE;
	}
	
	/**
	 * Gets the value of the Parameter as a String.
	 * @return the String.
	 */
	public abstract String getValueString();
	
	/**
	 * Checks whether the Parameter is adjustable.
	 * @return true or false.
	 */
	public boolean isAdjustable()
	{
		return isAdjustable;
	}
	
	/**
	 * Increments the value of the Parameter.
	 */
	public abstract void incValue();
	
	/**
	 * Decrements the value of the Parameter.
	 */
	public abstract void decValue();
	
	/**
	 * Sets the value of the Parameter using a String containing the value.
	 */
	public abstract void setValue(String s);
	
	/**
	 * Sets the value of the Parameter to its default value.
	 */
	public abstract void setDefault();
	

	
}
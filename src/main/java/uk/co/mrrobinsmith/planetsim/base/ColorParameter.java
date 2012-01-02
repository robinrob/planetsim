package uk.co.mrrobinsmith.planetsim.base;

import java.awt.Color;

/**
 * ColorParameter extends Parameter to implement functionality to store a
 * parameter with a Color object as its value.
 * 
 * @author Robin Smith
 * @version 1 (03/11/2010)
 */

public class ColorParameter extends Parameter
{
	private Color value;
	private Color defaultValue;
	private String[] possibleCols;
	
    private static Color[] colors = {Color.black, Color.blue, Color.cyan,
                                     Color.darkGray, Color.gray, Color.green,
                                     Color.lightGray, Color.magenta,
                                     Color.orange, Color.pink, Color.red,
                                     Color.white, Color.yellow};
    private static String[] colorNames = {"black", "blue", "cyan", "darkGray",
                                          "gray", "green", "lightGray",
                                          "magenta", "orange", "pink", "red",
                                          "white", "yellow"};
	/**
	 * Creates a ColorParameter given a name for the parameter, a String
	 * array of possible color names and a boolean value to indicate whether or
	 * not the parameter is adjustable during running of the simulation.
	 * @param name the name.
	 * @param possibleCols a String array of color names (corresponding exactly
	 * to the names of the static Colors held by the java.awt.Color class, e.g.
	 * Color.blue, Color.lightGray). The array can hold any number of color
	 * names. This ColorParameter's color will then be adjustable within this
	 * defined set of possible colors. If the ColorParameter is not to be
	 * adjustable, then the possibleCols array should contain just the one name
	 * of the color to be used.
	 * @param isAdjustable true or false.
	 */
	public ColorParameter(String name, String[] possibleCols,
	                      boolean isAdjustable)
	{
		super(name, isAdjustable);
		this.possibleCols = possibleCols;
		value = stringToColor(possibleCols[0]);
		TYPE = Parameter.COLOR;
		defaultValue = value;
	}
	
	/**
	 * Gets the value of the ColorParameter.
	 * @return the Color object.
	 */
	public Color getValue()
	{
		return value;
	}
	
	/**
	 * Gets the name of the Color value of this this ColorParameter.
	 * @return the String color name.
	 */
	public String getValueString()
	{
		return colorToString(value);
	}
	
	/**
	 * Gets the set of possible color values for this ColorParameter.
	 * @return String array with names of colors.
	 */
	public String[] getPossibleColors()
	{
		return possibleCols;
	}
	
	/**
	 * Sets the value of this ColorParameter given a new Color.
	 * @param color the new Color.
	 */
	public void setValue(Color color)
	{
		
	}
	
	/**
	 * Sets the value of this ColorParameter given a String with the name of the
	 * desired new Color.
	 * @param color the String with the Color name.
	 */
	public void setValue(String color)
	{
		value = stringToColor(color);
	}
	
	/**
	 * Changes the color of this ColorParameter to the next color in its
	 * possibleCols array.
	 */
	public void incValue()
	{
		int n = possibleCols.length;
		for (int i = 0; i < n; ++i) {
			if (possibleCols[i].equals(colorToString(value))) {
				value = stringToColor(possibleCols[(i + 1) % n]);
				break;
			}
		}
	}
	
	/**
	 * Changes the color of this ColorParameter to the previous color in its
	 * possibleCols array.
	 */
	public void decValue()
	{
		int n = possibleCols.length;
		for (int i = 0; i < n; ++i) {
			if (possibleCols[i].equals(colorToString(value))) {
				value = stringToColor(possibleCols[(n + i - 1) % n]);
				break;
			}
		}
	}
	
	/**
	 * Sets the Color of this ColorParameter to its initial value.
	 */
	public void setDefault()
	{
		value = defaultValue;
	}
	
    /**
     * Converts a Color object to its String name.
     * @param color Color object.
     * @return a String containing the name of that color.
     */
    private static String colorToString(Color color)
    {
    	for (int i = 0; i < colors.length; ++i) {
    		if (colors[i].equals(color)) {
    			return colorNames[i];
    		}
    	}
    	return null;
    }
    
    /**
     * Converts a color name to the color object of that name.
     * @param colorName a String containing a color name.
     * @return the corresponding Color object.
     */
    private static Color stringToColor(String colorName)
    {
    	for (int i = 0; i < colorNames.length; ++i) {
    		if (colorNames[i].equals(colorName)) {
    			return colors[i];
    		}
    	}
    	return null;
    }
	
}
